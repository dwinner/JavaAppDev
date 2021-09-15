package popuptriggertest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Автодополнение в текстовом поле.
 *
 * @author Denis
 */
public class PopupTriggerTest extends Application
{
   public static void main(String[] args)
   {
      launch(args);      
   }
   private final static String[] dataModel = AutoCompleteModel.getWords();
   private final static double LISTVIEW_ONE_ROW_HEIGHT = 25.;
   private final static double LISTVIEW_PREF_RATIO = 5.;
   private final static double LISTVIEW_PREF_HEIGHT = LISTVIEW_PREF_RATIO * LISTVIEW_ONE_ROW_HEIGHT;
   private StringBuilder currentSelectedValue = new StringBuilder(0);
   private Label resultLabel = new Label("");

   @Override
   public void start(Stage primaryStage)
   {
      final TextField textField = new TextField(); // -- Текстовое поле

      // -- Динамический узел списка, находящийся под текстовым полем.

      final ListView<String> listView = new ListView<>(FXCollections.<String>observableArrayList());
      DropShadow dropShadowEffect = new DropShadow();
      dropShadowEffect.setOffsetX(.8);
      dropShadowEffect.setOffsetY(.8);
      listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      listView.setEffect(dropShadowEffect);
      listView.setPrefHeight(LISTVIEW_PREF_HEIGHT);
      listView.setOpacity(.5);
      listView.focusedProperty().addListener(new ChangeListener<Boolean>()
      {
         @Override
         public void changed(ObservableValue<? extends Boolean> observable,
                             Boolean oldValue,
                             Boolean newValue)
         {
            listView.setOpacity(newValue.booleanValue() ? 1. : .6);
         }
      });

      // -- Обеспечение одинаковых размеров текстовой области и списка

      listView.setPrefWidth(textField.getWidth());
      listView.widthProperty().addListener(new ChangeListener<Number>()
      {
         @Override
         public void changed(ObservableValue<? extends Number> observable,
                             Number oldValue,
                             Number newValue)
         {
            listView.setPrefWidth(newValue.doubleValue());
         }
      });
      listView.setPrefHeight(3. * LISTVIEW_ONE_ROW_HEIGHT);
      listView.setVisible(false);

      // -- Динамическое появление списка при изменении текста

      textField.textProperty().addListener(new ChangeListener<String>()
      {
         @Override
         public void changed(ObservableValue<? extends String> observable,
                             String oldValue,
                             String newValue)
         {
            if (newValue != null && !newValue.trim().isEmpty())
            {
               String newSearch = newValue.trim();
               listView.getItems().clear();
               for (int i = 0; i < dataModel.length; i++)
               {
                  if (dataModel[i].startsWith(newSearch))
                  {
                     listView.getItems().add(dataModel[i]);
                  }
               }

               listView.setVisible(listView.getItems().size() == 0 ? false : true);
               if (listView.getItems().size() <= (int) LISTVIEW_PREF_RATIO)
               {
                  listView.setPrefHeight(listView.getItems().size() * LISTVIEW_ONE_ROW_HEIGHT);
               }
            }
            else if (listView.isVisible())
            {
               listView.setVisible(false);
            }
         }
      });

      // -- Перенос фокуса ввода с текстового поля в список при нажатии кнопки "Вниз"

      textField.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent event)
         {
            if (listView.isVisible())
            {
               if (event.getCode() == KeyCode.DOWN)
               {
                  listView.requestFocus();
                  listView.getSelectionModel().selectFirst();
               }
               else if (event.getCode() == KeyCode.ESCAPE)
               {
                  listView.setVisible(false);
               }
            }
         }
      });

      // -- Перенос фокуса ввода со списка обратно в текстовое поле при нажатии клавиши "Вверх"

      listView.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         @Override
         public void handle(KeyEvent event)
         {
            int selectedIndex = listView.getSelectionModel().getSelectedIndex();
            if (selectedIndex == 0 && event.getCode() == KeyCode.UP)
            {
               textField.requestFocus();
            }
            if (selectedIndex >= 0 && event.getCode() == KeyCode.ENTER)
            {
               textField.requestFocus();
               textField.setText(currentSelectedValue.toString());
               listView.setVisible(false);
               textField.end();
            }
         }
      });

      // -- Получение и запись текущего выбранного значения в списке в поле StringBilder

      listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>()
      {
         @Override
         public void changed(ObservableValue<? extends String> observable,
                             String oldValue,
                             String newValue)
         {
            if (newValue != null && !newValue.trim().isEmpty())
            {
               currentSelectedValue.setLength(0);
               currentSelectedValue.append(newValue);
            }
         }
      });

      // -- Действие в текстовом поле

      textField.setOnAction(new EventHandler<ActionEvent>()
      {
         @Override
         public void handle(ActionEvent event)
         {
            resultLabel.setText(textField.getText());
         }
      });

      // -- Расположение узлов в графе сцены

      BorderPane root = new BorderPane();

      VBox topBox = new VBox();
      topBox.getChildren().addAll(textField, listView);

      root.setTop(topBox);
      root.setBottom(resultLabel);

      Scene scene = new Scene(root, 640, 480);

      primaryStage.setTitle("Trigger By Events");
      primaryStage.setScene(scene);
      primaryStage.show();
   }
}

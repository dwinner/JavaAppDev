package pkg23.menutestapp;

import java.util.AbstractMap;
import java.util.Map;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Построение меню в FX.
 *
 * @author DenisIV
 */
public class MenuTestApp extends Application
{
    final PageData[] pages = new PageData[]
    {
        new PageData("Apple",
                     "The apple is the pomaceous fruit of the apple tree, species Malus "
        + "domestica in the rose family (Rosaceae). It is one of the most "
        + "widely cultivated tree fruits, and the most widely known of "
        + "the many members of genus Malus that are used by humans. "
        + "The tree originated in Western Asia, where its wild ancestor, "
        + "the Alma, is still found today.",
                     "Malus domestica"),
        new PageData("Hawthorn",
                     "The hawthorn is a large genus of shrubs and trees in the rose "
        + "family, Rosaceae, native to temperate regions of the Northern "
        + "Hemisphere in Europe, Asia and North America. "
        + " The name hawthorn was "
        + "originally applied to the species native to northern Europe, "
        + "especially the Common Hawthorn C. monogyna, and the unmodified "
        + "name is often so used in Britain and Ireland.",
                     "Crataegus monogyna"),
        new PageData("Ivy",
                     "The ivy is a flowering plant in the grape family (Vitaceae) native to "
        + " eastern Asia in Japan, Korea, and northern and eastern China. "
        + "It is a deciduous woody vine growing to 30 m tall or more given "
        + "suitable support,  attaching itself by means of numerous small "
        + "branched tendrils tipped with sticky disks.",
                     "Parthenocissus tricuspidata"),
        new PageData("Quince",
                     "The quince is the sole member of the genus Cydonia and is native to "
        + "warm-temperate southwest Asia in the Caucasus region. The "
        + "immature fruit is green with dense grey-white pubescence, most "
        + "of which rubs off before maturity in late autumn when the fruit "
        + "changes color to yellow with hard, strongly perfumed flesh.",
                     "Cydonia oblonga")
    };
    final String[] viewOptions = new String[]
    {
        "Title",
        "Binomial name",
        "Picture",
        "Description"
    };
    @SuppressWarnings("unchecked")
    final Map.Entry<String, Effect>[] effects = new Map.Entry[]
    {
        new AbstractMap.SimpleEntry<>("Sepia Tone", new SepiaTone()),
        new AbstractMap.SimpleEntry<>("Glow", new Glow()),
        new AbstractMap.SimpleEntry<>("Shadow", new DropShadow())
    };
    final ImageView pic = new ImageView();
    final Label name = new Label();
    final Label binName = new Label();
    final Label description = new Label();
    private int currentIndex = -1;
    
    public static void main(String[] args)
    {
        launch(args);
    }
    
    private void shuffle()
    {
        int i = currentIndex;
        while (i == currentIndex)
        {
            i = (int) (Math.random() * pages.length);
        }
        pic.setImage(pages[i].image);
        name.setText(pages[i].name);
        binName.setText("(" + pages[i].binNames + ")");
        description.setText(pages[i].description);
        currentIndex = i;
    }
    
    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.OLDLACE);
        
        name.setFont(new Font("Verdana Bold", 22));
        binName.setFont(new Font("Arial Italic", 10));
        pic.setFitHeight(150);
        pic.setPreserveRatio(true);
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.JUSTIFY);
        
        shuffle();
        
        MenuBar menuBar = new MenuBar();    // Панель меню

        final VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(0, 10, 0, 10));
        vBox.getChildren().addAll(name, binName, pic, description);
        
        Menu menuFile = new Menu("File");   // Меню File

        MenuItem add = new MenuItem("Shuffle",
                                    new ImageView(new Image(getClass().
           getResourceAsStream("new.png"))));
        add.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                shuffle();
                vBox.setVisible(true);
            }
        });
        
        MenuItem clear = new MenuItem("Clear");
        clear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        clear.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                vBox.setVisible(false);
            }
        });
        
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.exit(0);
            }
        });
        
        menuFile.getItems().addAll(add, clear, new SeparatorMenuItem(), exit);
        
        Menu menuEdit = new Menu("Edit");   // Меню Edit

        Menu menuEffect = new Menu("Picure Effect");    // Меню Edit->Picture Effect
        final ToggleGroup groupEffect = new ToggleGroup();
        for (Map.Entry<String, Effect> effect : effects)
        {
            RadioMenuItem itemEffect = new RadioMenuItem(effect.getKey());
            itemEffect.setUserData(effect.getValue());
            itemEffect.setToggleGroup(groupEffect);
            menuEffect.getItems().add(itemEffect);
        }
        
        final MenuItem noEffects = new MenuItem("No Effects");
        noEffects.setDisable(true);
        noEffects.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                pic.setEffect(null);
                groupEffect.getSelectedToggle().setSelected(false);
                noEffects.setDisable(true);
            }
        });

        // Обработка выбора эффектов
        groupEffect.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable,
                                Toggle oldValue,
                                Toggle newValue)
            {
                if (groupEffect.getSelectedToggle() != null)
                {
                    Effect effect = (Effect) groupEffect.getSelectedToggle().getUserData();
                    pic.setEffect(effect);
                    noEffects.setDisable(false);
                }
                else
                {
                    noEffects.setDisable(true);
                }
            }
        });

        // Добавление новых пунктов в меню Edit
        menuEdit.getItems().addAll(menuEffect, noEffects);
        
        Menu menuView = new Menu("View");   // Меню View

        CheckMenuItem titleView = createCheckMenuItem("Title", name);
        CheckMenuItem binNameView = createCheckMenuItem("Binomial name", binName);
        CheckMenuItem picView = createCheckMenuItem("Picture", pic);
        CheckMenuItem descriptionView = createCheckMenuItem("Description", description);
        
        menuView.getItems().addAll(titleView, binNameView, picView, descriptionView);
        
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

        // Контекстное меню

        final ContextMenu cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("Copy Image");
        cmItem1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putImage(pic.getImage());
                clipboard.setContent(content);
            }
        });
        
        cm.getItems().add(cmItem1);
        pic.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                if (mouseEvent.getButton() == MouseButton.SECONDARY)
                {
                    cm.show(pic, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
        });
        
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar, vBox);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Создание помеченного элемента меню
     *
     * @param title Строка меню
     * @param root Узел, меняющий свойство видимости в зависимости от состояния
     * @return Помеченный элемент меню.
     */
    private static CheckMenuItem createCheckMenuItem(String title, final Node node)
    {
        CheckMenuItem cmi = new CheckMenuItem(title);
        cmi.setSelected(true);
        cmi.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldValue,
                                Boolean newValue)
            {
                node.setVisible(newValue);
            }
        });
        return cmi;
    }
    
    private class PageData
    {
        public String name;
        public String description;
        public String binNames;
        public Image image;
        
        PageData(String name, String description, String binNames)
        {
            this.name = name;
            this.description = description;
            this.binNames = binNames;
            this.image = new Image(getClass().getResourceAsStream(name + ".jpg"));
        }
    }
    
}

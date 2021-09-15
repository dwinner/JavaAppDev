package fxaudio.view.playtoolbar;

import fxaudio.view.PlacedInPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import static fxaudio.view.playtoolbar.MenuButtonFactory.*;
import static fxaudio.view.playtoolbar.TextFieldFactory.createTextField;
import static fxaudio.view.playtoolbar.TrackGrouper.*;
import static resources.ResourceLoader.IconLoader.TURN_OFF;

/**
 * Вид для панели инструментов.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-07-2012
 */
public final class PlayToolbarView implements PlacedInPane
{
   private MenuButton openMenuButton;
   private MenuButton saveMenuButton;
   private MenuButton exitMenuButton;
   private TextField searchByArtistField;
   private TextField searchByTrackField;
   private Button shutdownButton;
   private ProgressBar loading = new ProgressBar(0);
   private ProgressIndicator loadingIndicator = new ProgressIndicator(0);
   private ToggleGroup playlistGrouper;
   private static PlayToolbarView instance = null;

   
   {
      loading.setPrefWidth(200.);
      loading.setMaxWidth(Double.MAX_VALUE);
      loading.setPrefHeight(15.);
      // loading.setMaxHeight(Double.MAX_VALUE);

      // loading.progressProperty().bindBidirectional(loadingIndicator.progressProperty());
   }

   /**
    * Возвращение панели инструментов как Singleton-экземпляра.
    *
    * @return Единая панель инструментов.
    */
   public static PlayToolbarView getInstance()
   {
      if (instance == null)
      {
         instance = new PlayToolbarView(createOpenMenuButton().wizardMenuButton(),
                                        createSaveMenuButton().wizardMenuButton(),
                                        createExitMenuButton().wizardMenuButton(),
                                        createTextField().wizardTextField("Search By Artist"), // TODO: Locale
                                        createTextField().wizardTextField("Search By Track")); // TODO: Locale
      }
      return instance;
   }

   /**
    * Конструктор для класса-одиночки.
    *
    * @param open Кнопка меню Open
    * @param save Кнопка меню Save
    * @param exit Кнопка меню Exit
    * @param searchArtist Текстовое поле для поиска по артисту
    * @param searchTrack Текстовое поле для поиска по треку
    */
   private PlayToolbarView(MenuButton open, MenuButton save, MenuButton exit, TextField searchArtist,
                           TextField searchTrack)
   {
      openMenuButton = open;
      saveMenuButton = save;
      exitMenuButton = exit;
      searchByArtistField = searchArtist;
      searchByTrackField = searchTrack;

      ButtonBuilder<?> btnBuilder = ButtonBuilder.create();
      shutdownButton = btnBuilder.alignment(Pos.CENTER). // Выравнивание
        graphic(new ImageView(TURN_OFF)). // Изображение
        tooltip(new Tooltip("Shutdown wizard")). // TODO: Locale
        build();

      playlistGrouper = getGroup();
   }

   /**
    * Кнопка меню Open
    *
    * @return Кнопка меню Open
    */
   public MenuButton getOpenMenuButton()
   {
      return openMenuButton;
   }

   /**
    * Кнопка меню Save
    *
    * @return Кнопка меню Save
    */
   public MenuButton getSaveMenuButton()
   {
      return saveMenuButton;
   }

   /**
    * Кнопка меню Exit
    *
    * @return Кнопка меню Exit
    */
   public MenuButton getExitMenuButton()
   {
      return exitMenuButton;
   }

   /**
    * Текстовое поле для поиска по артисту
    *
    * @return Текстовое поле для поиска по артисту
    */
   public TextField getSearchByArtistField()
   {
      return searchByArtistField;
   }

   /**
    * Текстовое поле для поиска по треку
    *
    * @return Текстовое поле для поиска по треку
    */
   public TextField getSearchByTrackField()
   {
      return searchByTrackField;
   }

   /**
    * Кнопка для настройки выключения компьютера
    *
    * @return Кнопка для настройки выключения компьютера
    */
   public Button getShutdownButton()
   {
      return shutdownButton;
   }

   /**
    * Прогресс-бар загрузки файлов/плейлистов
    *
    * @return Прогресс-бар загрузки файлов/плейлистов
    */
   public ProgressBar getLoading()
   {
      return loading;
   }

   /**
    * Прогресс-индикатор загрузки файлов/плей-листов
    *
    * @return Прогресс-индикатор загрузки файлов/плей-листов.
    */
   public ProgressIndicator getLoadingIndicator()
   {
      return loadingIndicator;
   }

   /**
    * Получение группы переключателей, изменяющие вид плей-листа
    *
    * @return Группа переключателей, изменяющие вид плей-листа
    */
   public ToggleGroup getPlaylistGrouper()
   {
      return playlistGrouper;
   }

   @Override
   public Pane layoutComponents()
   {  // TODO: Enter constant specific class
      HBox leftTopBox = new HBox(10);
      leftTopBox.setPadding(new Insets(10));
      leftTopBox.getChildren().addAll(openMenuButton, saveMenuButton, exitMenuButton);

      HBox rightTopBox = new HBox(10);
      rightTopBox.setPadding(new Insets(10));
      rightTopBox.getChildren().addAll(searchByArtistField, searchByTrackField, shutdownButton);

      VBox leftBottomBox = new VBox(10);
      leftBottomBox.getChildren().add(loading);
      VBox.setVgrow(leftBottomBox, Priority.ALWAYS);
      leftBottomBox.setFillWidth(true);
      HBox leftBottomWrapper = new HBox(10.);
      leftBottomWrapper.getChildren().addAll(leftBottomBox, loadingIndicator);

      HBox rightBottomBox = new HBox(10);
      VBox grouperBox = new VBox(10);
      grouperBox.getChildren().addAll(solidRadioButton, artistRadioButton, folderRadioButton);
      Label grouperLabel = new Label("Group playlist by: ", null);   // TODO: Locale & ImageView
      rightBottomBox.getChildren().addAll(grouperLabel, grouperBox);
      rightBottomBox.setAlignment(Pos.BASELINE_LEFT);
      grouperLabel.setAlignment(Pos.BOTTOM_LEFT);
      grouperBox.setAlignment(Pos.BOTTOM_LEFT);
      AnchorPane rightBottomWrapper = new AnchorPane();
      rightBottomWrapper.getChildren().add(rightBottomBox);
      AnchorPane.setRightAnchor(rightBottomBox, 10.);

      GridPane playtoolbar = new GridPane();
      playtoolbar.setHgap(10);
      playtoolbar.setVgap(10);
      playtoolbar.setPadding(new Insets(10));

      playtoolbar.add(leftTopBox, 0, 0);
      GridPane.setHalignment(leftTopBox, HPos.LEFT);
      GridPane.setValignment(leftTopBox, VPos.TOP);

      playtoolbar.add(rightTopBox, 1, 0);
      GridPane.setHalignment(rightTopBox, HPos.RIGHT);
      GridPane.setValignment(rightTopBox, VPos.TOP);

      playtoolbar.add(leftBottomWrapper, 0, 1);
      GridPane.setHalignment(leftBottomWrapper, HPos.LEFT);
      GridPane.setValignment(leftBottomWrapper, VPos.BASELINE);
      GridPane.setMargin(leftBottomWrapper, new Insets(20, 0, 0, 10));
      GridPane.setHgrow(leftBottomWrapper, Priority.ALWAYS);

      playtoolbar.add(rightBottomWrapper, 1, 1);
      GridPane.setHalignment(rightBottomBox, HPos.RIGHT);
      GridPane.setValignment(rightBottomBox, VPos.BOTTOM);

      playtoolbar.setStyle("-fx-border-color: deepskyblue;\n"
        + "-fx-border-insets: 5;\n"
        + "-fx-border-width: 2;\n"
        + "-fx-border-style: solid;"
        + "-fx-border-radius: 7;\n");

      return playtoolbar;
   }
}

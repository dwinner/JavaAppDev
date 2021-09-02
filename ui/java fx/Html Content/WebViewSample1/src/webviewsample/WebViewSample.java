package webviewsample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import netscape.javascript.JSObject;

/**
 * HTML-содержимое.
 *
 * @author Denis
 */
public class WebViewSample extends Application
{
    public static final Color DEFAULT_SCENE_BACKGROUND = Color.web("#666970");
    private Scene scene;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        // Создание сцены.
        stage.setTitle("Web View");
        scene = new Scene(new Browser(),
                          Browser.START_WIDTH,
                          Browser.START_HEIGHT,
                          DEFAULT_SCENE_BACKGROUND);
        stage.setScene(scene);
        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");
        stage.show();
    }
}

/**
 * Регион CSS для внедрения браузерного компонента.
 *
 * @author Denis
 */
class Browser extends Region
{
    private HBox toolBar;
    private static String[] imageFiles = new String[]
    {
        "product.png",
        "blog.png",
        "forum.png",
        "partners.png",
        "help.png"
    };
    private static String[] captions = new String[]
    {
        "Product",
        "Blogs",
        "Forums",
        "Partners",
        "Help"
    };
    private static String[] urls = new String[]
    {
        "http://www.oracle.com/products/index.html",
        "http://blogs.oracle.com/",
        "http://forums.oracle.com/forums/",
        "http://www.oracle.com/partners/index.html",
        WebViewSample.class.getResource("help.html").toExternalForm()
    };
    final ImageView selectedImage = new ImageView();
    final Hyperlink[] hpls = new Hyperlink[captions.length];
    final Image[] images = new Image[imageFiles.length];
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    final Button hideAll = new Button("Hide All");
    final Button showAll = new Button("Show All");
    final WebView smallView = new WebView();
    static final String START_URL = "http://www.oracle.com";
    static final int START_WIDTH = 750;
    static final int START_HEIGHT = 500;

    Browser()
    {
        getStyleClass().add("browser"); // Применить стили.
        webEngine.load(START_URL);  // Загрузить страницу по умолчанию

        for (int i = 0; i < captions.length; i++)
        {
            final Hyperlink hpl = hpls[i] = new Hyperlink(captions[i]);
            Image image = images[i] =
               new Image(getClass().getResourceAsStream(imageFiles[i]));
            hpl.setGraphic(new ImageView(image));
            final String currentUrl = urls[i];

            hpl.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent actionEvent)
                {
                    webEngine.load(currentUrl);

                    // Добавление кнопок для обработки JavaScript-комманд при активизации ссылки Forums
                    webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>()
                    {
                        @Override
                        public void changed(ObservableValue<? extends State> observable,
                                            State oldStateValue,
                                            State newStateValue)
                        {
                            if (!hpl.getText().equals("Forums"))
                            {
                                toolBar.getChildren().removeAll(showAll, hideAll);
                            }
                            if (newStateValue == State.SUCCEEDED)
                            {
                                // Получение объекта JavaScript для внешних вызовов JavaFX
                                JSObject win = (JSObject) webEngine.executeScript("window");
                                webEngine.executeScript("window");
                                win.setMember("app", new JavaApp());

                                if (hpl.getText().equals("Forums"))
                                {
                                    toolBar.getChildren().removeAll(showAll, hideAll);
                                    toolBar.getChildren().addAll(showAll, hideAll);
                                }
                            }
                        }
                    });
                }
            });
        }

        // Создание панели инструментов.
        toolBar = new HBox();
        toolBar.getStyleClass().add("browser-toolbar");
        toolBar.getChildren().addAll(hpls);

        toolBar.getChildren().add(createSpacer());

        // Обработка действий для команд JavaScript
        hideAll.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                webEngine.executeScript("hideAll()");
            }
        });

        showAll.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                webEngine.executeScript("showAll()");
            }
        });

        // Обработка вызова контекстного меню в объекте webEngine
        smallView.setPrefSize(120, 80);
        webEngine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>()
        {
            @Override
            public WebEngine call(PopupFeatures param)
            {
                smallView.setFontScale(0.8);
                if (!toolBar.getChildren().contains(smallView))
                {
                    toolBar.getChildren().add(smallView);
                }
                return smallView.getEngine();
            }
        });

        // Добавление компонентов на сцену
        getChildren().add(toolBar);
        getChildren().add(browser);
    }

    /**
     * Создание горизонтального заполнителя
     *
     * @return Узел пробела-заполнителя.
     */
    private Node createSpacer()
    {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override
    protected void layoutChildren()
    {
        double w = getWidth();
        double h = getHeight();
        double toolBarHeight = toolBar.prefHeight(w);
        layoutInArea(browser, 0, 0, w, h - toolBarHeight, 0, HPos.CENTER, VPos.CENTER);
        layoutInArea(toolBar, 0, h - toolBarHeight, w, toolBarHeight, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height)
    {
        return START_WIDTH;
    }

    @Override
    protected double computePrefHeight(double width)
    {
        return START_HEIGHT;
    }

    /**
     * Интерфейсный класс для внешних вызовов JavaFX через объекты JavaScript.
     */
    private static class JavaApp
    {
        JavaApp()
        {
        }

        public void exit()
        {
            Platform.exit();
        }
    }

}

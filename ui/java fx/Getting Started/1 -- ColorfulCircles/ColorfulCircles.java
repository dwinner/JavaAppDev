package colorfulcircles;

import static java.lang.Math.random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Начало работы с объектами JavaFX.
 * <p/>
 * @author JavaFX Development Group.
 */
public class ColorfulCircles extends Application
{
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;

    public static void main(String[] args)
    {
        launch(args);   // Запуск объекта класса Application
    }

    @Override
    public void start(Stage primaryStage)
    {
        // Добавим объект сцены к контейнеру верхнего уровня Stage
        Group root = new Group();

        // Установим размер сцены и зальем сцену черным фоном.
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.BLACK);
        primaryStage.setScene(scene);

        // Добавим немного графики
        Group circles = new Group();
        for (int i = 0; i < 30; i++)
        {
            Circle circle = new Circle(150, Color.web("white", 0.05));
            circle.setStrokeType(StrokeType.OUTSIDE);
            circle.setStroke(Color.web("white", 0.16));
            circle.setStrokeWidth(4);
            circles.getChildren().add(circle);
        }

        // Добавим визуальный эффект прозрачности.
        circles.setEffect(new BoxBlur(10, 10, 3));

        // Создадим градиентный фон
        Rectangle colors =
            new Rectangle(scene.getWidth(), scene.getHeight(),
            new LinearGradient(0f, 1f, 1f, 0f, true,
            CycleMethod.NO_CYCLE,
            new Stop[]
            {
                new Stop(0, Color.web("#f8bd55")),
                new Stop(0.14, Color.web("#c0fe56")),
                new Stop(0.28, Color.web("#5dfbc1")),
                new Stop(0.43, Color.web("#64c2f8")),
                new Stop(0.57, Color.web("#be4af7")),
                new Stop(0.71, Color.web("#ed5fc2")),
                new Stop(0.85, Color.web("#ef504c")),
                new Stop(1, Color.web("#f2660f"))
            }));

        /*
         * root.getChildren().add(colors); root.getChildren().add(circles);
         */

        // Применим режим наложения: при анимации его эффект станет более наглядным.
        Group blendModeGroup =
            new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(), Color.BLACK),
            circles), colors);
        colors.setBlendMode(BlendMode.OVERLAY);
        root.getChildren().add(blendModeGroup);

        // Добавим анимацию
        Timeline timeline = new Timeline();
        for (Node circle : circles.getChildren())
        {
            timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // Установим начальную позицию в 0
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)
                ),
                new KeyFrame(new Duration(40000), // Установим конечную позицию в 40 секунд
                    new KeyValue(circle.translateXProperty(), random() * 800),
                    new KeyValue(circle.translateYProperty(), random() * 600)
                )
            );
        }
        // Проигрываем 40 секунд анимации
        timeline.play();

        primaryStage.show();
    }
}
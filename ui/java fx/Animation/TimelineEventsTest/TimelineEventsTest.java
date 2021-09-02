package pkg02.timelineeventstest;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * События анимационного цикла.
 * @author oracle_pr1
 */
public class TimelineEventsTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }
    // Главный Timeline
    private Timeline timeline;
    private AnimationTimer timer;
    private Integer i = 0;  // Переменная для сохранения текущего фрейма

    @Override
    public void start(Stage stage)
    {
        Group p = new Group();
        final Scene scene = new Scene(p);
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        p.setTranslateX(80);
        p.setTranslateY(80);

        // Создание круга с эффектом
        final Circle circle = new Circle(20, Color.rgb(156, 216, 255));
        circle.setEffect(new Lighting());

        // Создание текста внутри круга
        final Text text = new Text(i.toString());
        text.setStroke(Color.BLACK);

        // Создание расположения для круга с текстом внутри
        final StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);
        stack.setLayoutX(30);
        stack.setLayoutY(30);

        p.getChildren().add(stack);
        stage.show();

        // Создание timeline для перемещения круга
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        // Можно добавить конкретные действия, когда каждый кадр начинается.
        // Можно задать один или несколько кадров во время выполнения одного кадра в
        // зависимости от множества интерполятора.
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long l)
            {
                text.setText(i.toString());
                i++;
            }
        };

        // Создание keyValue's для анимационного увеличения круга в два раза
        // относительно обеих осей координат.
        KeyValue keyValueX = new KeyValue(stack.scaleXProperty(), 2);
        KeyValue keyValueY = new KeyValue(stack.scaleYProperty(), 2);

        // keyValue проходит пол-цикла анимации за 2 секунды
        Duration duration = Duration.millis(2000);

        // Можно добавить специфическое действие, когда keyframe достигнул "порога"
        EventHandler<ActionEvent> onFinished = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                stack.setTranslateX(
                   (scene.getWidth() - circle.getRadius()) * Math.random() / 2);
                stack.setTranslateY(
                   (scene.getWidth() - circle.getRadius()) * Math.random() / 2);
                i = 0;  // Сброс счетчика
            }
        };
        
        KeyFrame keyFrame = new KeyFrame(duration, onFinished, keyValueX, keyValueY);
        
        // Добавим keyFrame к timeline
        timeline.getKeyFrames().add(keyFrame);
        
        timeline.play();
        timer.start();
    }
}

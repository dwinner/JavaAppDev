package twobuttonstest;

import java.io.File;
import javafx.embed.swt.FXCanvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Простой тест взаимодействия SWT и JFX.
 *
 * @author oracle_pr1
 */
public class TwoButtonsTest
{
    public static void main(String[] args)
    {
        final Display display = new Display();
        final Shell shell = new Shell(display);
        final RowLayout layout = new RowLayout();
        shell.setLayout(layout);

        // Создание кнопки SWT

        final org.eclipse.swt.widgets.Button swtButton =
           new org.eclipse.swt.widgets.Button(shell, SWT.PUSH);
        swtButton.setText("SWT Button");

        // Создание полотна FXCanvas

        final FXCanvas fxCanvas = new FXCanvas(shell, SWT.NONE)
        {
            @Override
            public Point computeSize(int wHint, int hHint, boolean changed)
            {
                // Подгонка размера.
                getScene().getWindow().sizeToScene();
                int width = (int) getScene().getWidth();
                int height = (int) getScene().getHeight();
                return new Point(width, height);
            }
        };

        // Создание узла JFX Group
        Group group = new Group();

        // Создание JFX-кнопки
        final Button jfxButton = new Button("JFX Button");

        // Связывание идентификатора CSS с кнопкой
        jfxButton.setId("ipad-dark-grey");

        // Добавление кнопки в качестве дочернего узла группы
        group.getChildren().add(jfxButton);

        // Создание экземпляра графа сцены и установка группы в качестве корня
        Scene scene = new Scene(group, Color.rgb(shell.getBackground().getRed(),
                                                 shell.getBackground().getGreen(),
                                                 shell.getBackground().getBlue()));

        // Присоединение внешней таблицы стилей.
        scene.getStylesheets().add(TwoButtonsTest.class.getResource("Buttons.css").
           toExternalForm());
        fxCanvas.setScene(scene);

        // Добавление слушателей.
        swtButton.addListener(SWT.Selection, new Listener()
        {
            @Override
            public void handleEvent(Event event)
            {
                jfxButton.setText("JFX Button: Hello from SWT");
                shell.layout();
            }
        });
        jfxButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                swtButton.setText("SWT Button: Hello from JFX");
                shell.layout();
            }
        });

        // Диспетчеризация закрытия окна SWT
        shell.open();
        while (!shell.isDisposed())
        {
            if (!display.readAndDispatch())
            {
                display.sleep();
            }
        }
        display.dispose();
    }
}

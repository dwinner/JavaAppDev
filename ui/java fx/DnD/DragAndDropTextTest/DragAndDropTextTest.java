package draganddroptexttest;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * ƒемонстраци€ поддержки перетаскивани€ текста.
 * <p/>
 * @author oracle_pr1
 */
public class DragAndDropTextTest extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Hello Drag And Drop");

        Group root = new Group();
        Scene scene = new Scene(root, 400, 200);
        scene.setFill(Color.LIGHTGREEN);

        final Text source = new Text(50, 100, "DRAG ME");
        source.setScaleX(2.0);
        source.setScaleY(2.0);

        final Text target = new Text(250, 100, "DROP HERE");
        target.setScaleX(2.0);
        target.setScaleY(2.0);

        source.setOnDragDetected(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                // ѕеретаскивание было определено, начинаем DnD жест
                System.out.println("onDragDetected");

                // ѕозвол€ем любой режим передачи
                Dragboard dragboard = source.startDragAndDrop(TransferMode.ANY);

                // ѕомещаем строку в буфер перетаскивани€ Dragboard
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getText());
                dragboard.setContent(content);

                mouseEvent.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                // ѕеретаскиваемые данные наход€тс€ над источником назначени€
                System.out.println("onDragOver");

                // ѕозволить данное действие только, если узел пункта назначени€
                // не €вл€етс€ узлом пунктом источника и имеет строковые данные
                if (dragEvent.getGestureSource() != target && dragEvent.getDragboard().hasString())
                {
                    // ѕозволить и копирование и перемещение,
                    // независимо от того, что выбрал пользователь
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                dragEvent.consume();
            }
        });

        target.setOnDragEntered(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                // DnD жест зашел в пункт назначени€
                System.out.println("onDragEntered");

                // ѕоказать пользователю, что этот приемник перетаскивани€
                // €вл€етс€ в данный момент текущим
                if (dragEvent.getGestureSource() != target && dragEvent.getDragboard().hasString())
                {
                    target.setFill(Color.GREEN);
                }

                dragEvent.consume();
            }
        });

        target.setOnDragExited(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                // ћышь покинула приемник, удалить графические сигналы
                target.setFill(Color.BLACK);

                dragEvent.consume();
            }
        });

        target.setOnDragDropped(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                // ƒанные сброшены на приемник
                System.out.println("onDragDropped");

                // ≈сли есть строковые данные в панели перетаскивани€,
                // прочитать их и использовать
                Dragboard dragboard = dragEvent.getDragboard();
                boolean success = false;
                if (dragboard.hasString())
                {
                    target.setText(dragboard.getString());
                    success = true;
                }

                // ќповестим источник о том, что строка была перемещена
                // успешно и использована
                dragEvent.setDropCompleted(success);

                dragEvent.consume();
            }
        });

        source.setOnDragDone(new EventHandler<DragEvent>()
        {
            @Override
            public void handle(DragEvent dragEvent)
            {
                // DnD жест закончен
                System.out.println("onDragDone");
                
                // ≈сли данные были успешно перемещены, очистим их
                // из источника
                if (dragEvent.getTransferMode() == TransferMode.MOVE)
                {
                    source.setText("");
                }
                
                dragEvent.consume();
            }
        });
        
        root.getChildren().add(source);
        root.getChildren().add(target);
        stage.setScene(scene);
        stage.show();
    }
}

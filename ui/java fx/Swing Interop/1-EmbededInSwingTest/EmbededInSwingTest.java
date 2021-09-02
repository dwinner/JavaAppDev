package pkg1.embededinswingtest;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Внедрение JavaFX-сцены в приложение Swing.
 * @author Denis
 */
public class EmbededInSwingTest
{
    private static void initAndShowGUI()
    {
        // Этот метод вызывается из EDT-потока
        JFrame frame = new JFrame("Swing and JavaFX");
        final JFXPanel fXPanel = new JFXPanel();
        frame.add(fXPanel);
        frame.setSize(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                initFX(fXPanel);
            }
        });
    }

    private static void initFX(JFXPanel fXPanel)
    {
        // Этот метод вызывается из потока JavaFX Application thread
        Scene scene = createScene();
        fXPanel.setScene(scene);
    }

    private static Scene createScene()
    {
        Group root = new Group();
        Scene scene = new Scene(root, Color.ALICEBLUE);
        Text text = new Text();
        text.setX(40);
        text.setY(100);
        text.setFont(new Font(25));
        text.setText("Welcome JavaFX!");
        root.getChildren().add(text);

        return scene;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                initAndShowGUI();
            }
        });
    }
}

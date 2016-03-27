// Специальное оформление окон Swing
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class WindowDecorations
{
    public static void main(String[] args)
    {
        // Включим украшения для окон
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        // Окно с рамкой
        JFrame frame = new JFrame("Окно с рамкой");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);

        // Диалоговое окно
        JDialog dialog = new JDialog(frame, "Диалог");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(150, 100);

        // Так можно задавать тип оформления окна
        dialog.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        dialog.setVisible(true);
    }

}
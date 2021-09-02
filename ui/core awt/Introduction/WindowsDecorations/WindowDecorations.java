// ����������� ���������� ���� Swing
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;

public class WindowDecorations
{
    public static void main(String[] args)
    {
        // ������� ��������� ��� ����
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        // ���� � ������
        JFrame frame = new JFrame("���� � ������");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);

        // ���������� ����
        JDialog dialog = new JDialog(frame, "������");
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(150, 100);

        // ��� ����� �������� ��� ���������� ����
        dialog.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
        dialog.setVisible(true);
    }

}
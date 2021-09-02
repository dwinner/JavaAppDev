// ������� ������ � ���������� JFileChooser
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class FilteringFiles extends JFrame
{
    public FilteringFiles()
    {
        super("Filtering Files");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);

        // ����������� ��������� ��� ������ �����
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("�������� ��������� ����");
        // ������������ ������
        chooser.addChoosableFileFilter(new TextFileFilter());
        // ������� ���������� ���� �� �����
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(this, chooser.getSelectedFile());
        }
    }

    private static class TextFileFilter extends FileFilter
    {
        // ��������� ���� ��� ���������� ���
        @Override public boolean accept(File f)
        {
            // ��� �������� ���������
            if (f.isDirectory())
            {
                return true;
            }
            // ��� ������ ������� �� ����������
            return f.getName().endsWith("txt");
        }

        // �������� �������
        @Override public String getDescription()
        {
            return "��������� ����� (*.txt)";
        }

    }

    public static void main(String[] args)
    {
        new FilteringFiles();
    }

}
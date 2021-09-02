// ������� ��� ������ � ���������� JFileChooser
import java.awt.HeadlessException;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;

public class CustomFileView extends JFrame
{
    public CustomFileView() throws HeadlessException
    {
        super("Custom File View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
        
        // ����������� ��������� ��� ������ ������
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("�������� ����");
        chooser.setFileView(new NewFileView(chooser.getFileSystemView()));
        // ���������� ���������� ����
        @SuppressWarnings("LeakingThisInConstructor")
        int res = chooser.showOpenDialog(this);
    }
    
    /**
     * ����� ��� ����������� �������� ���� ������
     */
    private static class NewFileView extends FileView
    {
        // ������, ����������� ��� ������
        private Icon fileIcon = new ImageIcon("caution.gif");
        private Icon folderIcon = new ImageIcon("grninfo.gif");
        private FileSystemView fileSystem;
        
        // ������������ ���������� �������� �������� �������
        NewFileView(FileSystemView fileSystem)
        {
            this.fileSystem = fileSystem;
        }
        
        // ���������� ������ ��� �����
        @Override public Icon getIcon(File file)
        {
            // �������� ����� �������� ������� ����������
            if (fileSystem.isRoot(file) || fileSystem.isDrive(file))
            {
                return super.getIcon(file);
            }
            return (file.isDirectory()) ? folderIcon : fileIcon;
        }
    }
    
    public static void main(String[] args)
    {
        new CustomFileView();
    }

}
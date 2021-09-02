// ��������� JFrame ��� ����������� ����������� �������� ������� � ������
// JTree � ����������� ������ TreeModel

import java.io.File;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class FileTreeFrame extends JFrame
{
    // ������ JTree ��� ����������� �������� �������
    private JTree fileTree;
    // ���������� ������ FileSystemModel
    private FileSystemModel fileSystemModel;
    // ������ JTextArea ��� ����������� �������� � ��������� �����
    private JTextArea fileDetailsTextArea;

    // ����������� FileTreeFrame
    public FileTreeFrame(String directory)
    {
        super("JTree FileSystemViewer");

        // �������� ������� JTextArea ��� ����������� ���������� � �����
        fileDetailsTextArea = new JTextArea();
        fileDetailsTextArea.setEditable(false);

        // �������� ������ FileSystemModel ��� ��������� ��������
        fileSystemModel = new FileSystemModel(new File(directory));
        // �������� ������� JTree ��� ����������� ������ FileSystemModel
        fileTree = new JTree(fileSystemModel);
        // ������� ������ JTree ������������� ��� �������������� ������
        fileTree.setEditable(true);

        // ���������� ��������� TreeSelectionListener
        fileTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            // ����������� �������� � ����� ��������� ����� ��� ��������� ������
            @Override
            public void valueChanged(TreeSelectionEvent tse)
            {
                File file = (File) fileTree.getLastSelectedPathComponent();
                fileDetailsTextArea.setText(getFileDetails(file));
            }
        });

        // ��������� �������� fileTree � fileDetailsTextArea � ������ JSplitPane
        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            true,
            new JScrollPane(fileTree),
            new JScrollPane(fileDetailsTextArea));

        getContentPane().add(splitPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    // ������������ ������ �� ���������� � �����
    private String getFileDetails(File file)
    {
        // �� ���������� �������� � ������ ������ (null)
        if (file == null)
        {
            return "";
        }

        // ��������� ���������� � ����� � ������ StringBuilder
        StringBuilder buffer = new StringBuilder();
        buffer.append("Name: ").append(file.getName()).append("\n");
        buffer.append("Path: ").append(file.getPath()).append("\n");
        buffer.append("Size: ").append(file.length()).append("\n");

        return buffer.toString();
    }

    public static void main(String[] args)
    {
        // ������������ ������ ������������ ��� ��������
        if (args.length != 1)
        {
            System.err.println("Usage: java FileTreeFrame <path>");
        }
        else
        {
            new FileTreeFrame(args[0]);
        }
    }
}
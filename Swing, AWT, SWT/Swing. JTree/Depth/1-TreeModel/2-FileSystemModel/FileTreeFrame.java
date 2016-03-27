// Компонент JFrame для отображения содержимого файловой системы в дереве
// JTree с применением модели TreeModel

import java.io.File;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class FileTreeFrame extends JFrame
{
    // Объект JTree для отображения файловой системы
    private JTree fileTree;
    // Реализация модели FileSystemModel
    private FileSystemModel fileSystemModel;
    // Объект JTextArea для отображения сведений о выбранном файле
    private JTextArea fileDetailsTextArea;

    // Конструктор FileTreeFrame
    public FileTreeFrame(String directory)
    {
        super("JTree FileSystemViewer");

        // Создание объекта JTextArea для отображения информации о файле
        fileDetailsTextArea = new JTextArea();
        fileDetailsTextArea.setEditable(false);

        // Создание модели FileSystemModel для заданного каталога
        fileSystemModel = new FileSystemModel(new File(directory));
        // Создание объекта JTree для отображения модели FileSystemModel
        fileTree = new JTree(fileSystemModel);
        // Сделать дерево JTree редактируемым для переименования файлов
        fileTree.setEditable(true);

        // Добавление слушателя TreeSelectionListener
        fileTree.addTreeSelectionListener(new TreeSelectionListener()
        {
            // Отображение сведений о вновь созданном файле при изменении выбора
            @Override
            public void valueChanged(TreeSelectionEvent tse)
            {
                File file = (File) fileTree.getLastSelectedPathComponent();
                fileDetailsTextArea.setText(getFileDetails(file));
            }
        });

        // Помещение объектов fileTree и fileDetailsTextArea в панель JSplitPane
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

    // Формирование строки со сведениями о файле
    private String getFileDetails(File file)
    {
        // Не возвращать сведения о пустых файлах (null)
        if (file == null)
        {
            return "";
        }

        // Помещение информации о файле в объект StringBuilder
        StringBuilder buffer = new StringBuilder();
        buffer.append("Name: ").append(file.getName()).append("\n");
        buffer.append("Path: ").append(file.getPath()).append("\n");
        buffer.append("Size: ").append(file.length()).append("\n");

        return buffer.toString();
    }

    public static void main(String[] args)
    {
        // Пользователь должен предоставить имя каталога
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
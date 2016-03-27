/*
 * Реализация модели TreeModel, использующая объекты File в качестве узлов.
 */

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileSystemModel implements TreeModel
{
    // Корень иерархии
    private File root;
    // Слушатели TreeModelListener
    private Vector<TreeModelListener> listeners = new Vector<TreeModelListener>();

    // Конструктор FileSystemModel
    public FileSystemModel(File rootDirectory)
    {
        root = rootDirectory;
    }

    // Получить корень иерархии (корневой каталог)
    @Override
    public Object getRoot()
    {
        return root;
    }

    // Получить дочерний узел родителя по заданному индексу
    @Override
    public Object getChild(Object parent, int index)
    {
        // Получить родительский объект File
        File directory = (File) parent;

        // Получить список файлов в родительском каталоге
        String[] children = directory.list();

        // Вернуть файл по заданному индексу и заместить метод toString для
        // возврата только имени файла
        return new TreeFile(directory, children[index]);
    }

    // Получить количество дочерних узлов родителя
    @Override
    public int getChildCount(Object parent)
    {
        // Получить родительский объект File
        File file = (File) parent;

        // Получить число файлов в каталоге
        if (file.isDirectory())
        {
            String[] fileList = file.list();
            if (fileList != null)
            {
                return fileList.length;
            }
        }

        return 0;   // В счучае файлов
    }

    // true, если узел есть файл, false - если каталог
    @Override
    public boolean isLeaf(Object node)
    {
        File file = (File) node;
        return file.isFile();
    }

    // Вызывается делегатом, если значение объекта в указанном пути TreePath изменилось
    @Override
    public void valueForPathChanged(TreePath path, Object newValue)
    {
        // Получение измененного объекта File
        File oldFile = (File) path.getLastPathComponent();

        // Получение родительского каталога измененного файла
        String fileParentPath = oldFile.getParent();

        // Получение имени файла, введенного пользователем
        String newFileName = (String) newValue;

        // Создание объекта File newFileName для переименования объекта oldFile
        File targetFile = new File(fileParentPath, newFileName);

        // Переименование oldFile в targetFile
        oldFile.renameTo(targetFile);

        // Получение объекта File для родительского каталога
        File parent = new File(fileParentPath);
        // Создание целочисленного массива для индекса переименованного файла
        int[] changedChildrenIndices =
        {
            getIndexOfChild(parent, targetFile)
        };
        // Создание массива объектов, содержащего только переименованные файлы
        Object[] changedChildren =
        {
            targetFile
        };
        // Уведомление слушателей TreeModelListener об изменении узла
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);
    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children)
    {
        // Создание события TreeModelEvent для указание на изменение узла
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);

        Iterator<TreeModelListener> iterator = listeners.iterator();
        TreeModelListener listener;

        // Отправка события TreeModelEvent каждому слушателю
        while (iterator.hasNext())
        {
            listener = iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    // Получение числового индекса заданного дочернего узла
    @Override
    public int getIndexOfChild(Object parent, Object child)
    {
        // Получить родительский объект File
        File directory = (File) parent;

        // Получить дочерний объект File
        File file = (File) child;

        // Получить список файлов в каталоге
        String[] children = directory.list();

        // Поиск заданного дочернего узла в списке файлов
        for (int i = 0; i < children.length; i++)
        {
            if (file.getName().equals(children[i]))
            {
                // Возврат соответствующего индекса файла
                return i;
            }
        }

        return -1;  // Индекс дочернего узла не найден
    }

    // Добавление заданного слушателя TreeModelListener
    @Override
    public void addTreeModelListener(TreeModelListener l)
    {
        listeners.add(l);
    }

    // Удаление заданного слушателя TreeModelListener
    @Override
    public void removeTreeModelListener(TreeModelListener l)
    {
        listeners.remove(l);
    }

    // TreeFile - подкласс класса File, который замещает метод toString для
    // возврата только имени файла
    private class TreeFile extends File
    {
        TreeFile(File parent, String child)
        {
            super(parent, child);
        }

        @Override
        public String toString()
        {
            return getName();
        }
    }
}
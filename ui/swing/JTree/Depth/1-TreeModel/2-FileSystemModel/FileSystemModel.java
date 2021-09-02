/*
 * ���������� ������ TreeModel, ������������ ������� File � �������� �����.
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
    // ������ ��������
    private File root;
    // ��������� TreeModelListener
    private Vector<TreeModelListener> listeners = new Vector<TreeModelListener>();

    // ����������� FileSystemModel
    public FileSystemModel(File rootDirectory)
    {
        root = rootDirectory;
    }

    // �������� ������ �������� (�������� �������)
    @Override
    public Object getRoot()
    {
        return root;
    }

    // �������� �������� ���� �������� �� ��������� �������
    @Override
    public Object getChild(Object parent, int index)
    {
        // �������� ������������ ������ File
        File directory = (File) parent;

        // �������� ������ ������ � ������������ ��������
        String[] children = directory.list();

        // ������� ���� �� ��������� ������� � ��������� ����� toString ���
        // �������� ������ ����� �����
        return new TreeFile(directory, children[index]);
    }

    // �������� ���������� �������� ����� ��������
    @Override
    public int getChildCount(Object parent)
    {
        // �������� ������������ ������ File
        File file = (File) parent;

        // �������� ����� ������ � ��������
        if (file.isDirectory())
        {
            String[] fileList = file.list();
            if (fileList != null)
            {
                return fileList.length;
            }
        }

        return 0;   // � ������ ������
    }

    // true, ���� ���� ���� ����, false - ���� �������
    @Override
    public boolean isLeaf(Object node)
    {
        File file = (File) node;
        return file.isFile();
    }

    // ���������� ���������, ���� �������� ������� � ��������� ���� TreePath ����������
    @Override
    public void valueForPathChanged(TreePath path, Object newValue)
    {
        // ��������� ����������� ������� File
        File oldFile = (File) path.getLastPathComponent();

        // ��������� ������������� �������� ����������� �����
        String fileParentPath = oldFile.getParent();

        // ��������� ����� �����, ���������� �������������
        String newFileName = (String) newValue;

        // �������� ������� File newFileName ��� �������������� ������� oldFile
        File targetFile = new File(fileParentPath, newFileName);

        // �������������� oldFile � targetFile
        oldFile.renameTo(targetFile);

        // ��������� ������� File ��� ������������� ��������
        File parent = new File(fileParentPath);
        // �������� �������������� ������� ��� ������� ���������������� �����
        int[] changedChildrenIndices =
        {
            getIndexOfChild(parent, targetFile)
        };
        // �������� ������� ��������, ����������� ������ ��������������� �����
        Object[] changedChildren =
        {
            targetFile
        };
        // ����������� ���������� TreeModelListener �� ��������� ����
        fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices, changedChildren);
    }

    private void fireTreeNodesChanged(TreePath parentPath, int[] indices, Object[] children)
    {
        // �������� ������� TreeModelEvent ��� �������� �� ��������� ����
        TreeModelEvent event = new TreeModelEvent(this, parentPath, indices, children);

        Iterator<TreeModelListener> iterator = listeners.iterator();
        TreeModelListener listener;

        // �������� ������� TreeModelEvent ������� ���������
        while (iterator.hasNext())
        {
            listener = iterator.next();
            listener.treeNodesChanged(event);
        }
    }

    // ��������� ��������� ������� ��������� ��������� ����
    @Override
    public int getIndexOfChild(Object parent, Object child)
    {
        // �������� ������������ ������ File
        File directory = (File) parent;

        // �������� �������� ������ File
        File file = (File) child;

        // �������� ������ ������ � ��������
        String[] children = directory.list();

        // ����� ��������� ��������� ���� � ������ ������
        for (int i = 0; i < children.length; i++)
        {
            if (file.getName().equals(children[i]))
            {
                // ������� ���������������� ������� �����
                return i;
            }
        }

        return -1;  // ������ ��������� ���� �� ������
    }

    // ���������� ��������� ��������� TreeModelListener
    @Override
    public void addTreeModelListener(TreeModelListener l)
    {
        listeners.add(l);
    }

    // �������� ��������� ��������� TreeModelListener
    @Override
    public void removeTreeModelListener(TreeModelListener l)
    {
        listeners.remove(l);
    }

    // TreeFile - �������� ������ File, ������� �������� ����� toString ���
    // �������� ������ ����� �����
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
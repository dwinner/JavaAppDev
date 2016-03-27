import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Модель описывает структуру дерева с Java-объектами. Дочерние объекты отображают переменные
 * экземпляра.
 * <p/>
 * @author JavaFx
 */
public class ObjectTreeModel implements TreeModel
{
    private Variable root;
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Формирование пустого дерева.
     */
    public ObjectTreeModel()
    {
        root = null;
    }

    public void setRoot(Variable v)
    {
        Variable oldRoot = v;
        root = v;
        fireTreeStructureChanged(oldRoot);
    }

    @Override
    public Object getRoot()
    {
        return root;
    }

    @Override
    public int getChildCount(Object parent)
    {
        return ((Variable) parent).getFields().size();
    }

    @Override
    public Object getChild(Object parent, int index)
    {
        ArrayList<Field> fields = ((Variable) parent).getFields();
        Field f = fields.get(index);
        Object parentVelue = ((Variable) parent).getValue();
        try
        {
            return new Variable(f.getType(), f.getName(), f.get(parentVelue));
        }
        catch (IllegalArgumentException | IllegalAccessException ex)
        {
            return null;
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child)
    {
        int n = getChildCount(parent);
        for (int i = 0; i < n; i++)
        {
            if (getChild(parent, i).equals(child))
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLeaf(Object node)
    {
        return getChildCount(node) == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue)
    {
    }

    @Override
    public void addTreeModelListener(TreeModelListener tl)
    {
        listenerList.add(TreeModelListener.class, tl);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener tl)
    {
        listenerList.remove(TreeModelListener.class, tl);
    }

    protected void fireTreeStructureChanged(Object oldRoot)
    {
        TreeModelEvent event = new TreeModelEvent(this, new Object[]
            {
                oldRoot
            });
        EventListener[] listeners = listenerList.getListeners(TreeModelListener.class);
        for (int i = 0; i < listeners.length; i++)
        {
            ((TreeModelListener) listeners[i]).treeStructureChanged(event);
        }
    }
}

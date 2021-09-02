
/**
 * Реализация пользовательских моделей деревьев.
 * <p/>
 * @version 1.02 2004-08-21
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Пример применения пользовательской модели дерева для отображения полей объекта.
 */
public class ObjectInspectorTest
{
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ObjectInspectorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий дерево объектов.
 */
class ObjectInspectorFrame extends JFrame
{
    private JTree tree;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    ObjectInspectorFrame()
    {
        setTitle("ObjectInspectorTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Проверка фрейма с объектами.

        Variable v = new Variable(getClass(), "this", this);
        ObjectTreeModel model = new ObjectTreeModel();
        model.setRoot(v);

        // Формирование и отображение дерева.

        tree = new JTree(model);
        add(new JScrollPane(tree), BorderLayout.CENTER);
    }
}

/**
 * Модель описывает структуру дерева с Java-объектами. Дочерние объекты отображают переменные экземпляра
 */
class ObjectTreeModel implements TreeModel
{
    private Variable root;
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Формирование пустого дерева.
     */
    ObjectTreeModel()
    {
        root = null;
    }

    /**
     * Корневой узел для данной переменной.
     * <p/>
     * @param v Переменная, описываемая деревом
     */
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
        Object parentValue = ((Variable) parent).getValue();
        try
        {
            return new Variable(f.getType(), f.getName(), f.get(parentValue));
        }
        catch (IllegalAccessException e)
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
    public void addTreeModelListener(TreeModelListener l)
    {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l)
    {
        listenerList.remove(TreeModelListener.class, l);
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

/**
 * Переменная с укзанием типа, имени и значения.
 */
class Variable
{
    private Class<?> type;
    private String name;
    private Object value;
    private ArrayList<Field> fields;

    /**
     * Формирование переменной
     * <p/>
     * @param aType  Тип
     * @param aName  Имя
     * @param aValue Значение
     */
    Variable(Class aType, String aName, Object aValue)
    {
        type = aType;
        name = aName;
        value = aValue;
        fields = new ArrayList<>();

        // Поиск всех полей класса, за исключением строк и значений null

        if (!type.isPrimitive() && !type.isArray() && !type.equals(String.class) && value != null)
        {
            // Получение полей класса и всех суперклассов.
            for (Class<?> c = value.getClass(); c != null; c = c.getSuperclass())
            {
                Field[] fs = c.getDeclaredFields();
                AccessibleObject.setAccessible(fs, true);

                // Получение всех нестатических полей
                for (Field f : fs)
                {
                    if ((f.getModifiers() & Modifier.STATIC) == 0)
                    {
                        fields.add(f);
                    }
                }
            }
        }
    }

    /**
     * Получение значения переменной.
     * <p/>
     * @return Значение
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Получение всех нестатических полей для переменной.
     * <p/>
     * @return Списочный массив для полей
     */
    public ArrayList<Field> getFields()
    {
        return fields;
    }

    @Override
    public String toString()
    {
        String r = type + " " + name;
        if (type.isPrimitive())
        {
            r += "=" + value;
        }
        else if (type.equals(String.class))
        {
            r += "=" + value;
        }
        else if (value == null)
        {
            r += "=null";
        }
        return r;
    }
}

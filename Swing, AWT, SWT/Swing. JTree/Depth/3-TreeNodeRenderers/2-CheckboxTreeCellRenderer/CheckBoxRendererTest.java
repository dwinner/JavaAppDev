/*
 * Дерево, способное отображать в качестве узлов флажки.
 */

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;

class CheckBoxTree extends JTree
{
    // Стандартный объект для отображения узлов
    private DefaultTreeCellRenderer defRenderer = new DefaultTreeCellRenderer();

    // Конструктор на основе модели
    CheckBoxTree(TreeModel model)
    {
        super(model);
        // Задаем собственный отображающий объект
        setCellRenderer(new CheckBoxRenderer());
        // Следим за щелчками мыши
        addMouseListener(new MouseL());
    }

    private class CheckBoxRenderer extends JCheckBox implements TreeCellRenderer
    {
        CheckBoxRenderer()
        {
            // Делаем флажок прозрачным
            setOpaque(false);
        }

        /**
         * Данный метод должен вернуть компонент для узла
         * <p/>
         * @param tree     Дерево исходного объекта отображения
         * @param value    Компонент для отображения
         * @param selected Флаг выбора
         * @param expanded Флаг раскрытия
         * @param leaf     Флаг листа
         * @param row      Порядок вложенности узла
         * @param hasFocus Флаг фокуса
         * <p/>
         * @return Компонент отображающего объекта узла
         */
        @Override
        public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus)
        {
            // Проверяем, что используется стандартная модель
            if (!(value instanceof DefaultMutableTreeNode))
            {
                // Если нет, то используем стандартный объект отображения
                return defRenderer.getTreeCellRendererComponent(
                    tree,
                    value,
                    leaf,
                    expanded,
                    leaf,
                    row,
                    hasFocus);
            }
            Object data = ((DefaultMutableTreeNode) value).getUserObject();
            // Проверяем, подходит ли нам тип данных узла
            if (data instanceof CheckBoxListElement)
            {
                CheckBoxListElement element = (CheckBoxListElement) data;
                // Настраиваем флажок
                setSelected(element.isSelected());
                setText(element.getText());
                return this;
            }
            else
            {  // Иначе задействуем стандартный объект
                return defRenderer.getTreeCellRendererComponent(
                    tree,
                    value,
                    leaf,
                    expanded,
                    leaf,
                    row,
                    hasFocus);
            }
        }
    }

    private class MouseL extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            // Получаем путь к узлу
            TreePath path = CheckBoxTree.this.getClosestPathForLocation(e.getX(), e.getY());
            if (path == null)
            {
                return;
            }
            // Проверяем, подходят ли нам данные узла
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            Object data = node.getUserObject();
            if (data instanceof CheckBoxListElement)
            {
                // Меняем состояние флажка
                CheckBoxListElement element = (CheckBoxListElement) data;
                element.setSelected(!element.isSelected());
                repaint();
            }
        }
    }
}

class CheckBoxListElement
{
    private String text;
    private boolean selected;

    CheckBoxListElement(String text, boolean selected)
    {
        this.text = text;
        this.selected = selected;
    }

    CheckBoxListElement(String text)
    {
        this(text, false);
    }

    CheckBoxListElement()
    {
        this(null, false);
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

public class CheckBoxRendererTest extends JFrame
{
    // Листья дерева храним в массивах
    private String[] langs =
    {
        "<html><b>Java</b>",
        "<html><pre>C++</pre>",
        "C#"
    };
    private String[] ides =
    {
        "Visual Studio",
        "<html><i>Eclipse</i>",
        "NetBeans"
    };

    public CheckBoxRendererTest()
    {
        super("Test CheckBox-Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем модель и дерево
        TreeModel model = createTreeModel();
        CheckBoxTree tree = new CheckBoxTree(model);
        // Добавляем дерево в окно
        getContentPane().add(new JScrollPane(tree));
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * Создание несложной модели дерева
     * <p/>
     * @return Модель дерева
     */
    private TreeModel createTreeModel()
    {
        // Корень нашего дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("<html><font color=\"blue\">Создание кода</font>");
        // Основные ветви
        DefaultMutableTreeNode lang = new DefaultMutableTreeNode("Языки");
        DefaultMutableTreeNode ide = new DefaultMutableTreeNode("Среды");
        root.add(lang);
        root.add(ide);
        // Присоединяем листья с данными для флажков
        for (int i = 0; i < langs.length; i++)
        {
            lang.add(new DefaultMutableTreeNode(new CheckBoxListElement(langs[i])));
            ide.add(new DefaultMutableTreeNode(new CheckBoxListElement(ides[i])));
        }
        // Создаем и возвращаем стандартную модель
        return new DefaultTreeModel(root);
    }

    public static void main(String[] args)
    {
        new CheckBoxRendererTest();
    }
}
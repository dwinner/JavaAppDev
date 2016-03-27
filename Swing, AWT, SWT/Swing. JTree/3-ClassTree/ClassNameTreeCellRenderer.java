import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.lang.reflect.Modifier;

/**
 * Данный класс отображает имя класса либо обычным шрифтом,
 * либо курсивом. Курсивом выводятся абстрактные классы.
 */
public class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer
{
    private Font plaintFont = null;
    private Font italicFont = null;

    @Override
    public Component getTreeCellRendererComponent(JTree tree,
        Object value,
        boolean sel,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        // Получение пользовательского объекта.
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Class<?> c = (Class<?>) node.getUserObject();

        // Курсив формируется из обычного шрифта.
        if (plaintFont == null)
        {
            // Объект отображения узла иногда вызывается при отсутствующем шрифте.
            plaintFont = getFont();
            if (plaintFont != null)
                italicFont = plaintFont.deriveFont(Font.ITALIC);
        }

        // Если класс абстрактный, устанавливается курсив,
        // в противном случае - обычный шрифт.
        if ((c.getModifiers() & Modifier.ABSTRACT) == 0)
            setFont(plaintFont);
        else
            setFont(italicFont);

        return this;
    }
}

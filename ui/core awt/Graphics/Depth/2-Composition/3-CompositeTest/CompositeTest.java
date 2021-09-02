
/**
 * Правила композиции для изображений.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Демонстрация правил Портера-Даффа для объединения изображений.
 */
public class CompositeTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new CompositeTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм содержит раскрывающийся список для выбора правила композиции, линейный регулятор для изменения альфа-значения,
 * а также панель, демонстрирующую результат применения выбранного правила.
 */
class CompositeTestFrame extends JFrame
{
    private CompositePanel canvas;
    private JComboBox<Object> ruleCombo;
    private JSlider alphaSlider;
    private JTextField explanation;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    CompositeTestFrame()
    {
        setTitle("CompositeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new CompositePanel();
        add(canvas, BorderLayout.CENTER);

        ruleCombo = new JComboBox<>(new Object[]
            {
                new Rule("CLEAR", "  ", "  "),
                new Rule("SRC", " S", " S"),
                new Rule("DST", "  ", "DD"),
                new Rule("SRC_OVER", " S", "DS"),
                new Rule("DST_OVER", " S", "DD"),
                new Rule("SRC_IN", "  ", " S"),
                new Rule("SRC_OUT", " S", "  "),
                new Rule("DST_IN", "  ", " D"),
                new Rule("DST_OUT", "  ", "D "),
                new Rule("SRC_ATOP", "  ", "DS"),
                new Rule("DST_ATOP", " S", " D"),
                new Rule("XOR", " S", "D "),
            });
        ruleCombo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                Rule r = (Rule) ruleCombo.getSelectedItem();
                canvas.setRule(r.getValue());
                explanation.setText(r.getExplanation());
            }
        });

        alphaSlider = new JSlider(0, 100, 75);
        alphaSlider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent event)
            {
                canvas.setAlpha(alphaSlider.getValue());
            }
        });
        JPanel panel = new JPanel();
        panel.add(ruleCombo);
        panel.add(new JLabel("Alpha"));
        panel.add(alphaSlider);
        add(panel, BorderLayout.NORTH);

        explanation = new JTextField();
        add(explanation, BorderLayout.SOUTH);

        canvas.setAlpha(alphaSlider.getValue());
        Rule r = (Rule) ruleCombo.getSelectedItem();
        canvas.setRule(r.getValue());
        explanation.setText(r.getExplanation());
    }
}

/**
 * Класс, описывающий правило Портера-Даффа.
 */
class Rule
{
    private String name;
    private String porterDuff1;
    private String porterDuff2;

    /**
     * Создание правила Портера-Даффа.
     * <p/>
     * @param n   Имя правила
     * @param pd1 Первая строка квадрата Портера-Даффа
     * @param pd2 Вторая строка квадрата Портера-Даффа
     */
    Rule(String n, String pd1, String pd2)
    {
        name = n;
        porterDuff1 = pd1;
        porterDuff2 = pd2;
    }

    /**
     * Объяснение правила.
     * <p/>
     * @return Строка с текстом объяснения
     */
    public String getExplanation()
    {
        StringBuilder r = new StringBuilder("Source ");
        if (porterDuff2.equals("  "))
        {
            r.append("clears");
        }
        if (porterDuff2.equals(" S"))
        {
            r.append("overwrites");
        }
        if (porterDuff2.equals("DS"))
        {
            r.append("blends with");
        }
        if (porterDuff2.equals(" D"))
        {
            r.append("alpha modifies");
        }
        if (porterDuff2.equals("D "))
        {
            r.append("alpha complement modifies");
        }
        if (porterDuff2.equals("DD"))
        {
            r.append("does not affect");
        }
        r.append(" destination");
        if (porterDuff1.equals(" S"))
        {
            r.append(" and overwrites empty pixels");
        }
        r.append(".");
        return r.toString();
    }

    @Override
    public String toString()
    {
        return name;
    }

    /**
     * Получение значения правила в классе AlphaComposite.
     * <p/>
     * @return Константа класса AlphaComposite или -1, если подходящей константы нет.
     */
    public int getValue()
    {
        try
        {
            return (Integer) AlphaComposite.class.getField(name).get(null);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
        {
            return -1;
        }
    }
}

/**
 * На данной панели отображаются две фигуры, объединенные с учетом правил композиции.
 */
class CompositePanel extends JPanel
{
    private int rule;
    private Shape shape1;
    private Shape shape2;
    private float alpha;

    CompositePanel()
    {
        shape1 = new Ellipse2D.Double(100, 100, 150, 100);
        shape2 = new Rectangle2D.Double(150, 150, 150, 100);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gImage = image.createGraphics();
        gImage.setPaint(Color.red);
        gImage.fill(shape1);
        AlphaComposite composite = AlphaComposite.getInstance(rule, alpha);
        gImage.setComposite(composite);
        gImage.setPaint(Color.blue);
        gImage.fill(shape2);
        g2.drawImage(image, null, 0, 0);
    }

    /**
     * Установка правила композиции.
     * <p/>
     * @param r Правило (в виде константы AlphaComposite)
     */
    public void setRule(int r)
    {
        rule = r;
        repaint();
    }

    /**
     * Установка значения альфа источника.
     * <p/>
     * @param a Значение альфа в интервале от 0 до 100
     */
    public void setAlpha(int a)
    {
        alpha = (float) a / 100.0F;
        repaint();
    }
}

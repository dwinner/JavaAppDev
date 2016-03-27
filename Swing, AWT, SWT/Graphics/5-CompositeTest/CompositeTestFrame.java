import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Фрейм с раскрывающимся списком для выбора правила композиции, линейным регулятором для изменения
 * альфа-значения, а также компонентом для отображения результата применения выбранного правила.
 */
public class CompositeTestFrame extends JFrame
{
    private CompositeComponent canvas;
    private JComboBox<? extends Rule> ruleCombo;
    private JSlider alphaSlider;
    private JTextField explanation;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    public CompositeTestFrame()
    {
        setTitle("CompositeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        canvas = new CompositeComponent();
        add(canvas, BorderLayout.CENTER);

        ruleCombo = new JComboBox<>(new Rule[]
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
                new Rule("XOR", " S", "D ")
            });
        ruleCombo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
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
            public void stateChanged(ChangeEvent changeEvent)
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

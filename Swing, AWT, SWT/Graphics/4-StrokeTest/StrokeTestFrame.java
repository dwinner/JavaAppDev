import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Фрейм с набором переключателей для выбора стиля окончания штриха, стиля пересечения штрихов и
 * типа линии.
 */
public class StrokeTestFrame extends JFrame
{
    private StrokeComponent canvas;
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    
    public StrokeTestFrame()
    {
        setTitle("StrokeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        canvas = new StrokeComponent();
        add(canvas, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        add(buttonPanel, BorderLayout.NORTH);
        
        ButtonGroup group1 = new ButtonGroup();
        makeCapButton("Butt Cap", BasicStroke.CAP_BUTT, group1);
        makeCapButton("Round Cap", BasicStroke.CAP_ROUND, group1);
        makeCapButton("Square Cap", BasicStroke.CAP_SQUARE, group1);
        
        ButtonGroup group2 = new ButtonGroup();
        makeJoinButton("Miter Join", BasicStroke.JOIN_MITER, group2);
        makeJoinButton("Bevel Join", BasicStroke.JOIN_BEVEL, group2);
        makeJoinButton("Round Join", BasicStroke.JOIN_ROUND, group2);
        
        ButtonGroup group3 = new ButtonGroup();
        makeDashButton("Solid Line", false, group3);
        makeDashButton("Dashed Line", true, group3);
    }

    /**
     * Создание переключателей для выбора стиля окончания штриха.
     * <p/>
     * @param label Надпись рядом с переключателем
     * @param style Стиль окончания штриха
     * @param group Группа переключателей
     */
    private void makeCapButton(String label, final int style, ButtonGroup group)
    {
        // Выбор первого переключателя в группе.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                canvas.setCap(style);
            }
        });
    }

    /**
     * Создание переключателей для выбора стиля окончания штриха.
     * <p/>
     * @param label Надпись рядом с переключателем.
     * @param style Стиль окончания штриха
     * @param group Группа переключателей
     */
    private void makeJoinButton(String label, final int style, ButtonGroup group)
    {
        // Выбор первого переключателя в группе.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                canvas.setJoin(style);
            }
        });
    }

    /**
     * Создание переключателей для выбора сплошной или пунктирной линии
     * <p/>
     * @param label Надпись рядом с переключателем
     * @param style false для сплошной линии, true для пунктирной
     * @param group Группа переключателей
     */
    private void makeDashButton(String label, final boolean style, ButtonGroup group)
    {
        // Выбор первого переключателя в группе.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                canvas.setDash(style);
            }
        });
    }
}

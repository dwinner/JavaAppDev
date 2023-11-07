import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Этот фрейм показывает сортируемый массив вместе с кнопками
 * для пошаговой анимации и для запуска без остановок.
 * @author dwinner@inbox.ru
 */
public class AnimationFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    
    public AnimationFrame()
    {
        ArrayComponent comp = new ArrayComponent();
        add(comp, BorderLayout.CENTER);
        final Sorter sorter = new Sorter(comp);
        JButton runButton = new JButton("Run");
        runButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sorter.setRun();
            }
        });
        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                sorter.setStep();
            }
        });
        JPanel buttons = new JPanel();
        buttons.add(runButton);
        buttons.add(stepButton);
        add(buttons, BorderLayout.NORTH);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Thread t = new Thread(sorter);
        t.start();
    }
}

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Этот фрейм содержит две кнопки для наполнения выпадающего списка
 * из отдельного потока. Кнопка Good использует очередь событий,
 * а кнопка Bad модифицирует выпадающий список напрямую.
 */
public class SwingThreadFrame  extends JFrame
{
    @SuppressWarnings("unchecked")
    public SwingThreadFrame() throws HeadlessException
    {
        setTitle("SwingThreadTest");
        final JComboBox combo = new JComboBox();
        combo.insertItemAt(Integer.MAX_VALUE, 0);
        combo.setPrototypeDisplayValue(combo.getItemAt(0));
        combo.setSelectedIndex(0);
        JPanel panel = new JPanel();
        JButton goodButton = new JButton("Good");
        goodButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Thread(new GoodWorkerRunnable(combo)).start();
            }
        });
        panel.add(goodButton);
        JButton badButton = new JButton("Bad");
        badButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new Thread(new BadWorkerRunnable(combo)).start();
            }
        });
        panel.add(badButton);
        panel.add(combo);
        add(panel);
        pack();
    }   
}

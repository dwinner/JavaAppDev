// Различные алгоритмы передачи фокуса ввода
import java.awt.BorderLayout;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FocusPolicyTest extends JFrame
{
    public FocusPolicyTest()
    {
        super("Focus Policy Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new JButton("Левая"), BorderLayout.WEST);

        // Добавляем эту кнопку второй, но она будет ниже двух других кнопок
        JButton button = new JButton("Сменить");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // При нажатии сменим алгоритм для окна
                setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy());
            }

        });
        getContentPane().add(button, BorderLayout.SOUTH);
        getContentPane().add(new JButton("Правая"), BorderLayout.EAST);
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FocusPolicyTest();
    }

}
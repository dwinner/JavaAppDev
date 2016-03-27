import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Фрейм, содержащий поля редактирования и текстовую область, защищенную от недопустимых слов.
 */
public class PermissionTestFrame extends JFrame
{
    private JTextField textField;
    private WordCheckTextArea textArea;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    public PermissionTestFrame() throws HeadlessException
    {
        setTitle("PermissionTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        textField = new JTextField(20);
        JPanel panel = new JPanel();
        panel.add(textField);
        JButton openButton = new JButton("Insert");
        panel.add(openButton);
        openButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                insertWords(textField.getText());
            }
        });

        add(panel, BorderLayout.NORTH);

        textArea = new WordCheckTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    /**
     * Включение текста в текстовую область. Если попытка оказалась неудачной, отображается
     * диалоговое окно.
     * <p/>
     * @param words Включаемый текст
     */
    public void insertWords(String words)
    {
        try
        {
            textArea.append(words + "\n");
        }
        catch (SecurityException secEx)
        {
            JOptionPane.showMessageDialog(this, "I am sorry, but I cannot do that");
        }
    }
}

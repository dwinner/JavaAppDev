
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Апплет, который может работать за пределами "песочницы" и при наличии соответствующих прав
 * доступа обращаться к локальным файлам.
 *
 * @version 1.11 2007-10-06
 * @author Cay Horstmann
 */
public class FileReadApplet extends JApplet
{
    private JTextField fileNameField;
    private JTextArea fileText;

    @Override
    public void init()
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                fileNameField = new JTextField(20);
                JPanel panel = new JPanel();
                panel.add(new JLabel("File name:"));
                panel.add(fileNameField);
                JButton openButton = new JButton("Open");
                panel.add(openButton);
                final ActionListener actionListener = new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        loadFile(fileNameField.getText());
                    }
                };
                openButton.addActionListener(actionListener);
                fileNameField.addActionListener(actionListener);
                add(panel, "North");
                fileText = new JTextArea();
                add(new JScrollPane(fileText), "Center");
            }
        });
    }

    /**
     * Загрузка содержимого файла в текстовую область.
     *
     * @param fileName Имя файла
     */
    public void loadFile(String fileName)
    {
        fileText.setText("");
        try (Scanner in = new Scanner(new FileReader(fileName)))
        {
            while (in.hasNextLine())
            {
                fileText.append(in.nextLine() + "\n");
            }
        }
        catch (IOException ioEx)
        {
            fileText.append(ioEx + "\n");
        }
        catch (SecurityException secEx)
        {
            fileText.append("I am sorry, but I cannot do that.\n");
            fileText.append(secEx + "\n");
        }
    }
}
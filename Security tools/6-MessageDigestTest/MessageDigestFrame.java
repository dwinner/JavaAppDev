import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Фрейм, содержащий меню, посредством которого запускается процесс
 * вычисления профиля сообщения для текстового файла или содержимого
 * текстовой области, переключатели для выбора алгоритма SHA-1 или MD5,
 * текстовую область, а также поле редактирования для отображения
 * профиля сообщения.
 */
public class MessageDigestFrame extends JFrame
{
    private JTextArea message = new JTextArea();
    private JTextField digest = new JTextField();
    private MessageDigest currentAlgorithm;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    public MessageDigestFrame() throws HeadlessException
    {
        setTitle("MessageDigestTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JPanel panel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        addRadioButton(panel, "SHA-1", group);
        addRadioButton(panel, "MD5", group);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(message), BorderLayout.CENTER);
        add(digest, BorderLayout.SOUTH);
        digest.setFont(new Font("Monospaced", Font.PLAIN, 12));

        setAlgorithm("SHA-1");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem fileDigestItem = new JMenuItem("File digest");
        fileDigestItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                loadFile();
            }
        });
        menu.add(fileDigestItem);

        JMenuItem textDigestItem = new JMenuItem("Text area digest");
        textDigestItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String m = message.getText();
                computeDigest(m.getBytes());
            }
        });
        menu.add(textDigestItem);

        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    /**
     * Добавление переключателей, предназначенных для выбора алгоритма.
     * @param c Контейнер для размещения переключателей
     * @param name Имя алгоритма
     * @param group Группа переключателей
     */
    private void addRadioButton(Container c, final String name, ButtonGroup group)
    {
        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setAlgorithm(name);
            }
        };
        JRadioButton b = new JRadioButton(name, group.getButtonCount() == 0);
        c.add(b);
        group.add(b);
        b.addActionListener(listener);
    }

    /**
     * Установка алгоритма, используемого для вычисления дайджеста.
     * @param alg Имя алгоритма
     */
    public void setAlgorithm(String alg)
    {
        try
        {
            currentAlgorithm = MessageDigest.getInstance(alg);
            digest.setText("");
        }
        catch (NoSuchAlgorithmException e)
        {
            digest.setText("" + e);
        }
    }

    /**
     * Загрузка файла и вычисление дайджеста сообщения.
     */
    public void loadFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));

        int r = chooser.showOpenDialog(this);
        if (r == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                String name = chooser.getSelectedFile().getAbsolutePath();
                computeDigest(loadBytes(name));
            }
            catch (IOException ioEx)
            {
                JOptionPane.showMessageDialog(null, ioEx);
            }
        }
    }

    /**
     * Загрузка файла.
     * @param name Имя файла
     * @return Байтовый массив с данными из файла.
     * @throws IOException
     */
    public byte[] loadBytes(String name) throws IOException
    {
        try (FileInputStream in = new FileInputStream(name))
        {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int ch;
            while ((ch = in.read()) != -1)
                buffer.write(ch);
            return buffer.toByteArray();
        }
    }

    /**
     * Вычисление дайджеста сообщения для байтового массива
     * и отображение его в поле редактирования.
     * @param b Байтовый массив, для которого вычисляется дайджест.
     */
    public void computeDigest(byte[] b)
    {
        currentAlgorithm.reset();
        currentAlgorithm.update(b);
        byte[] hash = currentAlgorithm.digest();
        String d = "";
        for (int i = 0; i < hash.length; i++)
        {
            int v = hash[i] & 0xFF;
            if (v < 16)
                d += "0";
            d += Integer.toString(v, 16).toUpperCase() + " ";
        }
        digest.setText(d);
    }
}

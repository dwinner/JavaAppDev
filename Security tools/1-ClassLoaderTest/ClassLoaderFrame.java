import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.*;

/**
 * Фрейм с двумя полями редактирования для ввода имени загружаемого файла и ключа шифрования.
 */
public class ClassLoaderFrame extends JFrame
{
    private JTextField keyField = new JTextField("3", 4);
    private JTextField nameField = new JTextField("Calculator", 30);
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public ClassLoaderFrame() throws HeadlessException
    {
        setTitle("ClassLoaderTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        add(new JLabel("Class"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(nameField, new GBC(1, 0).setWeight(100, 0).setAnchor(GBC.WEST));
        add(new JLabel("Key"), new GBC(0, 1).setWeight(100, 0).setAnchor(GBC.WEST));
        add(keyField, new GBC(1, 1).setWeight(100, 0).setAnchor(GBC.WEST));
        JButton loadButton = new JButton("Load");
        add(loadButton, new GBC(0, 2, 2, 1));
        loadButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                runClass(nameField.getText(), keyField.getText());
            }
        });
        pack();
    }

    /**
     * Запуск метода main() указанного класса.
     * <p/>
     * @param name Имя класса
     * @param key  Ключ шифрования для файла класса.
     */
    public void runClass(String name, String key)
    {
        try
        {
            ClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> c = loader.loadClass(name);
            Method m = c.getMethod("main", String[].class);
            m.invoke(null, (Object) new String[]
                {
                });
        }
        catch (ClassNotFoundException | NoSuchMethodException | SecurityException |
               IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }
}

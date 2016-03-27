import java.awt.EventQueue;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.jnlp.*;

/**
 * Калькулятор с поддержкой хронологии. Может быть
 * доставлен средствами Java Web Start.
 * @version 1.02 2007-06-12
 * @author Cay Horstmann
 */
public class WebStartCalculator
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                CalculatorFrame frame = new CalculatorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий панель калькулятора и меню
 * для чтения и записи предыдущих результатов.
 */
class CalculatorFrame extends JFrame
{
    private CalculatorPanel panel;
   
    CalculatorFrame()
    {
        setTitle();
        panel = new CalculatorPanel();
        add(panel);

        JMenu fileMenu = new JMenu("File");

        JMenuItem openItem = fileMenu.add("Open");
        openItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                open();
            }
        });

        JMenuItem saveItem = fileMenu.add("Save");
        saveItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                save();
            }
        });
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        pack();
    }

    /**
     * Извлечение заголовка из постоянного хранилища.
     * Если ранее заголовок не был задан, он запрашивается у пользователя.
     */
    public void setTitle()
    {
        try
        {
            String title = null;

            BasicService basic = (BasicService) ServiceManager.lookup("javax.jnlp.BasicService");
            URL codeBase = basic.getCodeBase();

            PersistenceService service = 
                (PersistenceService) ServiceManager.lookup("javax.jnlp.PersistenceService");
            URL key = new URL(codeBase, "title");

            try
            {
                FileContents contents = service.get(key);
                InputStream in = contents.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                title = reader.readLine();
            }
            catch (FileNotFoundException e)
            {
                title = JOptionPane.showInputDialog("Please supply a frame title:");
                if (title == null) return;

                service.create(key, 100);
                FileContents contents = service.get(key);
                OutputStream out = contents.getOutputStream(true);
                PrintStream printOut = new PrintStream(out);
                printOut.print(title);
            }
            setTitle(title);
        }
        catch (UnavailableServiceException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (MalformedURLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * Открытие файла с хронологией и обновление отображаемых данных.
     */
    public void open()
    {
        try
        {
            FileOpenService service = 
                (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
            FileContents contents = service.openFileDialog(".", new String[] { "txt" });

            JOptionPane.showMessageDialog(this, contents.getName());
            if (contents != null)
            {
                InputStream in = contents.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null)
                {
                    panel.append(line);
                    panel.append("\n");
                }
            }
        }
        catch (UnavailableServiceException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    /**
     * Сохранение хронологии в файле.
     */
    public void save()
    {
        try
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream printOut = new PrintStream(out);
            printOut.print(panel.getText());
            InputStream data = new ByteArrayInputStream(out.toByteArray());
            FileSaveService service =
                (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");
            service.saveFileDialog(".", new String[] { "txt" }, data, "calc.txt");
        }
        catch (UnavailableServiceException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }

}
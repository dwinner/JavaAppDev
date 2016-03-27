// Проверка загрузки системы меню из XML-файла
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JFrame;

public class TestXMLMenuLoader extends JFrame
{
    public TestXMLMenuLoader()
    {
        super("Test XML Menu loader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Открываем XML-файл с описанием меню
        try
        {
            InputStream stream = new FileInputStream("MenuBar.xml");
            // Загружаем меню
            XMLMenuLoader loader = new XMLMenuLoader(stream);
            loader.parse();
            // Устанавливаем строку меню
            setJMenuBar(loader.getMenuBar("mainMenu"));
            // Быстрое присоединение слушателя
            loader.addActionListener("exit", new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent ae)
                {
                    System.exit(0);
                }

            });
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new TestXMLMenuLoader();
    }

}
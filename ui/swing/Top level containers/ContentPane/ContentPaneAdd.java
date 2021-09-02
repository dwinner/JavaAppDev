// Замена панели содержимого
import javax.swing.*;

public class ContentPaneAdd extends JFrame
{    
    public ContentPaneAdd()
    {
        super("ContentPaneAdd");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Панель с двумя кнопками
        JPanel contents = new JPanel();
        contents.add(new JButton("Один"));
        contents.add(new JButton("Два"));
        setContentPane(contents);
        setSize(200, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ContentPaneAdd();
    }   
}
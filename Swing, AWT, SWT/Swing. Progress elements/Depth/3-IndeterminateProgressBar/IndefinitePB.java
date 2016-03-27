// Индикаторы в состоянии "неопределенности"
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class IndefinitePB extends JFrame
{
    public IndefinitePB()
    {
        super("IndefinitePB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Неопределенный индикатор
        JProgressBar progress = new JProgressBar(0, 100);
        progress.setIndeterminate(true);
        progress.setStringPainted(true);
        
        // Добавляем его в окно и выводим на экран
        JPanel contents = new JPanel();
        contents.add(new JLabel("Соединение:"));
        contents.add(progress);
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new IndefinitePB();
    }
}
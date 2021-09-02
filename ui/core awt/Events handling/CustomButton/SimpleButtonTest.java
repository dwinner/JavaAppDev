
/**
 * �������� � ����������� ����������� �������
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

// ������� (�������) ��� ������
class ButtonPressEvent extends EventObject
{
    ButtonPressEvent(Object source)
    {   // �����������. ������� ������ �������� �������
        super(source);
    }

}

// ��������� ��������� ������� ������� ������
interface ButtonPressListener extends EventListener
{
    // ������ ����� ����� ���������� ��� ������� ������
    void buttonPressed(ButtonPressEvent bpe);

}

/**
 * ������ ���������� � ����������� ��������
 * <p/>
 * @author dwinner@inbox.ru
 */
class SimpleButton extends JComponent
{
    private static final int INITIAL_CAPACITY = 32;
    // ������ ����������
    private ArrayList<ButtonPressListener> lListenerList = new ArrayList<>(INITIAL_CAPACITY);
    // ���� ������ ������� �� ��� ������ �����
    private ButtonPressEvent event = new ButtonPressEvent(this);

    // ����������� - ������������ � ������ ��������� ������� �� ����
    SimpleButton()
    {
        addMouseListener(new PressL());
        // ������� ������� ����������
        setPreferredSize(new Dimension(100, 100));
    }

    // ������������ ��������� ������� ������
    public void addButtonPressListener(ButtonPressListener bpl)
    {
        lListenerList.add(bpl);
    }

    // ����������� ��������� ������� ������
    public void removeButtonPressListener(ButtonPressListener bpl)
    {
        lListenerList.remove(bpl);
    }

    // ���������� ������
    @Override
    public void paintComponent(Graphics g)
    {
        // ������ ������� ������
        g.setColor(Color.green);
        g.fillRect(0, 0, getWidth(), getHeight());
        // �����
        g.setColor(Color.black);
        g.draw3DRect(0, 0, getWidth(), getHeight(), true);
    }

    // ���������� ���������� � �������
    protected void fireButtonPressed()
    {
        Iterator<ButtonPressListener> i = lListenerList.iterator();
        while (i.hasNext())
        {
            i.next().buttonPressed(event);
        }
    }

    // ���������� �����, ������ �� �������� ����
    private class PressL extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent me)
        {   // ������� ���� � ������� ������
            // ��������� ����������
            fireButtonPressed();
        }

    }

}

// ��������� ������� ������ ����������
public class SimpleButtonTest extends JFrame
{
    public SimpleButtonTest()
    {
        super("Simple Button Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ������ � ������������ ����������
        SimpleButton button = new SimpleButton();
        // ��������� �����
        button.addButtonPressListener(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(ButtonPressEvent bpe)
            {
                System.out.println(bpe);
            }

        });

        // ���������� �����
        button.addButtonPressListener(new ButtonL());
        // ������� ������ � ����
        JPanel contents = new JPanel();
        contents.add(button);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    private class ButtonL implements ButtonPressListener
    {
        @Override
        public void buttonPressed(ButtonPressEvent bpe)
        {
            System.out.println(bpe);
        }

    }

    public static void main(String[] args)
    {
        new SimpleButtonTest();
    }

}
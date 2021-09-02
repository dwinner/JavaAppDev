
/**
 * LowLevelEvents.java. ���������� �� ��������� ��������������� ���������
 */
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LowLevelEvents extends JFrame
{
    private JTextArea out;  // ���� �� ����� �������� ����������
    private JButton button;

    public LowLevelEvents()
    {
        super("LowLevelEvents");
        // ��� �������� ���� - �����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ��������� ����
        getContentPane().add(new JScrollPane(out = new JTextArea()));
        button = new JButton("�������� �������");
        getContentPane().add(button, BorderLayout.SOUTH);
        // ������������ ������ ���������
        OutListener ol = new OutListener();
        button.addKeyListener(ol);
        button.addMouseListener(ol);
        button.addMouseMotionListener(ol);
        button.addFocusListener(ol);
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * ���������� ����� - ��������� �������
     */
    private class OutListener
       implements MouseListener,
                  KeyListener,
                  MouseMotionListener,
                  MouseWheelListener,
                  FocusListener
    {
        private void dispatch(AWTEvent e)
        {
            out.append(e.toString() + "\n\n");
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void keyTyped(KeyEvent e)
        {
            dispatch(e);
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            dispatch(e);
        }

        @Override
        public void keyReleased(KeyEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mouseMoved(MouseEvent e)
        {
            dispatch(e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            dispatch(e);
        }

        @Override
        public void focusGained(FocusEvent e)
        {
            dispatch(e);
        }

        @Override
        public void focusLost(FocusEvent e)
        {
            dispatch(e);
        }

    }

    public static void main(String[] args)
    {
        new LowLevelEvents();
    }

}
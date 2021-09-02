// ��������� ������ � ������� �������� � ��������� ����������� Swing
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.undo.UndoManager;

public class UndoAndRedo extends JFrame
{
    // ��������� ������/������� ��������
    private UndoManager undoManager = new UndoManager();

    public UndoAndRedo()
    {
        super("Undo and Redo operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������ ������������
        JToolBar toolBar = new JToolBar();
        toolBar.add(new UndoAction());
        toolBar.add(new RedoAction());
        // ��������� ����
        JTextArea textArea = new JTextArea();
        // ��������� ��������� ��������
        textArea.getDocument().addUndoableEditListener(undoManager);
        // ��������� ���������� � ����
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(textArea));
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    // ������� ��� ������ ��������
    private class UndoAction extends AbstractAction
    {
        UndoAction()
        {
            // ��������� �������
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("undo.gif"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (undoManager.canUndo())
            {
                undoManager.undo();
            }
        }
    }

    // ������� ���������� ��������
    private class RedoAction extends AbstractAction
    {
        RedoAction()
        {
            // ��������� �������
            putValue(AbstractAction.SMALL_ICON, new ImageIcon("redo.jpg"));
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (undoManager.canRedo())
            {
                undoManager.redo();
            }
        }
    }

    public static void main(String[] args)
    {
        new UndoAndRedo();
    }
}
// Поддержка отмены и повтора операций в текстовых компонентах Swing
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.undo.UndoManager;

public class UndoAndRedo extends JFrame
{
    // Поддержка отмены/повтора операций
    private UndoManager undoManager = new UndoManager();

    public UndoAndRedo()
    {
        super("Undo and Redo operations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Панель инструментов
        JToolBar toolBar = new JToolBar();
        toolBar.add(new UndoAction());
        toolBar.add(new RedoAction());
        // Текстовое поле
        JTextArea textArea = new JTextArea();
        // Добавляем слушателя операций
        textArea.getDocument().addUndoableEditListener(undoManager);
        // Добавляем компоненты в окно
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(textArea));
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    // Команда для отмены операции
    private class UndoAction extends AbstractAction
    {
        UndoAction()
        {
            // Настройка команды
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

    // Команда повторения операции
    private class RedoAction extends AbstractAction
    {
        RedoAction()
        {
            // Настройка команды
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
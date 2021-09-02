// �������� �������� JSpinner �� ������ �������
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerLabelEditor extends JFrame
{
    // ������ ��� ��������
    private String[] data =
    {
        "�������",
        "�������",
        "�����"
    };

    public SpinnerLabelEditor()
    {
        super("Spinner label editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� �������
        JSpinner spinner = new JSpinner(new SpinnerListModel(data));
        // ������������ ��� ��������
        LabelEditor editor = new LabelEditor();
        spinner.setEditor(editor);
        // ������������ ���������
        spinner.addChangeListener(editor);
        // ������� ���� �� �����
        JPanel contents = new JPanel();
        contents.add(spinner);
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    /**
     * ����������� �������� ��� ��������
     */
    private class LabelEditor extends JLabel implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent e)
        {
            // �������� �������
            JSpinner spinner = (JSpinner) e.getSource();
            // �������� ������� �������
            Object value = spinner.getValue();
            // ������������� ����� ��������
            if (value.equals(data[0]))
            {
                setText("<html><h2><font color=\"red\">" + value + "</font></h2>");
            }
            else if (value.equals(data[1]))
            {
                setText("<html><h3><font color=\"green\">" + value + "</font></h2>");
            }
            else if (value.equals(data[2]))
            {
                setText("<html><h2><font color=\"blue\">" + value + "</font></h2>");
            }
            else
            {
                setText(value.toString());
            }
        }
        // ������ ���������

        @Override
        public Dimension getPreferredSize()
        {
            return new Dimension(100, 30);
        }
    }

    public static void main(String[] args)
    {
        new SpinnerLabelEditor();
    }
}
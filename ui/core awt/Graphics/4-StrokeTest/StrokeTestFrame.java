import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * ����� � ������� �������������� ��� ������ ����� ��������� ������, ����� ����������� ������� �
 * ���� �����.
 */
public class StrokeTestFrame extends JFrame
{
    private StrokeComponent canvas;
    private JPanel buttonPanel;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    
    public StrokeTestFrame()
    {
        setTitle("StrokeTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        canvas = new StrokeComponent();
        add(canvas, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        add(buttonPanel, BorderLayout.NORTH);
        
        ButtonGroup group1 = new ButtonGroup();
        makeCapButton("Butt Cap", BasicStroke.CAP_BUTT, group1);
        makeCapButton("Round Cap", BasicStroke.CAP_ROUND, group1);
        makeCapButton("Square Cap", BasicStroke.CAP_SQUARE, group1);
        
        ButtonGroup group2 = new ButtonGroup();
        makeJoinButton("Miter Join", BasicStroke.JOIN_MITER, group2);
        makeJoinButton("Bevel Join", BasicStroke.JOIN_BEVEL, group2);
        makeJoinButton("Round Join", BasicStroke.JOIN_ROUND, group2);
        
        ButtonGroup group3 = new ButtonGroup();
        makeDashButton("Solid Line", false, group3);
        makeDashButton("Dashed Line", true, group3);
    }

    /**
     * �������� �������������� ��� ������ ����� ��������� ������.
     * <p/>
     * @param label ������� ����� � ��������������
     * @param style ����� ��������� ������
     * @param group ������ ��������������
     */
    private void makeCapButton(String label, final int style, ButtonGroup group)
    {
        // ����� ������� ������������� � ������.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                canvas.setCap(style);
            }
        });
    }

    /**
     * �������� �������������� ��� ������ ����� ��������� ������.
     * <p/>
     * @param label ������� ����� � ��������������.
     * @param style ����� ��������� ������
     * @param group ������ ��������������
     */
    private void makeJoinButton(String label, final int style, ButtonGroup group)
    {
        // ����� ������� ������������� � ������.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                canvas.setJoin(style);
            }
        });
    }

    /**
     * �������� �������������� ��� ������ �������� ��� ���������� �����
     * <p/>
     * @param label ������� ����� � ��������������
     * @param style false ��� �������� �����, true ��� ����������
     * @param group ������ ��������������
     */
    private void makeDashButton(String label, final boolean style, ButtonGroup group)
    {
        // ����� ������� ������������� � ������.
        boolean selected = group.getButtonCount() == 0;
        JRadioButton button = new JRadioButton(label, selected);
        buttonPanel.add(button);
        group.add(button);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                canvas.setDash(style);
            }
        });
    }
}

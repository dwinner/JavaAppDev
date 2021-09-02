// �������� ������� ���� � Swing
import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuSystem extends JFrame
{    
    public MenuSystem()
    {
        super("Menu System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �������� ���� JMenuBar
        JMenuBar menuBar = new JMenuBar();
        // ��������� � ��� ���������� ����
        menuBar.add(createFileMenu());
        menuBar.add(createWhoMenu());
        // � ������������� �� � �������� ���� ������ ����
        setJMenuBar(menuBar);
        // ������� ���� �� �����
        setSize(300, 200);
        setVisible(true);
    }
    
    /**
     * ������� ���� "����"
     * @return ������ �� ��������� ����
     */
    private JMenu createFileMenu()
    {
        // ������� ���������� ����, ������� ����� ��������� ������� �������� ����
        JMenu file = new JMenu("File");
        // ������� ���� �� �������
        JMenuItem open = new JMenuItem("�������", new ImageIcon("open.gif"));
        // ������� ���� - �������
        JMenuItem exit = new JMenuItem(new ExitAction());
        // ��������� ��� � ����
        file.add(open);
        file.addSeparator();    // �����������
        file.add(exit);
        return file;
    }
    
    /**
     * ������� �������� ����
     * @return ������ �� ��������� ����
     */
    private JMenu createWhoMenu()
    {
        // ������� ���������� ����
        JMenu who = new JMenu("��� ��?");
        // �������� ���� - ������
        JCheckBoxMenuItem clever = new JCheckBoxMenuItem("�����");
        JCheckBoxMenuItem smart = new JCheckBoxMenuItem("��������");
        JCheckBoxMenuItem tender = new JCheckBoxMenuItem("������");
        // �������� ���� - �������������
        JRadioButtonMenuItem male = new JRadioButtonMenuItem("�������");
        JRadioButtonMenuItem female = new JRadioButtonMenuItem("�������");
        // ���������� ������������� � ���������� ������
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        // ��������� ��� � ����
        who.add(clever);
        who.add(smart);
        who.add(tender);
        // ����������� ����� ������� � ����
        who.add(new JSeparator());
        who.add(male);
        who.add(female);
        return who;
    }
    
    /**
     * ������� ������ �� ����������
     */
    private class ExitAction extends AbstractAction
    {
        ExitAction()
        {
            putValue(Action.NAME, "�����");
        }
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args)
    {
        new MenuSystem();
    }

}
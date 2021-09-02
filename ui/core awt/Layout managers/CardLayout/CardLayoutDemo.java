// ������������� CardLayout.
import java.awt.*;
import java.awt.event.*;

public class CardLayoutDemo extends Frame implements ActionListener, MouseListener
{
    private Checkbox Win98, winNT, solaris, mac;
    private Panel osCards;
    private CardLayout cardLO;
    private Button Win, Other;

    public CardLayoutDemo(String title)
    {
        super(title);
        Win = new Button("Windows");
        Other = new Button("Other");
        add(Win);
        add(Other);

        cardLO = new CardLayout();
        osCards = new Panel();
        osCards.setLayout(cardLO);  // ���������� panel-���������� ��� card-����������

        Win98 = new Checkbox("Windows 98", null, true);
        winNT = new Checkbox("Windows NT");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("MacOS");

        // �������� � ������ Windows ������
        Panel winPan = new Panel();
        winPan.add(Win98);
        winPan.add(winNT);

        // �������� � ������ ������ OS-������
        Panel otherPan = new Panel();
        otherPan.add(solaris);
        otherPan.add(mac);

        // �������� ������ � ������ ������ ����
        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");
        // �������� ����� � ������� ������ �������
        add(osCards);

        // ���������������� ��� ������ action-�������
        Win.addActionListener(this);
        Other.addActionListener(this);

        // �������������� ������� ����
        addMouseListener(this);
    }

    // ���� ���� � ������
    public void mousePressed(MouseEvent me)
    {
        cardLO.next(osCards);
    }

    // ���������� ������ ���������� ��� ������ ������� MouseListener
    public void mouseClicked(MouseEvent me) { }
    public void mouseEntered(MouseEvent me) { }
    public void mouseExited(MouseEvent me) { }
    public void mouseReleased(MouseEvent me) { }

    public void actionPerformed(ActionEvent ae)
    {
        cardLO.show(osCards, ae.getSource() == Win ? "Windows" : "Other");
    }

    public static void main(String args[])
    {
        CardLayoutDemo cld = new CardLayoutDemo("CardLayout manager");
        cld.setSize(300, 250);
        cld.setVisible(true);
        cld.addWindowListener(new WindowAdapter()
        {
            @Override public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }
        });
    }
}
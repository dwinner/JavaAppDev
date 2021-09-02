// Демонстрирует CardLayout.
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
        osCards.setLayout(cardLO);  // установить panel-компоновку для card-компоновки

        Win98 = new Checkbox("Windows 98", null, true);
        winNT = new Checkbox("Windows NT");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("MacOS");

        // добавить в панель Windows флажки
        Panel winPan = new Panel();
        winPan.add(Win98);
        winPan.add(winNT);

        // добваить в панель другие OS-флажки
        Panel otherPan = new Panel();
        otherPan.add(solaris);
        otherPan.add(mac);

        // добавить панели к панели колоды карт
        osCards.add(winPan, "Windows");
        osCards.add(otherPan, "Other");
        // добавить карты к главной панели апплета
        add(osCards);

        // регистрироваться для приема action-событий
        Win.addActionListener(this);
        Other.addActionListener(this);

        // регистрировать события мыши
        addMouseListener(this);
    }

    // цикл карт в панели
    public void mousePressed(MouseEvent me)
    {
        cardLO.next(osCards);
    }

    // обеспечить пустые реализации для других методов MouseListener
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
// Иллюстрирует меню.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

// создать подкласс для Frame
class MenuFrame extends Frame
{
    String msg = "";
    CheckboxMenuItem debug, test;

    MenuFrame(String title)
    {
        super(title);

        // создать строку главного меню и добавить ее во фрейм
        MenuBar mbar = new MenuBar();
        setMenuBar(mbar);

        // создать элементы меню
        Menu file = new Menu("File");
        MenuItem item1, item2, item3, item4, item5;
        file.add(item1 = new MenuItem("New..."));
        file.add(item2 = new MenuItem("Open..."));
        file.add(item3 = new MenuItem("Close"));
        file.add(item4 = new MenuItem("-"));
        file.addSeparator();
        file.add(item5 = new MenuItem("Quit"));
        mbar.add(file);

        Menu edit = new Menu("Edit");
        MenuItem item6, item7, item8, item9;
        edit.add(item6 = new MenuItem("Cut"));
        edit.add(item7 = new MenuItem("Copy"));
        edit.add(item8 = new MenuItem("Paste"));
        edit.add(item9 = new MenuItem("-"));
        edit.addSeparator();
        Menu sub = new Menu("Special");
        MenuItem item10, item11, item12;
        sub.add(item10 = new MenuItem("First"));
        sub.add(item11 = new MenuItem("Second"));
        sub.add(item12 = new MenuItem("Third"));
        edit.add(sub);
        edit.addSeparator();

        // это элементы меню с метками.
        debug = new CheckboxMenuItem("Debug");
        edit.add(debug);
        test = new CheckboxMenuItem("Testing");
        edit.add(test);

        mbar.add(edit);

        // создать объект для обработки action- и item-событий
        MyMenuHandler handler = new MyMenuHandler(this);
        // регистрировать его для приема этих событий
        item1.addActionListener(handler);
        item2.addActionListener(handler);
        item3.addActionListener(handler);
        item4.addActionListener(handler);
        item5.addActionListener(handler);
        item6.addActionListener(handler);
        item7.addActionListener(handler);
        item8.addActionListener(handler);
        item9.addActionListener(handler);
        item10.addActionListener(handler);
        item11.addActionListener(handler);
        item12.addActionListener(handler);

        debug.addItemListener(handler);
        test.addItemListener(handler);
        // создать объект для обработки window-событий
        MyWindowAdapter adapter = new MyWindowAdapter(this);
        addWindowListener(adapter);
    }

    @Override public void paint(Graphics g)
    {
        g.drawString(msg, 10, 200);

        if (debug.getState())
            g.drawString("Debug is on.", 10, 220);
        else
            g.drawString("Debug is off.", 10, 220);

        if (test.getState())
            g.drawString("Testing is on.", 10, 240);
        else
            g.drawString("Testing is off.", 10, 240);
    }
}

class MyWindowAdapter extends WindowAdapter
{
    MenuFrame menuFrame;

    public MyWindowAdapter(MenuFrame menuFrame)
    {
        this.menuFrame = menuFrame;
    }

    @Override
    public void windowClosing(WindowEvent we)
    {
        menuFrame.setVisible(false);
    }
}

class MyMenuHandler implements ActionListener, ItemListener
{
    MenuFrame menuFrame;

    public MyMenuHandler(MenuFrame menuFrame)
    {
        this.menuFrame = menuFrame;
    }

    // Обработка action-событий.
    public void actionPerformed(ActionEvent ae)
    {
        String msg = "You selected ";
        String arg = (String) ae.getActionCommand();
        if (arg.equals("New..."))
            msg += "New.";
        else if (arg.equals("Open..."))
            msg += "Open.";
        else if (arg.equals("Close"))
            msg += "Close.";
        else if (arg.equals("Quit..."))
            msg += "Quit.";
        else if (arg.equals("Edit"))
            msg += "Edit.";
        else if (arg.equals("Cut"))
            msg += "Cut.";
        else if (arg.equals("Copy"))
            msg += "Copy.";
        else if (arg.equals("Paste"))
            msg += "Paste.";
        else if (arg.equals("First"))
            msg += "First.";
        else if (arg.equals("Second"))
            msg += "Second.";
        else if (arg.equals("Third"))
            msg += "Third.";
        else if (arg.equals("Debug"))
            msg += "Debug.";
        else if (arg.equals("Testing"))
            msg += "Testing.";
        menuFrame.msg = msg;
        menuFrame.repaint();
    }

    // обработка item-событий
    public void itemStateChanged(ItemEvent ie)
    {
        menuFrame.repaint();
    }
}

// создать frame-окно
public class MenuDemo extends Applet
{
    Frame f;

    @Override public void init()
    {
        f = new MenuFrame("Menu Demo");
        int width = Integer.parseInt(getParameter("width"));
        int height = Integer.parseInt(getParameter("height"));

        setSize(new Dimension(width, height));

        f.setSize(width, height);
        f.setVisible(true);
    }

    @Override public void start()
    {
        f.setVisible(true);
    }

    @Override public void stop()
    {
        f.setVisible(false);
    }
}
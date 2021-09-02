// Демонстрирует текстовое поле.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class TextFieldDemo extends Applet implements ActionListener
{
    TextField name, pass;

    @Override
    public void init()
    {
        Label namep = new Label("Name: ", Label.RIGHT);
        Label passp = new Label("Password: ", Label.RIGHT);
        name = new TextField(12);
        pass = new TextField(8);
        pass.setEchoChar('?');

        add(namep);
        add(name);
        add(passp);
        add(pass);

        // регистрироваться для получения action-событий
        name.addActionListener(this);
        pass.addActionListener(this);
    }

    // клавиша <Enter>, нажатая пользователем
    public void actionPerformed(ActionEvent ae)
    {
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawString("Name: " + name.getText(), 6, 60);
        g.drawString("Selected text in name: " + name.getSelectedText(), 6, 80);
        g.drawString("Password: " + pass.getText(), 6, 100);
    }
}
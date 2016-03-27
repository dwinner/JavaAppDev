/**
   Апплет для поддержки закладок.
   @version 1.22 2004-05-07
   @author Cay Horstmann
*/

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.net.*;
import javax.swing.*;

public class Bookmark extends JApplet 
{
    public void init()
    {
        Box box = Box.createVerticalBox();
        ButtonGroup group = new ButtonGroup();

        int i = 1;
        String urlString;

        // Чтение параметров link.n
        while ((urlString = getParameter("link." + i)) != null)
        {
            try
            {
                final URL url = new URL(urlString);
                // Создание переключателя для каждой ссылки.
                JRadioButton button = new JRadioButton(urlString);
                box.add(button);
                group.add(button);
                // Выбор переключателя приводит к отображению
                // в правом фрейме соответствующего документа.
                button.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent event)
                    {
                        AppletContext context = getAppletContext();
                        context.showDocument(url, "right");
                    }
                });
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            i++;
        }
        add(box);
    }
}
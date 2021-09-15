import java.applet.AppletContext;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

class Lware extends Frame
{

    private AppletContext appletContext;
    static final char anurl[] = {
        'h', 't', 't', 'p', ':', '/', '/', 'w', 'w', 'w', 
        '.', 'a', 'n', 'f', 'y', 'j', 'a', 'v', 'a', '.', 
        'c', 'o', 'm'
    };

    public Lware(AppletContext appletcontext, Label label)
    {
        appletContext = appletcontext;
        setFont(new Font("System", 1, 12));
        setLayout(new GridLayout(5, 1, 1, 2));
        setBackground(Color.lightGray);
        setForeground(Color.black);
        Label label1 = new Label("You can connect to my java applets page");
        Label label2 = new Label("if you want to download my latest applets!");
        add(label);
        add(label1);
        add(label2);
        Button button = new Button("Connect");
        add(button);
        Button button1 = new Button("Cancel");
        add(button1);
        pack();
    }

    public final void show()
    {
        super.show();
    }

    public final boolean action(Event event, Object obj)
    {
        if(obj.equals("Cancel"))
        {
            hide();
            return true;
        }
        if(obj.equals("Connect"))
        {
            hide();
            URL url = null;
            appletContext.showStatus("Linking with anfyjava.com");
            try
            {
                url = new URL(new String(anurl));
            }
            catch(MalformedURLException _ex)
            {
                appletContext.showStatus("Error linking www.anfyjava.com");
            }
            appletContext.showDocument(url, "_blank");
            return true;
        } else
        {
            return false;
        }
    }

    public final boolean handleEvent(Event event)
    {
        if(event.id == 203 || event.id == 201)
        {
            hide();
            return true;
        } else
        {
            return super.handleEvent(event);
        }
    }

}
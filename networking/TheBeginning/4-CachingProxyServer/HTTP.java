// Панель proxy-сервера.
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class HTTP extends Applet implements LogMessage, ActionListener
{
    private int m_port = 80;
    private String m_docroot = "c:\\www";
    private httpd m_httpd;
    private TextArea m_log;
    private Label status;
    private final String PARAM_port = "port";
    private final String PARAM_docroot = "docroot";

    public HTTP()
    {
    }

    public void init()
    {
        setBackground(Color.white);
        String param;

        // port: номер порта прослушивания
        param = getParameter(PARAM_port);
        if (param != null)
        {
            m_port = Integer.parseInt(param);
        }

        // docroot: путь Web-документа
        param = getParameter(PARAM_docroot);
        if (param != null)
        {
            m_docroot = param;
        }

        setLayout(new BorderLayout());

        Label lab = new Label("Java HTTPD");

        lab.setFont(new Font("SansSerif", Font.BOLD, 18));
        add("North", lab);
        m_log = new TextArea("", 24, 80);
        add("Center", m_log);
        Panel p = new Panel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        add("South", p);
        Button bstart = new Button("Start");
        bstart.addActionListener(this);
        p.add(bstart);
        Button bstop = new Button("Stop");
        bstop.addActionListener(this);
        p.add(bstop);
        status = new Label("raw");
        status.setForeground(Color.green);
        status.setFont(new Font("SansSerif", Font.BOLD, 10));
        p.add(status);
        m_httpd = new httpd(m_port, m_docroot, this);
    }

    public void destroy()
    {
        stop();
    }

    public void paint(Graphics g)
    {
    }

    public void start()
    {
        m_httpd.start();
        status.setText("Running ");
        clear_log("Log started on " + new Date() + "\n");
    }

    public void stop()
    {
        m_httpd.stop();
        status.setText("Stopped ");
    }

    public void actionPerformed(ActionEvent ae)
    {
        String label = ae.getActionCommand();
        if (label.equals("Start"))
        {
            start();
        }
        else
        {
            stop();
        }
    }

    public void clear_log(String msg)
    {
        m_log.setText(msg + "\n");
    }

    public void log(String msg)
    {
        m_log.append(msg);
        status.setText(m_httpd.hits_served + " hits ("
            + (m_httpd.bytes_served / 1024) + "K), "
            + m_httpd.files_in_cache + " cached files ("
            + (m_httpd.bytes_in_cache / 1024) + "K), "
            + m_httpd.hits_to_cache + " cached hits");
        status.setSize(status.getPreferredSize());
    }
}
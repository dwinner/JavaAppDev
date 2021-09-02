
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;

/**
 * ƒанна€ программа демонстрирует использование сокетов дл€ передачи почтовых сообщений.
 * <p/>
 * @author Cay Horstmann
 * @version 1.11 2007-06-25
 */
public class MailTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new MailTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * ‘рейм дл€ пользовательского интерфейса.
 */
class MailTestFrame extends JFrame
{
    private Scanner in;
    private PrintWriter out;
    private JTextField from;
    private JTextField to;
    private JTextField smtpServer;
    private JTextArea message;
    private JTextArea comm;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
    
    MailTestFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("MailTest");

        setLayout(new GridBagLayout());

        // ћы ипользуем класс GBC
        add(new JLabel("From:"), new GBC(0, 0).setFill(GBC.HORIZONTAL));

        from = new JTextField(20);
        add(from, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(100, 0));

        add(new JLabel("To:"), new GBC(0, 1).setFill(GBC.HORIZONTAL));

        to = new JTextField(20);
        add(to, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(100, 0));

        add(new JLabel("SMTP server:"), new GBC(0, 2).setFill(GBC.HORIZONTAL));

        smtpServer = new JTextField(20);
        add(smtpServer, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(100, 0));

        message = new JTextArea();
        add(new JScrollPane(message), new GBC(0, 3, 2, 1).setFill(GBC.BOTH).setWeight(100, 100));

        comm = new JTextArea();
        add(new JScrollPane(comm), new GBC(0, 4, 2, 1).setFill(GBC.BOTH).setWeight(100, 100));

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, new GBC(0, 5, 2, 1));

        JButton sendButton = new JButton("Send");
        buttonPanel.add(sendButton);
        sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                new SwingWorker<Void, Void>()
                {
                    @Override
                    protected Void doInBackground() throws Exception
                    {
                        comm.setText("");
                        sendMail();
                        return null;
                    }
                }.execute();
            }
        });
    }

    /**
     * ѕередача почтового сообщени€, которое было задано с применением
     * средств пользовательского интерфейса.
     */
    public void sendMail()
    {
        try
        {
            try (Socket s = new Socket(smtpServer.getText(), 25))
            {
                InputStream inStream = s.getInputStream();
                OutputStream outStream = s.getOutputStream();

                in = new Scanner(inStream);
                out = new PrintWriter(outStream, true);

                String hostName = InetAddress.getLocalHost().getHostName();

                receive();
                send("HELO " + hostName);
                receive();
                send("MAIL FROM: <" + from.getText() + ">");
                receive();
                send("RCPT TO: <" + to.getText() + ">");
                receive();
                send("DATA");
                receive();
                send(message.getText());
                send(".");
                receive();
            }
        }
        catch (IOException e)
        {
            comm.append("Error: " + e);
        }
    }

    /**
     * ѕередача строки сокету и воспроизведение ее в текстовой области comm.
     * <p/>
     * @param s —трока дл€ передачи.
     */
    public void send(String s) throws IOException
    {
        comm.append(s);
        comm.append("\n");
        out.print(s.replaceAll("\n", "\r\n"));
        out.print("\r\n");
        out.flush();
    }

    /**
     * ѕолучение строки посредством сокета и отображение ее в текстовой области comm.
     */
    public void receive() throws IOException
    {
        String line = in.nextLine();
        comm.append(line);
        comm.append("\n");
    }
}

/**
 * This class simplifies the use of the GridBagConstraints class.
 */
class GBC extends GridBagConstraints
{
    /**
     * Constructs a GBC with a given gridx and gridy position and all other grid bag constraint
     * values set to the default.
     * <p/>
     * @param gridx the gridx position
     * @param gridy the gridy position
     */
    GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     * Constructs a GBC with given gridx, gridy, gridwidth, gridheight and all other grid bag
     * constraint values set to the default.
     * <p/>
     * @param gridx      the gridx position
     * @param gridy      the gridy position
     * @param gridwidth  the cell span in x-direction
     * @param gridheight the cell span in y-direction
     */
    GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     * Sets the anchor.
     * <p/>
     * @param anchor the anchor value
     * <p/>
     * @return this object for further modification
     */
    GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     * Sets the fill direction.
     * <p/>
     * @param fill the fill direction
     * <p/>
     * @return this object for further modification
     */
    GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     * Sets the cell weights.
     * <p/>
     * @param weightx the cell weight in x-direction
     * @param weighty the cell weight in y-direction
     * <p/>
     * @return this object for further modification
     */
    GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * Sets the insets of this cell.
     * <p/>
     * @param distance the spacing to use in all directions
     * <p/>
     * @return this object for further modification
     */
    GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * Sets the insets of this cell.
     * <p/>
     * @param top    the spacing to use on top
     * @param left   the spacing to use to the left
     * @param bottom the spacing to use on the bottom
     * @param right  the spacing to use to the right
     * <p/>
     * @return this object for further modification
     */
    GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * Sets the internal padding
     * <p/>
     * @param ipadx the internal padding in x-direction
     * @param ipady the internal padding in y-direction
     * <p/>
     * @return this object for further modification
     */
    GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}

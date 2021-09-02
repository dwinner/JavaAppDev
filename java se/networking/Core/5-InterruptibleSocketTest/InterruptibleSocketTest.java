
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import javax.swing.*;

/**
 * ������ �������� ������������� ����������� ���������� ��� ������������� ������ ������.
 * <p/>
 * @author Cay Horstmann
 * @version 1.01 2007-06-25
 */
public class InterruptibleSocketTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new InterruptibleSocketFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class InterruptibleSocketFrame extends JFrame
{
    private Scanner in;
    private JButton interruptibleButton;
    private JButton blockingButton;
    private JButton cancelButton;
    private JTextArea messages;
    private TestServer server;
    private Thread connectThread;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;
    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8189;

    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    InterruptibleSocketFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("InterruptibleSocketTest");

        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);

        messages = new JTextArea();
        add(new JScrollPane(messages));

        interruptibleButton = new JButton("Interruptible");
        blockingButton = new JButton("Blocking");

        northPanel.add(interruptibleButton);
        northPanel.add(blockingButton);

        interruptibleButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancelButton.setEnabled(true);
                connectThread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            connectInterruptibly();
                        }
                        catch (IOException e)
                        {
                            messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
                        }
                    }
                });
                connectThread.start();
            }
        });

        blockingButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                interruptibleButton.setEnabled(false);
                blockingButton.setEnabled(false);
                cancelButton.setEnabled(true);
                connectThread = new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            connectBlocking();
                        }
                        catch (UnknownHostException e)
                        {
                            messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
                        }
                        catch (IOException e)
                        {
                            messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
                        }
                    }
                });
                connectThread.start();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setEnabled(false);
        northPanel.add(cancelButton);
        cancelButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                connectThread.interrupt();
                cancelButton.setEnabled(false);
            }
        });
        server = new TestServer();
        new Thread(server).start();
    }

    /**
     * ���������� � �������� �������� ����������� ������������ �����-������.
     */
    public void connectInterruptibly() throws IOException
    {
        messages.append("Interruptible:\n");
        try (SocketChannel channel =
                SocketChannel.open(new InetSocketAddress(DEFAULT_HOST, DEFAULT_PORT)))
        {
            in = new Scanner(channel);
            while (!Thread.currentThread().isInterrupted())
            {
                messages.append("Reading ");
                if (in.hasNextLine())
                {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }
        finally
        {
            EventQueue.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    messages.append("Channel closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }

    /**
     * ���������� � �������� �������� ����������� ������������ �����-������.
     */
    public void connectBlocking() throws UnknownHostException, IOException
    {
        messages.append("Blocking:\n");
        try (Socket sock = new Socket(DEFAULT_HOST, DEFAULT_PORT))
        {
            in = new Scanner(sock.getInputStream());
            while (!Thread.currentThread().isInterrupted())
            {
                messages.append("Reading ");
                if (in.hasNextLine())
                {
                    String line = in.nextLine();
                    messages.append(line);
                    messages.append("\n");
                }
            }
        }
        finally
        {
            EventQueue.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    messages.append("Socket closed\n");
                    interruptibleButton.setEnabled(true);
                    blockingButton.setEnabled(true);
                }
            });
        }
    }

    /**
     * ������������� ������, ������� ������� ��������� �� ����� 8189 � �������� �������
     * ��������������� ��������, �������� ������� ������� ����� 10 �����.
     */
    private class TestServer implements Runnable
    {
        @Override
        public void run()
        {
            try
            {
                ServerSocket s = new ServerSocket(DEFAULT_PORT);

                while (true)
                {
                    Socket incoming = s.accept();
                    Runnable r = new TestServerHandler(incoming);
                    Thread t = new Thread(r);
                    t.start();
                }
            }
            catch (IOException e)
            {
                messages.append("\nTestServer.run: " + e);
            }
        }
    }

    /**
     * ������ ����� ������������ ������, ���������� �� ������� � ������ ������ ����������.
     */
    private class TestServerHandler implements Runnable
    {
        private Socket incoming;
        private int counter;

        /**
         * ����������� �����������.
         * <p/>
         * @param i - �������� �����.
         */
        TestServerHandler(Socket i)
        {
            incoming = i;
        }

        @Override
        public void run()
        {
            try
            {
                OutputStream outStream = incoming.getOutputStream();
                PrintWriter out = new PrintWriter(outStream, true /* autoFlush */);
                while (counter < 100)
                {
                    counter++;
                    if (counter <= 10)
                    {
                        out.println(counter);
                    }
                    Thread.sleep(100);
                }
                incoming.close();
                messages.append("Closing server\n");
            }
            catch (IOException | InterruptedException e)
            {
                messages.append("\nTestServerHandler.run: " + e);
            }
        }
    }
}

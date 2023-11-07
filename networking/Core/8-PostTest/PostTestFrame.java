import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.*;

public class PostTestFrame extends JFrame
{
    private JPanel northPanel;

    /**
     * Создает запрос POST и возвращает ответ сервера.
     * <p/>
     * @param urlString      URL, по которому происходит передача
     * @param nameValuePairs Карта с данными для запроса
     * <p/>
     * @return Ответ сервера (получен либо из входного потока, либо из потока ошибки)
     * <p/>
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String doPost(String urlString, Map<String, String> nameValuePairs)
        throws MalformedURLException, IOException
    {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setDoOutput(true);
        try (PrintWriter out = new PrintWriter(connection.getOutputStream()))
        {
            boolean first = true;
            for (Map.Entry<String, String> pair : nameValuePairs.entrySet())
            {
                if (first)
                {
                    first = false;
                }
                else
                {
                    out.print('&');
                }
                String name = pair.getKey();
                String value = pair.getValue();
                out.print(name);
                out.print('=');
                out.print(URLEncoder.encode(value, "UTF-8"));
            }
        }
        Scanner in;
        StringBuilder response = new StringBuilder(0xFF + 1);
        try
        {
            in = new Scanner(connection.getInputStream());
        }
        catch (IOException ioEx)
        {
            if (!(connection instanceof HttpURLConnection))
            {
                throw ioEx;
            }
            InputStream err = ((HttpURLConnection) connection).getErrorStream();
            if (err == null)
            {
                throw ioEx;
            }
            in = new Scanner(err);
        }
        while (in.hasNextLine())
        {
            response.append(in.nextLine());
            response.append("\n");
        }

        in.close();
        return response.toString();
    }

    public PostTestFrame()
    {
        setTitle("PostTest");

        northPanel = new JPanel();
        add(northPanel, BorderLayout.NORTH);
        northPanel.setLayout(new GridLayout(0, 2));
        northPanel.add(new JLabel("Host: ", SwingConstants.TRAILING));
        final JTextField hostField = new JTextField("http://www.youtube.com");
        northPanel.add(hostField);
        northPanel.add(new JLabel("Action: ", SwingConstants.TRAILING));
        final JTextField actionField = new JTextField("results");
        northPanel.add(actionField);
        northPanel.add(new JTextField("search_query"));
        northPanel.add(new JTextField("javafx learn"));
        for (int i = 1; i <= 6; i++)
        {
            northPanel.add(new JTextField());
        }

        final JTextArea result = new JTextArea(20, 40);
        add(new JScrollPane(result));

        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.SOUTH);
        JButton addButton = new JButton("More");
        southPanel.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                northPanel.add(new JTextField());
                northPanel.add(new JTextField());
                pack();
            }
        });

        JButton getButton = new JButton("Get");
        southPanel.add(getButton);
        getButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                result.setText("");
                final Map<String, String> post = new HashMap<>(0x1F + 1);
                for (int i = 4; i < northPanel.getComponentCount(); i += 2)
                {
                    String name = ((JTextField) northPanel.getComponent(i)).getText();
                    if (name != null && name.trim().length() > 0)
                    {
                        String value = ((JTextField) northPanel.getComponent(i + 1)).getText();
                        if (value != null)
                        {
                            post.put(name, value);
                        }
                    }
                }

                new SwingWorker<Void, Void>()
                {
                    @Override
                    protected Void doInBackground() throws Exception
                    {
                        try
                        {
                            String urlString = hostField.getText() + "/" + actionField.getText();
                            result.setText(doPost(urlString, post));
                        }
                        catch (IOException ioEx)
                        {
                            result.setText("" + ioEx);
                        }
                        return null;
                    }
                }.execute();
            }
        });
        pack();
    }
}

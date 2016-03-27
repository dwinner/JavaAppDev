package amazonapp;

import com.horstmann.amazon.*;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.xml.ws.Holder;

/**
 * Фрейм для выбора автора книги и отображения ответа сервера.
 *
 * @author Cay Horstmann
 */
public class AmazonTestFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 300;
    private static final String accessKey = "12Y1EEATQ8DDYJCVQYR2";
    private JTextField author;
    private JTextArea result;

    public AmazonTestFrame() throws HeadlessException
    {
        setTitle("AmazonTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Author:"));
        author = new JTextField(20);
        panel.add(author);

        JButton searchButton = new JButton("Search");
        panel.add(searchButton);

        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                result.setText("Please wait...");
                new SwingWorker<Void, Void>()
                {
                    @Override
                    protected Void doInBackground() throws Exception
                    {
                        String name = author.getText();
                        String books = searchByAuthor(name);
                        result.setText(books);

                        return null;
                    }
                }.execute();
            }
        });

        result = new JTextArea();
        result.setLineWrap(true);
        result.setEditable(false);
        if (accessKey.equals("your key here"))  // TODO: Insert Amazon access key
        {
            result.setText("You need to edit the Amazon access key.");
            // Необходимо отредактировать ключ доступа Amazon.
            searchButton.setEnabled(false);
        }
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(result), BorderLayout.CENTER);
    }

    /**
     * Вызов Web-службы Amazon для поиска соответствующих автору заголовков книг.
     *
     * @param name Имя автора
     * @return Описание заголовков, которые должны считаться соответствующими
     */
    private String searchByAuthor(String name)
    {
        AWSECommerceService service = new AWSECommerceService();
        AWSECommerceServicePortType port =
           service.getPort(AWSECommerceServicePortType.class);
        ItemSearchRequest request = new ItemSearchRequest();
        request.getResponseGroup().add("ItemAttributes");
        request.setSearchIndex("Books");

        Holder<List<Items>> responseHolder = new Holder<>();
        request.setAuthor(name);
        port.itemSearch("", accessKey, "", "", "", request, null, null, responseHolder);

        List<Item> response = responseHolder.value.get(0).getItem();

        StringBuilder r = new StringBuilder();
        for (Item item : response)
        {
            r.append("authors=");
            List<String> authors = item.getItemAttributes().getAuthor();
            r.append(authors);
            r.append(",title=");
            r.append(item.getItemAttributes().getTitle());
            r.append(",publisher=");
            r.append(item.getItemAttributes().getPublisher());
            r.append(",pubdate=");
            r.append(item.getItemAttributes().getPublicationDate());
            r.append("\n");
        }

        return r.toString();
    }
}

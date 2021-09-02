
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.swing.*;

/**
 * ���������, ��������������� ������������� ������� ��������.
 * <p/>
 * @version 1.23 2007-06-28
 * @author Cay Horstmann
 */
public class QueryDB
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new QueryDBFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


/**
 * ���� ����� �������� �������������� ������ ��� ����� ���������� �������, ��������� ������� ���
 * ����������� ����������� ������� � ����� ������ ��� ���������� ������� � ���������� ������.
 */
class QueryDBFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;
    private JComboBox<String> authors;
    private JComboBox<String> publishers;
    private JTextField priceChange;
    private JTextArea result;
    private Connection conn;
    private PreparedStatement authorQueryStmt;
    private PreparedStatement authorPublisherQueryStmt;
    private PreparedStatement publisherQueryStmt;
    private PreparedStatement allQueryStmt;
    private PreparedStatement priceUpdateStmt;
    
    private static final String authorPublisherQuery =
        "select "
        + " books.price, "
        + " books.title "
        + "from "
        + " books, "
        + " booksauthors, "
        + " authors, "
        + " publishers "
        + "where "
        + " authors.Author_Id = booksauthors.Author_Id "
        + " and booksauthors.ISBN = books.ISBN "
        + " and books.Publisher_Id = publishers.Publisher_Id "
        + " and authors.Name = ? "
        + " and publishers.Name = ?";
    
    private static final String authorQuery =
        "select "
        + " books.Price, "
        + " books.Title "
        + "from "
        + " books, "
        + " booksauthors, "
        + " authors "
        + "where "
        + " authors.Author_Id = booksauthors.Author_Id "
        + " and booksauthors.ISBN = books.ISBN "
        + " and authors.Name = ?";
    
    private static final String publisherQuery =
        "select "
        + " books.price, "
        + " books.title "
        + "from "
        + " books, "
        + " publishers "
        + "where "
        + " books.Publisher_Id = publishers.Publisher_Id "
        + " and publishers.Name = ?";
    
    private static final String allQuery =
        "select books.price, books.title from books";
    
    private static final String priceUpdate =
        "update books "
        + " set price = price + ? "
        + " where books.Publisher_Id = "
        + " (select publisher_id from publishers where name = ?)";

    QueryDBFrame() throws HeadlessException
    {
        setTitle("QueryDB");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        
        authors = new JComboBox<>();
        authors.setEditable(false);
        authors.addItem("Any");
        
        publishers = new JComboBox<>();
        publishers.setEditable(false);
        publishers.addItem("Any");
        
        result = new JTextArea(4, 50);
        result.setEditable(false);
        
        priceChange = new JTextField(8);
        priceChange.setText("-5.00");
        
        try
        {
            conn = getConnection();
            try (Statement stat = conn.createStatement())
            {
                String query = "SELECT Name FROM authors";
                try (ResultSet rs = stat.executeQuery(query))
                {
                    while (rs.next())
                    {                
                        authors.addItem(rs.getString(1));
                    }
                }
                
                query = "SELECT Name FROM Publishers";
                try (ResultSet rs = stat.executeQuery(query))
                {
                    while (rs.next())
                    {                
                        publishers.addItem(rs.getString(1));
                    }
                }
            }
        }
        catch (FileNotFoundException filenotEx)
        {
            result.setText("" + filenotEx);
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                result.append(t.getMessage());
            }
        }
        catch (IOException ioEx)
        {
            result.setText("" + ioEx);
        }
        
        // �� ���������� ����� GBC
        add(authors, new GBC(0, 0, 2, 1));
        add(publishers, new GBC(2, 0, 2, 1));
        
        JButton queryButton = new JButton("Query");
        queryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                executeQuery();
            }
        });
        add(queryButton, new GBC(0, 1, 1, 1).setInsets(3));
        
        JButton changeButton = new JButton("Change prices");
        changeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                changePrices();
            }
        });
        add(changeButton, new GBC(2, 1, 1, 1).setInsets(3));
        
        add(priceChange, new GBC(3, 1, 1, 1).setFill(GBC.HORIZONTAL));
        add(new JScrollPane(result), new GBC(0, 2, 4, 1).setFill(GBC.BOTH).setWeight(100, 100));
        
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                try
                {
                    if (conn != null)
                    {
                        conn.close();
                    }
                }
                catch (SQLException sqlEx)
                {
                    for (Throwable t : sqlEx)
                    {
                        t.printStackTrace();
                    }
                }
            }           
        });
    }
    
    /**
     * ���������� �������.
     */
    private void executeQuery()
    {
        ResultSet rs = null;
        try
        {
            String author = (String) authors.getSelectedItem();
            String publisher = (String) publishers.getSelectedItem();
            if (!author.equals("Any") && !publisher.equals("Any"))
            {
                if (authorPublisherQueryStmt == null)
                {
                    authorPublisherQueryStmt = conn.prepareStatement(authorPublisherQuery);
                }
                authorPublisherQueryStmt.setString(1, author);
                authorPublisherQueryStmt.setString(2, publisher);
                rs = authorPublisherQueryStmt.executeQuery();
            }
            else if (!author.equals("Any"))
            {
                if (authorQueryStmt == null)
                {
                    authorQueryStmt = conn.prepareStatement(authorQuery);
                }
                authorQueryStmt.setString(1, author);
                rs = authorQueryStmt.executeQuery();
            }
            else if (author.equals("Any") && !publisher.equals("Any"))
            {
                if (publisherQueryStmt == null)
                {
                    publisherQueryStmt = conn.prepareStatement(publisherQuery);
                }
                publisherQueryStmt.setString(1, publisher);
                rs = publisherQueryStmt.executeQuery();
            }
            else
            {
                if (allQueryStmt == null)
                {
                    allQueryStmt = conn.prepareStatement(allQuery);
                }
                rs = allQueryStmt.executeQuery();
            }
            result.setText("");
            while (rs.next())
            {                
                result.append(rs.getString(1));
                result.append(", ");
                result.append(rs.getString(2));
                result.append("\n");
            }
            rs.close();
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                result.append(t.getMessage());
            }
        }
    }
    
    /**
     * ���������� ������ ��� ��������� ���.
     */
    private void changePrices()
    {
        String publisher = (String) publishers.getSelectedItem();
        if (publisher.equals("Any"))
        {
            result.setText("I am sorry, but I cannot do that.");
        }
        try
        {
            if (priceUpdateStmt == null)
            {
                priceUpdateStmt = conn.prepareStatement(priceUpdate);
            }
            priceUpdateStmt.setString(1, priceChange.getText());
            priceUpdateStmt.setString(2, publisher);
            int r = priceUpdateStmt.executeUpdate();
            result.setText(r + " records updated.");
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                result.append(t.getMessage());
            }
        }
    }
    
    /**
     * ��������� ���������� � �������������� �������,
     * �������� � ����� database.properties.
     * @return ���������� � ����� ������
     * @throws FileNotFoundException
     * @throws IOException
     * @throws SQLException 
     */
    public static Connection getConnection() throws SQLException, IOException
    {
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("database.properties"))
        {
            props.load(in);
        }

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null)
        {
            System.setProperty("jdbc.drivers", drivers);
        }

        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}

/**
 * ���� ����� �������� ������ � ������� GridBagConstraints.
 * <p/>
 * @version 1.01 2004-05-06
 * @author Cay Horstmann
 */
class GBC extends GridBagConstraints
{
    /**
     * ������� ������ GBC, ��������� gridx, gridy. �������� ��������� ���������� ����������� ��
     * ���������.
     * <p/>
     * @param gridx ������� gridx
     * @param gridy ������� gridy
     */
    GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     * ������� ������ GBC, ��������� gridx, gridy, gridwidth � gridheight. �������� ���������
     * ���������� ����������� �� ���������.
     * <p/>
     * @param gridx      ������� gridx
     * @param gridy      ������� gridy
     * @param gridwidth  ���������� ������ � ����������� x
     * @param gridheight ���������� ������ � ����������� y
     */
    GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     * ������������� �������� anchor.
     * <p/>
     * @param anchor �������� ���������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     * ������������� �������� fill.
     * <p/>
     * @param fill �������� ���������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     * ������������� ���� ������.
     * <p/>
     * @param weightx ��� � ����������� x
     * @param weighty ��� � ����������� y
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * ������������� ������� ���������� ������������ ��� ������.
     * <p/>
     * @param distance ������� �� ���� ������������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * ������������� ������� ���������� ������������ ��� ������.
     * <p/>
     * @param top    ������ ������� ����� ���������� ������������
     * @param left   ������ ����� ����� ���������� ������������
     * @param bottom ������ ������ ����� ���������� ������������
     * @param right  ������ ������ ����� ���������� ������������
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * ������������� ���������� ����������.
     * <p/>
     * @param ipadx ���������� ���������� � ����������� x
     * @param ipady ���������� ���������� � ����������� y
     * <p/>
     * @return ������ this, ��������� ��� ���������� �����������
     */
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}

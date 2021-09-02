import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.sql.rowset.CachedRowSet;
import javax.swing.*;

/**
 * Фрейм, содержащий панель с данными и кнопки перемещения по записям.
 */
public class ViewDBFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 640;
    public static final int DEFAULT_HEIGHT = 480;
    private JButton previousButton;
    private JButton nextButton;
    private JButton deleteButton;
    private JButton saveButton;
    private DataPanel dataPanel;
    private Component scrollPane;
    private JComboBox<String> tableNames;
    private Properties props;
    private CachedRowSet crs;

    public ViewDBFrame()
    {
        setTitle("ViewDB");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        tableNames = new JComboBox<>();
        tableNames.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showTable((String) tableNames.getSelectedItem());
            }
        });
        add(tableNames, BorderLayout.NORTH);

        try
        {
            readDatabaseProperties();
            try (Connection conn = getConnection())
            {
                DatabaseMetaData meta = conn.getMetaData();
                ResultSet mrs = meta.getTables(null, null, null, new String[]
                    {
                        "TABLE"
                    });
                while (mrs.next())
                {
                    tableNames.addItem(mrs.getString(3));
                }
            }
        }
        catch (FileNotFoundException filenotEx)
        {
            JOptionPane.showMessageDialog(this, filenotEx);
        }
        catch (IOException | SQLException sqlOrIOEx)
        {
            JOptionPane.showMessageDialog(this, sqlOrIOEx);
        }

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showPreviousRow();
            }
        });
        buttonPanel.add(previousButton);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showNextRow();
            }
        });
        buttonPanel.add(nextButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                deleteRow();
            }
        });
        buttonPanel.add(deleteButton);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                saveChanges();
            }
        });
        buttonPanel.add(saveButton);
    }

    /**
     * Подготовка текстовых полей к отображению новой таблицы и показ первой строки.
     * <p/>
     * @param tableName Имя таблицы для отображения.
     */
    public void showTable(String tableName)
    {
        try
        {
            try (Connection conn = getConnection())
            {
                // Получение результирующего набора
                Statement stat = conn.createStatement();
                ResultSet result = stat.executeQuery("SELECT * FROM " + tableName);
                // Копирование в кэшированный результирующий набор
                crs = new CachedRowSetImpl();
                crs.setTableName(tableName);
                crs.populate(result);
            }

            if (scrollPane != null)
            {
                remove(scrollPane);
            }
            dataPanel = new DataPanel(crs);
            scrollPane = new JScrollPane(dataPanel);
            add(scrollPane, BorderLayout.CENTER);
            validate();
            showNextRow();
        }
        catch (SQLException sqlEx)
        {
            JOptionPane.showMessageDialog(this, sqlEx);
        }
    }

    /**
     * Переход к предыдущей строке таблицы.
     */
    public void showPreviousRow()
    {
        try
        {
            if (crs == null || crs.isFirst())
            {
                return;
            }
            crs.previous();
            dataPanel.showRow(crs);
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                t.printStackTrace();
            }
        }
    }

    /**
     * Переход к следующей строке таблицы.
     */
    public void showNextRow()
    {
        try
        {
            if (crs == null || crs.isLast())
            {
                return;
            }
            crs.next();
            dataPanel.showRow(crs);
        }
        catch (SQLException sqlEx)
        {
            JOptionPane.showMessageDialog(this, sqlEx);
        }
    }

    /**
     * Удаление текущей строки таблицы.
     */
    public void deleteRow()
    {
        try
        {
            try (Connection conn = getConnection())
            {
                crs.deleteRow();
                crs.acceptChanges(conn);
                if (!crs.isLast())
                {
                    crs.next();
                }
                else if (!crs.isFirst())
                {
                    crs.previous();
                }
                else
                {
                    crs = null;
                }
                dataPanel.showRow(crs);
            }
        }
        catch (SQLException sqlEx)
        {
            JOptionPane.showMessageDialog(this, sqlEx);
        }
    }

    /**
     * Сохранение всех изменений.
     */
    public void saveChanges()
    {
        try
        {
            try (Connection conn = getConnection())
            {
                dataPanel.setRow(crs);
                crs.acceptChanges(conn);
            }
        }
        catch (SQLException sqlEx)
        {
            JOptionPane.showMessageDialog(this, sqlEx);
        }
    }

    private void readDatabaseProperties() throws FileNotFoundException, IOException
    {
        props = new Properties();
        try (FileInputStream in = new FileInputStream("database.properties"))
        {
            props.load(in);
        }
        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null)
        {
            System.setProperty("jdbc.drivers", drivers);
        }
    }

    /**
     * Получение соединения из свойств, определенных в файле database.properties
     * <p/>
     * @return Соединение с базой данных
     * <p/>
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException
    {
        String url = props.getProperty("jdbc.url");
        String username = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, username, password);
    }
}

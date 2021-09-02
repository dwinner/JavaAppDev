
/**
 * Отображение БД-запросов в таблице.
 * <p/>
 * @version 1.1 2004-08-22
 * @author Cay Horstmann
 */
import com.sun.rowset.CachedRowSetImpl;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.sql.rowset.CachedRowSet;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * Пример отображения в таблице результатов выполнения запроса к БД.
 */
public class ResultSetTable
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame frame = new ResultSetFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         }

      });
   }

}

/**
 * Этот фрейм содержит раскрывающийся список для выбора таблицы БД и таблицу для отображения информации из БД.
 */
class ResultSetFrame extends JFrame
{
   private JScrollPane scrollPane;
   private ResultSetTableModel model;
   private JComboBox<String> tableNames;
   private ResultSet rs;
   private Connection conn;
   private Statement stat;
   private boolean scrolling;
   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 300;

   ResultSetFrame()
   {
      setTitle("ResultSet");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // Поиск всех таблиц БД и включение информации о них в раскрывающийся список

      tableNames = new JComboBox<>();
      tableNames.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent event)
         {
            try
            {
               if (scrollPane != null)
               {
                  remove(scrollPane);
               }
               String tableName = (String) tableNames.getSelectedItem();
               if (rs != null)
               {
                  rs.close();
               }
               String query = "SELECT * FROM " + tableName;
               rs = stat.executeQuery(query);
               if (scrolling)
               {
                  model = new ResultSetTableModel(rs);
               }
               else
               {
                  CachedRowSet crs = new CachedRowSetImpl();
                  crs.populate(rs);
                  model = new ResultSetTableModel(crs);
               }

               JTable table = new JTable(model);
               scrollPane = new JScrollPane(table);
               add(scrollPane, BorderLayout.CENTER);
               validate();
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
         }

      });
      JPanel p = new JPanel();
      p.add(tableNames);
      add(p, BorderLayout.NORTH);

      try
      {
         conn = getConnection();
         DatabaseMetaData meta = conn.getMetaData();
         if (meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE))
         {
            scrolling = true;
            stat = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                        ResultSet.CONCUR_READ_ONLY);
         }
         else
         {
            stat = conn.createStatement();
            scrolling = false;
         }
         try (ResultSet tables = meta.getTables(null, null, null, new String[]
           {
              "TABLE"
           }))
         {
            while (tables.next())
            {
               tableNames.addItem(tables.getString(3));
            }
         }
      }
      catch (IOException | SQLException e)
      {
         e.printStackTrace();
      }

      addWindowListener(new WindowAdapter()
      {
         @Override
         public void windowClosing(WindowEvent event)
         {
            try
            {
               if (conn != null)
               {
                  conn.close();
               }
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
         }

      });
   }

   /**
    * Установка соединения на основании информации, заданной в файле database.properties.
    * <p/>
    * @return Соединение с БД
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
 * На основе этого класса реализуются средства прокрутки и кэширования набора результатов. В нем хранится
 * набор результатов и метаданные.
 */
class ResultSetTableModel extends AbstractTableModel
{
   private ResultSet rs;
   private ResultSetMetaData rsmd;

   /**
    * Конструктор модели таблицы.
    * <p/>
    * @param aResultSet Набор результатов для отображения.
    */
   ResultSetTableModel(ResultSet aResultSet)
   {
      rs = aResultSet;
      try
      {
         rsmd = rs.getMetaData();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public String getColumnName(int c)
   {
      try
      {
         return rsmd.getColumnName(c + 1);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         return "";
      }
   }

   @Override
   public int getColumnCount()
   {
      try
      {
         return rsmd.getColumnCount();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         return 0;
      }
   }

   @Override
   public Object getValueAt(int r, int c)
   {
      try
      {
         rs.absolute(r + 1);
         return rs.getObject(c + 1);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public int getRowCount()
   {
      try
      {
         rs.last();
         return rs.getRow();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         return 0;
      }
   }

}
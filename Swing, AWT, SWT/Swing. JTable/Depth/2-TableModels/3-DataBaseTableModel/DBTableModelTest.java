/*
 * Модель данных таблицы, работающая с запросами к базам данных.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * Класс, отражающий поведение источника данных из БД на модель таблицы Swing
 * <p/>
 * @author dwinner@inbox.ru
 */
class DatabaseTableModel extends AbstractTableModel
{
    // Здесь мы будем хранить названия столбцов
    private ArrayList<String> columnNames = new ArrayList<>(0x8);
    // Список типов столбцов
    private ArrayList<Class<?>> columnTypes = new ArrayList<>(0x8);
    // Хранилище для получения данных из базы
    private List<List<String>> data =
        Collections.synchronizedList(new ArrayList<List<String>>(0x40));
    private boolean editable;

    // Конструктор позволяет задать возможность редактирования
    DatabaseTableModel(boolean editable)
    {
        this.editable = editable;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }

    // Количество строк
    @Override
    public int getRowCount()
    {
        return data.size();
    }

    // Количество столбцов
    @Override
    public int getColumnCount()
    {
        return columnNames.size();
    }

    // Данные в ячейке
    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data.get(rowIndex).get(columnIndex);
    }

    // Тип данных столбца
    @Override
    public Class<?> getColumnClass(int column)
    {
        return columnTypes.get(column);
    }

    // Название столбца
    @Override
    public String getColumnName(int column)
    {
        return columnNames.get(column);
    }

    // Возможность редактирования
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return isEditable();
    }

    // Замена значения ячейки
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        data.get(rowIndex).set(columnIndex, aValue.toString());
    }

    // Получение данных из объекта ResultSet
    public void setDataSource(ResultSet rs)
        throws SQLException, ClassNotFoundException
    {
        // Удаляем прежние данные
        data.clear();
        columnNames.clear();
        columnTypes.clear();

        // Получаем вспомогательную информацию о столбцах
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        for (int i = 0; i < columnCount; i++)
        {
            // Название столбца
            columnNames.add(rsmd.getColumnName(i + 1));
            // Тип столбца
            Class<?> type = Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);
        }

        // Сообщаем об изменениях в структуре данных
        fireTableStructureChanged();

        // Здесь будем хранить ячейки одной строки
        ArrayList<String> row = new ArrayList<>(0x8);
        for (int i = 0; i < columnCount; i++)
        {
            if (columnTypes.get(i) == String.class)
            {
                row.add(rs.getString(i + 1));
            }
            else
            {
                row.add(rs.getObject(i + 1).toString());
            }
        }

        data.add(row);
        // Сообщаем о прибавлении строки
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

/**
 * Таблица, работающая с базой данных посредством модели.
 */
public class DBTableModelTest
{
    // Параметры подключения
    private static String
        dsn ="jdbc:derby://localhost:1527/sample",
        uid = "app",
        pwd = "app";

    public static void main(String[] args) throws SQLException
    {
        // Инициализация JDBC
        Connection conn = null;
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            try
            {
                // Объект-соединение с БД
                conn = DriverManager.getConnection(dsn, uid, pwd);
                Statement st = conn.createStatement();
                try (ResultSet rs = st.executeQuery("select * from readers"))
                {
                    DatabaseTableModel dbtm = new DatabaseTableModel(false);
                    // Таблица и окно
                    JTable table = new JTable(dbtm);
                    JFrame frame = new JFrame("Title");
                    frame.setSize(400, 300);
                    frame.getContentPane().add(new JScrollPane(table));
                    frame.setVisible(true);
                    // Выводим результат запроса на экран
                    dbtm.setDataSource(rs);
                }
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
        }
        catch (ClassNotFoundException ex)
        {
            throw new RuntimeException(ex);
        }
        finally
        {
            if (conn != null)
            {
                conn.close();
            }
        }
    }
}
package downloadmanager;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Этот класс управляет загрузкой данных таблицы.
 * @author dwinner@inbox.ru
 */
class DownloadsTableModel extends AbstractTableModel implements Observer {

    private static final long serialVersionUID = 0x2L;

    // Это имена для колонок таблицы.
    private static final String[] columnNames =
    {
        "URL",
        "Size",
        "Progress",
        "Status"
    };

    // Это классы для каждого значения колонки.
    private static final Class[] columnClasses =
    {
        String.class,
        String.class,
        JProgressBar.class,
        String.class
    };

    // Список для загрузки.
    private ArrayList downloadList = new ArrayList();

    // Добавить новую загрузку в таблицу.
    public void addDownload(Download download) {
        // Зарегистрировать для уведомления об изменениях в загрузке.
        download.addObserver(this);
        downloadList.add(download);
        // Ввести уведомление в строку таблицы.
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    // Получить загрузку для указанной строки.
    public Download getDownload(int row) {
        return (Download) downloadList.get(row);
    }

    // Удалить загрузку из списка.
    public void clearDownload(int row) {
        downloadList.remove(row);
        // Удаление уведомления в строке таблицы.
        fireTableRowsDeleted(row, row);
    }

    // Получить счетчик колонок таблицы.
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Получить имя колонки.
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Получить класс колонки.
    @Override
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }

    // Получить счетчик строк таблицы.
    @Override
    public int getRowCount() {
        return downloadList.size();
    }

    // Получить значение для указанной комбинации строки и колонки.
    @Override
    public Object getValueAt(int row, int col) {
        Download download = (Download) downloadList.get(row);
        switch (col) {
            case 0: // URL.
                return download.getUrl();
            case 1: // Размер.
                int size = download.getSize();
                return (size == -1) ? "" : Integer.toString(size);
            case 2: // Процесс.
                return new Float(download.getProgress());
            case 3: // Состояние.
                return Download.STATUSES[download.getStatus()];
        }
        return "";
    }

    /* Обновить при вызове, когда класс Download уведомляет экземпляры
     Observers об изменениях. */
    @Override
    public void update(Observable o, Object arg) {
        int index = downloadList.indexOf(o);
        // Запустить обновление уведомлений для строки таблицы.
        fireTableRowsUpdated(index, index);
    }
}
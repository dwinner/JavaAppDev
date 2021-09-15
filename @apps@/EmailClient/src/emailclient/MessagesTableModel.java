package emailclient;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.swing.table.*;

/**
 * Этот класс управляет данными в таблице почтового клиента.
 * @author dwinner@inbox.ru
 */
public class MessagesTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 4L;

    // Это имена колонок таблицы.
    private static final String[] columnNames =
    {
        "Sender",
        "Subject",
        "Date"
    };

    // Список сообщений таблицы.
    private ArrayList<Message> messageList = new ArrayList<Message>();

    // Установить список сообщений таблицы.
    public void setMessages(Message[] messages) {
        messageList.addAll(Arrays.asList(messages));
        // Создать уведомление для тадлицы об изменении данных.
        fireTableDataChanged();
    }

    // Получить сообщение от указанной строки.
    public Message getMessage(int row) {
        return messageList.get(row);
    }

    // Удалить сообщение из списка.
    public void deleteMessage(int row) {
        messageList.remove(row);
        // Создать уведомление для таблицы об удалении строки.
        fireTableRowsDeleted(row, row);
    }

    // Получить число колонок в таблице.
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // Получить имена колонок.
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // Получить число строк в таблице.
    @Override
    public int getRowCount() {
        return messageList.size();
    }

    // Получить комбинацию строки и колонки.
    @Override
    public Object getValueAt(int row, int col) {
        try {
            Message message = messageList.get(row);
            switch (col) {
                case 0: // Отправитель.
                    Address[] senders;
                    senders = message.getFrom();
                    return (senders != null || senders.length > 0) ? senders[0].toString() : "[none]";
                case 1: // Описание.
                    String subject = message.getSubject();
                    return (subject != null && subject.length() > 0) ? subject : "[none]";
                case 2: // Данные о дате.
                    Date date = message.getSentDate();
                    return (date != null) ? date.toString() : "[none]";
            }
        }
        catch (MessagingException ex) { // Ошибка.
            Logger.getLogger(MessagesTableModel.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        return "";
    }

}
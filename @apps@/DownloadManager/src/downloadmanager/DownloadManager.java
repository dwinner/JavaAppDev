package downloadmanager;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Менеджер загрузок
 * @author dwinner@inbox.ru
 */
public class DownloadManager extends JFrame implements Observer {

    private static final long serialVersionUID = 3L;

    // Добавить текстовое поле для загрузки.
    private JTextField addTextField;

    // Модель данных таблицы загрузки.
    private DownloadsTableModel tableModel;

    // Таблица для списка загрузок.
    private JTable table;

    // Кнопки для управления выбранной загрузкой
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton cancelButton;
    private JButton clearButton;

    // Текущая выбранная загрузка.
    private Download selectedDownload;

    // Флаг, указывающий на очистку элемента таблицы.
    private boolean clearing;

    // Конструктор для менеджера загрузок.
    public DownloadManager() {
        // Создать заголовок приложения.
        setTitle("Download Manager");

        // Установить размеры окна.
        setSize(640, 480);

        // Обработать событие закрытия окна.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });

        // Подготовить меню файлов.
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Добавить панель.
        JPanel addPanel = new JPanel();
        addTextField = new JTextField(30);
        addPanel.add(addTextField);
        JButton addButton = new JButton("Add Download");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionAdd();
            }
        });
        addPanel.add(addButton);

        // Создать таблицу загрузок.
        tableModel = new DownloadsTableModel();
        table = new JTable(tableModel);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tableSelectionChanged();
            }
        });
        // Разрешить выбирать одновременно только одну строку.
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Создать ProgressBar как формирователь изображения для колонки Progress
        ProgressRenderer renderer = new ProgressRenderer(0, 100);
        // Показывать текст на прогресс-баре.
        renderer.setStringPainted(true);
        table.setDefaultRenderer(JProgressBar.class, renderer);

        // Установить высоту для строк таблицы, достаточной для использования JProgressBar.
        table.setRowHeight((int) renderer.getPreferredSize().getHeight());

        // Создать панель загрузки.
        JPanel downloadsPanel = new JPanel();
        downloadsPanel.setBorder(BorderFactory.createTitledBorder("Downloads"));
        downloadsPanel.setLayout(new BorderLayout());
        downloadsPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Создать панель кнопок.
        JPanel buttonsPanel = new JPanel();
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPause();
            }
        });
        pauseButton.setEnabled(false);
        buttonsPanel.add(pauseButton);
        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionResume();
            }
        });
        resumeButton.setEnabled(false);
        buttonsPanel.add(resumeButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionCancel();
            }
        });
        cancelButton.setEnabled(false);
        buttonsPanel.add(cancelButton);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionClear();
            }
        });
        clearButton.setEnabled(false);
        buttonsPanel.add(clearButton);

        // Добавить панели для отображения.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(addPanel, BorderLayout.NORTH);
        getContentPane().add(downloadsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
    }

    // Выход из программы.
    private void actionExit() {
        System.exit(0);
    }

    // Добавить новую загрузку.
    private void actionAdd() {
        URL verifiedUrl = verifyUrl(addTextField.getText());
        if (verifiedUrl != null) {
            tableModel.addDownload(new Download(verifiedUrl));
            // Переустановить добавленные текстовые поля.
            addTextField.setText("");
        }
        else {
            JOptionPane.showMessageDialog(this, "Invalid Download URL", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Проверить URL загрузки.
    private URL verifyUrl(String url) {
        // Разрешены только HTTP URL's
        if (!url.toLowerCase().startsWith("http://")) {
            return null;
        }

        // Проверить формат URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        }
        catch (Exception e) {
            return null;
        }

        // Убедиться, что URL указывает на файл.
        if (verifiedUrl.getFile().length() < 2) {
            return null;
        }

        return verifiedUrl;
    }

    // Вызов, когда изменяется выбор строки таблицы.
    private void tableSelectionChanged() {
        // Отменить регистрацию для приема уведомлений от последней выбранной загрузки.
        if (selectedDownload != null) {
            selectedDownload.deleteObserver(DownloadManager.this);
        }

        /* Если загрузка не очищается, то установить выбранную загрузку и
         зарегистрировать для приема уведомлений от нее. */
        if (!clearing) {
            selectedDownload = tableModel.getDownload(table.getSelectedRow());
            selectedDownload.addObserver(DownloadManager.this);
            updateButtons();
        }
    }

    // Приостановить выбранную загрузку.
    private void actionPause() {
        selectedDownload.pause();
        updateButtons();
    }

    // Возобновить выбранную загрузку.
    private void actionResume() {
        selectedDownload.resume();
        updateButtons();
    }

    // Отменить выбранную загрузку.
    private void actionCancel() {
        selectedDownload.cancel();
        updateButtons();
    }

    // Очистить выбранную загрузку.
    private void actionClear() {
        clearing = true;
        tableModel.clearDownload(table.getSelectedRow());
        clearing = false;
        selectedDownload = null;
        updateButtons();
    }

    // Обновить состояние кнопки на основе статуса текущей выбранной загрузки.
    private void updateButtons() {
        if (selectedDownload != null) {
            int status = selectedDownload.getStatus();
            switch (status) {
                case Download.DOWNLOADING:
                    pauseButton.setEnabled(true);
                    resumeButton.setEnabled(false);
                    cancelButton.setEnabled(true);
                    clearButton.setEnabled(false);
                    break;
                case Download.PAUSED:
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                    clearButton.setEnabled(false);
                    break;
                case Download.ERROR:
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(true);
                    cancelButton.setEnabled(false);
                    clearButton.setEnabled(true);
                    break;
                default: // Загрузка завершена или отменена.
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(false);
                    cancelButton.setEnabled(false);
                    clearButton.setEnabled(true);
            }
        }
        else {
            // В таблице ничего не выбрано.
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(false);
            cancelButton.setEnabled(false);
            clearButton.setEnabled(false);
        }
    }

    /*
     * Обновление вызывается, когда загрузка уведомляет своего наблюдателя
     * об изменениях.
     */
    @Override
    public void update(Observable o, Object arg) {
        // Обновить кнопки, если выбранная загрузка была изменена.
        if (selectedDownload != null && selectedDownload.equals(o)) {
            updateButtons();
        }
    }

    // Запустить менеджер загрузок
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        DownloadManager manager = new DownloadManager();
        manager.show();
    }
}
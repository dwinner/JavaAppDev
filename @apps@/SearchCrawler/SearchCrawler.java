import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;

/**
 * Поисковый "Червь" для Web
 * @author dwinner@inbox.ru
 */
public class SearchCrawler extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SearchCrawler.class.getName());

    // Максимальное количество раскрывающихся значений URL.
    private static final String[] MAX_URLS = {
        "50",
        "100",
        "500",
        "1000"
    };

    // Кэш-память для списка ограничений робота.
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    private HashMap<String, ArrayList<String>> disallowListCache = new HashMap<String, ArrayList<String>>();

    // Элементы управления графического интерфейса панели Search.
    private JTextField startTextField;
    private JComboBox maxComboBox;
    private JCheckBox limitCheckBox;
    private JTextField logTextField;
    private JTextField searchTextField;
    private JCheckBox caseCheckBox;
    private JButton searchButton;

    // Элементы управления графического интерфейса панели Stats.
    private JLabel crawlingLabel2;
    private JLabel crawledLabel2;
    private JLabel toCrawlLabel2;
    private JProgressBar progressBar;
    private JLabel matchesLabel2;

    // Список соответствий.
    private JTable table;

    // Флаг отображения состояния поиска.
    private boolean crawling;

    // Файл журнала для тектового вывода.
    private PrintWriter logFileWriter;

    // Конструктор для поскового червя.
    @SuppressWarnings("unchecked")
    public SearchCrawler() {
        // Установка заголовка приложения.
        setTitle("Search Crawler");

        // Установка размеров окна.
        setSize(640, 480);

        // Нельзя изменять размеры окна.
        setResizable(false);

        // Обработка событий по закрытию окна.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });

        // Установить меню "Файл".
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Установить панель поиска.
        JPanel searchPanel = new JPanel();
        GridBagConstraints constraints;
        GridBagLayout layout = new GridBagLayout();
        searchPanel.setLayout(layout);

        JLabel startLabel = new JLabel("Start URL:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(startLabel, constraints);
        searchPanel.add(startLabel);

        startTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(startTextField, constraints);
        searchPanel.add(startTextField);

        JLabel maxLabel = new JLabel("Max URLs to Crawl:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(maxLabel, constraints);
        searchPanel.add(maxLabel);

        maxComboBox = new JComboBox(MAX_URLS);
        maxComboBox.setEditable(true);
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(maxComboBox, constraints);
        searchPanel.add(maxComboBox);

        limitCheckBox = new JCheckBox("Limit crawling to Start URL site");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(0, 10, 0, 0);
        layout.setConstraints(limitCheckBox, constraints);
        searchPanel.add(limitCheckBox);

        JLabel blankLabel = new JLabel();
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(blankLabel, constraints);
        searchPanel.add(blankLabel);

        JLabel logLabel = new JLabel("Matches Log File:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(logLabel, constraints);
        searchPanel.add(logLabel);

        String file = System.getProperty("user.dir") + System.getProperty("file.separator") + "crawler.log";
        logTextField = new JTextField(file);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(logTextField, constraints);
        searchPanel.add(logTextField);

        JLabel searchLabel = new JLabel("Search String:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(searchLabel, constraints);
        searchPanel.add(searchLabel);

        searchTextField = new JTextField();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 0, 0);
        constraints.gridwidth = 2;
        constraints.weightx = 1.0d;
        layout.setConstraints(searchTextField, constraints);
        searchPanel.add(searchTextField);

        caseCheckBox = new JCheckBox("Case Sensitive");
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 0, 5);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        layout.setConstraints(caseCheckBox, constraints);
        searchPanel.add(caseCheckBox);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionSearch();
            }
        });
        constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(searchButton, constraints);
        searchPanel.add(searchButton);

        JSeparator separator = new JSeparator();
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 5, 5);
        layout.setConstraints(separator, constraints);
        searchPanel.add(separator);

        JLabel crawlingLabel1 = new JLabel("Crawling:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(crawlingLabel1, constraints);
        searchPanel.add(crawlingLabel1);

        crawlingLabel2 = new JLabel();
        crawlingLabel2.setFont(crawlingLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(crawlingLabel2, constraints);
        searchPanel.add(crawlingLabel2);

        JLabel crawledLabel1 = new JLabel("Crawled URLs:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(crawledLabel1, constraints);
        searchPanel.add(crawledLabel1);

        crawledLabel2 = new JLabel();
        crawledLabel2.setFont(crawledLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(crawledLabel2, constraints);
        searchPanel.add(crawledLabel2);

        JLabel toCrawlLabel1 = new JLabel("URLs to Crawl:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(toCrawlLabel1, constraints);
        searchPanel.add(toCrawlLabel1);

        toCrawlLabel2 = new JLabel();
        toCrawlLabel2.setFont(toCrawlLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(toCrawlLabel2, constraints);
        searchPanel.add(toCrawlLabel2);

        JLabel progressLabel = new JLabel("Crawling Progress:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 0, 0);
        layout.setConstraints(progressLabel, constraints);
        searchPanel.add(progressLabel);

        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setStringPainted(true);
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 0, 5);
        layout.setConstraints(progressBar, constraints);
        searchPanel.add(progressBar);

        JLabel matchesLabel1 = new JLabel("Search Matches:");
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(5, 5, 10, 0);
        layout.setConstraints(matchesLabel1, constraints);
        searchPanel.add(matchesLabel1);

        matchesLabel2 = new JLabel();
        matchesLabel2.setFont(matchesLabel2.getFont().deriveFont(Font.PLAIN));
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets = new Insets(5, 5, 10, 5);
        layout.setConstraints(matchesLabel2, constraints);
        searchPanel.add(matchesLabel2);

        // Установить таблицу совпадений.
        table = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"URL"}) {
            private static final long serialVersionUID = 02L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // Установить панель совпадений.
        JPanel matchesPanel = new JPanel();
        matchesPanel.setBorder(BorderFactory.createTitledBorder("Matches"));
        matchesPanel.setLayout(new BorderLayout());
        matchesPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Отобразить панели на дисплее.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(matchesPanel, BorderLayout.CENTER);
    }

    // Выход из программы.
    private void actionExit() {
        System.exit(0);
    }

    // Обработать щелчок на кнопке search/stop.
    private void actionSearch() {
        // Если произведен щелчок на кнопке stop, сбросить флаг.
        if (crawling) {
            crawling = false;
            return;
        }
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> errorList = new ArrayList<String>();

        // Проверить ввод начального адреса (URL).
        String startUrl = startTextField.getText().trim();
        if (startUrl.length() < 1) {
            errorList.add("Missing Start URL.");
        }
        else if (verifyUrl(startUrl) == null) { // Проверить начальный URL.
            errorList.add("Invalid Start URL");
        }

        // Проверить, что введено значение для максимально допустимого
        // количества адресов и что это число.
        int maxUrls = 0;
        String max = ((String) maxComboBox.getSelectedItem()).trim();
        if (max.length() > 0) {
            try {
                maxUrls = Integer.parseInt(max);
            }
            catch (NumberFormatException e) {
                maxUrls = 0;
            }
            if (maxUrls < 1) {
                errorList.add("Invalid Max URLs value.");
            }
        }

        // Проверить, что файл с журналом совпадений существует.
        String logFile = logTextField.getText().trim();
        if (logFile.length() < 1) {
            errorList.add("Missing Matches Log File.");
        }

        // Проверить, что введена строка для поиска.
        String searchString = searchTextField.getText().trim();
        if (searchString.length() < 1) {
            errorList.add("Missing Search String.");
        }

        // Показать ошибки, если они есть, и возврат.
        if (errorList.size() > 0) {
            @SuppressWarnings("StringBufferWithoutInitialCapacity")
            StringBuilder message = new StringBuilder();

            // Объединить ошибки в одно сообщение.
            for (int i = 0; i < errorList.size(); i++) {
                message.append(errorList.get(i));
                if (i + 1 < errorList.size()) {
                    message.append("\n");
                }
            }
            showError(message.toString());
            return;
        }

        // Удалить символы "www" из начального URL, если они есть.
        startUrl = removeWwwFromUrl(startUrl);

        // Запустить поискового червя.
        search(logFile, startUrl, maxUrls, searchString);
    }

    private void search(final String logFile,
                        final String startUrl,
                        final int maxUrls,
                        final String searchString)
    {
        // Начать поиск в новом потоке.
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // Отобразить песочные часы на время работы поискового червя.
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                // Заблокировать элементы управления поиска.
                startTextField.setEnabled(false);
                maxComboBox.setEnabled(false);
                limitCheckBox.setEnabled(false);
                logTextField.setEnabled(false);
                searchTextField.setEnabled(false);
                caseCheckBox.setEnabled(false);

                // переключить кнопку поиска в состояние "Stop."
                searchButton.setText("Stop");

                // Переустановить панель Stats.
                table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"URL"}) {
                    private static final long serialVersionUID = 9L;
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                updateStats(startUrl, 0, 0, maxUrls);

                // Открыть журнал совпадений.
                try {
                    logFileWriter = new PrintWriter(new FileWriter(logFile));
                }
                catch (Exception e) {
                    showError("Unable to open matches log file.");
                    return;
                }

                // Установить флаг поиска.
                crawling = true;

                // Выполнять реальный поиск.
                crawl(startUrl, maxUrls, limitCheckBox.isSelected(), searchString, caseCheckBox.isSelected());

                // Сбросить флаг поиска.
                crawling = false;

                // Закрыть журнал совпадений.
                try {
                    logFileWriter.close();
                }
                catch (Exception e) {
                    showError("Unable to close matches log file.");
                }

                // Отметить окончание поиска.
                crawlingLabel2.setText("Done");

                // Разблокировать элементы контроля поиска.
                startTextField.setEnabled(true);
                maxComboBox.setEnabled(true);
                limitCheckBox.setEnabled(true);
                logTextField.setEnabled(true);
                searchTextField.setEnabled(true);
                caseCheckBox.setEnabled(true);

                // Переключить кнопку поиска в состояние "Search."
                searchButton.setText("Search");

                // Возвратить курсор по умолчанию.
                setCursor(Cursor.getDefaultCursor());

                // Отобразить сообщение, если строка не найдена.
                if (table.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(  SearchCrawler.this,
                                                    "Your Search String was not found. Please try another.",
                                                    "Search String Not Found",
                                                    JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        thread.start();
    }

    // Отобразить диалоговое окно с сообщением об ошибке.
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Обновить панель stats.
    private void updateStats(String crawling, int crawled, int toCrawl, int maxUrls) {
        crawlingLabel2.setText(crawling);
        crawledLabel2.setText("" + crawled);
        toCrawlLabel2.setText("" + toCrawl);

        // Обновить индикатор выполнения.
        if (maxUrls == -1) {
            progressBar.setMaximum(crawled + toCrawl);
        }
        else {
            progressBar.setMaximum(maxUrls);
        }
        progressBar.setValue(crawled);
        matchesLabel2.setText("" + table.getRowCount());
    }

    // Добавить совпадение в таблицу совпадений и в журнал совпадений.
    private void addMatch(String url) {
        // Добавить URL в таблицу совпадений.
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] {url});

        // Добавить URL в журнал совпадений.
        try {
            logFileWriter.println(url);
        }
        catch (Exception e) {
            showError("Unable to log match.");
        }
    }

    // Проверить формат URL.
    private URL verifyUrl(String url) {
        // Разрешить только адреса HTTP.
        if (!url.toLowerCase().startsWith("http://")) {
            return null;
        }
        // Проверить формат URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        }
        catch (MalformedURLException ex) {
            Logger.getLogger(SearchCrawler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return verifiedUrl;
    }

    // Проверить, если робот разрешает доступ к данному URL.
    @SuppressWarnings({"NestedAssignment", "CollectionWithoutInitialCapacity"})
    private boolean isRobotAllowed(URL urlToCheck) {
        String host = urlToCheck.getHost().toLowerCase();
        // Извлечь список ограничений сайта из кэш-памяти.
        ArrayList<String> disallowList = disallowListCache.get(host);

        // Если в кэш-памяти нет списка, загрузить его.
        if (disallowList == null) {
            disallowList = new ArrayList<String>();
            try {
                URL robotsFileUrl = new URL("http://" + host + "/robots.txt");

                // Открыть файл робота заданного URL для чтения.
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(robotsFileUrl.openStream()));

                // Прочитать файл робота, создать список запрещенных путей.
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.indexOf("Disallow:") == 0) {
                        String disallowPath = line.substring("Disallow:".length());
                        // Просмотреть список запрещенных путей и удалить
                        // комментарии, если они есть.
                        int commentIndex = disallowPath.indexOf('#');
                        if (commentIndex != -1) {
                            disallowPath = disallowPath.substring(0, commentIndex);
                        }
                        // Удалить начальные или конечные пробелы из запрещенных путей.
                        disallowPath = disallowPath.trim();
                        // Добавить запрещенные пути в список.
                        disallowList.add(disallowPath);
                    }
                }
                // Добавить новый список в кэш-память.
                disallowListCache.put(host, disallowList);
            }
            catch (MalformedURLException ex) {
                Logger.getLogger(SearchCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(SearchCrawler.class.getName()).log(Level.SEVERE, null, ex);
                // Использовать присвоенного робота после генерации исключительной
                // ситуации при отсутствии файла робота.
                return true;
            }
        }

        // Просмотр списка запрещенных путей для проверки нахождения
        // в нем заданного URL.
        String file = urlToCheck.getFile();
        for (int i = 0; i < disallowList.size(); i++) {
            String disallow = disallowList.get(i);
            if (file.startsWith(disallow)) {
                return false;
            }
        }
        return true;
    }

    // Загрузить страницу с заданным URL.
    @SuppressWarnings("NestedAssignment")
    private String downloadPage(URL pageUrl) {
        try {
            // Открыть соединение по заданному URL для чтения.
            BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()));
            // Считать в буфер.
            String line;
            @SuppressWarnings("StringBufferWithoutInitialCapacity")
            StringBuilder pageBuffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                pageBuffer.append(line);
            }
            return pageBuffer.toString();
        }
        catch (IOException ex) {
            Logger.getLogger(SearchCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Удалить начальные символы "www" из адреса, если они присутствуют.
    private String removeWwwFromUrl(String url) {
        int index = url.indexOf("://www.");
        if (index != -1) {
            return url.substring(0, index + 3) + url.substring(index + 7);
        }
        return url;
    }

    // Произвести синтаксический анализ и возвратить ссылки.
    private ArrayList<String> retrieveLinks(URL pageUrl,
                                            String pageContents,
                                            HashSet<String> crawledList,
                                            boolean limitHost) 
    {
        // Компилировать ссылки шаблонов совпадений.
        Pattern p = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(pageContents);

        // Создать список совпадающих ссылок.
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> linkList = new ArrayList<String>();
        while (m.find()) {
            String link = m.group(1).trim();

            // Пропустить пустые ссылки.
            if (link.length() < 1) {
                continue;
            }

            // Пропустить ссылки, которые указывают на заданную страницу.
            if (link.charAt(0) == '#') {
                continue;
            }

            // Пропустить ссылки, которые используются для почтовых отправлений.
            if (link.indexOf("mailto:") != -1) {
                continue;
            }

            // Пропустить ссылки на сценарии JavaScript.
            if (link.toLowerCase().indexOf("javascript") != -1) {
                continue;
            }

            // Восстановить префикс абсолютного или относительного URL.
            if (link.indexOf("://") == -1) {
                // Обработать абсолютный URL.
                if (link.charAt(0) == '/') {
                    link = "http://" + pageUrl.getHost() + link;
                }
                else {  // Обработать относительный URL.
                    String file = pageUrl.getFile();
                    if (file.indexOf('/') == -1) {
                        link = "http://" + pageUrl.getHost() + "/" + link;
                    }
                    else {
                        String path = file.substring(0, file.lastIndexOf('/') + 1);
                        link = "http://" + pageUrl.getHost() + path + link;
                    }
                }
            }

            // Удалить привязки из ссылок.
            int index = link.indexOf('#');
            if (index != -1) {
                link = link.substring(0, index);
            }

            // Удалить начальные символы "www" из URL, если они есть.
            link = removeWwwFromUrl(link);

            // Проверить ссылки и отбросить все неправильные.
            URL verifiedLink = verifyUrl(link);
            if (verifiedLink == null) {
                continue;
            }

            // Если указано, то использовать только ссылки для сайта с начальным URL.
            if (limitHost && !pageUrl.getHost().toLowerCase().equals(verifiedLink.getHost().toLowerCase())) {
                continue;
            }

            // Отбросить ссылки, если они уже просмотрены.
            if (crawledList.contains(link)) {
                continue;
            }

            // Добавить ссылку в список.
            linkList.add(link);
        }
        return linkList;
    }

    // Определить, есть ли совпадения для строки поиска на данной странице.
    private boolean searchStringMatches(String pageContents, String searchString, boolean caseSensitive) {
        String searchContents = pageContents;

        // Если учитывается регистр клавиатуры, то преобразовать
        // содержимое в нижний регистр для сравнения.
        if (!caseSensitive) {
            searchContents = pageContents.toLowerCase();
        }

        // Разделить строку поиска на отдельные термы.
        Pattern p = Pattern.compile("[\\s]+");
        String[] terms = p.split(searchString);

        // Проверки на совпадения каждый терм.
        for (int i = 0; i < terms.length; i++) {
            if (caseSensitive) {
                if (searchContents.indexOf(terms[i]) == -1) {
                    return false;
                }
            }
            else {
                if (searchContents.indexOf(terms[i].toLowerCase()) == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    // Выполнить просмотр, производя поиск для заданной строки.
    public void crawl(  String startUrl,
                        int maxUrls,
                        boolean limitHost,
                        String searchString,
                        boolean caseSensitive)
    {
        // Установить список поиска.
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        HashSet<String> crawledList = new HashSet<String>();
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        LinkedHashSet<String> toCrawlList = new LinkedHashSet<String>();

        // Добавить начальный URL в список поиска.
        toCrawlList.add(startUrl);

        // Выполнить поиск, последовательно просматривая список поиска.
        while (crawling && toCrawlList.size() > 0) {
            // Проверить, не достигнуто ли максимальное число разрешенных
            // URL, если это значение задано.
            if (maxUrls != -1) {
                if (crawledList.size() == maxUrls) {
                    break;
                }
            }

            // Получить URL.
            String url = toCrawlList.iterator().next();

            // Удалить URL из списка поиска.
            toCrawlList.remove(url);

            // Преобразовать строку url в объект URL.
            URL verifiedUrl = verifyUrl(url);

            // Пропустить URL, если по списку робота к нему нет доступа.
            if (!isRobotAllowed(verifiedUrl)) {
                continue;
            }

            // Обновить панель Stats.
            updateStats(url, crawledList.size(), toCrawlList.size(), maxUrls);

            // Добавить страницу в список поиска.
            crawledList.add(url);

            // Загрузить страницу с заданным url.
            String pageContents = downloadPage(verifiedUrl);

            // Если страница успешно загружена, извлечь из нее все ссылки и
            // затем произвести поиск совпадающих строк.
            if (pageContents != null && pageContents.length() > 0) {
                // Извлечь список допустимых ссылок из страницы.
                ArrayList<String> links = retrieveLinks(verifiedUrl, pageContents, crawledList, limitHost);

                // Добавить ссылки в список поиска.
                toCrawlList.addAll(links);

                // Проверить на наличие совпадающей строки, и если совпадение есть,
                // то записать совпадение.
                if (searchStringMatches(pageContents, searchString, caseSensitive)) {
                    addMatch(url);
                }
            }

            // Обновить панель Stats.
            updateStats(url, crawledList.size(), toCrawlList.size(), maxUrls);
        }
    }

    // Запустить поискового червя.
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        SearchCrawler crawler = new SearchCrawler();
        crawler.show();
    }
}
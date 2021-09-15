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
 * ��������� "�����" ��� Web
 * @author dwinner@inbox.ru
 */
public class SearchCrawler extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(SearchCrawler.class.getName());

    // ������������ ���������� �������������� �������� URL.
    private static final String[] MAX_URLS = {
        "50",
        "100",
        "500",
        "1000"
    };

    // ���-������ ��� ������ ����������� ������.
    @SuppressWarnings("CollectionWithoutInitialCapacity")
    private HashMap<String, ArrayList<String>> disallowListCache = new HashMap<String, ArrayList<String>>();

    // �������� ���������� ������������ ���������� ������ Search.
    private JTextField startTextField;
    private JComboBox maxComboBox;
    private JCheckBox limitCheckBox;
    private JTextField logTextField;
    private JTextField searchTextField;
    private JCheckBox caseCheckBox;
    private JButton searchButton;

    // �������� ���������� ������������ ���������� ������ Stats.
    private JLabel crawlingLabel2;
    private JLabel crawledLabel2;
    private JLabel toCrawlLabel2;
    private JProgressBar progressBar;
    private JLabel matchesLabel2;

    // ������ ������������.
    private JTable table;

    // ���� ����������� ��������� ������.
    private boolean crawling;

    // ���� ������� ��� ��������� ������.
    private PrintWriter logFileWriter;

    // ����������� ��� ��������� �����.
    @SuppressWarnings("unchecked")
    public SearchCrawler() {
        // ��������� ��������� ����������.
        setTitle("Search Crawler");

        // ��������� �������� ����.
        setSize(640, 480);

        // ������ �������� ������� ����.
        setResizable(false);

        // ��������� ������� �� �������� ����.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                actionExit();
            }
        });

        // ���������� ���� "����".
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

        // ���������� ������ ������.
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

        // ���������� ������� ����������.
        table = new JTable(new DefaultTableModel(new Object[][]{}, new String[]{"URL"}) {
            private static final long serialVersionUID = 02L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        // ���������� ������ ����������.
        JPanel matchesPanel = new JPanel();
        matchesPanel.setBorder(BorderFactory.createTitledBorder("Matches"));
        matchesPanel.setLayout(new BorderLayout());
        matchesPanel.add(new JScrollPane(table), BorderLayout.CENTER);

        // ���������� ������ �� �������.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(matchesPanel, BorderLayout.CENTER);
    }

    // ����� �� ���������.
    private void actionExit() {
        System.exit(0);
    }

    // ���������� ������ �� ������ search/stop.
    private void actionSearch() {
        // ���� ���������� ������ �� ������ stop, �������� ����.
        if (crawling) {
            crawling = false;
            return;
        }
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> errorList = new ArrayList<String>();

        // ��������� ���� ���������� ������ (URL).
        String startUrl = startTextField.getText().trim();
        if (startUrl.length() < 1) {
            errorList.add("Missing Start URL.");
        }
        else if (verifyUrl(startUrl) == null) { // ��������� ��������� URL.
            errorList.add("Invalid Start URL");
        }

        // ���������, ��� ������� �������� ��� ����������� �����������
        // ���������� ������� � ��� ��� �����.
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

        // ���������, ��� ���� � �������� ���������� ����������.
        String logFile = logTextField.getText().trim();
        if (logFile.length() < 1) {
            errorList.add("Missing Matches Log File.");
        }

        // ���������, ��� ������� ������ ��� ������.
        String searchString = searchTextField.getText().trim();
        if (searchString.length() < 1) {
            errorList.add("Missing Search String.");
        }

        // �������� ������, ���� ��� ����, � �������.
        if (errorList.size() > 0) {
            @SuppressWarnings("StringBufferWithoutInitialCapacity")
            StringBuilder message = new StringBuilder();

            // ���������� ������ � ���� ���������.
            for (int i = 0; i < errorList.size(); i++) {
                message.append(errorList.get(i));
                if (i + 1 < errorList.size()) {
                    message.append("\n");
                }
            }
            showError(message.toString());
            return;
        }

        // ������� ������� "www" �� ���������� URL, ���� ��� ����.
        startUrl = removeWwwFromUrl(startUrl);

        // ��������� ���������� �����.
        search(logFile, startUrl, maxUrls, searchString);
    }

    private void search(final String logFile,
                        final String startUrl,
                        final int maxUrls,
                        final String searchString)
    {
        // ������ ����� � ����� ������.
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // ���������� �������� ���� �� ����� ������ ���������� �����.
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                // ������������� �������� ���������� ������.
                startTextField.setEnabled(false);
                maxComboBox.setEnabled(false);
                limitCheckBox.setEnabled(false);
                logTextField.setEnabled(false);
                searchTextField.setEnabled(false);
                caseCheckBox.setEnabled(false);

                // ����������� ������ ������ � ��������� "Stop."
                searchButton.setText("Stop");

                // �������������� ������ Stats.
                table.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"URL"}) {
                    private static final long serialVersionUID = 9L;
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                updateStats(startUrl, 0, 0, maxUrls);

                // ������� ������ ����������.
                try {
                    logFileWriter = new PrintWriter(new FileWriter(logFile));
                }
                catch (Exception e) {
                    showError("Unable to open matches log file.");
                    return;
                }

                // ���������� ���� ������.
                crawling = true;

                // ��������� �������� �����.
                crawl(startUrl, maxUrls, limitCheckBox.isSelected(), searchString, caseCheckBox.isSelected());

                // �������� ���� ������.
                crawling = false;

                // ������� ������ ����������.
                try {
                    logFileWriter.close();
                }
                catch (Exception e) {
                    showError("Unable to close matches log file.");
                }

                // �������� ��������� ������.
                crawlingLabel2.setText("Done");

                // �������������� �������� �������� ������.
                startTextField.setEnabled(true);
                maxComboBox.setEnabled(true);
                limitCheckBox.setEnabled(true);
                logTextField.setEnabled(true);
                searchTextField.setEnabled(true);
                caseCheckBox.setEnabled(true);

                // ����������� ������ ������ � ��������� "Search."
                searchButton.setText("Search");

                // ���������� ������ �� ���������.
                setCursor(Cursor.getDefaultCursor());

                // ���������� ���������, ���� ������ �� �������.
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

    // ���������� ���������� ���� � ���������� �� ������.
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // �������� ������ stats.
    private void updateStats(String crawling, int crawled, int toCrawl, int maxUrls) {
        crawlingLabel2.setText(crawling);
        crawledLabel2.setText("" + crawled);
        toCrawlLabel2.setText("" + toCrawl);

        // �������� ��������� ����������.
        if (maxUrls == -1) {
            progressBar.setMaximum(crawled + toCrawl);
        }
        else {
            progressBar.setMaximum(maxUrls);
        }
        progressBar.setValue(crawled);
        matchesLabel2.setText("" + table.getRowCount());
    }

    // �������� ���������� � ������� ���������� � � ������ ����������.
    private void addMatch(String url) {
        // �������� URL � ������� ����������.
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] {url});

        // �������� URL � ������ ����������.
        try {
            logFileWriter.println(url);
        }
        catch (Exception e) {
            showError("Unable to log match.");
        }
    }

    // ��������� ������ URL.
    private URL verifyUrl(String url) {
        // ��������� ������ ������ HTTP.
        if (!url.toLowerCase().startsWith("http://")) {
            return null;
        }
        // ��������� ������ URL.
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

    // ���������, ���� ����� ��������� ������ � ������� URL.
    @SuppressWarnings({"NestedAssignment", "CollectionWithoutInitialCapacity"})
    private boolean isRobotAllowed(URL urlToCheck) {
        String host = urlToCheck.getHost().toLowerCase();
        // ������� ������ ����������� ����� �� ���-������.
        ArrayList<String> disallowList = disallowListCache.get(host);

        // ���� � ���-������ ��� ������, ��������� ���.
        if (disallowList == null) {
            disallowList = new ArrayList<String>();
            try {
                URL robotsFileUrl = new URL("http://" + host + "/robots.txt");

                // ������� ���� ������ ��������� URL ��� ������.
                BufferedReader reader;
                reader = new BufferedReader(new InputStreamReader(robotsFileUrl.openStream()));

                // ��������� ���� ������, ������� ������ ����������� �����.
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.indexOf("Disallow:") == 0) {
                        String disallowPath = line.substring("Disallow:".length());
                        // ����������� ������ ����������� ����� � �������
                        // �����������, ���� ��� ����.
                        int commentIndex = disallowPath.indexOf('#');
                        if (commentIndex != -1) {
                            disallowPath = disallowPath.substring(0, commentIndex);
                        }
                        // ������� ��������� ��� �������� ������� �� ����������� �����.
                        disallowPath = disallowPath.trim();
                        // �������� ����������� ���� � ������.
                        disallowList.add(disallowPath);
                    }
                }
                // �������� ����� ������ � ���-������.
                disallowListCache.put(host, disallowList);
            }
            catch (MalformedURLException ex) {
                Logger.getLogger(SearchCrawler.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex) {
                Logger.getLogger(SearchCrawler.class.getName()).log(Level.SEVERE, null, ex);
                // ������������ ������������ ������ ����� ��������� ��������������
                // �������� ��� ���������� ����� ������.
                return true;
            }
        }

        // �������� ������ ����������� ����� ��� �������� ����������
        // � ��� ��������� URL.
        String file = urlToCheck.getFile();
        for (int i = 0; i < disallowList.size(); i++) {
            String disallow = disallowList.get(i);
            if (file.startsWith(disallow)) {
                return false;
            }
        }
        return true;
    }

    // ��������� �������� � �������� URL.
    @SuppressWarnings("NestedAssignment")
    private String downloadPage(URL pageUrl) {
        try {
            // ������� ���������� �� ��������� URL ��� ������.
            BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()));
            // ������� � �����.
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

    // ������� ��������� ������� "www" �� ������, ���� ��� ������������.
    private String removeWwwFromUrl(String url) {
        int index = url.indexOf("://www.");
        if (index != -1) {
            return url.substring(0, index + 3) + url.substring(index + 7);
        }
        return url;
    }

    // ���������� �������������� ������ � ���������� ������.
    private ArrayList<String> retrieveLinks(URL pageUrl,
                                            String pageContents,
                                            HashSet<String> crawledList,
                                            boolean limitHost) 
    {
        // ������������� ������ �������� ����������.
        Pattern p = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(pageContents);

        // ������� ������ ����������� ������.
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        ArrayList<String> linkList = new ArrayList<String>();
        while (m.find()) {
            String link = m.group(1).trim();

            // ���������� ������ ������.
            if (link.length() < 1) {
                continue;
            }

            // ���������� ������, ������� ��������� �� �������� ��������.
            if (link.charAt(0) == '#') {
                continue;
            }

            // ���������� ������, ������� ������������ ��� �������� �����������.
            if (link.indexOf("mailto:") != -1) {
                continue;
            }

            // ���������� ������ �� �������� JavaScript.
            if (link.toLowerCase().indexOf("javascript") != -1) {
                continue;
            }

            // ������������ ������� ����������� ��� �������������� URL.
            if (link.indexOf("://") == -1) {
                // ���������� ���������� URL.
                if (link.charAt(0) == '/') {
                    link = "http://" + pageUrl.getHost() + link;
                }
                else {  // ���������� ������������� URL.
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

            // ������� �������� �� ������.
            int index = link.indexOf('#');
            if (index != -1) {
                link = link.substring(0, index);
            }

            // ������� ��������� ������� "www" �� URL, ���� ��� ����.
            link = removeWwwFromUrl(link);

            // ��������� ������ � ��������� ��� ������������.
            URL verifiedLink = verifyUrl(link);
            if (verifiedLink == null) {
                continue;
            }

            // ���� �������, �� ������������ ������ ������ ��� ����� � ��������� URL.
            if (limitHost && !pageUrl.getHost().toLowerCase().equals(verifiedLink.getHost().toLowerCase())) {
                continue;
            }

            // ��������� ������, ���� ��� ��� �����������.
            if (crawledList.contains(link)) {
                continue;
            }

            // �������� ������ � ������.
            linkList.add(link);
        }
        return linkList;
    }

    // ����������, ���� �� ���������� ��� ������ ������ �� ������ ��������.
    private boolean searchStringMatches(String pageContents, String searchString, boolean caseSensitive) {
        String searchContents = pageContents;

        // ���� ����������� ������� ����������, �� �������������
        // ���������� � ������ ������� ��� ���������.
        if (!caseSensitive) {
            searchContents = pageContents.toLowerCase();
        }

        // ��������� ������ ������ �� ��������� �����.
        Pattern p = Pattern.compile("[\\s]+");
        String[] terms = p.split(searchString);

        // �������� �� ���������� ������ ����.
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

    // ��������� ��������, ��������� ����� ��� �������� ������.
    public void crawl(  String startUrl,
                        int maxUrls,
                        boolean limitHost,
                        String searchString,
                        boolean caseSensitive)
    {
        // ���������� ������ ������.
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        HashSet<String> crawledList = new HashSet<String>();
        @SuppressWarnings("CollectionWithoutInitialCapacity")
        LinkedHashSet<String> toCrawlList = new LinkedHashSet<String>();

        // �������� ��������� URL � ������ ������.
        toCrawlList.add(startUrl);

        // ��������� �����, ��������������� ������������ ������ ������.
        while (crawling && toCrawlList.size() > 0) {
            // ���������, �� ���������� �� ������������ ����� �����������
            // URL, ���� ��� �������� ������.
            if (maxUrls != -1) {
                if (crawledList.size() == maxUrls) {
                    break;
                }
            }

            // �������� URL.
            String url = toCrawlList.iterator().next();

            // ������� URL �� ������ ������.
            toCrawlList.remove(url);

            // ������������� ������ url � ������ URL.
            URL verifiedUrl = verifyUrl(url);

            // ���������� URL, ���� �� ������ ������ � ���� ��� �������.
            if (!isRobotAllowed(verifiedUrl)) {
                continue;
            }

            // �������� ������ Stats.
            updateStats(url, crawledList.size(), toCrawlList.size(), maxUrls);

            // �������� �������� � ������ ������.
            crawledList.add(url);

            // ��������� �������� � �������� url.
            String pageContents = downloadPage(verifiedUrl);

            // ���� �������� ������� ���������, ������� �� ��� ��� ������ �
            // ����� ���������� ����� ����������� �����.
            if (pageContents != null && pageContents.length() > 0) {
                // ������� ������ ���������� ������ �� ��������.
                ArrayList<String> links = retrieveLinks(verifiedUrl, pageContents, crawledList, limitHost);

                // �������� ������ � ������ ������.
                toCrawlList.addAll(links);

                // ��������� �� ������� ����������� ������, � ���� ���������� ����,
                // �� �������� ����������.
                if (searchStringMatches(pageContents, searchString, caseSensitive)) {
                    addMatch(url);
                }
            }

            // �������� ������ Stats.
            updateStats(url, crawledList.size(), toCrawlList.size(), maxUrls);
        }
    }

    // ��������� ���������� �����.
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        SearchCrawler crawler = new SearchCrawler();
        crawler.show();
    }
}
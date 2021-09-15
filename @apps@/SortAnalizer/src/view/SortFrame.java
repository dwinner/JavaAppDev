package view;

import apputils.ArrayGenerator;
import apputils.SortAlgorithm;
import apputils.SortingAlgs;
import controller.SortTask;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.LayerUI;
import model.SortEntity;
import model.SortResultTableModel;

/**
 * Главное окно для сортировки.
 *
 * @author Denis
 * @version 0.0.1 15-12-2012
 */
public class SortFrame extends JFrame
{
   public static SortFrame getInstance()
   {
      return INSTANCE;
   }

   private static final SortFrame INSTANCE = new SortFrame();

   /**
    * Конструктор главного окна приложения.
    *
    * @throws HeadlessException
    */
   private SortFrame() throws HeadlessException
   {
      setTitle("Sort Analizer");   // TODO: i18n
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      setJMenuBar(MenuBarWizard.getInstance().getAppMenuBar());
      initComponents();
   }

   /**
    * Инициализация UI-компонентов.
    */
   private void initComponents()
   {
      // Верхняя панель ввода
      JPanel topControlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      JLabel numberLabel = new JLabel("Number of elements:");  // TODO: i18n
      startSortButton = new JButton("Start sorting"); // TODO: i18n
      LayerUI<JTextField> layerUI = new ValidationLayerUI();
      numberField = new JTextField("" + 10000);
      numberField.setPreferredSize(
        new Dimension(startSortButton.getPreferredSize().width,
                      startSortButton.getPreferredSize().height));
      numberField.getDocument().addDocumentListener(new NumberTextHandler());
      SortButtonHandler sortButtonHandler = new SortButtonHandler();
      startSortButton.addActionListener(sortButtonHandler);
      numberField.addActionListener(sortButtonHandler);
      topControlPanel.add(numberLabel);
      topControlPanel.add(new JLayer<>(numberField, layerUI));
      topControlPanel.add(startSortButton);
      topControlPanel.setBorder(
        BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                                           EMPTY_BORDER));
      add(topControlPanel, BorderLayout.NORTH);

      // Центральная панель таблицы
      tableModel = new SortResultTableModel();
      sortTable = new JTable(tableModel);
      sortTable.setAutoCreateRowSorter(true);
      sortTable.setCellSelectionEnabled(true);
      sortTable.setDefaultRenderer(JProgressBar.class,
                                   TableRendererFactory.createProgressTableCellRenderer());
      sortTable.setDefaultRenderer(JButton.class,
                                   TableRendererFactory.createButtonTableCellRenderer());
      JScrollPane scrollPane = new JScrollPane(sortTable);
      scrollPane.setBorder(
        BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                                           EMPTY_BORDER));
      add(scrollPane);

      // Нижняя панель ошибок
      JPanel bottomErrorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
      errorLabel = new JLabel();
      bottomErrorPanel.add(errorLabel);
      bottomErrorPanel.setBorder(
        BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
                                           EMPTY_BORDER));
      add(bottomErrorPanel, BorderLayout.SOUTH);
   }

   /**
    * Проверка, является ли введенное значение целым положительным числом.
    *
    * @return Положительное число, или значение -1 как индикатор ошибки.
    */
   private int parseNumberField()
   {
      try
      {
         int number = Integer.parseInt(numberField.getText().trim());
         if (number <= 0)
         {
            return -1;
         }
         return number;
      }
      catch (final NumberFormatException nfEx)
      {
         Logger.getLogger(
           getClass().getName()).log(Level.INFO, nfEx.getLocalizedMessage(), nfEx);
         return -1;
      }
   }

   private JTable sortTable;
   private SortResultTableModel tableModel;
   private JTextField numberField;
   private JButton startSortButton;
   private JLabel errorLabel;
   private static final ExecutorService EXEC_SERVICE =
     Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
   private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(20, 10, 20, 10);
   private static final int DEFAULT_WIDTH = 640;
   private static final int DEFAULT_HEIGHT = 480;

   static
   {
      Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            if (!EXEC_SERVICE.isShutdown())
            {
               EXEC_SERVICE.shutdownNow();
            }
         }

      }));
   }

   /**
    * Обработка текстового поля для ввода чисел.
    */
   private class NumberTextHandler implements DocumentListener
   {
      @Override
      public void insertUpdate(DocumentEvent documentEvent)
      {
         String labelText = parseNumberField() == -1
           ? "Error: incorrect number" : "";   // TODO: i18n
         errorLabel.setText(labelText);
      }

      @Override
      public void removeUpdate(DocumentEvent documentEvent)
      {
         String labelText = parseNumberField() == -1
           ? "Error: incorrect number" : "";   // TODO: i18n
         errorLabel.setText(labelText);
      }

      @Override
      public void changedUpdate(DocumentEvent documentEvent)
      {
      }

   }

   /**
    * Обработка действий для кнопки запуска сортировки.
    */
   private class SortButtonHandler implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent actionEvent)
      {
         int arrayLen = parseNumberField();
         if (arrayLen == -1)
         {
            errorLabel.setText("Error: incorrect number");  // TODO: i18n
            return;
         }
         reinitWorker(arrayLen);
         swingWorker.execute();
         reinitWatcherTimer();
      }

      /**
       * Повторная инициализация рабочего потока.
       */
      private void reinitWorker(final int arrayLen)
      {
         startSortButton.setEnabled(false);
         numberField.setEnabled(false);
         swingWorker = new SwingWorker<Void, Void>()  // FIXME: Лишнее создание объектов
         {
            @Override
            protected Void doInBackground() throws Exception
            {
               Double[] newArray = ArrayGenerator.newArray(Double.class, arrayLen);
               ArrayGenerator.fillArrayByRandomValues(newArray);
               if (!sortTasks.isEmpty())
               {
                  sortTasks.clear();
               }
               tableModel.clearAllEntities();
               for (SortingAlgs aSortingAlg : SortingAlgs.values())
               {
                  SortAlgorithm sortAlg = SortAlgorithm.newSortAlgorithm(aSortingAlg);
                  SortTask sortTask = new SortTask(sortAlg, newArray);
                  sortTasks.add(sortTask);
                  tableModel.addEntity(sortAlg.getSortEntity());
               }
               if (!futures.isEmpty())
               {
                  futures.clear();
               }
               for (SortTask sortTask : sortTasks)
               {
                  futures.add(EXEC_SERVICE.submit(sortTask));
               }
               // EXEC_SERVICE.shutdown();
               return null;
            }

         };
      }

      /**
       * Повторная инициализация таймера освобождения UI.
       */
      private void reinitWatcherTimer()
      {
         sortWatcher = new java.util.Timer();   // FIXME: Лишнее создание объектов
         sortWatcher.schedule(new TimerTask()
         {
            @Override
            public void run()
            {
               outerLoopLabel:
               while (true)
               {
                  boolean readyToBreak = true;
                  for (SortEntity sortEntity : tableModel.getSortEntities())
                  {
                     if (sortEntity.getSortProgress() != 100.0)
                     {
                        readyToBreak = false;
                        continue outerLoopLabel;
                     }
                  }
                  if (readyToBreak)
                  {
                     break;
                  }
               }
               startSortButton.setEnabled(true);
               numberField.setEnabled(true);
            }
            // FIXME: Полагаемся на то, что futures заполнится другим потоком в течение 100 миллисекунд

         }, 1000);
      }

      private SortButtonHandler()
      {
      }

      private final List<SortTask> sortTasks = new LinkedList<>();
      private final List<Future<?>> futures = new LinkedList<>();
      private SwingWorker<Void, Void> swingWorker;
      private java.util.Timer sortWatcher;
   }

   /**
    * Слой-декоратор для проверки поля на числа.
    */
   private class ValidationLayerUI extends LayerUI<JTextField>
   {
      @Override
      public void paint(Graphics g, JComponent c)
      {
         super.paint(g, c);
         JLayer<?> jLayer = (JLayer<?>) c;
         JTextField textField = (JTextField) jLayer.getView();
         boolean isNumber = true;
         int parsedInt = 1;
         try
         {
            parsedInt = Integer.parseInt(textField.getText().trim());
         }
         catch (NumberFormatException nfEx)
         {
            isNumber = false;
         }
         if (!isNumber || parsedInt <= 0)
         {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            int w = c.getWidth();
            int h = c.getHeight();
            int s = 8;
            int pad = 4;
            int x = w - pad - s;
            int y = (h - s) / 2;
            g2.setPaint(Color.red);
            g2.fillRect(x, y, s + 1, s + 1);
            g2.setPaint(Color.white);
            g2.drawLine(x, y, x + s, y + s);
            g2.drawLine(x, y + s, x + s, y);
            g2.dispose();
         }
      }

   }

}

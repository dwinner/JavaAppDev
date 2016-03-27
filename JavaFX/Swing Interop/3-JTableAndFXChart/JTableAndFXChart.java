package pkg3.jtableandfxchart;

import java.awt.*;
import java.text.DecimalFormat;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Взаимодействие между таблицами Swing и JFX-диаграммами.
 *
 * @author Denis
 */
public class JTableAndFXChart extends JApplet
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                       UnsupportedLookAndFeelException e)
                {
                    JOptionPane.showMessageDialog(null, "Unexpected error");
                    System.exit(1);
                }

                JFrame frame = new JFrame("Swing JTable");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JApplet applet = new JTableAndFXChart();
                applet.init();

                frame.setContentPane(applet.getContentPane());

                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);

                // applet.start();
            }
        });
    }
    private static final int PANEL_WIDTH_INT = 600;
    private static final int PANEL_HEIGHT_INT = 400;
    private static final int TABLE_PANEL_HEIGHT_INT = 100;
    private static JFXPanel chartFxPanel;
    private static SampleTableModel tableModel;
    private Chart chart;

    @Override
    public void init()
    {
        tableModel = new SampleTableModel();

        // Создание JFX-панели для диаграмм.

        chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));

        // Создание таблицы JTable

        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        table.setGridColor(Color.DARK_GRAY);
        DecimalFormatRenderer renderer = new DecimalFormatRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int i = 0; i < table.getColumnCount(); i++)
        {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, TABLE_PANEL_HEIGHT_INT));

        JPanel chartTablePanel = new JPanel();
        chartTablePanel.setLayout(new BorderLayout());

        // Создание разделительной панели.

        JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setTopComponent(chartTablePanel);
        jSplitPane.setBottomComponent(tablePanel);
        jSplitPane.setDividerLocation(410);
        chartTablePanel.add(chartFxPanel, BorderLayout.CENTER);

        add(jSplitPane, BorderLayout.CENTER);

        // Создание сцены.

        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                createScene();
            }
        });
    }

    private void createScene()
    {
        chart = createBarChart();
        chartFxPanel.setScene(new Scene(chart));
    }

    private Chart createBarChart()
    {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList(tableModel.getColumnNames()));
        xAxis.setLabel("Year");

        double tickUnit = tableModel.getTickUnit();

        NumberAxis yAxis = new NumberAxis();
        yAxis.setTickUnit(tickUnit);
        yAxis.setLabel("Units Sold");

        @SuppressWarnings("unchecked")
        final BarChart _chart = new BarChart(xAxis, yAxis, tableModel.getBarChartData());
        tableModel.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tableModelEvent)
            {
                if (tableModelEvent.getType() == TableModelEvent.UPDATE)
                {
                    final int row = tableModelEvent.getFirstRow();
                    final int column = tableModelEvent.getColumn();
                    final Object value =
                       ((SampleTableModel) tableModelEvent.getSource()).getValueAt(row, column);

                    Platform.runLater(new Runnable()
                    {
                        @Override
                        @SuppressWarnings("unchecked")
                        public void run()
                        {
                            XYChart.Series<String, Number> s =
                               (XYChart.Series<String, Number>) _chart.getData().get(row);
                            BarChart.Data data = s.getData().get(column);
                            data.setYValue(value);
                        }
                    });
                }
            }
        });
        return _chart;
    }

    private static class DecimalFormatRenderer extends DefaultTableCellRenderer
    {
        private static final DecimalFormat formatter = new DecimalFormat("#.0");

        DecimalFormatRenderer()
        {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row,
                                                       int column)
        {
            Object _value = formatter.format((Number) value);
            return super.getTableCellRendererComponent(table, _value, isSelected, hasFocus, row, column);
        }
    }

}

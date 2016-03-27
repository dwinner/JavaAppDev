package statistics;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

/**
 * Обработка и отображение статистических данных.
 * @author dwinner@inbox.ru
 */
public class StatsWin extends Frame implements ItemListener, ActionListener {
    
    private static final long serialVersionUID = 1L;
    
    private NumberFormat nf = NumberFormat.getInstance();
    private TextArea statsTA;
    private Checkbox bar = new Checkbox("Bar Graph");
    private Checkbox scatter = new Checkbox("Scatter Graph");
    private Checkbox regplot = new Checkbox("Regression Line Plot");
    private Checkbox datawin = new Checkbox("Show Data");
    private double[] data;
    private Graphs bg;
    private Graphs sg;
    private Graphs rp;
    private DataWin da;
    private RegData rd;
    
    @SuppressWarnings("LeakingThisInConstructor")
    public StatsWin(double vals[]) {
        data = vals;    // Сохранить ссылку на данные.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                shutdown();
            }
        });
        
        // Создать меню file.
        createMenu();
        
        // Изменить расположение окна, разместив его по центру.
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setSize(new Dimension(300, 240));
        setTitle("Statistical Data");
        rd = Stats.regress(data);
        
        // Установить цифровой формат с двумя десятичными цифрами.
        nf.setMaximumFractionDigits(2);
        
        // Создать выходную строку.
        String mstr;
        try {
            // Получить режим, если он установлен.
            mstr = nf.format(Stats.mode(data));
        }
        catch (NoModeException exc) {
            mstr = exc.toString();
        }
        String str =    "Mean: " + nf.format(Stats.mean(data)) + "\n" +
                        "Median: " + nf.format(Stats.median(data)) + "\n" + 
                        "Mode: " + mstr + "\n" +
                        "Standart Deviation: " + nf.format(Stats.stdDev(data)) + "\n\n" +
                        "Regression equation: " + rd.getEquation() + "\n" +
                        "Correlation coefficient: " + nf.format(rd.getCor());
        // Разместить выход в текстовом пространстве.
        statsTA = new TextArea(str, 6, 38, TextArea.SCROLLBARS_NONE);
        statsTA.setEditable(false);
        
        // Добавить компоненты в окно.
        add(statsTA);
        add(bar);
        add(scatter);
        add(regplot);
        add(datawin);
        
        // Добавить слушателей компонентов.
        bar.addItemListener(this);
        scatter.addItemListener(this);
        regplot.addItemListener(this);
        datawin.addItemListener(this);
        
        setVisible(true);
    }
    
    // Обработать пункт меню "Close".
    @Override
    public void actionPerformed(ActionEvent ae) {
        String arg = ae.getActionCommand();
        if ("Close".equals(arg)) {
            shutdown();
        }
    }
    
    // Пользователь изменил состояние флажка.
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (bar.getState()) {
            if (bg == null) {
                bg = new Graphs(data, Graphs.BAR);
                bg.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        bar.setState(false);
                        bg = null;
                    }
                });
            }
        }
        else {
            if (bg != null) {
                bg.dispose();
                bg = null;
            }
        }
        if (scatter.getState()) {
            if (sg == null) {
                sg = new Graphs(data, Graphs.SCATTER);
                sg.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        scatter.setState(false);
                        sg = null;
                    }
                });
            }
        }
        else {
            if (sg != null) {
                sg.dispose();
                sg = null;
            }
        }
        if (regplot.getState()) {
            if (rp == null) {
                rp = new Graphs(data, Graphs.REGPLOT);
                rp.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        regplot.setState(false);
                        rp = null;
                    }
                });
            }
        }
        else {
            if (rp != null) {
                rp.dispose();
                rp = null;
            }
        }
        if (datawin.getState()) {
            if (da == null) {
                da = new DataWin(data);
                da.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        datawin.setState(false);
                        da = null;
                    }
                });
            }
        }
        else {
            if (da != null) {
                da.dispose();
                da = null;
            }
        }
    }
    
    // Создать пункты меню для работы с файлами.
    private void createMenu() {
        MenuBar mbar = new MenuBar();
        setMenuBar(mbar);
        Menu file = new Menu("File");
        MenuItem close = new MenuItem("Close");
        file.add(close);
        mbar.add(file);
        close.addActionListener(this);
    }
    
    // Закрыть окно.
    private void shutdown() {
        if (bg != null) {
            bg.dispose();
        }
        if (sg != null) {
            sg.dispose();
        }
        if (rp != null) {
            rp.dispose();
        }
        if (da != null) {
            da.dispose();
        }
        setVisible(false);
        dispose();
    }

}
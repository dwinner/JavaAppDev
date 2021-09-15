package statistics;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Графический класс общего назначения.
 * @author dwinner@inbox.ru
 */
public class Graphs extends Frame {
    private static final long serialVersionUID = 2L;
    // Константы для типов графиков.
    public final static int BAR = 0;
    public final static int SCATTER = 1;
    public final static int REGPLOT = 2;
    private int graphStyle;

    // Здесь устанавливается размер пространства, которое должно оставаться
    // между данными и рамкой окна.
    private final int leftGap = 2;
    private final int topGap = 2;
    private final int bottomGap = 2;
    private int rightGap;   // Это значение рассчитывается.

    // В этих переменных сохраняются максимальное и минимальное значения данных.
    private double min, max;

    // Ссылка на данные.
    private double[] data;

    // Цвета, используемые графиком.
    private Color gridColor = new Color(0, 150, 150);
    private Color dataColor = new Color(0, 0, 0);

    // Различные переменные, используемые для масштабирования
    // и отображения данных.
    private int hGap;   // Расстояние между данными.
    private int spread; // Расстояние между значениями min и max.
    private double scale;   // Коэффициент масштабирования.
    private int baseline;   // Вертикальная координата базовой линии.

    // Размещение пространства данных в окне.
    private int top, bottom, left, right;

    public Graphs(double[] vals, int style) {
        // Обработка закрытия окна.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
            }
        });

        // Обработка изменения размеров.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                repaint();
            }
        });
        graphStyle = style;
        data = vals;

        // Сортировка данных при нахождении значений min и max.
        double t[] = new double[vals.length];
        System.arraycopy(vals, 0, t, 0, vals.length);
        Arrays.sort(t);
        min = t[0];
        max = t[t.length - 1];
        setSize(new Dimension(200, 120));
        switch (graphStyle) {
            case BAR:
                setTitle("Bar Graph");
                setLocation(25, 250);
                break;
            case SCATTER:
                setTitle("Scatter Graph");
                setLocation(250, 250);
                break;
            case REGPLOT:
                setTitle("Regression Plot");
                setLocation(475, 250);
                break;
        }
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Dimension winSize = getSize();  // Размер окна.
        Insets ins = getInsets();       // Размер рамки.

        // Получить размер текущего символа.
        FontMetrics fm = g.getFontMetrics();

        // Подсчитать правый интервал.
        rightGap = fm.stringWidth("" + data.length);

        // Подсчитать размер области данных.
        left = ins.left + leftGap + fm.charWidth('0');
        top = ins.top + topGap + fm.getAscent();
        bottom = ins.bottom + bottomGap + fm.getAscent();
        right = ins.right + rightGap;

        // Если минимальное значение положительное, то использовать 0 как
        // начальную точку графика. Если максимальное значение отрицательное,
        // используйте 0.
        if (min > 0) {
            min = 0;
        }
        if (max < 0) {
            max = 0;
        }

        // Подсчитать расстояние между максимальным и минимальным значениями.
        spread = (int) (max - min);

        // Подсчитать коэффициент масштабирования.
        scale = (double) (winSize.height - bottom - top) / spread;

        // Найти расположение базовой линии.
        baseline = (int) (winSize.height - bottom + min * scale);

        // Подсчитать расстояние между данными.
        hGap = (winSize.width - left - right) / (data.length - 1);

        // Установить цвет сетки.
        g.setColor(gridColor);

        // Нарисовать базовую линию.
        g.drawLine(left, baseline, left + (data.length-1) * hGap, baseline);

        // Нарисовать ось Y.
        if (graphStyle != BAR) {
            g.drawLine(left, winSize.height - bottom, left, top);
        }

        // Отобразить значения min, max и 0.
        g.drawString("0", ins.left, baseline + fm.getAscent()/2);
        if (max != 0) {
            g.drawString("" + max, ins.left, baseline - (int) (max*scale) - 4);
        }
        if (min != 0) {
            g.drawString("" + min, ins.left, baseline - (int) (min*scale) + fm.getAscent());
        }

        // Отобразить номера значений.
        g.drawString("" + data.length, (data.length-1) * (hGap) + left, baseline + fm.getAscent());

        // Установить цвет данных.
        g.setColor(dataColor);

        // Отобразить данные.
        switch (graphStyle) {
            case BAR:
                bargraph(g);
                break;
            case SCATTER:
                scatter(g);
                break;
            case REGPLOT:
                regplot(g);
                break;
        }
    }

    // Отобразить гистограмму.
    private void bargraph(Graphics g) {
        int v;
        for (int i=0; i<data.length; i++) {
            v = (int) (data[i] * scale);
            g.drawLine(i*hGap+left, baseline, i*hGap+left, baseline-v);
        }
    }

    // Отобразить график в виде точек.
    private void scatter(Graphics g) {
        int v;
        for (int i=0; i<data.length; i++) {
            v = (int) (data[i] * scale);
            g.drawRect(i*hGap+left, baseline-v, 1, 1);
        }
    }

    // Отобразить точки графика и линию регрессии.
    private void regplot(Graphics g) {
        int v;
        RegData rd = Stats.regress(data);
        for (int i=0; i<data.length; i++) {
            v = (int) (data[i] * scale);
            g.drawRect(i*hGap+left, baseline-v, 1, 1);
        }

        // Нарисовать линию регрессии.
        g.drawLine(left, baseline-(int)((rd.getA())*scale), hGap*(data.length-1)+left+1, baseline-(int)((rd.getA()+(rd.getB()*(data.length-1)))*scale));
    }

    /**
     * @return the gridColor
     */
    public Color getGridColor() {
        return gridColor;
    }

    /**
     * @param gridColor the gridColor to set
     */
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    /**
     * @return the dataColor
     */
    public Color getDataColor() {
        return dataColor;
    }

    /**
     * @param dataColor the dataColor to set
     */
    public void setDataColor(Color dataColor) {
        this.dataColor = dataColor;
    }
}
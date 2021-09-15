package statistics;

import java.util.*;
import java.text.*;

/**
 * Это класс исключения для метода Mode().
 * @author dwinner@inbox.ru
 */
class NoModeException extends Exception {
    private static final long serialVersionUID = 1L;
    @Override
    public String toString() {
        return "Set contains no mode.";
    }
}

/**
 * Класс общего назначения для статистичеких вычислений.
 * @author dwinner@inbox.ru
 */
public class Stats {

    // Возврат среднего значения для массива значений.
    public static double mean(double[] vals) {
        double avg = 0.0;
        for (int i=0; i<vals.length; i++) {
            avg += vals[i];
        }
        avg /= vals.length;
        return avg;
    }

    // Возврат медианы для массива значений.
    public static double median(double[] vals) {
        double temp[] = new double[vals.length];
        System.arraycopy(vals, 0, temp, 0, vals.length);
        Arrays.sort(temp);  // Сортировка данных.
        // Возврат значения среднего элемента.
        return ((vals.length)%2==0) ? (temp[temp.length/2]+temp[(temp.length/2)-1])/2 : temp[temp.length/2];
    }

    /**
     * Возврат моды для массива значений. Генерируется исключение NoModeException, если
     * на одно значение не повторяется чаще, чем другие значения. Если одно или
     * несколько значений повторяются одинаковое количество раз, то возвращается
     * первое значение.
     * @param vals
     * @return modeVal
     * @throws NoModeException
     */
    public static double mode(double[] vals) throws NoModeException {
        double m, modeVal = 0.0;
        int count, oldcount = 0;
        for (int i=0; i < vals.length; i++) {
            m = vals[i];
            count = 0;

            // Подсчет числа вхождений для каждого значения.
            for (int j = i+1; j < vals.length; j++) {
                if (m == vals[j]) {
                    count++;
                }
            }

            // Если это значение повторяется большее количество раз, чем
            // все предыдущие, сохранить его.
            if (count > oldcount) {
                modeVal = m;
                oldcount = count;
            }
        }
        if (oldcount == 0) {
            throw new NoModeException();
        }
        else {
            return modeVal;
        }
    }

    // Возвратить среднее значение для массива значений.
    public static double stdDev(double[] vals) {
        double std = 0.0;
        double avg = mean(vals);
        for (int i = 0; i < vals.length; i++) {
            std += (vals[i] - avg) * (vals[i] - avg);
        }
        std /= vals.length;
        std = Math.sqrt(std);
        return std;
    }

    /**
     * Подсчитать уравнение регрессии и коэффициент корреляции для массива
     * значений. Значения представлены по оси Y. По оси X размещается время с
     * возрастанием на 1.
     * @param vals
     * @return Object RegData данные регрессионного анализа
     */
    @SuppressWarnings("NestedAssignment")
    public static RegData regress(double[] vals) {
        double a, b, yAvg, xAvg, temp, temp2, cor;
        double vals2[] = new double[vals.length];

        // Создать числовой формат для 2-х десятичных чисел.
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);

        // Найти среднее значение для оси Y.
        yAvg = mean(vals);

        // Найти среднее значение для оси X.
        xAvg = 0.0;
        for (int i = 0; i < vals.length; i++) {
            xAvg += i;
        }
        xAvg /= vals.length;

        // Найти коэффициент b.
        temp = temp2 = 0.0;
        for (int i = 0; i < vals.length; i++) {
            temp += (vals[i] - yAvg) * (i - xAvg);
            temp2 += (i - xAvg) * (i - xAvg);
        }
        b = temp/temp2;

        // Найти коэффициент a.
        a = yAvg - (b*xAvg);

        // Подсчитать коэффициент корреляции.
        for (int i=0; i<vals.length; i++) {
            vals2[i] = i+1;
        }
        cor = temp/vals.length;
        cor /= stdDev(vals) * stdDev(vals2);
        // Было бы лучше сделать класс RegData внешним, т.к. я возвращаю ссылку из внешнего интерфейса!
        return new RegData(a, b, cor, "Y = " + nf.format(a) + " + " + nf.format(b) + " * X");
    }
}
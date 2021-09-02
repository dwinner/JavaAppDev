
/**
 * Эта программа демонстрирует сохранение табличных данных в двумерном массиве.
 * @version 1.40 2004-02-10
 * @author Cay Horstmann
 */
public class CompoundInterest
{
    public static void main(String[] args)
    {
        final double STARTRATE = 10;
        final int NRATES = 6;
        final int NYEARS = 10;

        // Установка процентной ставки 10 . . . 15%
        double[] interestRate = new double[NRATES];
        for (int j = 0; j < interestRate.length; j++)
        {
            interestRate[j] = (STARTRATE + j) / 100.0;
        }

        double[][] balances = new double[NYEARS][NRATES];

        // Установка исходной суммы в 10000
        for (int j = 0; j < balances[0].length; j++)
        {
            balances[0][j] = 10000;
        }

        // Вычисление баланса на следующие годы
        for (int i = 1; i < balances.length; i++)
        {
            for (int j = 0; j < balances[i].length; j++)
            {
                // Извлечение баланса за прошлый год
                double oldBalance = balances[i - 1][j];

                // Вычисление процентного дохода
                double interest = oldBalance * interestRate[j];

                // Вычисление баланса за текущий год
                balances[i][j] = oldBalance + interest;
            }
        }

        // Вывод процентных ставок
        for (int j = 0; j < interestRate.length; j++)
        {
            System.out.printf("%9.0f%%", 100 * interestRate[j]);
        }

        System.out.println();

        // Вывод таблицы
        for (double[] row : balances)
        {
            // Вывод строки таблицы
            for (double b : row)
            {
                System.out.printf("%10.2f", b);
            }

            System.out.println();
        }
    }
}

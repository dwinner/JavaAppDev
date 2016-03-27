import java.util.logging.Logger;

/**
 * Тип-Создатель матриц
 * @author <a href="mailto:dwinner@inbox.ru">dwinner@inbox.ru</a>
 */
public class MatrixFactory
{
    private MatrixFactory() {}
    private static final Logger LOG = Logger.getLogger(MatrixFactory.class.getName());
    
    /**
     * Создание матрицы случайных чисел
     * @param n Количество строк
     * @param m Количество столбцов
     * @return Построенную матрицу
     */
    public static Matrix createRandomMatrix(int n, int m)
    {
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                matrix.setElement(i, j, (int) (Math.random() * n * m));
            }
        }
        return matrix;
    }
    
    /**
     * Создание квадратной матрицы случайных чисел
     * @param n Размер квадратной матрицы
     * @return Построенную матрицу
     */
    public static Matrix createRandomMatrix(int n)
    {
        return createRandomMatrix(n, n);
    }
    
    /**
     * Создание матрицы случайных чисел
     * @param n Количество строк
     * @param m Количество столбцов
     * @param upper Верхний предел случайного числа
     * @return Построенную матрицу
     */
    public static Matrix createRandomMatrix(int n, int m, int upper)
    {
        Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                int currentRandom = (int) (Math.random() * upper);
                matrix.setElement(i, j, currentRandom);
            }
        }
        return matrix;
    }

}
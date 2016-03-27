import java.util.logging.Logger;

/**
 * Тип для хранения матрицы.
 * @author <a href="mailto:dwinner@inbox.ru">dwinner@inbox.ru</a>
 */
public class Matrix
{
    private int[][] a;
    private static final int DEFAULT_MATRIX_DIMENSION = 5;
    private static final Logger LOG = Logger.getLogger(Matrix.class.getName());
    
    /**
     * Создание матрицы размера n x m
     * @param n Количество строк
     * @param m Количество столбцов
     */
    public Matrix(int n, int m)
    {
        // Создание и заполнение нулевыми значениями
        a = new int[n][m];
    }
    
    /**
     * Создание квадратной матрицы
     * @param n Размерность матрицы
     */
    public Matrix(int n) { this(n, n); }
    
    /**
     * Создание квадратной матрицы с размером по умолчанию (5)
     */
    public Matrix() { this(DEFAULT_MATRIX_DIMENSION); }
    
    /**
     * Размер первого измерения
     * @return Размер первого измерения
     */
    public int getFirstDim() { return a.length; }
    
    /**
     * Размер первого измерения
     * @return Размер второго измерения
     */
    public int getSecondDim() { return a[0].length; }
    
    /**
     * Получение элемента на пересечении строки и столбца
     * @param i Номер строки
     * @param j Номер столбца
     * @return Элемент на пересечении строки и столбца
     * @throws ArrayIndexOutOfBoundsException  
     */
    public int getElement(int i, int j) throws ArrayIndexOutOfBoundsException
    {
        return a[i][j];
    }
    
    /**
     * Установка элемента на пересечении строки и столбца
     * @param i Номер строки
     * @param j Номер столбца
     * @param value Элемент для установки
     * @throws ArrayIndexOutOfBoundsException 
     */
    public void setElement(int i, int j, int value) throws ArrayIndexOutOfBoundsException
    {
        a[i][j] = value;
    }

    @Override public String toString()
    {
        StringBuilder s = new StringBuilder("\nMatrix : "+a.length+"x"+a[0].length+"\n");
        for (int[] vector : a)
        {
            for (int value : vector)
            {
                s.append(value).append("\t");
            }
            s.append("\n");
        }
        
        return s.toString();
    }
    
}
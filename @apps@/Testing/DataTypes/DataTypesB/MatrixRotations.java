import java.util.logging.Logger;

/**
 * Тип, который способен переворачивать квадратные матрицы 
 * по, против часовой стрелки, на 180 градусов.
 * @author <a href="mailto:dwinner@inbox.ru">dwinner@inbox.ru</a>
 */
public class MatrixRotations
{
    private MatrixRotations() {}
    private static final Logger LOG = Logger.getLogger(MatrixRotations.class.getName());
    
    /**
     * Поворот матрицы на 90 градусов против часовой стрелки
     * @param p Source Matrix
     * @return Повернутая матрица
     * @throws MatrixNotSquareException Если матрица не квадратная
     */
    public static Matrix rotateLeftPiHalfDegrees(Matrix p) throws MatrixNotSquareException
    {
        int h = p.getFirstDim();
        int w = p.getSecondDim();
        // Проверим, является ли матрица квадратной
        if (h != w)
        {
            throw new MatrixNotSquareException();
        }
        // Создание матрицы, повернутой на 90 градусов против часовой стрелки
        Matrix rot = new Matrix(w);
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                int value = p.getElement(j, w-1-i);
                rot.setElement(i, j, value);
            }
        }
        return rot;
    }
    
    /**
     * Поворот матрицы на 90 градусов по часовой стрелки
     * @param p Source Matrix
     * @return Повернутая матрица
     * @throws MatrixNotSquareException Если матрица не квадратная
     */
    public static Matrix rotateRightPiHalfDegrees(Matrix p) throws MatrixNotSquareException
    {
        int h = p.getFirstDim();
        int w = p.getSecondDim();
        // Проверим, является ли матрица квадратной
        if (h != w)
        {
            throw new MatrixNotSquareException();
        }
        // Создание матрицы, повернутой на 90 градусов по часовой стрелке
        Matrix rot = new Matrix(w);
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                int value = p.getElement(w-1-j, i);
                rot.setElement(i, j, value);
            }
        }
        return rot;
    }
    
    /**
     * Переворот матрицы на 180 градусов
     * @param p Source Matrix
     * @return Перевернутая матрица
     * @throws MatrixNotSquareException Если матрица не квадратная
     */
    public static Matrix rotateBackwards(Matrix p) throws MatrixNotSquareException
    {
        int h = p.getFirstDim();
        int w = p.getSecondDim();
        // Проверим, является ли матрица квадратной
        if (h != w)
        {
            throw new MatrixNotSquareException();
        }
        // Создание перевернутой матрицы
        Matrix rot = new Matrix(w);
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                int value = p.getElement(h-1-i, w-1-j);
                rot.setElement(i, j, value);
            }
        }
        return rot;
    }
    
}
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Поворот матриц.
 * @author <a href="mailto:dwinner@inbox.ru">dwinner@inbox.ru</a>
 */
public class DataTypesB
{
    private static final Logger LOG = Logger.getLogger(DataTypesB.class.getName());
    private static final int DEFAULT_DIMENSION = 5;
    private DataTypesB() {}
    
    /**
     * Точка запуска
     * @param args the command line arguments
     */
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public static void main(String[] args)
    {
        // Задать размерность матрицы
        Scanner scanIn;
        int dim;
        System.out.print("Enter the matrix dimension: ");
        scanIn = new Scanner(System.in);
        try
        {
            dim = scanIn.nextInt();
        }
        catch (InputMismatchException imEx)
        {
            System.out.println("So we will use default dimension: " + DEFAULT_DIMENSION);
            dim = DEFAULT_DIMENSION;
        }
        
        // Создать матрицу случайных значений
        Matrix src = MatrixFactory.createRandomMatrix(dim, dim, dim*dim);
        // Повернутые матрицы
        Matrix rotLeft, rotRight, backWards;
        try
        {
            rotLeft = MatrixRotations.rotateLeftPiHalfDegrees(src);
            rotRight = MatrixRotations.rotateRightPiHalfDegrees(src);
            backWards = MatrixRotations.rotateBackwards(src);
            System.out.println(src);
            System.out.println("Left 90 degrees rotation: " + rotLeft);
            System.out.println("Right 90 degrees rotation: " + rotRight);
            System.out.println("Backwards rotation: " + backWards);
        }
        catch (MatrixNotSquareException ex)
        {
            System.err.println("Error occured, cause matrix is not square");
        }
    }
    
}
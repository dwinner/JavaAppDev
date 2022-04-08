import java.util.Arrays;

public final class Matrix
{
   public Matrix(int[] integers)
   {
      assert integers.length == 9;
      _data = new int[DEFAULT_ROWS_NUMBER][DEFAULT_COLS_NUMBER];
      for (int i = 0; i < DEFAULT_ROWS_NUMBER; i++)
      {
         System.arraycopy(integers, i * 3, _data[i], 0, DEFAULT_COLS_NUMBER);
      }
   }

   public Matrix()
   {
      this(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
   }

   public int get(int rowIndex, int columnIndex)
   {
      return _data[rowIndex][columnIndex];
   }

   public int trace()
   {
      int trace = 0;
      for (int i = 0; i < DEFAULT_ROWS_NUMBER; i++)
      {
         trace += _data[i][i];
      }

      return trace;
   }

   public int det()
   {
      return internalDet(_data);
   }

   public Matrix add(Matrix matrixToAdd)
   {
      Matrix newMatrix = new Matrix();
      for (int i = 0; i < DEFAULT_ROWS_NUMBER; i++)
      {
         for (int j = 0; j < DEFAULT_COLS_NUMBER; j++)
         {
            newMatrix._data[i][j] = _data[i][j] + matrixToAdd._data[i][j];
         }
      }

      return newMatrix;
   }

   public Matrix subtract(Matrix matrixToSub)
   {
      Matrix newMatrix = new Matrix();
      for (int i = 0; i < DEFAULT_ROWS_NUMBER; i++)
      {
         for (int j = 0; j < DEFAULT_COLS_NUMBER; j++)
         {
            newMatrix._data[i][j] = _data[i][j] - matrixToSub._data[i][j];
         }
      }

      return newMatrix;
   }

   public Matrix multiply(Matrix matrixToMul)
   {
      Matrix newMatrix = new Matrix();
      for (int i = 0; i < DEFAULT_ROWS_NUMBER; i++)
      {
         for (int j = 0; j < DEFAULT_COLS_NUMBER; j++)
         {
            for (int k = 0; k < DEFAULT_COLS_NUMBER; k++)
            {
               newMatrix._data[i][j] += _data[i][k] * matrixToMul._data[k][j];
            }
         }
      }

      return newMatrix;
   }

   @Override
   public String toString()
   {
      StringBuilder matrixDump = new StringBuilder("[");
      for (int i = 0; i < DEFAULT_ROWS_NUMBER; i++)
      {
         matrixDump.append('[');
         for (int j = 0; j < DEFAULT_COLS_NUMBER; j++)
         {
            matrixDump.append(_data[i][j]);
            if (j == DEFAULT_COLS_NUMBER - 1)
            {
               matrixDump.append(']');
            }
            else
            {
               matrixDump.append(", ");
            }
         }

         if (i != DEFAULT_ROWS_NUMBER - 1)
         {
            matrixDump.append(", ");
         }
      }

      matrixDump.append(']');

      return matrixDump.toString();
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o)
      {
         return true;
      }

      if (o == null || getClass() != o.getClass())
      {
         return false;
      }

      Matrix matrix = (Matrix) o;

      return Arrays.equals(_data, matrix._data);
   }

   @Override
   public int hashCode()
   {
      return Arrays.hashCode(_data);
   }

   private static int internalDet(int[][] matrix)
   {
      int[][] tempArray;
      int determinant = 0;

      if (matrix.length == 1)
      {
         determinant = matrix[0][0];
         return determinant;
      }

      if (matrix.length == 2)
      {
         determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
         return determinant;
      }

      for (int i = 0; i < matrix[0].length; i++)
      {
         tempArray = new int[matrix.length - 1][matrix[0].length - 1];
         for (int j = 1; j < matrix.length; j++)
         {
            for (int k = 0; k < matrix[0].length; k++)
            {
               if (k < i)
               {
                  tempArray[j - 1][k] = matrix[j][k];
               }
               else if (k > i)
               {
                  tempArray[j - 1][k - 1] = matrix[j][k];
               }
            }
         }

         determinant += matrix[0][i] * Math.pow(-1, i) * internalDet(tempArray);
      }

      return determinant;
   }

   private static final int DEFAULT_ROWS_NUMBER = 3;
   private static final int DEFAULT_COLS_NUMBER = 3;
   private final int[][] _data;
}

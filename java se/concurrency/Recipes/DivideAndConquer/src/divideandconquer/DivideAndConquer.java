package divideandconquer;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * Паралелльное вычисление в многопроцессорном окружении.
 *
 * @author Denis
 */
public class DivideAndConquer
{
   public static void main(String[] args) throws InterruptedException, InvocationTargetException
   {
      DivideAndConquer recipe = new DivideAndConquer();
      recipe.start();
   }

   private void start() throws InvocationTargetException, InterruptedException
   {
      boolean[][] lifeBoard = new boolean[50][50];
      final LifeTableModel model = new LifeTableModel(lifeBoard);
      final JTable lifeTable = new JTable();

      SwingUtilities.invokeAndWait(new Runnable()
      {
         @Override
         public void run()
         {
            JFrame frame = new JFrame("Game of Life");
            lifeTable.setModel(model);
            lifeTable.setDefaultRenderer(Boolean.class, new LifeTableCellRenderer());
            frame.setContentPane(new JScrollPane(lifeTable));
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.addWindowListener(new WindowAdapter()
            {
               @Override
               public void windowClosing(WindowEvent e)
               {
                  shouldRun = false;
               }

            });

            frame.setSize(640, 480);
            frame.setVisible(true);
         }

      });

      // Инициализация.
      populateRandomBoard(lifeBoard);
      model.fireTableDataChanged();

      ForkJoinPool pool = new ForkJoinPool();
      long i = 0;

      while (shouldRun)
      {
         i++;
         final boolean[][] newBoard = new boolean[lifeBoard.length][lifeBoard[0].length];
         long startTime = System.nanoTime();
         GameOfLifeAdvancer advancer = new GameOfLifeAdvancer(lifeBoard, 0, 0, lifeBoard.length - 1,
                                                              lifeBoard[0].length - 1, newBoard);
         pool.invoke(advancer);
         long endTime = System.nanoTime();
         if (i % 100 == 0)
         {
            System.out.println("Taking " + (endTime - startTime) / 1000 + "ms");
         }
         SwingUtilities.invokeAndWait(new Runnable()
         {
            @Override
            public void run()
            {
               model.setBoard(newBoard);
               lifeTable.repaint();
            }

         });
         lifeBoard = newBoard;
      }
   }

   private void populateRandomBoard(boolean[][] lifeBoard)
   {
      for (int row = 0; row < lifeBoard.length; row++)
      {
         for (int col = 0; col < lifeBoard[0].length; col++)
         {
            lifeBoard[row][col] = random.nextBoolean();
         }
      }
   }

   private Random random = new Random();
   private volatile boolean shouldRun = true;

   static class LifeTableCellRenderer extends JLabel implements TableCellRenderer
   {
      LifeTableCellRenderer()
      {
         setOpaque(true);
      }

      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                     int row, int column)
      {
         Color backColor = ((Boolean) value) ? Color.black : Color.white;
         setBackground(backColor);
         return this;
      }

   }

   static class LifeTableModel extends AbstractTableModel
   {
      LifeTableModel(boolean[][] lifeBoard)
      {
         this.lifeBoard = lifeBoard;
      }

      @Override
      public String getColumnName(int column)
      {
         return null;
      }

      @Override
      public int getRowCount()
      {
         return lifeBoard.length;
      }

      @Override
      public int getColumnCount()
      {
         return lifeBoard[0].length;
      }

      @Override
      public Object getValueAt(int rowIndex, int columnIndex)
      {
         return lifeBoard[rowIndex][columnIndex];
      }

      @Override
      public Class<?> getColumnClass(int columnIndex)
      {
         return Boolean.class;
      }

      public void setBoard(boolean[][] newBoard)
      {
         lifeBoard = newBoard;
      }

      private boolean[][] lifeBoard;
   }

   static class GameOfLifeAdvancer extends RecursiveAction
   {
      GameOfLifeAdvancer(boolean[][] originalBoard, int startRow, int startCol, int i, int endCol,
                         boolean[][] destinationBoard)
      {
         this.originalBoard = originalBoard;
         this.destinationBoard = destinationBoard;
         this.startRow = startRow;
         this.endRow = endRow;
         this.endCol = endCol;
         this.startCol = startCol;
      }

      @Override
      protected void compute()
      {
         if (getArea() < 20)
         {
            computeDirectly();
            return;
         }
         int halfRows = (endRow - startRow) / 2;
         int halfCols = (endCol - startCol) / 2;
         if (halfRows > halfCols)
         {  // Разделение строк
            invokeAll(new GameOfLifeAdvancer(originalBoard, startRow, startCol, startRow + halfRows, endCol,
                                             destinationBoard),
                      new GameOfLifeAdvancer(originalBoard, startRow + halfRows + 1, startCol, endRow, endCol,
                                             destinationBoard));
         }
         else
         {  // Разделение столбцов
            invokeAll(new GameOfLifeAdvancer(originalBoard, startRow, startCol, endRow, startCol + halfCols,
                                             destinationBoard),
                      new GameOfLifeAdvancer(originalBoard, startRow, startCol + halfCols + 1, endRow, endCol,
                                             destinationBoard));
         }

      }

      private void computeDirectly()
      {
         for (int row = startRow; row <= endRow; row++)
         {
            for (int col = startCol; col <= endCol; col++)
            {
               int numberOfNeighbors = getNumberOfNeighbors(row, col);
               if (originalBoard[row][col])
               {
                  destinationBoard[row][col] = true;
                  if (numberOfNeighbors < 2)
                  {
                     destinationBoard[row][col] = false;
                  }
                  if (numberOfNeighbors > 3)
                  {
                     destinationBoard[row][col] = false;
                  }
               }
               else
               {
                  destinationBoard[row][col] = false;
                  if (numberOfNeighbors == 3)
                  {
                     destinationBoard[row][col] = true;
                  }
               }
            }
         }
      }

      private int getNumberOfNeighbors(int row, int col)
      {
         int neighborCount = 0;
         for (int leftIndex = - 1; leftIndex < 2; leftIndex++)
         {
            for (int topIndex = -1; topIndex < 2; topIndex++)
            {
               if ((leftIndex == 0) && (topIndex == 0))
               {
                  continue;
               }
               int neighbourRowIndex = row + leftIndex;
               int neighbourColIndex = col + topIndex;
               if (neighbourRowIndex < 0)
               {
                  neighbourRowIndex += originalBoard.length;
               }
               if (neighbourColIndex < 0)
               {
                  neighbourColIndex += originalBoard[0].length;
               }
               boolean neighbour =
                 originalBoard[neighbourRowIndex % originalBoard.length][neighbourColIndex % originalBoard[0].length];
               if (neighbour)
               {
                  neighborCount++;
               }
            }
         }
         return neighborCount;
      }

      private int getArea()
      {
         return (endRow - startRow) * (endCol - startCol);
      }

      private boolean[][] originalBoard;
      private boolean[][] destinationBoard;
      private int startRow;
      private int endRow;
      private int endCol;
      private int startCol;
   }

}

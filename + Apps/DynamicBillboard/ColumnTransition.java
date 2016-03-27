import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dwinner@inbox.ru
 */
public class ColumnTransition extends BillTransition {
    
    private static final Logger LOG = Logger.getLogger(ColumnTransition.class.getName());
    final public static int CELLS = 7;
    final public static int WIDTH_INCREMENT = 3;
    final public static int MAX_COLUMN_WIDTH = 24;
    
    private int rightmost_columns_max_width;
    private int rightmost_columns_x_start;
    private int column_width = WIDTH_INCREMENT;

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void init(Component owner, int[] current_pixels, int[] next_pixels) {
        init(owner, current_pixels, next_pixels, CELLS, 200);
        rightmost_columns_max_width = getCell_w() % MAX_COLUMN_WIDTH;
        rightmost_columns_x_start = getCell_w() - rightmost_columns_max_width;
        System.arraycopy(current_pixels, 0, getWork_pixels(), 0, getPixels_per_cell());
        for (int c = 0; c < CELLS; ++c) {
            try {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            NextCell();
            try {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            createCellFromWorkPixels(c);
            column_width += WIDTH_INCREMENT;
        }
        setWork_pixels(null);
    }

    private void NextCell() {
        int old_column_width = MAX_COLUMN_WIDTH - column_width;
        for (int p = getPixels_per_cell() - getCell_w(); p >= 0; p -= getCell_w()) {
            for (int x = 0; x < rightmost_columns_x_start; x += MAX_COLUMN_WIDTH) {
                System.arraycopy(getNext_pixels(), x + p, getWork_pixels(), old_column_width + x + p, column_width);
            }
            if (old_column_width <= rightmost_columns_max_width) {
                System.arraycopy(
                        getNext_pixels(),
                        rightmost_columns_x_start + p,
                        getWork_pixels(),
                        rightmost_columns_x_start + old_column_width + p - 1,
                        rightmost_columns_max_width - old_column_width + 1
                );
            }
        }
    }
    
}

package billboard;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dwinner@inbox.ru
 */
public class UnrollTransition extends BillTransition {
    
    private static final Logger LOG = Logger.getLogger(UnrollTransition.class.getName());
    
    final static int CELLS = 9;
    private static int fill_pixels[] = {
        0xFFFFFFFF,
        0xFF000000,
        0xFF000000,
        0xFFFFFFFF
    };
    
    private int location;
    private int[] unroll_amount;
    
    private static int[] createUnrollAmountArray(int cell_h) {
        float unroll_increment = ((float) cell_h / (float) (CELLS + 1)) / ((float) (CELLS + 2) / 2.0f);
        int total = 0;
        int unroll_amount[] = new int[CELLS + 1];
        for (int u = 0; u <= CELLS; ++u) {
            unroll_amount[u] = (int) (unroll_increment * (CELLS - u + 1));
            total += unroll_amount[u];
        }
        if (total < 0) {
            unroll_amount[0] -= 1;
        }
        return unroll_amount;
    }

    @Override
    @SuppressWarnings({"SleepWhileInLoop", "unchecked"})
    public void init(Component owner, int[] current, int[] next) {
        init(owner, current, next, CELLS, 220);
        location = getPixels_per_cell();
        System.arraycopy(getCurrent_pixels(), 0, getWork_pixels(), 0, getPixels_per_cell());
        unroll_amount = (int[]) BillTransition.getObject_table().get(getClass().getName() + getCell_h());
        if (unroll_amount == null) {
            unroll_amount = createUnrollAmountArray(getCell_h());
            BillTransition.getObject_table().put(getClass().getName() + getCell_h(), unroll_amount);
        }
        for (int c = 0; c < CELLS; ++c) {
            location -= unroll_amount[c] * getCell_w();
            try {
                Thread.sleep(150);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            Unroll(c);
            try {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            createCellFromWorkPixels(c);
            System.arraycopy(getNext_pixels(), location, getWork_pixels(), location, unroll_amount[c] * getCell_w());
        }
        this.setWork_pixels(null);
    }
    
    void Unroll(int c) {
        int y_flip = getCell_w();
        int offset[] = new int[unroll_amount[c]];
        for (int o = 0; o < unroll_amount[c]; ++o) {
            offset[o] = 4;
        }
        offset[0] = 2;
        if (unroll_amount[c] > 1) {
            offset[1] = 3;
        }
        if (unroll_amount[c] > 2) {
            offset[unroll_amount[c] - 1] = 2;
        }
        if (unroll_amount[c] > 3) {
            offset[unroll_amount[c] - 2] = 3;
        }
        int offset_index = 0;
        int end_location = location + unroll_amount[c] * getCell_w();
        for (int p = location; p < end_location; p += getCell_w()) {
            System.arraycopy(getNext_pixels(), p - y_flip + offset[offset_index], getWork_pixels(), p, getCell_w() - offset[offset_index]);
            System.arraycopy(fill_pixels, 0, getWork_pixels(), p + getCell_w() - offset[offset_index], offset[offset_index]);
            ++offset_index;
            y_flip += 2 * getCell_w();
        }
        for (int x = location + getCell_w() - 1; x > location; --x) {
            getWork_pixels()[x] |= 0xFFAAAAAA;
            getWork_pixels()[x + unroll_amount[c]] &= 0xFF555555;
        }
    }
    
}

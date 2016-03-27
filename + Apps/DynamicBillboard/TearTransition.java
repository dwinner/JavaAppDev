import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dwinner@inbox.ru
 */
public class TearTransition extends BillTransition {
    
    static final int CELLS = 7;
    static final float INITIAL_X_CROSS = 1.6f;
    static final float X_CROSS_DIVISOR = 3.5f;
    private float x_cross;
    private static final Logger LOG = Logger.getLogger(TearTransition.class.getName());

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void init(Component owner, int[] current, int[] next) {
        init(owner, current, next, CELLS);
        System.arraycopy(getNext_pixels(), 0, getWork_pixels(), 0, getPixels_per_cell());
        System.arraycopy(getCurrent_pixels(), 0, getWork_pixels(), 0, getCell_w());
        
        x_cross = INITIAL_X_CROSS;
        
        for (int c = CELLS - 1; c >= 0; --c) {
            try {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            Tear();
            try {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            createCellFromWorkPixels(c);
            x_cross /= X_CROSS_DIVISOR;
        }
        setWork_pixels(null);
    }

    /**
     * @return the x_cross
     */
    public float getX_cross() {
        return x_cross;
    }

    /**
     * @param x_cross the x_cross to set
     */
    public void setX_cross(float x_cross) {
        this.x_cross = x_cross;
    }

    @SuppressWarnings({"FinalMethod", "ValueOfIncrementOrDecrementUsed", "NestedAssignment"})
    final public void Tear() {
        float x_increment;
        int p, height_adder;
        
        height_adder = getCell_w();
        p = height_adder;
        for (int y = 1; y < getCell_h(); ++y) {
            x_increment = x_cross * y;
            if (x_increment >= 0.5f) {
                float fx = 0.0f;
                x_increment += 1.0f;
                int x = 0;
                do {
                    getWork_pixels()[p++] = this.getCurrent_pixels()[height_adder + x];
                    x = (int) (fx += x_increment);
                }
                while (x < getCell_w());
            }
            else {
                float overflow = 1.0f / x_increment;
                float dst_end = overflow / 2.0f + 1.49999999f;
                int dst_start = 0, src_offset = 0, length = (int) dst_end;
                while (dst_start + src_offset + length < getCell_w()) {
                    System.arraycopy(getCurrent_pixels(), p + src_offset, getWork_pixels(), p, length);
                    ++src_offset;
                    dst_end += overflow;
                    p += length;
                    dst_start += length;
                    length = (int) dst_end - dst_start;
                }
                length = getCell_w() - src_offset - dst_start;
                System.arraycopy(getCurrent_pixels(), p + src_offset, getWork_pixels(), p, length);
            }
            p = height_adder += getCell_w();
        }
    }
       
}

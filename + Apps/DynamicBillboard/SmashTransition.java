import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dwinner@inbox.ru
 */
public class SmashTransition extends BillTransition {
    
    private static final Logger LOG = Logger.getLogger(SmashTransition.class.getName());
    public final static int CELLS = 8;
    public final static float FOLDS = 8.0f;
    private static int[] fill_pixels;
    private int drop_amount;
    private int location;
    
    public static void setupFillPixels(int width) {
        if (fill_pixels != null && fill_pixels.length <= width) {
            return;
        }
        fill_pixels = new int[width];
        for (int f = 0; f < width; ++f) {
            fill_pixels[f] = 0xFFFFFFFF;
        }
    }

    /**
     * @return the fill_pixels
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public static int[] getFill_pixels() {
        return fill_pixels;
    }

    /**
     * @param aFill_pixels the fill_pixels to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public static void setFill_pixels(int[] aFill_pixels) {
        fill_pixels = aFill_pixels;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void init(Component owner, int[] current, int[] next) {
        init(owner, current, next, CELLS, 160);
        setupFillPixels(getCell_w());
        drop_amount = (getCell_h() / CELLS) * getCell_w();
        location = getPixels_per_cell() - ((getCell_h() / CELLS) / 2) * getCell_w();
        for (int c = CELLS - 1; c >= 0; --c) {
            try {
                Thread.sleep(100);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            Smash(c + 1);
            try {
                Thread.sleep(150);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            createCellFromWorkPixels(c);
            location -= drop_amount;
        }
        setWork_pixels(null);
    }
    
    private void Smash(int max_fold) {
        System.arraycopy(getNext_pixels(), getPixels_per_cell() - location, getWork_pixels(), 0, location);
        int height = getCell_h() - location / getCell_w();
        float fold_offset_adder = (float) max_fold * FOLDS / (float) height;
        float fold_offset = 0.0f;
        int fold_width = getCell_w() - max_fold;
        float src_y_adder = (float) getCell_h() / (float) height;
        float src_y_offset = getCell_h() - src_y_adder / 2;
        for (int p = getPixels_per_cell() - getCell_w(); p >= location; p -= getCell_w()) {
            System.arraycopy(fill_pixels, 0, this.getWork_pixels(), p, this.getCell_w());
            System.arraycopy(getCurrent_pixels(), (int) src_y_offset * getCell_w(), getWork_pixels(), p + (int) fold_offset, fold_width);
            src_y_offset -= src_y_adder;
            fold_offset += fold_offset_adder;
            if (fold_offset < 0.0 || fold_offset >= max_fold) {
                fold_offset_adder *= -1.0f;
            }
        }
    }
    
}

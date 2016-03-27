import java.awt.Component;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.util.Hashtable;
import java.util.logging.Logger;

/**
 * Базовый класс для всех анимаций перехода
 * @author dwinner@inbox.ru
 */
public abstract class BillTransition {
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    private static Hashtable object_table = new Hashtable(20);

    /**
     * @return the object_table
     */
    @SuppressWarnings({"ReturnOfCollectionOrArrayField", "UseOfObsoleteCollectionType"})
    public static Hashtable getObject_table() {
        return object_table;
    }

    /**
     * @param aObject_table the object_table to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public static void setObject_table(@SuppressWarnings("UseOfObsoleteCollectionType") Hashtable aObject_table) {
        object_table = aObject_table;
    }
    
    private Image[] cells;   
    private int delay;
    private Component owner;
    private int cell_w;
    private int cell_h;
    private int pixels_per_cell;
    private int[] current_pixels;
    private int[] next_pixels;
    private int[] work_pixels;

    public abstract void init(Component owner, int[] current_pixels, int[] next_pixels);
    
    @SuppressWarnings({"AssignmentToCollectionOrArrayFieldFromParameter", "FinalMethod"})
    final protected void init(Component owner,
                        int[] current_pixels,
                        int[] next_pixels,
                        int number_of_cells,
                        int delay)
    {
        this.delay = delay;
        this.setNext_pixels(next_pixels);
        this.setCurrent_pixels(current_pixels);
        this.setOwner(owner);
        
        cells = new Image[number_of_cells];
        setCell_w(owner.getSize().width);
        setCell_h(owner.getSize().height);
        setPixels_per_cell(getCell_w() * getCell_h());
        setWork_pixels(new int[getPixels_per_cell()]);
    }
    
    @SuppressWarnings("FinalMethod")
    final protected void init(Component owner,
                        int[] current_pixels,
                        int[] next_pixels,
                        int number_of_cells)
    {
        init(owner, current_pixels, next_pixels, number_of_cells, 120);
    }
    
    @SuppressWarnings("FinalMethod")
    final void createCellFromWorkPixels(int cell) {
        cells[cell] = getOwner().createImage(new MemoryImageSource(getCell_w(), getCell_h(), getWork_pixels(), 0, getCell_w()));
        getOwner().prepareImage(cells[cell], null);
    }

    /**
     * @return the cells
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public Image[] getCells() {
        return cells;
    }

    /**
     * @param cells the cells to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setCells(Image[] cells) {
        this.cells = cells;
    }

    /**
     * @return the delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * @param delay the delay to set
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * @return the owner
     */
    public Component getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Component owner) {
        this.owner = owner;
    }

    /**
     * @return the cell_w
     */
    public int getCell_w() {
        return cell_w;
    }

    /**
     * @param cell_w the cell_w to set
     */
    public void setCell_w(int cell_w) {
        this.cell_w = cell_w;
    }

    /**
     * @return the cell_h
     */
    public int getCell_h() {
        return cell_h;
    }

    /**
     * @param cell_h the cell_h to set
     */
    public void setCell_h(int cell_h) {
        this.cell_h = cell_h;
    }

    /**
     * @return the pixels_per_cell
     */
    public int getPixels_per_cell() {
        return pixels_per_cell;
    }

    /**
     * @param pixels_per_cell the pixels_per_cell to set
     */
    public void setPixels_per_cell(int pixels_per_cell) {
        this.pixels_per_cell = pixels_per_cell;
    }

    /**
     * @return the current_pixels
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public int[] getCurrent_pixels() {
        return current_pixels;
    }

    /**
     * @param current_pixels the current_pixels to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setCurrent_pixels(int[] current_pixels) {
        this.current_pixels = current_pixels;
    }

    /**
     * @return the next_pixels
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public int[] getNext_pixels() {
        return next_pixels;
    }

    /**
     * @param next_pixels the next_pixels to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setNext_pixels(int[] next_pixels) {
        this.next_pixels = next_pixels;
    }

    /**
     * @return the work_pixels
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public int[] getWork_pixels() {
        return work_pixels;
    }

    /**
     * @param work_pixels the work_pixels to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setWork_pixels(int[] work_pixels) {
        this.work_pixels = work_pixels;
    }
    
}

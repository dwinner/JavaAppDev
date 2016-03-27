
import java.awt.*;

/**
 * This class simplifies the use of the GridBagConstraints class.
 */
public class GBC extends GridBagConstraints
{
    /**
     * Constructs a GBC with a given gridx and gridy position and all other grid bag constraint values set to the
     * default.
     * <p/>
     * @param gridx the gridx position
     * @param gridy the gridy position
     */
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     * Constructs a GBC with given gridx, gridy, gridwidth, gridheight and all other grid bag constraint values set to
     * the default.
     * <p/>
     * @param gridx      the gridx position
     * @param gridy      the gridy position
     * @param gridwidth  the cell span in x-direction
     * @param gridheight the cell span in y-direction
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     * Sets the anchor.
     * <p/>
     * @param anchor the anchor value
     * <p/>
     * @return this object for further modification
     */
    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     * Sets the fill direction.
     * <p/>
     * @param fill the fill direction
     * <p/>
     * @return this object for further modification
     */
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     * Sets the cell weights.
     * <p/>
     * @param weightx the cell weight in x-direction
     * @param weighty the cell weight in y-direction
     * <p/>
     * @return this object for further modification
     */
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * Sets the insets of this cell.
     * <p/>
     * @param distance the spacing to use in all directions
     * <p/>
     * @return this object for further modification
     */
    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * Sets the insets of this cell.
     * <p/>
     * @param top    the spacing to use on top
     * @param left   the spacing to use to the left
     * @param bottom the spacing to use on the bottom
     * @param right  the spacing to use to the right
     * <p/>
     * @return this object for further modification
     */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * Sets the internal padding
     * <p/>
     * @param ipadx the internal padding in x-direction
     * @param ipady the internal padding in y-direction
     * <p/>
     * @return this object for further modification
     */
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}

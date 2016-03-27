import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Структура данных, связанная с конкретными досками объявлений.
 * @author dwinner@inbox.ru
 */
public class BillData {
    
    private static final Logger LOG = Logger.getLogger(BillData.class.getName());
    
    private Image image;
    private int[] image_pixels;
    private URL link;

    public BillData(URL link, Image new_image) {
        this.link = link;
        this.image = new_image;
    }

    public void initPixels(int image_width, int image_height) {
        image_pixels = new int[image_width * image_height];
        PixelGrabber pixel_grabber = new PixelGrabber(image.getSource(), 0, 0, image_width, image_height, image_pixels, 0 , image_width);
        try {
            pixel_grabber.grabPixels();
        } 
        catch (InterruptedException ex) {
            image_pixels = null;
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the image_pixels
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public int[] getImage_pixels() {
        return image_pixels;
    }

    /**
     * @param image_pixels the image_pixels to set
     */
    @SuppressWarnings("AssignmentToCollectionOrArrayFieldFromParameter")
    public void setImage_pixels(int[] image_pixels) {
        this.image_pixels = image_pixels;
    }

    /**
     * @return the link
     */
    public URL getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(URL link) {
        this.link = link;
    }
    
}

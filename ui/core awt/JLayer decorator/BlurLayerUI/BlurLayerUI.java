
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

public class BlurLayerUI extends LayerUI<JComponent>
{
    private static final long serialVersionUID = -2396817860540160334L;
    private BufferedImage mOffscreenImage;
    private BufferedImageOp mOperation;

    public BlurLayerUI()
    {
        float ninth = 1.0f / 9.0f;
        float[] blurKernel =
        {
            ninth, ninth, ninth,
            ninth, ninth, ninth,
            ninth, ninth, ninth
        };
        mOperation = new ConvolveOp(
           new Kernel(3, 3, blurKernel),
           ConvolveOp.EDGE_NO_OP,
           null);
    }

    @Override
    public void paint(Graphics g, JComponent c)
    {
        // super.paint(g, c);
        int w = c.getWidth();
        int h = c.getHeight();

        if (w == 0 || h == 0)
        {
            return;
        }

        // ������� ������ ����������� �����������, ���� ���� �� ��� ����� ��������� ������.
        if (mOffscreenImage == null
           || mOffscreenImage.getWidth() != w
           || mOffscreenImage.getHeight() != h)
        {
            mOffscreenImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        }

        Graphics2D ig2 = mOffscreenImage.createGraphics();
        ig2.setClip(g.getClip());
        super.paint(ig2, c);
        ig2.dispose();

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(mOffscreenImage, mOperation, 0, 0);
    }

}

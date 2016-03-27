package game3d;

import java.awt.BorderLayout;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author dwinner@inbox.ru
 */
public class Game3D extends JFrame {
    
    private static final Logger LOG = Logger.getLogger(Game3D.class.getName());
    private static final long serialVersionUID = 1L;
    private Java3DWorld1 java3DWorld;
    private JPanel controlPanel;
    
    public Game3D() {
        super("3D Game");
        
        java3DWorld = new Java3DWorld1("andLinux_logo.jpg");
        controlPanel = new ControlPanel1(java3DWorld);
        
        getContentPane().add(java3DWorld, BorderLayout.CENTER);
        getContentPane().add(controlPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        Game3D app = new Game3D();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.pack();
        app.setVisible(true);
    }

}

import java.awt.*;
import java.awt.event.*;

class Flag extends Frame{
    
        public static final int W = 480, H = 480;
    
	Flag(String s){
		super(s);
		setBounds(0,0,W,H);
		setVisible(true);
	}
	@Override
        public void paint(Graphics g){
            int x = 120;
            int y = 120;
            Color initColor = g.getColor();
            g.setColor(Color.RED);
            int[] xp = new int[5];
            xp[0] = x+W/60;
            xp[1] = x+W/15;
            xp[2] = x+W/30;
            xp[3] = x+W/15;
            xp[4] = x+W/60;
            int[] yp = new int[5];
            yp[0] = y+H/60;
            yp[1] = y+H/60;
            yp[2] = y+H/30;
            yp[3] = y+H/20;
            yp[4] = y+H/20;
            g.fillPolygon(xp, yp, 5);
            g.drawLine(x+W/60, y+H/20, x+W/60, y+H/15);
            g.setColor(initColor);
	}
	public static void main(String[] args){
		Flag f = new Flag("Флажок");
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(0);
			}
		}); 
        }
}

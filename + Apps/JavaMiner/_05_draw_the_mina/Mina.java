import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; 
import java.awt.image.*; 

class Mina extends Frame{
    
        public static final int W = 480, H = 480;
    
	Mina(String s){
		super(s);
		setBounds(0,0,W,H);
		setVisible(true);
	}
	@Override
        public void paint(Graphics g){
            int x = 120;
            int y = 120;
            Graphics2D gr = (Graphics2D)g;
            GradientPaint gp = 
			new GradientPaint(x, y, Color.white, 
				x+W/12, y+H/12, Color.black, true);
            gr.setPaint(gp);
            gr.fill(new Ellipse2D.Double(x,y,W/12,H/12));
	}
	public static void main(String[] args){
		Mina f = new Mina("Мина");
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent ev){
				System.exit(0);
			}
		}); 
        }
}

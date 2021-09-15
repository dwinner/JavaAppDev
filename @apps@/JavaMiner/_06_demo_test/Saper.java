import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; 
import java.awt.image.*;

public class Saper extends Frame implements MouseListener{
    
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int MR = 10;
    public static final int MC = 10;
    public static final int NM = 10;
    public static int row,col;
    public int status = 0; // ������ ������ ����
    public int nMin = 0; // ��� ������������ ���
    public int nFlag = 0; // ���� �� ���������
    public int[][] Pole;

    public void Flag(int x, int y){
            Graphics g = getGraphics();
            Color initColor = g.getColor();
            g.setColor(Color.RED);
            int[] xp = new int[5];
	    Dimension d = this.getSize();
	    int dx = d.width;
	    int dy = d.height;
            xp[0] = x+dx/60;
            xp[1] = x+dx/15;
            xp[2] = x+dx/30;
            xp[3] = x+dx/15;
            xp[4] = x+dx/60;
            int[] yp = new int[5];
            yp[0] = y+dy/60;
            yp[1] = y+dy/60;
            yp[2] = y+dy/30;
            yp[3] = y+dy/20;
            yp[4] = y+dy/20;
            g.fillPolygon(xp, yp, 5);
            g.drawLine(x+dx/60, y+dy/20, x+dx/60, y+dy/15);
            g.setColor(initColor);
    }

    public void Mina(int x, int y){
            Dimension d = this.getSize();
	    int dx = d.width;
	    int dy = d.height;
	    Graphics g = getGraphics();
            Graphics2D gr = (Graphics2D)g;
            GradientPaint gp = 
			new GradientPaint(x, y, Color.white, 
				x+dx/12, y+dy/12, Color.black, true);
            gr.setPaint(gp);
            gr.fill(new Ellipse2D.Double(x,y,dx/12,dy/12));
    }
    
    // �����������
    public Saper(String s){    
        super(s);
	// ��������� ����
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        // ���� ����
        Menu mFile = new Menu("����");
        mb.add(mFile);
        MenuShortcut keyCreate = new MenuShortcut(KeyEvent.VK_N);
        MenuItem create = new MenuItem("����� ����",keyCreate);
        mFile.add(create);
        Scribble scr = new Scribble(this,480,480);
        add(scr);
        create.addActionListener(scr);
        Menu mColor = new Menu("����");
        MenuItem mGreen = new MenuItem("������");
        mColor.add(mGreen);
        MenuItem mRed = new MenuItem("�������");
        mColor.add(mRed);
        MenuItem mBlue = new MenuItem("�����");
        mGreen.addActionListener(scr);
        mRed.addActionListener(scr);
        mBlue.addActionListener(scr);
        mColor.add(mBlue);
        mFile.add(mColor);
        MenuItem mSound = new MenuItem("����");
        mSound.setEnabled(false);
        mFile.add(mSound);
        mFile.addSeparator();
        MenuShortcut keyExit = new MenuShortcut(KeyEvent.VK_X); 
        MenuItem exit = new MenuItem("�����",keyExit);
        mFile.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });	
        // ���� "�������"
        Menu mHelp = new Menu("�������");
        mb.setHelpMenu(mHelp);
        MenuShortcut keyHelp = new MenuShortcut(KeyEvent.VK_H);
        MenuItem mInfo = new MenuItem("� ���������",keyHelp);
	// ������� ��������� ��� ���������� mInfo
	// ���������� �������� agtqv ������� � ���������
        mHelp.add(mInfo);
        // ����� ��������� ����
        addWindowListener(new WinClose());
	addMouseListener(this);
        setBounds(0,0,480,480); // setSize(480,480)
        setVisible(true);
    }
    
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e){
	    if(this.status == 2) 
		    return;
	    if(this.status == 0) 
		    this.status = 1;
	    int x = e.getX();
	    int y = e.getY();
	    x -= LEFT;
	    y -= TOP;
	    if(x > 0 && y > 0){
		    // ����������� ���������� ���� � ������� ������ ����
		    Dimension d = this.getSize();
		    int dx = d.width/12;
		    int dy = d.height/12;
		    row = y/dy + 1;
		    col = x/dx + 1;
		    if((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0){
			    if(Pole[row][col] == 9){
				    Pole[row][col] += 100;
				    status = 2; // ���� ���������
				    ShowPole(status);
			    }
			    else if(Pole[row][col] < 9){
				    Open(row,col);
				    ShowPole(status);
			    }
		    }
		    else if((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0){
			    nFlag++;
			    if(Pole[row][col] == 9)
				    nMin++;
			    Pole[row][col] += 200; // �������� ����
			    if(nMin == NM && nFlag == NM){
				    status = 2; // ���� ���������
				    ShowPole(status);
			    }
			    else Kletka(row,col,status);
		    }
	    }
    }
    
    // ��������� ������ ���
    public void RandomArray(){
             
        Pole  = new int[MR+2][MC+2]; 

        for(row = 0; row <= MR+1; row++)
            for(col = 0; col <= MC+1; col++)
                Pole[row][col] = -3;		
        
        for(row = 1; row <= MR; row++)
            for(col = 1; col <= MC; col++)
                Pole[row][col] = 0;
	
	int n = 0; // ���������� ���
	M1:
	for(row = 1; row <= MR; row++){
	    for(col = 1; col <= MC; col++){
		double rand = Math.random(); // ���������� ��������� �����    
		Pole[row][col] = (int)(10*rand % MR);
		if(Pole[row][col] == 9){
			n++;
		}
		if(n == 10) break M1;
	    }
	}

        // �������� ���������� ��� � �������� �������
        int k;
        for(row = 1; row <= MR; row++)
            for(col = 1; col <= MC; col++)
                if(Pole[row][col] != 9){
                    k = 0;
                    if(Pole[row-1][col-1] == 9) k++;
                    if(Pole[row-1][col] == 9) k++;
                    if(Pole[row-1][col+1] == 9) k++;
                    if(Pole[row][col-1] == 9) k++;
                    if(Pole[row][col+1] == 9) k++;
                    if(Pole[row+1][col-1] == 9) k++;
                    if(Pole[row+1][col] == 9) k++;
                    if(Pole[row+1][col+1] == 9) k++;
                    Pole[row][col] = k;
                }
    }
    
    public void Kletka(int row, int col, int status){
	    
	    Graphics g = getGraphics();
	    Dimension d = this.getSize();
	    int dx = d.width/12;
	    int dy = d.height/12;
	    
	    int x = LEFT + (col-1)*dx;
	    int y = TOP + (row-1)*dy;
	    
	    if(status == 0){
		    // ����� �������
		    Color initColor = g.getColor();
		    g.setColor(Color.gray);
		    g.fillRect(x-1,y-1,x+dx,y+dy);
		    g.setColor(initColor);
		    return;
	    }
	    
	    // status =1 � staus = 2
	    if(Pole[row][col] < 100){
		    // ������ �� �������
		    Color initColor = g.getColor();
		    g.setColor(Color.gray); // �� ������� �����
		    g.fillRect(x-1,y-1,x+dx,y+dy);
		    if(status == 2 && Pole[row][col] == 9)
			    Mina(x,y); // �������� ����
		    g.setColor(initColor);
		    return;
	    }
	    
	    // ������ �������
	    Color initColor = g.getColor();
	    g.setColor(Color.white);
	    g.fillRect(x-1,y-1,x+dx,y+dy);
	    if(Pole[row][col] == 100) return;
	    if(Pole[row][col] >= 101 && Pole[row][col] <= 108){
		    g.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,14));
		    g.setColor(Color.blue);
		    g.drawString(Integer.toString(Pole[row][col]-100),x+3,y+2);
		    g.setColor(initColor);
		    return;
	    }
	    
	    if(Pole[row][col] >= 200)
		    Flag(x,y);
	    if(Pole[row][col] == 109){
		    g.setColor(Color.red);
		    g.fillRect(x,y,x+dx,y+dy);
	    }
	    
	    if((Pole[row][col] % 10 == 9) && (status == 2))
		    Mina(x,y);
    }
    
    public void ShowPole(int status){
	    for(row = 1; row <= MR; row++)
		    for(col = 1; col <= MC; col++)
			    Kletka(row,col,status);
    }
    
    @Override
    public void paint(Graphics g){
	    ShowPole(this.status);
    }
    
    // ����������� �������, ����������� �������
    // � ��� �������� ������, � ������� ��� ���
    public void Open(int row, int col){
        if(Pole[row][col] == 0){
            Pole[row][col] = 100;
            Open(row,col-1);
            Open(row-1,col);
            Open(row,col+1);
            Open(row+1,col);
            Open(row-1,col-1);
            Open(row-1,col+1);
            Open(row+1,col-1);
            Open(row+1,col+1);
        }
        else{
            if(Pole[row][col] < 100 && Pole[row][col] != -3)
                Pole[row][col] += 100;
        }
    }
    
    class WinClose extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
    
    public static void main(String[] args){
        Saper f = new Saper("\"����\" �� Java");
	f.RandomArray();
    }   
}

class Scribble extends Component implements ActionListener{
    
    protected Color currColor = Color.WHITE;
    protected Frame f;
    protected int w,h;
    
    public Scribble(Frame f, int w, int h){
		
		this.f = f;
		this.w = w;
                this.h = h;
    }
    
    @Override
    public Dimension getPreferredSize(){
		return new Dimension(w,h);
    }
	
    public void actionPerformed(ActionEvent event){
		
	String s = event.getActionCommand();
	if(s.equals("����� ����")) repaint();
	else if(s.equals("�������")) currColor = Color.red;
	else if(s.equals("������")) currColor = Color.green;
	else if(s.equals("�����")) currColor = Color.blue;
    }

}
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*; 
import java.awt.image.*;

// класс игры
class Miner extends Frame implements MouseListener{
    
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int MR = 10;
    public static final int MC = 10;
    public static final int W=40, H=40;
    public static Color currColor = Color.gray;
    public static int row,col;
    public static int status = 0;
    public int nMin = 0;
    public int nFlag = 0;
    public static int NM = 10;
    public static int[][] Pole;

    public Miner(String s){	    
        super(s);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        Menu mFile = new Menu("Файл");
	Menu mHelp = new Menu("Справка");
        mb.add(mFile);
	mb.setHelpMenu(mHelp);
	// элементы меню файл
        MenuItem create = new MenuItem("Новая игра",new MenuShortcut(KeyEvent.VK_N));
	Menu mColor = new Menu("Цвет");
	  MenuItem mGreen = new MenuItem("Зелёный");
	  MenuItem mRed = new MenuItem("Красный");
	  MenuItem mBlue = new MenuItem("Синий");
	  mColor.add(mGreen);
          mColor.add(mRed);
	  mColor.add(mBlue);
	MenuItem mSound = new MenuItem("Звук");
	  mSound.setEnabled(false);
	MenuItem exit = new MenuItem("Выход",new MenuShortcut(KeyEvent.VK_X));
        mFile.add(create);
	mFile.add(mColor);
	mFile.add(mSound);
	mFile.addSeparator();      
        mFile.add(exit);
        // вызов новой игры
        create.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                status = 0;
                repaint();
                RandomArray();
            }
        });
        // автономный класс-слушатель элементов меню файл
        Scribble scr = new Scribble(this,480,480);
        mGreen.addActionListener(scr);
        mRed.addActionListener(scr);
        mBlue.addActionListener(scr);
        add(scr);
	// безымянный объект-слушатель
	exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        // Спаравочное меню
        MenuItem mInfo = new MenuItem("О программе",new MenuShortcut(KeyEvent.VK_H));
        mHelp.add(mInfo);	
        // вызов справки
	mInfo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new AboutProgram("О программе");
            }
        });
        setBounds(0,0,480,480); // setSize(480,480); 
        setVisible(true);
	setLayout(null);
	addMouseListener(this); // самообработчик компонента
	addWindowListener(new WinClose());
    }
    // вложенный класс-адаптер для закрытия окна
    class WinClose extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
    // "слушаем" события мыши в компоненте   
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    
    public void mouseClicked(MouseEvent e){
	    
	    if(status == 2) 
		    return;
	    if(status == 0) 
		    status = 1;
	    int x = e.getX();
	    int y = e.getY();
	    x -= LEFT;
	    y -= TOP;
            Graphics g = getGraphics();
	    if(x > 0 && y > 0){
		    // преобразуем координаты мыши в индексы клетки поля
		    row = y/H + 1;
		    col = x/W + 1;
		    if((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0){
			    if(Pole[row][col] == 9){
				    Pole[row][col] += 100;
				    status = 2; // игра закончена
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
			    Pole[row][col] += 200; // поставим флаг
			    if(nMin == NM && nFlag == NM){
				    status = 2; // игра закончена
				    ShowPole(status);
			    }
			    else Kletka(row,col,status);
		    }
	    }
    }  
    // случайный массив мин
    public static void RandomArray(){
	    
        int lrow, lcol;
        Pole  = new int[MR+2][MC+2]; 
        for(lrow = 0; lrow <= MR+1; lrow++)
            for(lcol = 0; lcol <= MC+1; lcol++)
                Pole[lrow][lcol] = -3;		       
        for(lrow = 1; lrow <= MR; lrow++)
            for(lcol = 1; lcol <= MC; lcol++)
                Pole[lrow][lcol] = 0;	
	int n = 0; // количество мин
	M1:
	for(lrow = 1; lrow <= MR; lrow++){
	    for(lcol = 1; lcol <= MC; lcol++){
		double rand = Math.random(); // генерируем случайное число    
		Pole[lrow][lcol] = (int)(10*rand % MR);
		if(Pole[lrow][lcol] == 9){
			n++;
		}
		if(n == NM) break M1;
	    }
	}
        // вычислим количество мин в соседних клетках
        int k;
        for(lrow = 1; lrow <= MR; lrow++)
            for(lcol = 1; lcol <= MC; lcol++)
                if(Pole[lrow][lcol] != 9){
                    k = 0;
                    if(Pole[lrow-1][lcol-1] == 9) k++;
                    if(Pole[lrow-1][lcol] == 9) k++;
                    if(Pole[lrow-1][lcol+1] == 9) k++;
                    if(Pole[lrow][lcol-1] == 9) k++;
                    if(Pole[lrow][lcol+1] == 9) k++;
                    if(Pole[lrow+1][lcol-1] == 9) k++;
                    if(Pole[lrow+1][lcol] == 9) k++;
                    if(Pole[lrow+1][lcol+1] == 9) k++;
                    Pole[lrow][lcol] = k;
                }
    }
    // Рисуем флаг
    public void Flag(int x, int y){
            Graphics g = getGraphics();
            g.setColor(Color.RED);
	    Dimension d = getSize();
	    int dx = d.width/(MC+2);
	    int dy = d.height/(MR+2);
            int[] xp = new int[5];
            xp[0] = x+dx/5;
            xp[1] = x+4*dx/5;
            xp[2] = x+2*dx/5;
            xp[3] = x+4*dx/5;
            xp[4] = x+dx/5;
            int[] yp = new int[5];
            yp[0] = y+dy/5;
            yp[1] = y+dy/5;
            yp[2] = y+2*dy/5;
            yp[3] = y+3*dy/5;
            yp[4] = y+3*dy/5;
            g.fillPolygon(xp, yp, 5);
            g.drawLine(x+dx/5, y+3*dy/5, x+dx/5, y+4*dy/5);
	    g.setColor(Color.black);
	    g.drawLine(x-1,y-1,x-1,y+H);
            g.drawLine(x-1,y-1,x+W,y-1);
	    g.drawLine(x-1+W,y-1,x-1+W,y+H);
	    g.drawLine(x-1,y-1+W,x-1+W,y-1+H);
	    g.setColor(Miner.currColor);
    }
    // Рисуем мину
    public void Mina(int x, int y){
            Dimension d = getSize();
	    int dx = d.width/(MC+2);
	    int dy = d.height/(MR+2);
	    Graphics g = getGraphics();
            Graphics2D gr = (Graphics2D)g;
            GradientPaint gp = 
			new GradientPaint(x, y, Color.white, 
				x+dx, y+dy, Color.black, true);
            gr.setPaint(gp);
            gr.fill(new Ellipse2D.Double(x,y,dx,dy));
	    g.setColor(Color.black);
	    g.drawLine(x-1,y-1,x-1,y+H);
            g.drawLine(x-1,y-1,x+W,y-1);
	    g.drawLine(x-1+W,y-1,x-1+W,y+H);
	    g.drawLine(x-1,y-1+W,x-1+W,y-1+H);
	    g.setColor(Miner.currColor);
    }
    // Рисуем отдельную клетку поля
    public void Kletka(int row, int col, int status){
	    
	    Graphics g = getGraphics();
	    
	    int x = LEFT + (col-1)*W;
	    int y = TOP + (row-1)*H;
	    
	    if(status == 0){
		    // серый квадрат
		    g.setColor(Miner.currColor);
		    g.fillRect(x-1,y-1,x+W,y+H);
		    g.setColor(Color.black);
		    g.drawLine(x-1,y-1,x-1,y+H);
		    g.drawLine(x-1,y-1,x+W,y-1);
		    g.drawLine(x-1+W,y-1,x-1+W,y+H);
		    g.drawLine(x-1,y-1+W,x-1+W,y-1+H);
		    g.setColor(Miner.currColor);
		    return;
	    }
	    
	    // status = 1 и status = 2
	    if(Pole[row][col] < 100){
		    // клетка не открыта
		    g.setColor(Miner.currColor);
		    g.fillRect(x-1,y-1,x+W,y+H);
		    g.setColor(Color.black);
		    g.drawLine(x-1,y-1,x-1,y+H);
		    g.drawLine(x-1,y-1,x+W,y-1);
		    g.drawLine(x-1+W,y-1,x-1+W,y+H);
		    g.drawLine(x-1,y-1+W,x-1+W,y-1+H);
		    if(status == 2 && Pole[row][col] == 9)
			    Mina(x,y); // показать мину
		    g.setColor(Miner.currColor);
		    return;
	    }
	    
	    // клетка открыта
	    g.setColor(Color.white);
            g.fillRect(x,y,x+W,y+H);
            g.setColor(Color.black);
	    g.drawLine(x-1,y-1,x-1,y+H);
	    g.drawLine(x-1,y-1,x+W,y-1);
	    g.drawLine(x-1+W,y-1,x-1+W,y+H);
	    g.drawLine(x-1,y-1+H,x-1+W,y-1+H);
	    g.setColor(Miner.currColor);
	    if(Pole[row][col] == 100) return;
	    if(Pole[row][col] >= 101 && Pole[row][col] <= 108){
		    g.setFont(new Font("Arial",Font.BOLD|Font.ITALIC,18));
		    g.setColor(Color.blue);
		    g.drawString(Integer.toString(Pole[row][col]-100),x+W/2,y+H/2);
		    g.setColor(Miner.currColor);
		    return;
	    }
	    
	    if(Pole[row][col] >= 200)
		    Flag(x,y);
	    if(Pole[row][col] == 109){
		    g.setColor(Color.red);
		    g.fillRect(x,y,x+W,y+H);
		    g.setColor(Color.black);
		    g.drawLine(x-1,y-1,x-1,y+H);
		    g.drawLine(x-1,y-1,x+W,y-1);
		    g.drawLine(x-1+W,y-1,x-1+W,y+H);
		    g.drawLine(x-1,y-1+H,x-1+W,y-1+H);
		    g.setColor(Miner.currColor);
	    }
	    
	    if((Pole[row][col] % 10 == 9) && (status == 2))
		    Mina(x,y);
    }
    // Заполняем поле
    public void ShowPole(int status){
	    for(row = 1; row <= MR; row++)
		    for(col = 1; col <= MC; col++)
			    Kletka(row,col,status);
    }
    // Заполняем графический контекст компонента
    @Override
    public void paint(Graphics g){
	    ShowPole(status);
    }  
    // рекурсивная функция, открывающая текущию
    // и все соседние клетки, в которых нет мин
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
}
// точка входа в приложение
public class Saper{
	public static void main(String[] args){
        Miner f = new Miner("\"Сапёр\" на Java");
	Miner.RandomArray();
    } 
}
// автономный класс-слушатель элементов меню файл
class Scribble extends Component implements ActionListener{
    
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
	if(s.equals("Красный")) Miner.currColor = Color.red;
	else if(s.equals("Зелёный")) Miner.currColor = Color.green;
	else if(s.equals("Синий")) Miner.currColor = Color.blue;
    }
}
// автономный класс для вызова справки
class AboutProgram extends Frame{
	
	AboutProgram(String s){
		super(s);
		setBounds(50,50,420,420);
		setVisible(true);
		setLayout(null);
		addWindowListener(new WindowAdapter(){
			@Override
                public void windowClosing(WindowEvent ev){
				dispose();
			}
		});
	}
	@Override
	public void paint(Graphics g){
		g.setFont(new Font("Times New Roman",Font.BOLD,30));
		g.setColor(new Color(200,10,100));
		g.drawString("Java-сапёр\n",150,50);
		g.setColor(Color.blue);
		g.setFont(new Font("Serif", Font.ITALIC | Font.BOLD, 15)); 
		g.drawString("Автор \t Виневцев Д.И. \n",20,100);
		g.drawString("Период написания \t 29.10.2007 - 04.11.2007",20,120);
		g.drawString("Правила игры \n ",20,140);
		g.drawString("Вы должны пометить все мины флажками \n ",40,160);
		g.drawString("Флажок ставится правой кнопкой мыши\n ",40,180);
		g.drawString("Открыть отдельное поле можно левой кнопкой мыши\n ",40,200);
		g.drawString("Если поле пустое, то открываются все\n ",40,220);
		g.drawString("соседние поля, в которых нет мин\n ",40,240);
		g.drawString("Цифра в отдельном поле означает, что\n ",40,260);
		g.drawString("в соседних клетках соответствующее число мин\n ",40,280);
		g.drawString("Игра заканчивается, если\n ",20,300);
		g.drawString("1) Все мины помечены флажками\n ",40,320);
		g.drawString("2) Вы наткнулись на мину!\n ",40,340);
		g.setColor(Color.red);
		g.drawString("Помните!\n ",20,360);
		g.setColor(Color.blue);
		g.drawString("Сапёр ошибается только один раз!\n ",40,380);
	}  
}
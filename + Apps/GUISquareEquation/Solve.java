import java.awt.*;
import java.awt.event.*;

class Solve extends Frame{
	
	Solve(String s){
		super(s);
		Font f = new Font("Serif",Font.PLAIN,17);
		setFont(f);
		setSize(500,500);
		setVisible(true);
		setLayout(null);
		
		Label l1 = new Label("ax*x+bx+c=0",Label.CENTER);
		Label a = new Label("Введите a",Label.LEFT);
		Label b = new Label("Введите b",Label.LEFT);
		Label c = new Label("Введите c",Label.LEFT);
		TextField tf1 = new TextField(30);
		TextField tf2 = new TextField(30);
		TextField tf3 = new TextField(30);
		TextArea ta = new TextArea("Результаты вычислений\n",5,50,TextArea.SCROLLBARS_VERTICAL_ONLY);
		Button b1 = new Button("Вычислить");
		l1.setBounds(125,25,300,25);
		a.setBounds(25,100,150,25);
		b.setBounds(25,150,150,25);
		c.setBounds(25,200,150,25);
		tf1.setBounds(225,100,100,25);
		tf2.setBounds(225,150,100,25);
		tf3.setBounds(225,200,100,25);
		b1.setBounds(125,250,150,30);
		ta.setBounds(100,330,350,125);
		ta.setEditable(false);
		add(a);
		add(b);
		add(c);
		add(tf1);
		add(tf2);
		add(tf3);		
		add(b1);
		add(ta);		
		b1.addActionListener(new Calculate(tf1, tf2, tf3, ta));
	}
	public static void main(String[] args){
		Frame f = new Solve("Квадратное уравнение");
		f.addWindowListener(new WindowAdapter(){
		@Override
                public void windowClosing(WindowEvent ev){
				System.exit(0);
			}
		});
	}	
}

class Calculate implements ActionListener{
	
	private TextField tf1;
	private TextField tf2;
	private TextField tf3;
	private TextArea ta;
	
	Calculate(TextField tf1, TextField tf2, TextField tf3, TextArea ta){
            
		this.tf1 = tf1;
		this.tf2 = tf2;
		this.tf3 = tf3;
		this.ta = ta;
	}
	
	public void actionPerformed(ActionEvent ae){
                           
                float a = Float.parseFloat(tf1.getText());
                float b = Float.parseFloat(tf2.getText());
                float c = Float.parseFloat(tf3.getText());               
                
                if(a == 0.0f){
                    if(b == 0.0f){
                        if(c == 0.0f){
                            ta.append("x - любое число\n\n");
                        }
                        else{
                            ta.append("Решений нет\n\n");
                        }
                    }
                    else{
                        ta.append("x = "+(-c/b)+"\n\n");
                    }
                }
                else{
                    float d;
                    if((d = b*b-4*a*c) > 0){
                        ta.append("Первый корень - "+(-b+Math.sqrt(d))/(2*a)+"\n");
                        ta.append("Второй корень - "+(-b-Math.sqrt(d))/(2*a)+"\n\n");
                    }
                    else if(d == 0){
                        ta.append("Единственный корень - "+(-b/(2*a))+"\n\n");
                    }
                    else{
                        ta.append("Первый корень - "+(-b/(2*a)+"+"+Math.sqrt(Math.abs(d))+"*i\n"));
                        ta.append("Второй корень - "+(-b/(2*a)+"-"+Math.sqrt(Math.abs(d)))+"*i\n\n");
                    }
                }     
        }
}

// Простой апплет с функциями калькулятора.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
 
public class Calc extends Applet implements ActionListener {

	private TextField expText, resText;
	Parser p;

	public void init() {
		Label heading = new Label("Expression Calculator ", Label.CENTER);
		Label explab = new Label("Expression ", Label.CENTER);
		Label reslab = new Label("Result     ", Label.CENTER);
		expText = new TextField(24);
		resText = new TextField(24);
		resText.setEditable(false); // Поле результата только для отображения.
		add(heading);
		add(explab);
		add(expText);
		add(reslab);
		add(resText);
		expText.addActionListener(this);
		p = new Parser();
	}

	// Нажатие Enter в текстовом поле.
	public void actionPerformed(ActionEvent ae) {
		repaint();
	}

	public void paint(Graphics g) {
		double result = 0.0;
		String expstr = expText.getText();
		try {
			if (expstr.length() != 0)
				result = p.evaluate(expstr);
			resText.setText(Double.toString(result));
			showStatus("");
		}
		catch (ParserException exc) {
			showStatus(exc.toString());
			resText.setText("");
		}
	}
}
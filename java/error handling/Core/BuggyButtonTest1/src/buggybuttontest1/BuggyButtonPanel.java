package buggybuttontest1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BuggyButtonPanel extends JPanel
{
    public BuggyButtonPanel()
    {
        ActionListener listener = new ButtonListener();
        
        JButton yellowButton = new JButton("Yellow");
        add(yellowButton);
        yellowButton.addActionListener(listener);
        
        JButton blueButton = new JButton("Blue");
        add(blueButton);
        blueButton.addActionListener(listener);
        
        JButton redButton = new JButton("Red");
        add(redButton);
        redButton.addActionListener(listener);
    }
    
    private class ButtonListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent e)
        {
            String arg = e.getActionCommand().toLowerCase();
            
            switch (arg)
            {
                case "yellow":
                    setBackground(Color.yellow);
                    break;
                case "blue":
                    setBackground(Color.blue);
                    break;
                case "red":
                    setBackground(Color.red);
                    break;
                default: break;
            }
        }
    }
}

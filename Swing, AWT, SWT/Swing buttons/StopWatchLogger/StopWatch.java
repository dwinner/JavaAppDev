import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StopWatch
{
    private JLabel jlab;
    private JLabel jlabLog;
    private JCheckBox jcbKeepLog;
    private JButton jbtnStart;
    private JButton jbtnStop;
    private long start;
    private String[] etstr;
    private static final int LOGMAX = 3;
    
    public StopWatch()
    {
        etstr = new String[LOGMAX];
        for (int i=0; i<LOGMAX; i++)
        {
            etstr[i] = "";
        }
      
        JFrame jfrm = new JFrame("Improved Stopwatch");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(230, 170);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        jbtnStart = new JButton("Start");
        jbtnStop = new JButton("Stop");
        jbtnStop.setEnabled(false);
      
        jcbKeepLog = new JCheckBox("Keep Time Log");
        jlabLog = new JLabel("<html>--Time Log--<br /><br /><br /><br />");
        jlabLog.setBorder(BorderFactory.createLineBorder(Color.BLUE));
      
        jlab = new JLabel("Press Start to begin timing.");
      
        jbtnStart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                start = ae.getWhen();
                jlab.setText("Stopwatch is running...");
                jbtnStop.setEnabled(true);
                jbtnStart.setEnabled(false);
            }
        });
      
        jbtnStop.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                String logstr = "<html>--Time Log--<br />";
                double t;
              
                t = (double) (ae.getWhen()-start)/1000;
                jlab.setText("Elapsed time is " + t);
              
                jbtnStop.setEnabled(false);
                jbtnStart.setEnabled(true);
              
                if (jcbKeepLog.isSelected())
                {
                    for (int i=LOGMAX-1; i>0; i--)
                    {
                        etstr[i] = etstr[i-1];
                    }
                    etstr[0] = "" + t;
                    for (int i=0; i<LOGMAX; i++)
                    {
                        logstr += etstr[i] + "<br />";
                    }
                  
                    jlabLog.setText(logstr);
                }
            }
        });
        
        jcbKeepLog.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                if (!jcbKeepLog.isSelected())
                {
                    for (int i=0; i<LOGMAX; i++)
                    {
                        etstr[i] = "";
                    }
                    jlabLog.setText("<html>--Time Log--<br /><br /><br /><br />");
                }
            }
        });
        
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jcbKeepLog);
        jfrm.getContentPane().add(jlabLog);
        
        jfrm.setVisible(true);
    }
     
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new StopWatch();
            }
        });
    }
}
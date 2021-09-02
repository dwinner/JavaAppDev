// Взаимодействие компонентов JScrollBar и JSlider

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SliderRelate
{
    private JScrollBar jsb;
    private JSlider jsldr;
    
    public SliderRelate(String title)
    {
        JFrame jfrm = new JFrame(title);
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(350, 250);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jsb = new JScrollBar(Adjustable.HORIZONTAL, 0, 5, 0, 105);
        jsb.setPreferredSize(new Dimension(250, 15));
        jsb.setBlockIncrement(5);
        
        jsldr = new JSlider(JSlider.HORIZONTAL);
        jsldr.setPreferredSize(new Dimension(250, 50));
        jsldr.setMajorTickSpacing(10);
        jsldr.setMinorTickSpacing(5);
        jsldr.setLabelTable(jsldr.createStandardLabels(10));
        jsldr.setPaintTicks(true);
        jsldr.setPaintLabels(true);
        jsldr.setSnapToTicks(true);
        
        jsb.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                int curSb = ae.getValue();
                int curSldr;
                if (curSb <= jsldr.getMinimum())
                {
                    curSldr = jsldr.getMinimum();
                }
                else if (curSb > jsldr.getMinimum() && curSb <= jsldr.getMaximum())
                {
                    curSldr = curSb;
                }
                else
                {
                    curSldr = jsldr.getMaximum();
                }
                jsldr.setValue(curSldr);
            }
        });
        
        jsldr.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                int curSldr = jsldr.getValue();
                int curSb;
                if (curSldr <= jsb.getMinimum())
                {
                    curSb = jsb.getMinimum();
                }
                else if (curSldr > jsb.getMinimum() && curSldr <= jsb.getMaximum())
                {
                    curSb = curSldr;
                }
                else
                {
                    curSb = jsb.getMaximum();
                }
                jsb.setValue(curSb);                   
            }
        });
        
        jfrm.getContentPane().add(jsb);
        jfrm.getContentPane().add(jsldr);
        jfrm.setVisible(true);
    }
    
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new SliderRelate("JScrollBar-JSlider relationship");
            }
        });
    }
}
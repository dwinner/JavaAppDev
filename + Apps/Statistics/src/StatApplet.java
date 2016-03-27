import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import statistics.*;

/**
 * Апплет для статистики.
 * @author dwinner@inbox.ru
 */
public class StatApplet extends Applet implements ActionListener {
    
    private static final long serialVersionUID = 1L;
    
    private StatsWin sw;
    private Button show;
    private ArrayList<String> al = new ArrayList<String>();

    @Override
    public void init() {
        StringTokenizer st = new StringTokenizer(getParameter("data"), ", \n\r\t");
        String v;
        // Получить значения из HTML.
        while (st.hasMoreTokens()) {
            v = st.nextToken();
            al.add(v);
        }
        show = new Button("Display Statictics");
        add(show);
        show.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (sw == null) {
            double nums[] = new double[al.size()];
            try {
                for (int i=0; i<nums.length; i++) {
                    nums[i] = Double.parseDouble(al.get(i));
                }
            }
            catch (NumberFormatException exc) {
                System.out.println("Error reading data.");
                return;
            }
            sw = new StatsWin(nums);
            show.setEnabled(false);
            sw.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {
                    sw = null;
                    show.setEnabled(true);
                }
            });
        }
    }
    
}
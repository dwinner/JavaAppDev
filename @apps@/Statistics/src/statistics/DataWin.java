package statistics;

import java.awt.event.*;
import java.awt.*;

/**
 * Отобразить массив цифровых данных
 * @author dwinner@inbox.ru
 */
class DataWin extends Frame {
    
    private static final long serialVersionUID = 0x15L;
    private TextArea dataTA;
    
    DataWin(double[] data) {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
                dispose();
            }
        });
        dataTA = new TextArea(10, 10);
        dataTA.setEditable(false);
        for (int i=0; i<data.length; i++) {
            dataTA.append(data[i] + "\n");
        }
        setSize(new Dimension(100, 140));
        setLocation(320, 100);
        setTitle("Data");
        setResizable(false);
        add(dataTA);
        setVisible(true);
    }
    
}

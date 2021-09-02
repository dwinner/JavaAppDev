import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Этот фрейм содержит две вложенных панели с границей раздела. В них отображаются рисунки и
 * текстовые данные.
 */
public class SplitPaneFrame extends JFrame
{
    private Planet[] planets =
    {
        new Planet("Mercury", 2440, 0),
        new Planet("Venus", 6052, 0),
        new Planet("Earth", 6378, 1),
        new Planet("Mars", 3397, 2),
        new Planet("Jupiter", 71492, 16),
        new Planet("Saturn", 60268, 18),
        new Planet("Uranus", 25559, 17),
        new Planet("Neptune", 24766, 8),
        new Planet("Pluto", 1137, 1)
    };
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    
    public SplitPaneFrame() throws HeadlessException
    {
        setTitle("SplitPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Создание компонентов для имен, изображений и описаний планет.
        
        final JList<Planet> planetList = new JList<>(planets);
        final JLabel planetImage = new JLabel();
        final JTextArea planetDescription = new JTextArea();
        
        planetList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lsEvent)
            {
                Planet value = planetList.getSelectedValue();
                
                // Обновление рисунка и описания.
                
                planetImage.setIcon(value.getImage());
                planetDescription.setText(value.getDescription());
            }
        });
        
        // Установка панелей с границами раздела.
        
        JSplitPane innerPane =
            new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, planetList, planetImage);
        innerPane.setContinuousLayout(true);
        innerPane.setOneTouchExpandable(true);
        
        JSplitPane outerPane =
            new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, planetDescription);
        
        add(outerPane, BorderLayout.CENTER);
    }
}

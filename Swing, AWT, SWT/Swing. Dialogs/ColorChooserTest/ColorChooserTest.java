
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @version 1.03 2007-06-12
 * @author Cay Horstmann
 */
public class ColorChooserTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                ColorChooserFrame frame = new ColorChooserFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм с панелью для выбора цвета.
 */
class ColorChooserFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    ColorChooserFrame()
    {
        setTitle("ColorChooserTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Добавление панели для выбора цвета к фрейму.

        ColorChooserPanel panel = new ColorChooserPanel();
        add(panel);
    }

}

/**
 * Панель с кнопками для выбора трех типов компонентов.
 */
class ColorChooserPanel extends JPanel
{
    ColorChooserPanel()
    {
        JButton modalButton = new JButton("Modal");
        modalButton.addActionListener(new ModalListener());
        add(modalButton);

        JButton modelessButton = new JButton("Modeless");
        modelessButton.addActionListener(new ModelessListener());
        add(modelessButton);

        JButton immediateButton = new JButton("Immediate");
        immediateButton.addActionListener(new ImmediateListener());
        add(immediateButton);
    }

    /**
     * Данный слушатель отображает модальное окно для выбора цвета.
     */
    private class ModalListener implements ActionListener
    {
        @Override public void actionPerformed(ActionEvent event)
        {
            Color defaultColor = getBackground();
            Color selected = JColorChooser.showDialog(
               ColorChooserPanel.this,
               "Set background",
               defaultColor);
            if (selected != null)
            {
                setBackground(selected);
            }
        }

    }

    /**
     * Данный слушатель отображает немодальное окно для выбора цвета.
     * Цвет панели изменяется после щелчка на кнопке OK.
     */
    private class ModelessListener implements ActionListener
    {
        private JDialog dialog;
        private JColorChooser chooser;

        ModelessListener()
        {
            chooser = new JColorChooser();
            dialog = JColorChooser.createDialog(
               ColorChooserPanel.this,
               "Background Color",
               false /*
                * немодальное
                */,
               chooser,
               new ActionListener()    // Слушатель кнопки OK
               {
                   @Override public void actionPerformed(ActionEvent event)
                   {
                       setBackground(chooser.getColor());
                   }

               },
               null /*
                * Слушатель кнопки Cancel не задается
                */);
        }

        @Override public void actionPerformed(ActionEvent event)
        {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }

    }

    /**
     * Данный слушатель отображает немодальное окно для выбора цвета. Цвет панели изменяется сразу
     * после выбора.
     */
    private class ImmediateListener implements ActionListener
    {
        private JDialog dialog;
        private JColorChooser chooser;

        ImmediateListener()
        {
            chooser = new JColorChooser();
            chooser.getSelectionModel().addChangeListener(new ChangeListener()
            {
                @Override public void stateChanged(ChangeEvent event)
                {
                    setBackground(chooser.getColor());
                }

            });

            dialog = new JDialog((Frame) null, false /*
                * немодальное
                */);
            dialog.add(chooser);
            dialog.pack();
        }

        @Override public void actionPerformed(ActionEvent event)
        {
            chooser.setColor(getBackground());
            dialog.setVisible(true);
        }

    }

}
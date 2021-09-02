
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Групповое размещение компонентов.
 * <p/>
 * @version 1.0 2007-04-27
 * @author Cay Horstmann
 */
public class GroupLayoutTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                FontFrame frame = new FontFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }

        });
    }

}

/**
 * Фрейм, использующий групповую компоновку для размещения компонентов селектора шрифта.
 */
class FontFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;
    private JComboBox<?> face;
    private JComboBox<?> size;
    private JCheckBox bold;
    private JCheckBox italic;
    private JScrollPane pane;
    private JTextArea sample;

    @SuppressWarnings("unchecked")
    FontFrame()
    {
        setTitle("GroupLayoutTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        ActionListener listener = new FontAction();

        // Конструирование компонентов.

        JLabel faceLabel = new JLabel("Face: ");

        face = new JComboBox<>(new String[]
           {
               "Serif",
               "SansSerif",
               "Monospaced",
               "Dialog",
               "DialogInput"
           });

        face.addActionListener(listener);

        JLabel sizeLabel = new JLabel("Size: ");

        size = new JComboBox<>(new String[]
           {
               "8", "10", "12", "15", "18", "24", "36", "48"
           });

        size.addActionListener(listener);

        bold = new JCheckBox("Bold");
        bold.addActionListener(listener);

        italic = new JCheckBox("Italic");
        italic.addActionListener(listener);

        sample = new JTextArea();
        sample.setText("The quick brown fox jumps over the lazy dog");
        sample.setEditable(false);
        sample.setLineWrap(true);
        sample.setBorder(BorderFactory.createEtchedBorder());

        pane = new JScrollPane(sample);

        GroupLayout layout = new GroupLayout(getContentPane());
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
           layout.createSequentialGroup().addContainerGap().addGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
           GroupLayout.Alignment.TRAILING,
           layout.createSequentialGroup().addGroup(
           layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(faceLabel).addComponent(sizeLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
           layout.createParallelGroup(
           GroupLayout.Alignment.LEADING, false).addComponent(size).addComponent(face))).addComponent(italic).addComponent(bold)).addPreferredGap(
           LayoutStyle.ComponentPlacement.RELATED).addComponent(pane).addContainerGap()));

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[]
           {
               face, size
           });

        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
           layout.createSequentialGroup().addContainerGap().addGroup(
           layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
           pane, GroupLayout.Alignment.TRAILING).addGroup(
           layout.createSequentialGroup().addGroup(
           layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(face).addComponent(faceLabel)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(
           layout.createParallelGroup(
           GroupLayout.Alignment.BASELINE).addComponent(size).addComponent(sizeLabel)).addPreferredGap(
           LayoutStyle.ComponentPlacement.RELATED).addComponent(
           italic, GroupLayout.DEFAULT_SIZE,
           GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(bold, GroupLayout.DEFAULT_SIZE,
           GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))).addContainerGap()));
    }

    /**
     * Обработчик, изменяющий шрифт текста примера.
     */
    private class FontAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String fontFace = (String) face.getSelectedItem();
            int fontStyle = (bold.isSelected() ? Font.BOLD : 0) + (italic.isSelected() ? Font.ITALIC : 0);
            int fontSize = Integer.parseInt((String) size.getSelectedItem());
            Font font = new Font(fontFace, fontStyle, fontSize);
            sample.setFont(font);
            sample.repaint();
        }

    }

}

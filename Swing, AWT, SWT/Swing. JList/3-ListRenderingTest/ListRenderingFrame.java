import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Этот фрейм содержит список шрифтов и текстовую область, в которой текст отображается выбранным
 * щрифтом.
 * <p/>
 * @author JavaFx
 */
public class ListRenderingFrame extends JFrame
{
    private JTextArea text;
    private JList<? extends Font> fontList;
    private Font[] fonts = new Font[allFonts.length];
    
    public static final Font[] allFonts =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
    public static final int SIZE = 24;

    static
    {
        for (Font font : allFonts)
        {
            font = font.deriveFont(Font.PLAIN, SIZE);
        }
    }
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;
    
    {
        for (int i = 0; i < allFonts.length; i++)
        {
            Font curfont = allFonts[i];
            Font newFont = new Font(curfont.getFontName(), curfont.getStyle(), SIZE);
            fonts[i] = newFont;
        }
    }

    public ListRenderingFrame() throws HeadlessException
    {
        setTitle("ListRenderingTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        fontList = new JList<>(fonts);
        fontList.setVisibleRowCount(4);
        fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fontList.setCellRenderer(new FontCellRenderer());
        JScrollPane scrollPane = new JScrollPane(fontList);

        JPanel p = new JPanel();
        p.add(scrollPane);
        fontList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lsEvent)
            {
                Font font = fontList.getSelectedValue();
                text.setFont(font);
            }
        });
        
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.SOUTH);
        text = new JTextArea("The quick brown fox jumps over the lazy dog");
        text.setFont(allFonts[0]);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        contentPane.add(text, BorderLayout.CENTER);
    }
}

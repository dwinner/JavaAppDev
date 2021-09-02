import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import javax.swing.*;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Этот фрейм содержит длинный список слов и текстовую метку, содержащую предложение, в которое
 * включены выбранные слова.
 */
public class LongListFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;
    private JList<String> wordList;
    private JLabel label;
    private String prefix = "The quick brown ";
    private String suffix = " jumps over the lazy dog.";

    public LongListFrame() throws HeadlessException
    {
        setTitle("LongListTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        wordList = new JList<>(new WordListModel(3));
        wordList.setSelectionMode(SINGLE_SELECTION);
        wordList.setPrototypeCellValue("www");
        JScrollPane scrollPane = new JScrollPane(wordList);

        JPanel p = new JPanel();
        p.add(scrollPane);
        wordList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent lsEvent)
            {
                StringBuilder word = new StringBuilder(wordList.getSelectedValue());
                setSubject(word.toString());
            }
        });
        
        Container contentPane = getContentPane();
        contentPane.add(p, BorderLayout.NORTH);
        label = new JLabel(prefix + suffix);
        contentPane.add(label, BorderLayout.CENTER);
        setSubject("fox");
    }

    /**
     * Включение слова в состав предложения.
     * <p/>
     * @param word Новое подлежащее, включаемое в предложение
     */
    private void setSubject(String word)
    {
        StringBuilder text = new StringBuilder(prefix);
        text.append(word);
        text.append(suffix);
        label.setText(text.toString());
    }
}

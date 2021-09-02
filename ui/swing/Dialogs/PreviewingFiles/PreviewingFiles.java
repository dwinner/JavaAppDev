// ��������������� �������� ������ � ���������� JFileChooser
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

public class PreviewingFiles extends JFrame
{
    public PreviewingFiles() throws HeadlessException
    {
        super("Previewing Files");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ���� �� �����
        setSize(300, 200);
        setVisible(true);
        
        // ����������� ��������� ��� ������ �����
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("�������� �����������");
        
        // ������������ ������
        chooser.addChoosableFileFilter(new ImageFileFilter());
        
        // ������������ �������������� ���������
        Previewer previewer = new Previewer();
        chooser.setAccessory(previewer);
        
        // ������������ � �������� ���������
        chooser.addPropertyChangeListener(previewer);
        
        // ������� ���������� ���� �� �����
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION)
        {
            JOptionPane.showMessageDialog(this, chooser.getSelectedFile());
        }
    }
    
    /**
     * ��������� ��� ���������������� ���������
     */
    private static class Previewer extends JPanel implements PropertyChangeListener
    {
        private JLabel label;
        
        Previewer()
        {
            // ����������� ���������
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(100, 100));
            // ������� ������� � ������ ���������
            label = new JLabel();
            JScrollPane scroller = new JScrollPane(label);
            add(scroller);
        }

        @Override public void propertyChange(PropertyChangeEvent evt)
        {
            if (evt.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
            {
                // �������� ��������� ����, ���������� ���
                if (evt.getNewValue() != null)
                {
                    label.setIcon(new ImageIcon(evt.getNewValue().toString()));
                }
            }
        }

    }
    
    /**
     * ������, ���������� ����� � �������������
     */
    private static class ImageFileFilter extends FileFilter
    {
        // ��������� ���� ��� ���������� ���
        @Override public boolean accept(File f)
        {
            // ��� �������� ���������
            if (f.isDirectory())
            {
                return true;
            }
            // ��� ������ ������� �� ����������
            String fName = f.getName();
            return (fName.endsWith("jpg") || fName.endsWith("gif") || fName.endsWith("png"));            
        }

        // �������� �������������� �����
        @Override public String getDescription() { return "����������� (*.jpg, *.gif, *.png)"; }
    }
    
    public static void main(String[] args)
    {
        new PreviewingFiles();
    }
    
}
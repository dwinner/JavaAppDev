package drawing;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * �������� ������ FileFilter ��� ������ ������ Drawing � ���������� ���� JFileChooser.
 * @author dwinner@inbox.ru
 */
public class DrawingFileFilter extends FileFilter {
    
    // ������, ������������ � �������� JFileChooser
    private String DESCRIPTION = "Drawing Files (*.df)";
    
    // �������� ���������� ��� ������ Drawing
    private String EXTENSION = ".df";

    @Override public boolean accept(File f) {
    	// �������� ���������� �� ������������
        return (f.getName().toLowerCase().endsWith(EXTENSION));
    }

    @Override public String getDescription() {
    	// ��������� �������� ��� ������ Drawing
        return DESCRIPTION;
    }
    
}
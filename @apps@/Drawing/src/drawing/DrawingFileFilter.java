package drawing;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * Подкласс класса FileFilter для выбора файлов Drawing в диалоговом окне JFileChooser.
 * @author dwinner@inbox.ru
 */
public class DrawingFileFilter extends FileFilter {
    
    // Строка, используемая в описании JFileChooser
    private String DESCRIPTION = "Drawing Files (*.df)";
    
    // Файловое расширение для файлов Drawing
    private String EXTENSION = ".df";

    @Override public boolean accept(File f) {
    	// Проверка расширения на корректность
        return (f.getName().toLowerCase().endsWith(EXTENSION));
    }

    @Override public String getDescription() {
    	// Получение описания для файлов Drawing
        return DESCRIPTION;
    }
    
}
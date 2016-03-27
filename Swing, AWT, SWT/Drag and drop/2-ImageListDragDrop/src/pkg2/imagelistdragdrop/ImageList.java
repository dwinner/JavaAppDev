package pkg2.imagelistdragdrop;

import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class ImageList extends JList
{
    @SuppressWarnings({"unchecked", "unchecked"})
    public ImageList(File[] imageFiles)
    {
        DefaultListModel model = new DefaultListModel();
        for (File aFile : imageFiles)
        {
            model.addElement(new ImageIcon(aFile.getPath()));
        }
        
        setModel(model);
        setVisibleRowCount(0);
        setLayoutOrientation(JList.HORIZONTAL_WRAP);
        setDragEnabled(true);
        setDropMode(DropMode.ON_OR_INSERT);
        setTransferHandler(new ImageListTransferHandler());
    }
}

import java.awt.*;
import java.awt.event.*;

public class SrcMenu extends Frame{
    
    public SrcMenu(String s){
        super(s);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        // ���� ����
        Menu mFile = new Menu("����");
        mb.add(mFile);
        MenuShortcut keyCreate = new MenuShortcut(KeyEvent.VK_N);
        MenuItem create = new MenuItem("����� ����",keyCreate);
        mFile.add(create);
        Menu mColor = new Menu("����");
        CheckboxMenuItem mGreen = new CheckboxMenuItem("������");
        mColor.add(mGreen);
        CheckboxMenuItem mRed = new CheckboxMenuItem("�������");
        mColor.add(mRed);
        CheckboxMenuItem mBlue = new CheckboxMenuItem("�����");
        mColor.add(mBlue);
        mFile.add(mColor);
        CheckboxMenuItem mSound = new CheckboxMenuItem("����");
        mFile.add(mSound);
        mFile.addSeparator();
        MenuShortcut keyExit = new MenuShortcut(KeyEvent.VK_X); 
        MenuItem exit = new MenuItem("�����",keyExit);
        mFile.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        // ���� "�������"
        Menu mHelp = new Menu("�������");
        mb.setHelpMenu(mHelp);
        MenuShortcut keyHelp = new MenuShortcut(KeyEvent.VK_H);
        MenuItem mInfo = new MenuItem("� ���������",keyHelp);
        mHelp.add(mInfo);
        // ����� ��������� ����
        addWindowListener(new WinClose());
        pack();
        setVisible(true);
    }
    
    class WinClose extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }
    }
    
    public static void main(String[] args){
        new SrcMenu("\"����\" �� Java");
    }   
}
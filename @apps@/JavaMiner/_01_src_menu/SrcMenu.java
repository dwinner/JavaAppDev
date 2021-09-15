import java.awt.*;
import java.awt.event.*;

public class SrcMenu extends Frame{
    
    public SrcMenu(String s){
        super(s);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        // меню файл
        Menu mFile = new Menu("Файл");
        mb.add(mFile);
        MenuShortcut keyCreate = new MenuShortcut(KeyEvent.VK_N);
        MenuItem create = new MenuItem("Новая игра",keyCreate);
        mFile.add(create);
        Menu mColor = new Menu("Цвет");
        CheckboxMenuItem mGreen = new CheckboxMenuItem("Зелёный");
        mColor.add(mGreen);
        CheckboxMenuItem mRed = new CheckboxMenuItem("Красный");
        mColor.add(mRed);
        CheckboxMenuItem mBlue = new CheckboxMenuItem("Синий");
        mColor.add(mBlue);
        mFile.add(mColor);
        CheckboxMenuItem mSound = new CheckboxMenuItem("Звук");
        mFile.add(mSound);
        mFile.addSeparator();
        MenuShortcut keyExit = new MenuShortcut(KeyEvent.VK_X); 
        MenuItem exit = new MenuItem("Выход",keyExit);
        mFile.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        // меню "Справка"
        Menu mHelp = new Menu("Справка");
        mb.setHelpMenu(mHelp);
        MenuShortcut keyHelp = new MenuShortcut(KeyEvent.VK_H);
        MenuItem mInfo = new MenuItem("О программе",keyHelp);
        mHelp.add(mInfo);
        // конец установки меню
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
        new SrcMenu("\"Сапёр\" на Java");
    }   
}
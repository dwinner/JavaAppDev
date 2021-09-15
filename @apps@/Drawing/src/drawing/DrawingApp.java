package drawing;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

/**
 * DrawingApp - приложение рисования, которое использует MVC, многодокументный интерфейс и Java2D.
 * @author dwinner@inbox.ru
 */
public class DrawingApp extends JFrame {
    
	private static final long serialVersionUID = -162228242866768372L;
	private static final Logger LOG = Logger.getLogger(DrawingApp.class.getName());
	
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;
    
    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;
    
    private Action  newAction,
                    openAction,
                    exitAction,
                    aboutAction;
    
    private JMenuItem saveMenuItem, saveAsMenuItem;
    
    private JToolBar toolBar;
    private JDesktopPane desktopPane;
    private SplashScreen splashScreen;

    public DrawingApp() {
        super("Drawing");        
        // Задание значка для верхнего левого угла JFrame
        ImageIcon icon = new ImageIcon(DrawingApp.class.getResource("../images/duke.jpg"));
        setIconImage(icon.getImage());
        showSplashScreen();       
        // Не скрывать окно при щелчке на кнопке закрытия
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Создание панели JDesktopPane для многодокументного интерфейса
        desktopPane = new JDesktopPane();        
        // Отображение содержимого при перетаскивании JInternalFrame
        desktopPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);        
        // Действии по созданию новых рисунков
        Icon newIcon = new ImageIcon(DrawingApp.class.getResource("../images/ani-new.gif"));       
        newAction = new AbstractDrawingAction("New", newIcon, "Create New Drawing", new Integer('N')) {
			private static final long serialVersionUID = -5051062419495266662L;
			@Override public void actionPerformed(ActionEvent ae) {
                createNewWindow();
            }
        };        
        // Создание действия для открытия существующих рисунков
        Icon openIcon = new ImageIcon(DrawingApp.class.getResource("../images/open.gif"));
        openAction = new AbstractDrawingAction("Open", openIcon, "Open Existing Drawing", new Integer('O')) {
			private static final long serialVersionUID = 9172470347568505350L;
			@Override public void actionPerformed(ActionEvent ae) {
                DrawingInternalFrame frame = createNewWindow();
                if (!frame.openDrawing()) {
                    frame.close();
                }
            }
        };        
        // Создание действия для завершения приложения
        Icon exitIcon = new ImageIcon(DrawingApp.class.getResource("../images/exit.gif"));
        exitAction = new AbstractDrawingAction("Exit", exitIcon, "Exit Application", new Integer('X')) {
			private static final long serialVersionUID = 7036189666832274585L;
			@Override public void actionPerformed(ActionEvent ae) {
                exitApplication();
            }
        };        
        // Создание действия для открытия диалогового окна About
        Icon aboutIcon = new ImageIcon(DrawingApp.class.getResource("../images/about.gif"));
        aboutAction = new AbstractDrawingAction("About", aboutIcon, "About Application", new Integer('B')) {
			private static final long serialVersionUID = -4116602713213973100L;
			@Override public void actionPerformed(ActionEvent ae) {
                String tm = "Drawing v1.0. \n Copyright 2011. Vinevcev D.I.";
                JOptionPane.showMessageDialog(DrawingApp.this, tm);
            }        
        };        
        // Создание меню File и его мнемоники
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');        
        // Создание меню Help и его мнемоники
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');        
        menuBar = new JMenuBar();        
        // Добавление действий New Drawing и Open Drawing в меню File и удаление их значков
        fileMenu.add(newAction).setIcon(null);
        fileMenu.add(openAction).setIcon(null);		
        // Создание пунктов меню JMenuItem для сохранения рисунков
        // Пункты меню будут активизировать действия по сохранению
        // для текущего DrawingInternalFrame
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save As");		
        // Добавление пунктов меню Save, Save As и Close в меню File
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);		
        fileMenu.addSeparator();		
        // Добавление действия Exit в меню File и удаление его значка
        fileMenu.add(exitAction).setIcon(null);		
        // Добавление действия About в меню File и удаление значка
        fileMenu.add(aboutAction).setIcon(null);	
        // Добавление меню File и Help в строку меню JMenuBar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);		
        // Задание строки меню JMenuBar
        setJMenuBar(menuBar);		
        // Создание панели инструментов JToolBar приложения
        toolBar = new JToolBar();		
        // Запрет перемещения панели JToolBar
        toolBar.setFloatable(false);
        // Добавление действий New Drawing и Open Drawing в панель JToolBar
        toolBar.add(newAction);
        toolBar.add(openAction);
        toolBar.addSeparator();		
        // Добавление действия Exit в панель JToolBar
        toolBar.add(exitAction);
        toolBar.addSeparator();		
        // Добавление действия About в панель JToolBar
        toolBar.add(aboutAction);
        // Добавление панелей toolBar и desktopPane в панель ContentPane
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        // Добавление слушателя WindowListener для события windowClosing
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent we) {
                exitApplication();
            }
        });		
        // Ожидание при отображении заставки SplashScreen
        while (splashScreen.isVisible()) {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException iEx) {
                LOG.log(Level.SEVERE, null, iEx);
            }
        }		
        // Задание начальных размеров окна JFrame
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);		
        // Размещение окна приложения
        centerWindowOnScreen();		
        // Сделать окно приложения видимым
        setVisible(true);		
        // Создание пустого окна рисунка
        createNewWindow();
    }
    
    /**
     * Создание нового окна DrawingInternalFrame
     * @return Новый MDI-объект
     */
    private DrawingInternalFrame createNewWindow() {
        // Создание нового окна DrawingInternalFrame
        DrawingInternalFrame frame = new DrawingInternalFrame("Untitled Drawing");       
        // Добавление слушателя для событий InternalFrame
        frame.addInternalFrameListener(new DrawingInternalFrameListener());        
        // Сделать окно DrawingInternalFrame непрозрачным
        frame.setOpaque(true);        
        // Добавление окна DrawingInternalFrame в панель desktopPane
        desktopPane.add(frame);        
        // Сделать окно DrawingInternalFrame видимым
        frame.setVisible(true);        
        try {   // Выбор нового окна DrawingInternalFrame
            frame.setSelected(true);
        } 
        catch (PropertyVetoException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }        
        // Возврат ссылки на вновь созданный DrawingInternalFrame
        return frame;
    }
    
    /**
     * Объект InternalFrameAdapter для прослушивания событий InternalFrame
     * @author dwinner@inbox.ru
     *
     */
    private class DrawingInternalFrameListener extends InternalFrameAdapter {
        DrawingInternalFrameListener() {
        }        
        @Override public void internalFrameClosing(InternalFrameEvent ife) {
        	// При закрытии окна DrawingInternalFrame запретить выполнение
            // соответствующих действий
            DrawingInternalFrame frame = (DrawingInternalFrame) ife.getSource();
            // Окно успешно закрыто, запретить пункты Save меню
            if (frame.close()) {
                saveMenuItem.setAction(null);
                saveAsMenuItem.setAction(null);
            }
        }        
        @Override public void internalFrameActivated(InternalFrameEvent ife) {
        	// При активации DrawingInternalFrame сделать его панель JToolBar видимым и установить
            // для пунктов меню JMenuItem действия DrawingInternalFrame
            DrawingInternalFrame frame = (DrawingInternalFrame) ife.getSource();
            // Установить для пункта меню saveMenuItem действие saveAction объекта DrawingInternalFrame
            saveMenuItem.setAction(frame.getSaveAction());
            saveMenuItem.setIcon(null);            
            // Установить для пункта меню saveAsMenuItem действие saveAction объекта DrawingInternalFrame
            saveAsMenuItem.setAction(frame.getSaveAsAction());
            saveAsMenuItem.setIcon(null);
        }        
    }
    
    /**
     * Закрыть каждое окно DrawingInternalFrame, чтобы дать возможность пользователю
     * сохранить рисунки, а затем выйти из приложения
     */
    private void exitApplication() {
        // Получение массива окон JInternalFrame из объекта desktopPane
        JInternalFrame frames[] = desktopPane.getAllFrames();        
        // Отслеживание незакрытых окон DrawingInternalFrame
        boolean allFramesClosed = true;        
        // Выбор и закрытие каждого из окон DrawingInternalFrame
        for (int i = 0; i < frames.length; i++) {
            DrawingInternalFrame nextFrame = (DrawingInternalFrame) frames[i];
            try {   // Выбор текущего окна DrawingInternalFrame
                nextFrame.setSelected(true);
            } 
            catch (PropertyVetoException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            // Закрытие окна DrawingInternalFrame и обновление переменной allFramesClosed
            allFramesClosed = allFramesClosed && nextFrame.close();
        }        
        // Выход из приложения, только если все окна закрыты
        if (allFramesClosed) {
            System.exit(0);
        }
    }
    
    /**
     * Отображение стартового экрана приложения
     */
    public final void showSplashScreen() {
        // Создание изображения ImageIcon для логотипа
        Icon logoIcon = new ImageIcon(getClass().getResource("../images/Tux.jpg"));        
        // Создание новой надписи JLabel для логотипа
        JLabel logoLabel = new JLabel(logoIcon);        
        // Задание фонового цвета для надписи JLabel
        logoLabel.setBackground(Color.white);        
        // Задание границ стартового экрана
        logoLabel.setBorder(new MatteBorder(5, 5, 5, 5, Color.black));        
        // Сделать надпись logoLabel непрозрачной
        logoLabel.setOpaque(true);        
        // Создание объекта SplashScreen для логотипа
        splashScreen = new SplashScreen(logoLabel);        
        // Отображение заставки SplashScreen в течение 3-х секунд
        splashScreen.showSplash(3000);
    }

    /**
     * Центрирование окна приложения на экране пользователя
     */
    private void centerWindowOnScreen() {
        // Получение размеров пользовательского экрана
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        // Использовать ширину и высоту экрана, ширину и высоту окна приложения для
        // центрирования окна приложения на экране
        int width = getSize().width;
        int height = getSize().height;
        int x = (screenDimension.width - width) / 2;
        int y = (screenDimension.height - height) / 2;        
        // Размещение окна приложения оп центру экрана
        setBounds(x, y, width, height);
    }

    /**
     * Точка входа
     * @param args Параметры не нужны
     */
    public static void main(String[] args) {
        new DrawingApp();
    }
    
}
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
 * DrawingApp - ���������� ���������, ������� ���������� MVC, ���������������� ��������� � Java2D.
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
        // ������� ������ ��� �������� ������ ���� JFrame
        ImageIcon icon = new ImageIcon(DrawingApp.class.getResource("../images/duke.jpg"));
        setIconImage(icon.getImage());
        showSplashScreen();       
        // �� �������� ���� ��� ������ �� ������ ��������
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // �������� ������ JDesktopPane ��� ����������������� ����������
        desktopPane = new JDesktopPane();        
        // ����������� ����������� ��� �������������� JInternalFrame
        desktopPane.setDragMode(JDesktopPane.LIVE_DRAG_MODE);        
        // �������� �� �������� ����� ��������
        Icon newIcon = new ImageIcon(DrawingApp.class.getResource("../images/ani-new.gif"));       
        newAction = new AbstractDrawingAction("New", newIcon, "Create New Drawing", new Integer('N')) {
			private static final long serialVersionUID = -5051062419495266662L;
			@Override public void actionPerformed(ActionEvent ae) {
                createNewWindow();
            }
        };        
        // �������� �������� ��� �������� ������������ ��������
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
        // �������� �������� ��� ���������� ����������
        Icon exitIcon = new ImageIcon(DrawingApp.class.getResource("../images/exit.gif"));
        exitAction = new AbstractDrawingAction("Exit", exitIcon, "Exit Application", new Integer('X')) {
			private static final long serialVersionUID = 7036189666832274585L;
			@Override public void actionPerformed(ActionEvent ae) {
                exitApplication();
            }
        };        
        // �������� �������� ��� �������� ����������� ���� About
        Icon aboutIcon = new ImageIcon(DrawingApp.class.getResource("../images/about.gif"));
        aboutAction = new AbstractDrawingAction("About", aboutIcon, "About Application", new Integer('B')) {
			private static final long serialVersionUID = -4116602713213973100L;
			@Override public void actionPerformed(ActionEvent ae) {
                String tm = "Drawing v1.0. \n Copyright 2011. Vinevcev D.I.";
                JOptionPane.showMessageDialog(DrawingApp.this, tm);
            }        
        };        
        // �������� ���� File � ��� ���������
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');        
        // �������� ���� Help � ��� ���������
        helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');        
        menuBar = new JMenuBar();        
        // ���������� �������� New Drawing � Open Drawing � ���� File � �������� �� �������
        fileMenu.add(newAction).setIcon(null);
        fileMenu.add(openAction).setIcon(null);		
        // �������� ������� ���� JMenuItem ��� ���������� ��������
        // ������ ���� ����� �������������� �������� �� ����������
        // ��� �������� DrawingInternalFrame
        saveMenuItem = new JMenuItem("Save");
        saveAsMenuItem = new JMenuItem("Save As");		
        // ���������� ������� ���� Save, Save As � Close � ���� File
        fileMenu.add(saveMenuItem);
        fileMenu.add(saveAsMenuItem);		
        fileMenu.addSeparator();		
        // ���������� �������� Exit � ���� File � �������� ��� ������
        fileMenu.add(exitAction).setIcon(null);		
        // ���������� �������� About � ���� File � �������� ������
        fileMenu.add(aboutAction).setIcon(null);	
        // ���������� ���� File � Help � ������ ���� JMenuBar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);		
        // ������� ������ ���� JMenuBar
        setJMenuBar(menuBar);		
        // �������� ������ ������������ JToolBar ����������
        toolBar = new JToolBar();		
        // ������ ����������� ������ JToolBar
        toolBar.setFloatable(false);
        // ���������� �������� New Drawing � Open Drawing � ������ JToolBar
        toolBar.add(newAction);
        toolBar.add(openAction);
        toolBar.addSeparator();		
        // ���������� �������� Exit � ������ JToolBar
        toolBar.add(exitAction);
        toolBar.addSeparator();		
        // ���������� �������� About � ������ JToolBar
        toolBar.add(aboutAction);
        // ���������� ������� toolBar � desktopPane � ������ ContentPane
        getContentPane().add(toolBar, BorderLayout.NORTH);
        getContentPane().add(desktopPane, BorderLayout.CENTER);
        // ���������� ��������� WindowListener ��� ������� windowClosing
        addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent we) {
                exitApplication();
            }
        });		
        // �������� ��� ����������� �������� SplashScreen
        while (splashScreen.isVisible()) {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException iEx) {
                LOG.log(Level.SEVERE, null, iEx);
            }
        }		
        // ������� ��������� �������� ���� JFrame
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);		
        // ���������� ���� ����������
        centerWindowOnScreen();		
        // ������� ���� ���������� �������
        setVisible(true);		
        // �������� ������� ���� �������
        createNewWindow();
    }
    
    /**
     * �������� ������ ���� DrawingInternalFrame
     * @return ����� MDI-������
     */
    private DrawingInternalFrame createNewWindow() {
        // �������� ������ ���� DrawingInternalFrame
        DrawingInternalFrame frame = new DrawingInternalFrame("Untitled Drawing");       
        // ���������� ��������� ��� ������� InternalFrame
        frame.addInternalFrameListener(new DrawingInternalFrameListener());        
        // ������� ���� DrawingInternalFrame ������������
        frame.setOpaque(true);        
        // ���������� ���� DrawingInternalFrame � ������ desktopPane
        desktopPane.add(frame);        
        // ������� ���� DrawingInternalFrame �������
        frame.setVisible(true);        
        try {   // ����� ������ ���� DrawingInternalFrame
            frame.setSelected(true);
        } 
        catch (PropertyVetoException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }        
        // ������� ������ �� ����� ��������� DrawingInternalFrame
        return frame;
    }
    
    /**
     * ������ InternalFrameAdapter ��� ������������� ������� InternalFrame
     * @author dwinner@inbox.ru
     *
     */
    private class DrawingInternalFrameListener extends InternalFrameAdapter {
        DrawingInternalFrameListener() {
        }        
        @Override public void internalFrameClosing(InternalFrameEvent ife) {
        	// ��� �������� ���� DrawingInternalFrame ��������� ����������
            // ��������������� ��������
            DrawingInternalFrame frame = (DrawingInternalFrame) ife.getSource();
            // ���� ������� �������, ��������� ������ Save ����
            if (frame.close()) {
                saveMenuItem.setAction(null);
                saveAsMenuItem.setAction(null);
            }
        }        
        @Override public void internalFrameActivated(InternalFrameEvent ife) {
        	// ��� ��������� DrawingInternalFrame ������� ��� ������ JToolBar ������� � ����������
            // ��� ������� ���� JMenuItem �������� DrawingInternalFrame
            DrawingInternalFrame frame = (DrawingInternalFrame) ife.getSource();
            // ���������� ��� ������ ���� saveMenuItem �������� saveAction ������� DrawingInternalFrame
            saveMenuItem.setAction(frame.getSaveAction());
            saveMenuItem.setIcon(null);            
            // ���������� ��� ������ ���� saveAsMenuItem �������� saveAction ������� DrawingInternalFrame
            saveAsMenuItem.setAction(frame.getSaveAsAction());
            saveAsMenuItem.setIcon(null);
        }        
    }
    
    /**
     * ������� ������ ���� DrawingInternalFrame, ����� ���� ����������� ������������
     * ��������� �������, � ����� ����� �� ����������
     */
    private void exitApplication() {
        // ��������� ������� ���� JInternalFrame �� ������� desktopPane
        JInternalFrame frames[] = desktopPane.getAllFrames();        
        // ������������ ���������� ���� DrawingInternalFrame
        boolean allFramesClosed = true;        
        // ����� � �������� ������� �� ���� DrawingInternalFrame
        for (int i = 0; i < frames.length; i++) {
            DrawingInternalFrame nextFrame = (DrawingInternalFrame) frames[i];
            try {   // ����� �������� ���� DrawingInternalFrame
                nextFrame.setSelected(true);
            } 
            catch (PropertyVetoException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
            // �������� ���� DrawingInternalFrame � ���������� ���������� allFramesClosed
            allFramesClosed = allFramesClosed && nextFrame.close();
        }        
        // ����� �� ����������, ������ ���� ��� ���� �������
        if (allFramesClosed) {
            System.exit(0);
        }
    }
    
    /**
     * ����������� ���������� ������ ����������
     */
    public final void showSplashScreen() {
        // �������� ����������� ImageIcon ��� ��������
        Icon logoIcon = new ImageIcon(getClass().getResource("../images/Tux.jpg"));        
        // �������� ����� ������� JLabel ��� ��������
        JLabel logoLabel = new JLabel(logoIcon);        
        // ������� �������� ����� ��� ������� JLabel
        logoLabel.setBackground(Color.white);        
        // ������� ������ ���������� ������
        logoLabel.setBorder(new MatteBorder(5, 5, 5, 5, Color.black));        
        // ������� ������� logoLabel ������������
        logoLabel.setOpaque(true);        
        // �������� ������� SplashScreen ��� ��������
        splashScreen = new SplashScreen(logoLabel);        
        // ����������� �������� SplashScreen � ������� 3-� ������
        splashScreen.showSplash(3000);
    }

    /**
     * ������������� ���� ���������� �� ������ ������������
     */
    private void centerWindowOnScreen() {
        // ��������� �������� ����������������� ������
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        // ������������ ������ � ������ ������, ������ � ������ ���� ���������� ���
        // ������������� ���� ���������� �� ������
        int width = getSize().width;
        int height = getSize().height;
        int x = (screenDimension.width - width) / 2;
        int y = (screenDimension.height - height) / 2;        
        // ���������� ���� ���������� �� ������ ������
        setBounds(x, y, width, height);
    }

    /**
     * ����� �����
     * @param args ��������� �� �����
     */
    public static void main(String[] args) {
        new DrawingApp();
    }
    
}
package drawing;

import java.awt.*;
import java.awt.event.*;
import java.awt.dnd.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import drawing.controller.*;
import drawing.model.DrawingModel;
import drawing.view.*;
import drawing.model.shapes.*;

/**
 * �������� ������ JInternalFrame ��� �������� Drawing.
 * @author dwinner@inbox.ru
 */
public class DrawingInternalFrame extends JInternalFrame implements Observer {
    
	private static final long serialVersionUID = -4085007214040331752L;
	private static final int DEFAULT_IFRAME_WIDTH = 500;
    private static final int DEFAULT_IFRAME_HEIGHT = 500;
    
    // �������� ��� ����� ����
    private static final int xOffset = 30;
    private static final int yOffset = 30;
    private static int openFrameCount = 0;
    
    // ���������� MVC
    private DrawingModel drawingModel;
    private DrawingView drawingView;
    private MyShapeController myShapeController;
    private DragAndDropController dragAndDropController;
    private MyShapeControllerFactory shapeControllerFactory;
    
    // �������� ��� ������ � �������
    private JFileChooser fileChooser;
    private String fileName;
    private String absoluteFilePath;
    private boolean saved = true;
    
    private DrawingToolBar toolBar;
    private ZoomDialog zoomDialog;
    
    // �������� �� ����������, ��������� �������� � �.�.
    private Action  saveAction,
                    saveAsAction,
                    zoomAction,
                    moveAction,
                    fillAction,
                    gradientAction;
    
    /**
     * ����������� DrawingInternalFrame
     * @param title ��������� ����
     */
    public DrawingInternalFrame(String title) {
        super(title + " - " + (++openFrameCount), true, true, false, true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // �������� ����� ������ DrawingModel
        drawingModel = new DrawingModel();
        // �������� ������ ���� DrawingView ��� DrawingModel
        drawingView = new DrawingView(drawingModel);
        // ����������� DrawingInternalFrame � �������� ����������� ��� DrawingModel
        drawingModel.addObserver(this);
        // ����� MyShapeControllerFactory ��� �������� ������������ MyShapeController
        shapeControllerFactory = MyShapeControllerFactory.getInstance();
        // �������� ����������� DragAndDropController ��� �������� ��������������
        dragAndDropController = new DragAndDropController(drawingModel);
        // ��������� ������� �� ��������� DragSource ��� ������� ���������
        DragSource dragSource = DragSource.getDefaultDragSource();
        // �������� ������� DragGestureRecognizer ��� ����������� DragAndDropController
        // ��� ��������� DragGestureListener
        dragSource.createDefaultDragGestureRecognizer(drawingView, DnDConstants.ACTION_COPY_OR_MOVE, dragAndDropController);
        // ���������� ��������� �������� ����������, � ������� ����������� dragAndDropController
        // � �������� ��������� DropTargetListener
        drawingView.setDropTarget(new DropTarget(drawingView, DnDConstants.ACTION_COPY_OR_MOVE, dragAndDropController));
        // ���������� ���� drawingView � ������ viewPanel, ��������� ������ viewPanel � ������ JScrollPane
        // � ���������� �� � ���� DrawingInternalFrame
        JPanel viewPanel = new JPanel();
        viewPanel.add(drawingView);
        getContentPane().add(new JScrollPane(viewPanel), BorderLayout.CENTER);
        // �������� ������� fileChooser � ������� ��� ���� ������� FileFilter
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new DrawingFileFilter());
        // �����/������� ����������� ���� ZoomDialog ��� ���������/����������� ������
        addInternalFrameListener(new InternalFrameAdapter() {
        	// ��� ��������� DrawingInternalFrame ������� ��������������� ���� zoomDialog �������
            @Override public void internalFrameActivated(InternalFrameEvent event) {
                if (zoomDialog != null) {
                    zoomDialog.setVisible(true);
                }
            }
            // ��� ����������� DrawingInternalFrame ������� ��������������� ���� zoomDialog ���������
            @Override public void internalFrameDeactivated(InternalFrameEvent event) {
                if (zoomDialog != null) {
                    zoomDialog.setVisible(false);
                }
            }
        });        
        // ���������� �������� ��� ������� ������ DrawingInternalFrame, ����� ��
        // ��������� ���������� ��� ������� ������ InternalFrame
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        // ���������� ����� ������ DrawingToolBar � ������� NORTH
        toolBar = new DrawingToolBar();
        getContentPane().add(toolBar, BorderLayout.NORTH);
        // ��������� ����� ������ ������ MyShape, ������� ������������ shapeControllerFactory,
        // � �������� ����������� MyShapeController
        String shapeName = shapeControllerFactory.getSupportedShapes()[ 0 ];    
        setMyShapeController(shapeControllerFactory.newMyShapeController(drawingModel, shapeName));
        // ��������� ������� ���� DrawingInternalFrame
        setSize(DEFAULT_IFRAME_WIDTH, DEFAULT_IFRAME_HEIGHT);
    }
    
    /**
     * ��������� �������� Save ������� DrawingInternalFrame
     * @return ������ �������� �� ����������
     */
    public Action getSaveAction() {
        return saveAction;
    }
    
    /**
     * ��������� �������� Save As ������� DrawingInternalFrame
     * @return ������ �������� �� ���������� ���
     */
    public Action getSaveAsAction() {
        return saveAsAction;
    }
    
    /**
     * ��������� ����� Saved ��� �������� ������� � ���������� ��������� ���
     * ����������� ������������ ��������� ������������
     * @param drawingSaved ���� ����������
     */
    public void setSaved(boolean drawingSaved) {
        // ��������� �������� Saved
        saved = drawingSaved;       
        // ��������� �������� ��������� ���� DrawingInternalFrame
        String lTitle = getTitle();
        // ���� ������� �� ��������, � ��������� �� ������������ ����������, ��������
        // ��������� � ��������� ����
        if (!lTitle.endsWith("*") && !isSaved()) {
            setTitle(lTitle + " *");
        }
        else if (lTitle.endsWith("*") && isSaved()) {
            setTitle(lTitle.substring(0, lTitle.length() - 2));
        }        
        // ��������� �������� �� ����������, ���� ������� �� ��� ��������
        getSaveAction().setEnabled(!isSaved());
    }
    
    /**
     * ������� �������� �������� saved
     * @return ���� ����������
     */
    public boolean isSaved() {
        return saved;
    }

    @Override public void update(Observable o, Object arg) {
        // ������� ��� �������� saved �������� false ��� ��������
        // �� ��������� ������ DrawingModel
        setSaved(false);
    }
    
    /**
     * ��������� ����� ����� fileName ��� �������� �������
     * @param file ��� ����� ��� �������� �������
     */
    public void setFileName(String file) {
        fileName = file;
        // ���������� ��������� ���� DrawingInternalFrame
        setTitle(fileName);
    }
    
    /**
     * ��������� ����� ����� fileName ��� �������� �������
     * @return ��� ����� ��� �������� �������
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * ��������� ������� ���� ��� �������� �������
     * @return ������ ���� � �����
     */    
    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }
    
    /**
     * ������� ������� ���� ��� �������� �������
     * @param path ������ ���� � �����
     */
    public void setAbsoluteFilePath(String path) {
        absoluteFilePath = path;
    }
    
    /**
     * ��������� ������ DrawingModel ��� �������� �������
     * @return ������ �� ������ ���������
     */
    public DrawingModel getModel() {
        return drawingModel;
    }
    
    @Override public void setTitle(String title) {
    	// ������� ���������� ���� JInternalFrame � ZoomDialog
        super.setTitle(title);
        if (zoomDialog != null) {
            zoomDialog.setTitle(title);
        }
    }
    
    /**
     * ������� ����������� MyShapeController ��� ��������� ����������������� �����
     * @param controller ���������� �����
     */
    public final void setMyShapeController(MyShapeController controller) {
        // �������� ������� ����������� MyShapeController
        if (myShapeController != null) {
            // �������� ���������� �������, ��������� � �����
            drawingView.removeMouseListener(myShapeController.getMouseListener());
            drawingView.removeMouseMotionListener(myShapeController.getMouseMotionListener());
        }        
        // ������� �������� MyShapeController
        myShapeController = controller;
        // ����������� ����������� MyShapeController ��� ��������� ������� ����
        drawingView.addMouseListener(myShapeController.getMouseListener());
        drawingView.addMouseMotionListener(myShapeController.getMouseMotionListener());        
        // ���������� MyShapeConttroller �������� ���������� ���������� ������� (������� �����, ����, ������� � �.�.)
        myShapeController.setStrokeSize(toolBar.getStrokeSize());
        myShapeController.setPrimaryColor(toolBar.getPrimarycolor());
        myShapeController.setSecondaryColor(toolBar.getSecondaryColor());
        myShapeController.setDragMode(toolBar.getDragMode());
        myShapeController.setShapeFilled(toolBar.getShapeFilled());
        myShapeController.setUseGradient(toolBar.getUseGradient());
    }
    
    /**
     * �������� DrawingInternalFrame; ������� false, ���� ������� �� ��� ��������,
     * � ������������ ������� �������� ��������
     * @return ���� ��������
     */
    public boolean close() {
        // ���� ������� �� ��������, ���������� ������������ ��������� ���
        if (!isSaved()) {
            // ����������� ����������� ���� ������������� JOptionPane,
            // ����� ������������ ��� ��������� �������
            int response = JOptionPane.showInternalConfirmDialog(
                    this,
                    "The drawing in this window has been modified. Would you like" +
                    " to save changes?",
                    "Save Changes",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            // ���� ������������ ������ Yes, ��������� ������� � ������� ����
            if (response == JOptionPane.YES_OPTION) {
                saveDrawing();
                dispose();
                // ������� true ��� ���������, ��� ���� ���� �������
                return true;
            }
            else if (response == JOptionPane.NO_OPTION) {
                // ���� ������������ ������ No, ������� ���� ��� ����������
                dispose();
                return true;
            }
            else {	// ���� �� ���� �������
                return false;
            }
        }
        else {  // ������� ��� ��������, ������� ����
            dispose();
            return true;
        }
    }
    
    /**
     * �������� ������������� ������� �� �����
     * @return ���� ��������
     */
    public boolean openDrawing() {
        // �������� ����������� ���� JFileChooser
        int response = fileChooser.showOpenDialog(this);        
        // ���� ������������ ������ ���������� ����, ������� ����� ����� InputStream
        // � ������� ����������� ������
        if (response == JFileChooser.APPROVE_OPTION) {
            // ��������� ���������� ����� �����
            String lFileName = fileChooser.getSelectedFile().getAbsolutePath();
            // ��������� ������ ����� �� �����
            Collection<MyShape> shapes = DrawingFileReaderWriter.readFile(lFileName);
            // ������� ����� � ������ DrawingModel
            drawingModel.setShapes(shapes);
            // ������� �������� fileName
            setFileName(fileChooser.getSelectedFile().getName());
            // ������� �������� absoluteFilePath
            setAbsoluteFilePath(lFileName);
            // ������� �������� saved
            setSaved(true);
            // ������� true ��� ���������, ��� ���� ��� ������� ������
            return true;
        }
        else {  // ���� �� ��� ������
            return false;
        }
    }

    private void saveDrawing() {    // ���������� ������� � �����
        // ��������� ���� ��� ���������� �����
        String lFileName = getAbsoluteFilePath();
        // ���� ��� ����� lFileName ����� ��� null, ����� saveDrawingAs
        if (lFileName == null || lFileName.isEmpty()) {
            saveDrawingAs();
        }
        else {  // ������ ������� � ���� � ������ lFileName
            DrawingFileReaderWriter.writeFile(drawingModel, lFileName);
            // ���������� �������� saved
            setSaved(true);
        }
    }
    
    /**
     * ������ ����� ����� � ������������ � ���������� �������
     */
    public void saveDrawingAs() {
        // ����������� ����������� ���� Save JFileChooser
        int response = fileChooser.showSaveDialog(this);     
        // ���� ������������ ������ ����, ��������� �������
        if (response == JFileChooser.APPROVE_OPTION) {
            // ������� �������� absoluteFilePath
            setAbsoluteFilePath(fileChooser.getSelectedFile().getAbsolutePath());
            // ������� �������� fileName
            setFileName(fileChooser.getSelectedFile().getName());
            // ������ ������� � ����
            DrawingFileReaderWriter.writeFile(drawingModel, getAbsoluteFilePath());
            // ���������� �������� saved
            setSaved(true);
        }
    }
    
    /**
     * ����������� ����������� ���� zoomDialog
     */
    public void showZoomDialog() {
        // ���� zoomDialog ����� null, ������� ���
        if (zoomDialog == null) {
            zoomDialog = new ZoomDialog(getModel(), getTitle());
        }
        else {  // ������� ���� zoomDialog �������
            zoomDialog.setVisible(true);
        }
    }
    
    @Override public void dispose() {
        // �������� ���������� ���� zoomDialog
        if (zoomDialog != null) {
            zoomDialog.dispose();
        }
        super.dispose();
    }
    
    /**
     * �������� ������ JToolBar ��� DrawingInternalFrame
     * @author dwinner@inbox.ru
     */
    private class DrawingToolBar extends JToolBar {
        
		private static final long serialVersionUID = -159938029361268996L;
		// UI-����������
        private GradientIcon gradientIcon;
        private JPanel primaryColorPanel;
        private JPanel secondaryColorPanel;
        private JButton primaryColorButton;
        private JButton secondaryColorButton;
        private JComboBox<?> shapeChoice;
        private JComboBox<?> strokeSizeChoice;
        private JToggleButton gradientButton;
        private JToggleButton fillButton;
        private JToggleButton moveButton;

		@SuppressWarnings("unused")
		private Icon moveIcon;

        @SuppressWarnings({"unchecked", "unchecked"})
        DrawingToolBar() {  // ����������� DrawingToolBar
            // �������� ���������� JComboBox ��� ������ ���� ������� ������
            shapeChoice = new JComboBox(shapeControllerFactory.getSupportedShapes());
            shapeChoice.setToolTipText("Choose Shape");           
            // ��� ��������� shapeChoice �������� ���������� MyShapeController �� MyShapeControllerFactory
            shapeChoice.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    // ��������� ���������� ���� ������
                    String className = shapeChoice.getSelectedItem().toString();
                    setMyShapeController(shapeControllerFactory.newMyShapeController(drawingModel, className));
                }           
            });            
            // �������� ���������� JComboBox ��� ������ ������� �����
            strokeSizeChoice = new JComboBox(
                    new String[] { "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0" }
            );
            strokeSizeChoice.setToolTipText("Choose Line Width");            
            // ������� ������� �����
            strokeSizeChoice.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    myShapeController.setStrokeSize(getStrokeSize());
                }
            });            
            // �������� ������ JToggleButton ��� ������� �����������
            fillButton = new JToggleButton("Fill");            
            fillAction = new AbstractDrawingAction("Fill", null, "Fill Shape", new Integer('L')) {               
				private static final long serialVersionUID = 6668771199018260235L;
				@Override public void actionPerformed(ActionEvent ae) {
                    myShapeController.setShapeFilled(getShapeFilled());
                }
            };            
            fillButton.setAction(fillAction);
            // �������� ������ GradientIcon ��� ����������� �������� ���������
            gradientIcon = new GradientIcon(Color.black, Color.white);
            // �������� ������ JToggleButton ��� ����������/������� ����������� �������
            gradientButton = new JToggleButton(gradientIcon);           
            gradientAction = new AbstractDrawingAction("", gradientIcon, "Use Gradient", new Integer('G')) {             
				private static final long serialVersionUID = 4064923064960957177L;
				@Override public void actionPerformed(ActionEvent ae) {
                    myShapeController.setUseGradient(getUseGradient());
                }
            };            
            gradientButton.setAction(gradientAction);
            // �������� ������ JPanel ��� ����������� ���������� ����� ���������
            primaryColorPanel = new JPanel();
            primaryColorPanel.setPreferredSize(new Dimension(16,16));
            primaryColorPanel.setOpaque(true);
            primaryColorPanel.setBackground(Color.black);            
            // �������� ������ JButton ��� ��������� ���������� �����
            primaryColorButton = new JButton();
            primaryColorButton.add(primaryColorPanel);
            // ����������� ������ JColorChooser ��� ������ �������� startColor
            primaryColorButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    Color color = JColorChooser.showDialog(
                            DrawingInternalFrame.this,
                            "Select Color",
                            primaryColorPanel.getBackground()
                    );
                    if (color != null) {
                        primaryColorPanel.setBackground(color);
                        gradientIcon.setStartColor(color);
                        myShapeController.setPrimaryColor(color);
                    }
                }
            });
            // �������� ������ JPanel ��� ����������� ���������� ����� ���������
            secondaryColorPanel = new JPanel();
            secondaryColorPanel.setPreferredSize(new Dimension(16, 16));
            secondaryColorPanel.setOpaque(true);
            secondaryColorPanel.setBackground(Color.white);
            // �������� ������ JButton ��� ��������� ���������� �����
            secondaryColorButton = new JButton();
            secondaryColorButton.add(secondaryColorPanel);
            // ����������� ������ JColorChooser ��� ������ �������� endColor
            secondaryColorButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent ae) {
                    Color color = JColorChooser.showDialog(
                            DrawingInternalFrame.this,
                            "Select Color",
                            secondaryColorPanel.getBackground()
                    );
                    if (color != null) {
                        secondaryColorPanel.setBackground(color);
                        gradientIcon.setEndColor(color);
                    }
                }
            });
            // �������� �������� ��� ���������� �������
            Icon saveIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/save.png"));
            saveAction = new AbstractDrawingAction("Save", saveIcon, "Save Drawing", new Integer('S')) {            
				private static final long serialVersionUID = 5227008922928268977L;
				@Override public void actionPerformed(ActionEvent ae) {
                    saveDrawing();
                }
            };
            // �������� �������� ��� ���������� �������� � �������� ������ �����
            Icon saveAsIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/saveas.png"));
            saveAsAction = new AbstractDrawingAction("Save As", saveAsIcon, "Save Drawing As", new Integer('A')) { 
				private static final long serialVersionUID = 6748469494647978524L;
				@Override public void actionPerformed(ActionEvent ae) {
                    saveDrawingAs();
                }
            };
            // �������� �������� ��� ����������� ���� zoomDialog
            Icon zoomIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/zoom.gif"));
            zoomAction = new AbstractDrawingAction("Zoom", zoomIcon, "Show Zoom Window", new Integer('Z')) {                
				private static final long serialVersionUID = 8654434038201909447L;
				@Override public void actionPerformed(ActionEvent ae) {
                    showZoomDialog();
                }
            };
            // �������� ������ JToggleButton ��� ��������� ������ DnD
            moveButton = new JToggleButton();
            moveIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/move.gif"));
			moveAction = new AbstractDrawingAction("Move", null, "Move Shape", new Integer('M')) {                
				private static final long serialVersionUID = -4055328472094656281L;
				@Override public void actionPerformed(ActionEvent ae) {
                    myShapeController.setDragMode(getDragMode());
                    dragAndDropController.setDragMode(getDragMode());
                }
            };
            moveButton.setAction(moveAction);
            add(saveAction);
            add(saveAsAction);
            addSeparator();
            add(zoomAction);
            addSeparator();
            add(shapeChoice);
            add(strokeSizeChoice);
            addSeparator();
            add(primaryColorButton);
            add(secondaryColorButton);
            addSeparator();
            add(gradientButton);
            add(fillButton);
            addSeparator();
            add(moveButton);            
            // ������ ����������
            setFloatable(false);
        }

        /**
         * ��������� ��������� � ������ ������ ������� �����
         * @return ������� �����
         */
		private float getStrokeSize() {
            Object selectedItem = strokeSizeChoice.getSelectedItem();
			return Float.parseFloat(selectedItem.toString());
        }
		
		/**
		 * ��������� �������� ��� ������� ������� ������
		 * @return ���� ������� ������� ������
		 */
		private boolean getShapeFilled() {
            return fillButton.isSelected();
        }
		
		/**
		 * ��������� �������� ��� �������� ������������� ���������
		 * @return ���� ������������� ���������
		 */
		private boolean getUseGradient() {
            return gradientButton.isSelected();
        }
		
		/**
		 * ��������� ���������� ����� �������
		 * @return ��������� ���� �������
		 */
		private Color getPrimarycolor() {
            return primaryColorPanel.getBackground();
        }

        /**
         * ��������� ���������� ����� �������
         * @return ��������� ���� �������
         */
		private Color getSecondaryColor() {
            return secondaryColorPanel.getBackground();
        }

        /**
         * ��������� �������� ������ ��������������
         * @return ���� �������� ������ ��������������
         */
		private boolean getDragMode() {
            return moveButton.isSelected();
        }

    }
    
}
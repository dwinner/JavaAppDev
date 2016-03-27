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
 * Подкласс класса JInternalFrame для рисунков Drawing.
 * @author dwinner@inbox.ru
 */
public class DrawingInternalFrame extends JInternalFrame implements Observer {
    
	private static final long serialVersionUID = -4085007214040331752L;
	private static final int DEFAULT_IFRAME_WIDTH = 500;
    private static final int DEFAULT_IFRAME_HEIGHT = 500;
    
    // Смещения для новых окон
    private static final int xOffset = 30;
    private static final int yOffset = 30;
    private static int openFrameCount = 0;
    
    // Компоненты MVC
    private DrawingModel drawingModel;
    private DrawingView drawingView;
    private MyShapeController myShapeController;
    private DragAndDropController dragAndDropController;
    private MyShapeControllerFactory shapeControllerFactory;
    
    // Свойства для работы с файлами
    private JFileChooser fileChooser;
    private String fileName;
    private String absoluteFilePath;
    private boolean saved = true;
    
    private DrawingToolBar toolBar;
    private ZoomDialog zoomDialog;
    
    // Действия по сохранению, изменению размеров и т.д.
    private Action  saveAction,
                    saveAsAction,
                    zoomAction,
                    moveAction,
                    fillAction,
                    gradientAction;
    
    /**
     * Конструктор DrawingInternalFrame
     * @param title Заголовок окна
     */
    public DrawingInternalFrame(String title) {
        super(title + " - " + (++openFrameCount), true, true, false, true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // Создание новой модели DrawingModel
        drawingModel = new DrawingModel();
        // Создание нового вида DrawingView для DrawingModel
        drawingView = new DrawingView(drawingModel);
        // Регистрация DrawingInternalFrame в качестве наблюдателя для DrawingModel
        drawingModel.addObserver(this);
        // Класс MyShapeControllerFactory для создания контроллеров MyShapeController
        shapeControllerFactory = MyShapeControllerFactory.getInstance();
        // Создание контроллера DragAndDropController для операций перетаскивания
        dragAndDropController = new DragAndDropController(drawingModel);
        // Получение объекта по умолчанию DragSource для текущей платформы
        DragSource dragSource = DragSource.getDefaultDragSource();
        // Создание объекта DragGestureRecognizer для регистрации DragAndDropController
        // как слушателя DragGestureListener
        dragSource.createDefaultDragGestureRecognizer(drawingView, DnDConstants.ACTION_COPY_OR_MOVE, dragAndDropController);
        // Разрешение принимать операции отпускания, с помощью контроллера dragAndDropController
        // в качестве слушателя DropTargetListener
        drawingView.setDropTarget(new DropTarget(drawingView, DnDConstants.ACTION_COPY_OR_MOVE, dragAndDropController));
        // Добавление вида drawingView в панель viewPanel, помещение панели viewPanel в панель JScrollPane
        // и добавление ее в окно DrawingInternalFrame
        JPanel viewPanel = new JPanel();
        viewPanel.add(drawingView);
        getContentPane().add(new JScrollPane(viewPanel), BorderLayout.CENTER);
        // Создание объекта fileChooser и задание для него фильтра FileFilter
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new DrawingFileFilter());
        // Показ/скрытие диалогового окна ZoomDialog при активации/деактивации фрейма
        addInternalFrameListener(new InternalFrameAdapter() {
        	// При активации DrawingInternalFrame сделать соответствующее окно zoomDialog видимым
            @Override public void internalFrameActivated(InternalFrameEvent event) {
                if (zoomDialog != null) {
                    zoomDialog.setVisible(true);
                }
            }
            // При деактивации DrawingInternalFrame сделать соответствующее окно zoomDialog невидимым
            @Override public void internalFrameDeactivated(InternalFrameEvent event) {
                if (zoomDialog != null) {
                    zoomDialog.setVisible(false);
                }
            }
        });        
        // Установить смещение для каждого фрейма DrawingInternalFrame, чтобы не
        // допустить перекрытия его другими окнами InternalFrame
        setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
        // Добавление новой панели DrawingToolBar в область NORTH
        toolBar = new DrawingToolBar();
        getContentPane().add(toolBar, BorderLayout.NORTH);
        // Получение имени первой фигуры MyShape, которую поддерживает shapeControllerFactory,
        // и создание контроллера MyShapeController
        String shapeName = shapeControllerFactory.getSupportedShapes()[ 0 ];    
        setMyShapeController(shapeControllerFactory.newMyShapeController(drawingModel, shapeName));
        // Установка размера окна DrawingInternalFrame
        setSize(DEFAULT_IFRAME_WIDTH, DEFAULT_IFRAME_HEIGHT);
    }
    
    /**
     * Получение действия Save объекта DrawingInternalFrame
     * @return Объект действия по сохранению
     */
    public Action getSaveAction() {
        return saveAction;
    }
    
    /**
     * Получение действия Save As объекта DrawingInternalFrame
     * @return Объект действия по сохранению как
     */
    public Action getSaveAsAction() {
        return saveAsAction;
    }
    
    /**
     * Установка флага Saved для текущего рисунка и обновление заголовка для
     * отображения сохраненного состояния пользователю
     * @param drawingSaved Флаг сохранения
     */
    public void setSaved(boolean drawingSaved) {
        // Установка свойства Saved
        saved = drawingSaved;       
        // Получение текущего заголовка окна DrawingInternalFrame
        String lTitle = getTitle();
        // Если рисунок не сохранен, а заголовок не оканчивается звездочкой, добавить
        // звездочку к заголовку окна
        if (!lTitle.endsWith("*") && !isSaved()) {
            setTitle(lTitle + " *");
        }
        else if (lTitle.endsWith("*") && isSaved()) {
            setTitle(lTitle.substring(0, lTitle.length() - 2));
        }        
        // Разрешить действия по сохранению, если рисунок не был сохранен
        getSaveAction().setEnabled(!isSaved());
    }
    
    /**
     * Возврат значения свойства saved
     * @return Флаг сохранения
     */
    public boolean isSaved() {
        return saved;
    }

    @Override public void update(Observable o, Object arg) {
        // Задание для свойства saved значения false для указания
        // на изменение модели DrawingModel
        setSaved(false);
    }
    
    /**
     * Установка имени файла fileName для текущего рисунка
     * @param file Имя файла для текущего рисунка
     */
    public void setFileName(String file) {
        fileName = file;
        // Обновление заголовка окна DrawingInternalFrame
        setTitle(fileName);
    }
    
    /**
     * Получение имени файла fileName для текущего рисунка
     * @return Имя файла для текущего рисунка
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Получение полного пути для текущего рисунка
     * @return Полный путь к файлу
     */    
    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }
    
    /**
     * Задание полного пути для текущего рисунка
     * @param path Полный путь к файлу
     */
    public void setAbsoluteFilePath(String path) {
        absoluteFilePath = path;
    }
    
    /**
     * Получение модели DrawingModel для текущего рисунка
     * @return Ссылка на модель рисования
     */
    public DrawingModel getModel() {
        return drawingModel;
    }
    
    @Override public void setTitle(String title) {
    	// Задание заголовков окон JInternalFrame и ZoomDialog
        super.setTitle(title);
        if (zoomDialog != null) {
            zoomDialog.setTitle(title);
        }
    }
    
    /**
     * Задание контроллера MyShapeController для обработки пользовательского ввода
     * @param controller Контроллер ввода
     */
    public final void setMyShapeController(MyShapeController controller) {
        // Удаление старого контроллера MyShapeController
        if (myShapeController != null) {
            // Удаление слушетелей событий, связанных с мышью
            drawingView.removeMouseListener(myShapeController.getMouseListener());
            drawingView.removeMouseMotionListener(myShapeController.getMouseMotionListener());
        }        
        // Задание свойства MyShapeController
        myShapeController = controller;
        // Регистрация контроллера MyShapeController для обработки событий мыши
        drawingView.addMouseListener(myShapeController.getMouseListener());
        drawingView.addMouseMotionListener(myShapeController.getMouseMotionListener());        
        // Обновление MyShapeConttroller текущими выбранными свойствами рисунка (толщина линии, цвет, заливка и т.д.)
        myShapeController.setStrokeSize(toolBar.getStrokeSize());
        myShapeController.setPrimaryColor(toolBar.getPrimarycolor());
        myShapeController.setSecondaryColor(toolBar.getSecondaryColor());
        myShapeController.setDragMode(toolBar.getDragMode());
        myShapeController.setShapeFilled(toolBar.getShapeFilled());
        myShapeController.setUseGradient(toolBar.getUseGradient());
    }
    
    /**
     * Закрытие DrawingInternalFrame; Возврат false, если рисунок не был сохранен,
     * и пользователь отменил операцию закрытия
     * @return Флаг закрытия
     */
    public boolean close() {
        // Если рисунок не сохранен, предложить пользователю сохранить его
        if (!isSaved()) {
            // Отображение диалогового окна подтверждения JOptionPane,
            // чтобы пользователь мог сохранить рисунок
            int response = JOptionPane.showInternalConfirmDialog(
                    this,
                    "The drawing in this window has been modified. Would you like" +
                    " to save changes?",
                    "Save Changes",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            // Если пользователь выбрал Yes, сохранить рисунок и закрыть окно
            if (response == JOptionPane.YES_OPTION) {
                saveDrawing();
                dispose();
                // Возврат true для индикации, что окно было закрыто
                return true;
            }
            else if (response == JOptionPane.NO_OPTION) {
                // Если пользователь выбрал No, закрыть окно без сохранения
                dispose();
                return true;
            }
            else {	// Окно не было закрыто
                return false;
            }
        }
        else {  // Рисунок был сохранен, закрыть окно
            dispose();
            return true;
        }
    }
    
    /**
     * Открытие существующего рисунка из файла
     * @return Флаг открытия
     */
    public boolean openDrawing() {
        // Открытие диалогового окна JFileChooser
        int response = fileChooser.showOpenDialog(this);        
        // Если пользователь выбрал правильный файл, открыть поток ввода InputStream
        // и извлечь сохраненные фигуры
        if (response == JFileChooser.APPROVE_OPTION) {
            // Получение выбранного имени файла
            String lFileName = fileChooser.getSelectedFile().getAbsolutePath();
            // Получение списка фигур из файла
            Collection<MyShape> shapes = DrawingFileReaderWriter.readFile(lFileName);
            // Задание фигур в модели DrawingModel
            drawingModel.setShapes(shapes);
            // Задание свойства fileName
            setFileName(fileChooser.getSelectedFile().getName());
            // Задание свойства absoluteFilePath
            setAbsoluteFilePath(lFileName);
            // Задание свойства saved
            setSaved(true);
            // Возврат true для индикации, что файл был успешно открыт
            return true;
        }
        else {  // Файл не был открыт
            return false;
        }
    }

    private void saveDrawing() {    // Сохранение рисунка в файле
        // Получение пути для сохранения файла
        String lFileName = getAbsoluteFilePath();
        // Если имя файла lFileName пусто или null, вызов saveDrawingAs
        if (lFileName == null || lFileName.isEmpty()) {
            saveDrawingAs();
        }
        else {  // Запись рисунка в файл с именем lFileName
            DrawingFileReaderWriter.writeFile(drawingModel, lFileName);
            // Обновление свойства saved
            setSaved(true);
        }
    }
    
    /**
     * Запрос имени файла у пользователя и сохранение рисунка
     */
    public void saveDrawingAs() {
        // Отображение диалогового окна Save JFileChooser
        int response = fileChooser.showSaveDialog(this);     
        // Если пользователь выбрал файл, сохранить рисунок
        if (response == JFileChooser.APPROVE_OPTION) {
            // Задание свойства absoluteFilePath
            setAbsoluteFilePath(fileChooser.getSelectedFile().getAbsolutePath());
            // Задание свойства fileName
            setFileName(fileChooser.getSelectedFile().getName());
            // Запись рисунка в файл
            DrawingFileReaderWriter.writeFile(drawingModel, getAbsoluteFilePath());
            // Обновление свойства saved
            setSaved(true);
        }
    }
    
    /**
     * Отображение диалогового окна zoomDialog
     */
    public void showZoomDialog() {
        // Если zoomDialog равен null, создать его
        if (zoomDialog == null) {
            zoomDialog = new ZoomDialog(getModel(), getTitle());
        }
        else {  // Сделать окно zoomDialog видимым
            zoomDialog.setVisible(true);
        }
    }
    
    @Override public void dispose() {
        // Удаление связанного окна zoomDialog
        if (zoomDialog != null) {
            zoomDialog.dispose();
        }
        super.dispose();
    }
    
    /**
     * Подкласс класса JToolBar для DrawingInternalFrame
     * @author dwinner@inbox.ru
     */
    private class DrawingToolBar extends JToolBar {
        
		private static final long serialVersionUID = -159938029361268996L;
		// UI-компоненты
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
        DrawingToolBar() {  // Конструктор DrawingToolBar
            // Создание компонента JComboBox для выбора типа текущей фигуры
            shapeChoice = new JComboBox(shapeControllerFactory.getSupportedShapes());
            shapeChoice.setToolTipText("Choose Shape");           
            // При изменении shapeChoice получить контроллер MyShapeController из MyShapeControllerFactory
            shapeChoice.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    // Получение выбранного типа фигуры
                    String className = shapeChoice.getSelectedItem().toString();
                    setMyShapeController(shapeControllerFactory.newMyShapeController(drawingModel, className));
                }           
            });            
            // Создание компонента JComboBox для выбора толщины линий
            strokeSizeChoice = new JComboBox(
                    new String[] { "1.0", "2.0", "3.0", "4.0", "5.0", "6.0", "7.0", "8.0", "9.0", "10.0" }
            );
            strokeSizeChoice.setToolTipText("Choose Line Width");            
            // Задание толщины линии
            strokeSizeChoice.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    myShapeController.setStrokeSize(getStrokeSize());
                }
            });            
            // Создание кнопки JToggleButton для заливки изображений
            fillButton = new JToggleButton("Fill");            
            fillAction = new AbstractDrawingAction("Fill", null, "Fill Shape", new Integer('L')) {               
				private static final long serialVersionUID = 6668771199018260235L;
				@Override public void actionPerformed(ActionEvent ae) {
                    myShapeController.setShapeFilled(getShapeFilled());
                }
            };            
            fillButton.setAction(fillAction);
            // Создание значка GradientIcon для отображения настроек градиента
            gradientIcon = new GradientIcon(Color.black, Color.white);
            // Создание кнопки JToggleButton для разрешения/запрета градиентной заливки
            gradientButton = new JToggleButton(gradientIcon);           
            gradientAction = new AbstractDrawingAction("", gradientIcon, "Use Gradient", new Integer('G')) {             
				private static final long serialVersionUID = 4064923064960957177L;
				@Override public void actionPerformed(ActionEvent ae) {
                    myShapeController.setUseGradient(getUseGradient());
                }
            };            
            gradientButton.setAction(gradientAction);
            // Создание панели JPanel для отображения первичного цвета рисования
            primaryColorPanel = new JPanel();
            primaryColorPanel.setPreferredSize(new Dimension(16,16));
            primaryColorPanel.setOpaque(true);
            primaryColorPanel.setBackground(Color.black);            
            // Создание кнопки JButton для изменения первичного цвета
            primaryColorButton = new JButton();
            primaryColorButton.add(primaryColorPanel);
            // Отображение панели JColorChooser для выбора значения startColor
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
            // Создание панели JPanel для отображения вторичного цвета рисования
            secondaryColorPanel = new JPanel();
            secondaryColorPanel.setPreferredSize(new Dimension(16, 16));
            secondaryColorPanel.setOpaque(true);
            secondaryColorPanel.setBackground(Color.white);
            // Создание кнопки JButton для изменения вторичного цвета
            secondaryColorButton = new JButton();
            secondaryColorButton.add(secondaryColorPanel);
            // Отображение панели JColorChooser для выбора значения endColor
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
            // Создание действия для сохранения рисунка
            Icon saveIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/save.png"));
            saveAction = new AbstractDrawingAction("Save", saveIcon, "Save Drawing", new Integer('S')) {            
				private static final long serialVersionUID = 5227008922928268977L;
				@Override public void actionPerformed(ActionEvent ae) {
                    saveDrawing();
                }
            };
            // Создание действия для сохранения рисунков с заданным именем файла
            Icon saveAsIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/saveas.png"));
            saveAsAction = new AbstractDrawingAction("Save As", saveAsIcon, "Save Drawing As", new Integer('A')) { 
				private static final long serialVersionUID = 6748469494647978524L;
				@Override public void actionPerformed(ActionEvent ae) {
                    saveDrawingAs();
                }
            };
            // Создание действия для отображения окна zoomDialog
            Icon zoomIcon = new ImageIcon(DrawingInternalFrame.class.getResource("../images/zoom.gif"));
            zoomAction = new AbstractDrawingAction("Zoom", zoomIcon, "Show Zoom Window", new Integer('Z')) {                
				private static final long serialVersionUID = 8654434038201909447L;
				@Override public void actionPerformed(ActionEvent ae) {
                    showZoomDialog();
                }
            };
            // Создание кнопки JToggleButton для установки режима DnD
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
            // Запрет всплывания
            setFloatable(false);
        }

        /**
         * Получение выбранной в данный момент толщины линии
         * @return Толщина линии
         */
		private float getStrokeSize() {
            Object selectedItem = strokeSizeChoice.getSelectedItem();
			return Float.parseFloat(selectedItem.toString());
        }
		
		/**
		 * Получение значения для заливки текущей фигуры
		 * @return Флаг заливки текущей фигуры
		 */
		private boolean getShapeFilled() {
            return fillButton.isSelected();
        }
		
		/**
		 * Получение свойства для текущего используемого градиента
		 * @return Флаг использования градиента
		 */
		private boolean getUseGradient() {
            return gradientButton.isSelected();
        }
		
		/**
		 * Получение первичного цвета рисунка
		 * @return Первичный цвет рисунка
		 */
		private Color getPrimarycolor() {
            return primaryColorPanel.getBackground();
        }

        /**
         * Получение вторичного цвета рисунка
         * @return Вторичный цвет рисунка
         */
		private Color getSecondaryColor() {
            return secondaryColorPanel.getBackground();
        }

        /**
         * Получение текущего режима перетаскивания
         * @return Флаг текущего режима перетаскивания
         */
		private boolean getDragMode() {
            return moveButton.isSelected();
        }

    }
    
}
package drawing.controller;

import java.util.*;
import java.io.*;
import java.awt.Point;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * DragAndDropController - контроллер для осуществления операций перетаскивания в
 * DeitelDrawing. DragAndDropController реализует интерфейсы DragGestureListener
 * и DragSourceListener для обработки событий перетаскивания и DropTargetListener
 * для обработки событий отпускания.
 * @author dwinner@inbox.ru
 */
public class DragAndDropController implements	DragGestureListener,
                								DragSourceListener,
                								DropTargetListener
{
    
    private static final Logger LOG = Logger.getLogger(DragAndDropController.class.getName());
    
    // Модель управления
    private DrawingModel drawingModel;
    private boolean dragMode = false;

    /**
     * Конструктор DragAndDropController
     * @param model Модель для фигур
     */
    public DragAndDropController(DrawingModel model) {
        drawingModel = model;
    }
    
    /**
     * Задание режима перетаскивания
     * @param drag Флаг режима перетаскивания
     */
    public void setDragMode(boolean drag) {
        dragMode = drag;
    }

    @Override public void dragGestureRecognized(DragGestureEvent dge) {
    	// Определение начала операции перетаскивания (метод интерфейса DragGestureListener)
        // Если это не режим перетаскивания dragMode, игнорировать действие
        if (!dragMode) {
            return;
        }
        // Получить точку Point начала перетаскивания
        Point origin = dge.getDragOrigin();        
        // Получение фигур MyShape из модели DrawingModel
        List<MyShape> shapes = new ArrayList<MyShape>(drawingModel.getShapes());        
        /*
         * Нахождение самой верхней фигуры, которая содержит исходную точку
         * перетаскивания (т.е. начать с конца ListIterator и двигаться вверх)
         */
        ListIterator<MyShape> shapeIterator = shapes.listIterator(shapes.size());
        while (shapeIterator.hasPrevious()) {
            MyShape shape = shapeIterator.previous();
            if (shape.contains(origin)) {
                // Создание объекта TransferableShape для перетаскивания фигуры
                // из исходной точки Point
                Transferable transfer = new TransferableShape(shape, origin);
                // Начало операции перетаскивания
                dge.startDrag(null, transfer, this);
                break;
            }
        }
    }
    
    @Override public void drop(DropTargetDropEvent dtde) {
    	// Обработка события отпускания (метод интерфейса DropTargetListener)
        // Получение отпускаемого объекта
        Transferable transferable = dtde.getTransferable();        
        // Получение DataFlavor для отпускаемого объекта
        DataFlavor[] dataFlavors = transferable.getTransferDataFlavors();        
        // Получение позиции DropTargetDropEvent
        Point location = dtde.getLocation();        
        // Обработка отпускания для поддерживаемых типов
        for (DataFlavor dataFlavor : dataFlavors) {
            if (dataFlavor.equals(DataFlavor.javaFileListFlavor)) {
                // Принятие операции отпускания
                dtde.acceptDrop(DnDConstants.ACTION_COPY);                
                // Попытка отпустить изображение и индикация, завершено ли отпускание
                dtde.dropComplete(dropImages(transferable, location));
            }
            else if (dataFlavor.isMimeTypeEqual(TransferableShape.MIME_TYPE)) {
                // Принятие отпускания объекта TransferableShape
                dtde.acceptDrop(DnDConstants.ACTION_MOVE);                
                // Отпустить объект TransferableShape на рисунок
                dropShape(transferable, location);                
                // Завершение операции отпускания
                dtde.dropComplete(true);
            }
            else {  // Отклонение всех других DataFlavor
                dtde.rejectDrop();
            }
        }
    }
    
    /**
     * Отпукание JPEG-изображения на рисунке
     * @param transferable Перетаскиваемый Transferable-объект
     * @param location Точка отпускания объекта
     * @return Флаг успешности операции отпукания
     */
    private boolean dropImages(Transferable transferable, Point location) {
        // Булево значение, указывающее на успешное отпускание
        boolean success = true;
        try {   // Попытка отпустить изображение на рисунке
            // Получение списка отпускаемых файлов
            @SuppressWarnings("unchecked")
            List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            Iterator<File> iterator = fileList.iterator();          
            // Поиск JPEG-изображений
            for (int i = 1; iterator.hasNext(); i++) {
                File file = iterator.next();
                // Если отпускаемый файл является JPEG-изображением, декодировать
                // и добавить изображение MyImage в модель drawingModel
                if (fileIsJPEG(file)) {
                    // Создание объекта MyImage для заданного JPEG-файла
                    MyImage image = new MyImage();
                    image.setFileName(file.getPath());
                    image.setPoint1(location.x, location.y);                    
                    // Добавление в модель DrawingModel
                    drawingModel.addShape(image);
                }
                else {
                    success = false;
                }
            }
        }
        catch (UnsupportedFlavorException ex) {
            LOG.log(Level.SEVERE, null, ex);
            success = false;
        } 
        catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            success = false;
        }        
        return success;
    }
    
    /**
     * Проверка окончания файла .jpg или .jpeg
     * @param file Файл
     * @return Флаг успешности проверки
     */
    private boolean fileIsJPEG(File file) {
        String fileName = file.getName().toLowerCase();
        return (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"));
    }
    
    /**
     * Отпускание объекта MyShape над рисунком
     * @param transferable Перетаскиваемый Transferable-объект
     * @param location Точка отпускания
     */
    private void dropShape(Transferable transferable, Point location) {
        DataFlavor flavor = new DataFlavor(TransferableShape.MIME_TYPE, "Shape");
        try {
            // Получение объекта TransferableShape
            TransferableShape transferableShape = (TransferableShape) transferable.getTransferData(flavor);
            // Получение фигуры MyShape и исходной точки Point из объекта TransferableShape
            MyShape shape = transferableShape.getShape();
            Point origin = transferableShape.getOrigin();          
            // Вычисление смещения для отпускаемой фигуры MyShape
            int xOffSet = location.x - origin.x;
            int yOffSet = location.y - origin.y;            
            shape.moveByOffset(xOffSet, yOffSet);            
            // Добавление фигуры MyShape в модель DrawingModel
            drawingModel.addShape(shape);
        } 
        catch (UnsupportedFlavorException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    @Override public void dragDropEnd(DragSourceDropEvent dsde) {
    	// Проверка успешного завершения операции перетаскивания
        // Если отпускание было успешным, удалить объект MyShape из исходной модели DrawingModel
        if (dsde.getDropSuccess()) {
            // Получение объекта Transferable из DragSourceContext
            Transferable transferable = dsde.getDragSourceContext().getTransferable();
            try {   // Получение объекта TranferableShape из Transferable
                // Получение объекта TranferableShape
                TransferableShape transferableShape = 
                        (TransferableShape) transferable.getTransferData(
                            new DataFlavor(TransferableShape.MIME_TYPE, "Shape")
                        );
                // Получение объекта MyShape из объекта TransferableShape и
                // удаление его из исходной модели DrawingModel
                drawingModel.removeShape(transferableShape.getShape());
            }
            catch (UnsupportedFlavorException ex) {
                LOG.log(Level.SEVERE, null, ex);
            } 
            catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override public void dragEnter(DragSourceDragEvent dsde) {    
    }

    @Override public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override public void dropActionChanged(DragSourceDragEvent dsde) {
    }

    @Override public void dragExit(DragSourceEvent dse) {
    }

    @Override public void dragEnter(DropTargetDragEvent dtde) {
    }

    @Override public void dragOver(DropTargetDragEvent dtde) {
    }

    @Override public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override public void dragExit(DropTargetEvent dte) {
    }
    
}
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
 * DragAndDropController - ���������� ��� ������������� �������� �������������� �
 * DeitelDrawing. DragAndDropController ��������� ���������� DragGestureListener
 * � DragSourceListener ��� ��������� ������� �������������� � DropTargetListener
 * ��� ��������� ������� ����������.
 * @author dwinner@inbox.ru
 */
public class DragAndDropController implements	DragGestureListener,
                								DragSourceListener,
                								DropTargetListener
{
    
    private static final Logger LOG = Logger.getLogger(DragAndDropController.class.getName());
    
    // ������ ����������
    private DrawingModel drawingModel;
    private boolean dragMode = false;

    /**
     * ����������� DragAndDropController
     * @param model ������ ��� �����
     */
    public DragAndDropController(DrawingModel model) {
        drawingModel = model;
    }
    
    /**
     * ������� ������ ��������������
     * @param drag ���� ������ ��������������
     */
    public void setDragMode(boolean drag) {
        dragMode = drag;
    }

    @Override public void dragGestureRecognized(DragGestureEvent dge) {
    	// ����������� ������ �������� �������������� (����� ���������� DragGestureListener)
        // ���� ��� �� ����� �������������� dragMode, ������������ ��������
        if (!dragMode) {
            return;
        }
        // �������� ����� Point ������ ��������������
        Point origin = dge.getDragOrigin();        
        // ��������� ����� MyShape �� ������ DrawingModel
        List<MyShape> shapes = new ArrayList<MyShape>(drawingModel.getShapes());        
        /*
         * ���������� ����� ������� ������, ������� �������� �������� �����
         * �������������� (�.�. ������ � ����� ListIterator � ��������� �����)
         */
        ListIterator<MyShape> shapeIterator = shapes.listIterator(shapes.size());
        while (shapeIterator.hasPrevious()) {
            MyShape shape = shapeIterator.previous();
            if (shape.contains(origin)) {
                // �������� ������� TransferableShape ��� �������������� ������
                // �� �������� ����� Point
                Transferable transfer = new TransferableShape(shape, origin);
                // ������ �������� ��������������
                dge.startDrag(null, transfer, this);
                break;
            }
        }
    }
    
    @Override public void drop(DropTargetDropEvent dtde) {
    	// ��������� ������� ���������� (����� ���������� DropTargetListener)
        // ��������� ������������ �������
        Transferable transferable = dtde.getTransferable();        
        // ��������� DataFlavor ��� ������������ �������
        DataFlavor[] dataFlavors = transferable.getTransferDataFlavors();        
        // ��������� ������� DropTargetDropEvent
        Point location = dtde.getLocation();        
        // ��������� ���������� ��� �������������� �����
        for (DataFlavor dataFlavor : dataFlavors) {
            if (dataFlavor.equals(DataFlavor.javaFileListFlavor)) {
                // �������� �������� ����������
                dtde.acceptDrop(DnDConstants.ACTION_COPY);                
                // ������� ��������� ����������� � ���������, ��������� �� ����������
                dtde.dropComplete(dropImages(transferable, location));
            }
            else if (dataFlavor.isMimeTypeEqual(TransferableShape.MIME_TYPE)) {
                // �������� ���������� ������� TransferableShape
                dtde.acceptDrop(DnDConstants.ACTION_MOVE);                
                // ��������� ������ TransferableShape �� �������
                dropShape(transferable, location);                
                // ���������� �������� ����������
                dtde.dropComplete(true);
            }
            else {  // ���������� ���� ������ DataFlavor
                dtde.rejectDrop();
            }
        }
    }
    
    /**
     * ��������� JPEG-����������� �� �������
     * @param transferable ��������������� Transferable-������
     * @param location ����� ���������� �������
     * @return ���� ���������� �������� ���������
     */
    private boolean dropImages(Transferable transferable, Point location) {
        // ������ ��������, ����������� �� �������� ����������
        boolean success = true;
        try {   // ������� ��������� ����������� �� �������
            // ��������� ������ ����������� ������
            @SuppressWarnings("unchecked")
            List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
            Iterator<File> iterator = fileList.iterator();          
            // ����� JPEG-�����������
            for (int i = 1; iterator.hasNext(); i++) {
                File file = iterator.next();
                // ���� ����������� ���� �������� JPEG-������������, ������������
                // � �������� ����������� MyImage � ������ drawingModel
                if (fileIsJPEG(file)) {
                    // �������� ������� MyImage ��� ��������� JPEG-�����
                    MyImage image = new MyImage();
                    image.setFileName(file.getPath());
                    image.setPoint1(location.x, location.y);                    
                    // ���������� � ������ DrawingModel
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
     * �������� ��������� ����� .jpg ��� .jpeg
     * @param file ����
     * @return ���� ���������� ��������
     */
    private boolean fileIsJPEG(File file) {
        String fileName = file.getName().toLowerCase();
        return (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg"));
    }
    
    /**
     * ���������� ������� MyShape ��� ��������
     * @param transferable ��������������� Transferable-������
     * @param location ����� ����������
     */
    private void dropShape(Transferable transferable, Point location) {
        DataFlavor flavor = new DataFlavor(TransferableShape.MIME_TYPE, "Shape");
        try {
            // ��������� ������� TransferableShape
            TransferableShape transferableShape = (TransferableShape) transferable.getTransferData(flavor);
            // ��������� ������ MyShape � �������� ����� Point �� ������� TransferableShape
            MyShape shape = transferableShape.getShape();
            Point origin = transferableShape.getOrigin();          
            // ���������� �������� ��� ����������� ������ MyShape
            int xOffSet = location.x - origin.x;
            int yOffSet = location.y - origin.y;            
            shape.moveByOffset(xOffSet, yOffSet);            
            // ���������� ������ MyShape � ������ DrawingModel
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
    	// �������� ��������� ���������� �������� ��������������
        // ���� ���������� ���� ��������, ������� ������ MyShape �� �������� ������ DrawingModel
        if (dsde.getDropSuccess()) {
            // ��������� ������� Transferable �� DragSourceContext
            Transferable transferable = dsde.getDragSourceContext().getTransferable();
            try {   // ��������� ������� TranferableShape �� Transferable
                // ��������� ������� TranferableShape
                TransferableShape transferableShape = 
                        (TransferableShape) transferable.getTransferData(
                            new DataFlavor(TransferableShape.MIME_TYPE, "Shape")
                        );
                // ��������� ������� MyShape �� ������� TransferableShape �
                // �������� ��� �� �������� ������ DrawingModel
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
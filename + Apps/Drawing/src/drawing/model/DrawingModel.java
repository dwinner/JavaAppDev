package drawing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;
import drawing.model.shapes.*;

/**
 * Модель для рисования в Drawing
 * Она предоставляет методы для добавления и удаления фигур рисунка.
 * @author dwinner@inbox.ru
 */
public class DrawingModel extends Observable {
    
    private static final int INITIAL_CAPACITY = 64;    
    // Фигуры, содержащиеся в модели
    private Collection<MyShape> shapes;
    
    /**
     * Конструктор без параметров
     */
    public DrawingModel() {
        shapes = new ArrayList<MyShape>(INITIAL_CAPACITY);
    }
    
    /**
     * Добавление фигуры в модель
     * @param shape Фигура, предназначенная для добавления 
     */
    public void addShape(MyShape shape) {
        // Добавление новой фигуры в список фигур
        shapes.add(shape);       
        // Отправка уведомления об изменении модели
        fireModelChanged();
    }
    
    /**
     * Удаление фигуры из модели
     * @param shape Фигура, предназначенная для удаления
     */
    public void removeShape(MyShape shape) {
        // Удаление фигуры из списка
        shapes.remove(shape);        
        // Отправка уведомления об изменении модели
        fireModelChanged();
    }
    
    /**
     * Получение коллекции фигур модели
     * @return Получение немодифицирующего представления коллекции фигур
     */
    public Collection<MyShape> getShapes() {
        return Collections.unmodifiableCollection(shapes);
    }
    
    /**
     * Задание коллекции фигур модели
     * @param newShapes Новая ссылка на коллекцию фигур
     */
    public void setShapes(Collection<? extends MyShape> newShapes) {
        // Копирование коллекции Collection в новый ArrayList
    	shapes.clear();
    	shapes.addAll(newShapes);
        // Отправка уведомления об изменениях модели
        fireModelChanged();
    }
    
    /**
     * Очистка текущего списка фигур ArrayList
     */
    public void clear() {
        shapes.clear();
        // Отправка уведомления об изменениях модели
        fireModelChanged();
    }

    /**
     * Отправка уведомления об изменениях модели
     */
    private void fireModelChanged() {
        // Установка флага изменения модели
        setChanged();      
        // Уведомление наблюдателей Observer об изменениях модели
        notifyObservers();
    }
}
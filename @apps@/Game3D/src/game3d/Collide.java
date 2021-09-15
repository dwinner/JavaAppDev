package game3d;

import com.sun.j3d.utils.universe.*;
import java.util.logging.Logger;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.util.*;

/**
 * Класс Collide реализует поведение по обнаружению столкновений для приложения
 * Java 3D. Collide переключает сцену, когда разрешенный объект сталкивается с
 * другим объектом.
 * @author dwinner@inbox.ru
 */
public class Collide extends Behavior {
    
    private static final Logger LOG = Logger.getLogger(Collide.class.getName());
    
    // Разрешенный узел возбуждает событие WakeupOnCollisionEntry при столкновении
    private Node armingNode;
    
    // Задает, на какие события WakeupEvents реагировать
    private WakeupCondition wakeupCondition;
    
    private Switch switchScene; // Группа Switch содержит сцены
    private SimpleUniverse simpleUniverse;
    // Индекс сцены, на которую осуществляется переключение при столкновении
    private static final int LOSER_SCENE = 1;

    public Collide(SimpleUniverse universe, Node node, Switch tempSwitch) {
        armingNode = node;
        switchScene = tempSwitch;
        simpleUniverse = universe;
        
        // Создание объекта WakeupOnCollisionEntry
        WakeupOnCollisionEntry wakeupEvent = new WakeupOnCollisionEntry(
                armingNode,
                WakeupOnCollisionEntry.USE_GEOMETRY
        );
        // Задание события WakeupEvent, на которое реагирует поведение Behavior
        WakeupCriterion[] wakeupCriteria = { wakeupEvent };
        
        // Действие Behavior возбуждается, когда имеет место событие WakeupEvent -
        // событие, указанное в WakeupCriterion
        wakeupCondition = new WakeupOr(wakeupCriteria);
    }
    
    /**
     * Инициализация условий возбуждения для поведения Behavior
     */
    @Override public void initialize() {
        // Регистрация критерия WakeupCriterion для реакции на столкновение
        wakeupOn(wakeupCondition);
    }
    
    /**
     * Обработка события WakeupEvent
     * @param detected Перечисление цикла обработки событий 
     */
    @Override public void processStimulus(Enumeration detected) {
        // Цикл обработки событий
        while (detected.hasMoreElements()) {
            // Получение следующего порядкового номера
            WakeupCriterion criterion = (WakeupCriterion) detected.nextElement();
            
            // Обработка события WakeupOnCollisionEntry
            if (criterion instanceof WakeupOnCollisionEntry) {
                processCollision();
                
                // Перерегистрация критерия WakeupCriterion для реакции на новое
                // событие WakeupOnCollisionEntry
                wakeupOn(wakeupCondition);
            }
        }
    }
    
    /**
     * Обработка столкновения путем отката камеры назад и переключения сцены в
     * группе Switch
     */
    private void processCollision() {
        Transform3D shiftViewBack = new Transform3D();
        
        // Установка смещения для Transform3D
        shiftViewBack.setTranslation(new Vector3f(0.0f, 0.0f, 8.0f));
        // Задание объекта Transform3D, который определяет представление
        ViewingPlatform viewPlatform = simpleUniverse.getViewingPlatform();
        
        TransformGroup platformTransform = viewPlatform.getViewPlatformTransform();
        
        platformTransform.setTransform(shiftViewBack);
        
        // Отображение сцены в группе Switch
        switchScene.setWhichChild(LOSER_SCENE);
    }
    
}

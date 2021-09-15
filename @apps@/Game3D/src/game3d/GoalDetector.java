package game3d;

import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.media.j3d.Behavior;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3f;

/**
 * Класс GoalDetector определяет поведение для проверки условия,
 * совпадает ли позиция узла Node с целевой позицией. Если позиции совпадают,
 * игра заканчивается а объект Switch Java 3D отображает другую сцену.
 * @author dwinner@inbox.ru
 */
public class GoalDetector extends Behavior {
    
    private static final Logger LOG = Logger.getLogger(GoalDetector.class.getName());
    
    // Индекс сцены, отображаемой при достижении цели
    private static final int WINNER_SCENE = 2;
    
    // Группа TransformGroup, ассоциированная с объектом
    private TransformGroup objectTransform;
    private Switch switchScene; // перейти к группе, которая содежит сцены
    private SimpleUniverse simpleUniverse;
    
    private float goalX, goalY, goalZ;  // координаты цели
    
    // радиус сферы с целевыми координатами
    private float sphereRadius;
    
    // Условия возбуждения для Behavior
    private WakeupCondition wakeupCondition;

    /**
     * Инициализация членов метода конструктора и создание критерия WakeupCriterion
     * @param universe
     * @param transform
     * @param switchGroup
     * @param goalVector
     * @param radius  
     */
    public GoalDetector(SimpleUniverse universe, TransformGroup transform, Switch switchGroup, Vector3f goalVector, float radius) {
        objectTransform = transform;
        switchScene = switchGroup;
        simpleUniverse = universe;
        
        // Задание в качестве целевых координат goalVector
        goalX = goalVector.x;
        goalY = goalVector.y;
        goalZ = goalVector.z;
        
        // Задание радиуса сферы
        sphereRadius = radius;
        
        // Инициализация события WakeupOnAWTEvent для реакции на событие AWT KeyEvent.KEY_PRESSED
        WakeupOnAWTEvent wakeupEvent = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
        
        // Задание событий WakeupEvent, на которые реагирует Behavior
        WakeupCriterion[] wakeupArray = { wakeupEvent };
        
        // Объект Behavior реагирует, если имеет место событие WakeupEvent,
        // отвечающее критерию WakeupCriterion
        wakeupCondition = new WakeupOr(wakeupArray);
    }

    /**
     * Регистрация условия активизации поведения
     */
    @Override public void initialize() {
        // Регистрация критерия WakeupCriterion для реакции на событие AWTEvent
        wakeupOn(wakeupCondition);
    }

    /**
     * Обработка события WakeupEvent
     * @param detected Перечисление событий 
     */
    @Override public void processStimulus(Enumeration detected) {
        // Цикл обработки событий
        while (detected.hasMoreElements()) {
            // Получение следующего по порядку критерия WakeupCriterion
            WakeupCriterion wakeupCriterion = (WakeupCriterion) detected.nextElement();
            
            // Обработать, если это событие WakeupOnAWTEvent
            if (wakeupCriterion instanceof WakeupOnAWTEvent) {
                // Проверка, что событием WakeupOnAWTEvent является KeyEvent.KEY_PRESSED
                WakeupOnAWTEvent awtEvent = (WakeupOnAWTEvent) wakeupCriterion;
                AWTEvent[] event = awtEvent.getAWTEvent();
                
                // Проверка положения объекта
                checkPosition(event);
                
                // Перерегистрация критерия WakeupCriterion для реакции на следующее нажатие клавиши
                wakeupOn(wakeupCondition);
            }
        }
    }
    
    /**
     * Проверка положения объекта в группе objectTransform TransformGroup
     * @param awtEvents массив AWT-событий
     */
    private void checkPosition(AWTEvent[] awtEvents) {
        Vector3f translate = new Vector3f();
        Transform3D transform3d = new Transform3D();
        // Получение объекта Transform3D, ассоциированного с objectTransform
        objectTransform.getTransform(transform3d);
        
        // Получение вектора смещений для объекта Transform3D
        transform3d.get(translate);
        
        // Обработка всех нажатий клавиш для событий awtEvents
        for (int x = 0; x < awtEvents.length; x++) {
            // Обработать, если AWTEvent является событием KeyEvent
            if (awtEvents[x] instanceof KeyEvent) {
                KeyEvent keyEvent = (KeyEvent) awtEvents[x];
                
                // Обработать, если это событие KeyEvent.KEY_PRESSED
                if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                    // Если позиция объекта совпадает с координатами цели
                    if (atGoal(translate)) {
                        Transform3D shiftBack = new Transform3D();
                        
                        // Установить смещение по оси +z, равное 8.0
                        shiftBack.setTranslation(new Vector3f(0.0f, 0.0f, 8.0f));
                        
                        // Задание объекта Transform3D, определяющего представление в SimpleUniverse
                        ViewingPlatform viewPlatform = simpleUniverse.getViewingPlatform();
                        TransformGroup platformTransform = viewPlatform.getViewPlatformTransform();
                        platformTransform.setTransform(shiftBack);
                        
                        // Отображение сцены победы в SimpleUniverse
                        switchScene.setWhichChild(WINNER_SCENE);
                    }
                }
            }
        }
    }
    
    /**
     * Определение факта нахождения текущей позиции в пределах границ цели
     * @param currentPosition текущая позиция
     * @return true ,если позиция находится внутри целевой сферы
     */
    private boolean atGoal(Vector3f currentPosition) {
        // Вычисление разницы между текущей позицией и позицией цели
        float x = Math.abs(currentPosition.x - goalX);
        float y = Math.abs(currentPosition.y - goalY);
        float z = Math.abs(currentPosition.z - goalZ);

        return ((x < sphereRadius) && (y < sphereRadius) && (z < sphereRadius));
    }
    
}

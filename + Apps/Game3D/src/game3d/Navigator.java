package game3d;

import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.media.j3d.Behavior;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Vector3f;

/**
 * Класс Navigator - подкласс класса Behavior, который реализует управление
 * движением с помощью клавиатуры. Navigator реагирует на нажатия клавиш,
 * перемещая объект по сцене.
 * @author dwinner@inbox.ru
 */
public class Navigator extends Behavior {
    
    private static final Logger LOG = Logger.getLogger(Navigator.class.getName());
    
    // Группа TransformGroup, ассоциированная с объектом, управляемым с помощью клавиатуры
    private TransformGroup objectTransform;
    
    // величины смещений
    private static final float LEFT = -0.02f;
    private static final float RIGHT = 0.02f;
    private static final float UP = 0.02f;
    private static final float DOWN = -0.02f;
    private static final float FORWARD = 0.02f;
    private static final float BACKWARD = -0.02f;
    
    // Условия возбуждения для поведения
    private WakeupCondition wakeupCondition;

    public Navigator(TransformGroup transform) {
        objectTransform = transform;
        
        // Инициализация события WakeupOnAWTEvent в ответ на событие AWT KeyEvent.KEY_PRESSED
        WakeupOnAWTEvent wakeupEvent = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
        
        // Задание событий WakeupEvent, на которые реагирует Behavior
        WakeupCriterion[] wakeupCriteria = { wakeupEvent };
        
        // Поведение Behavior имеет место при наступлении события WakeupEvent,
        // соответствующего критерию WakeupCriterion
        wakeupCondition = new WakeupOr(wakeupCriteria);
    }
    
    @Override
    public void initialize() {  // Инициализация условий возбуждения
        // Регистрация критерия WakeupCriterion для возбуждения событий
        // WakeupEvent при наступлении AWT-событий
        wakeupOn(wakeupCondition);
    }
    
    @Override   // Обработка событий WakeupEvent
    public void processStimulus(Enumeration detected) {
        // Цикл обработки событий
        while (detected.hasMoreElements()) {
            // Получение следующего критерия WakeupCriterion
            WakeupCriterion wakeupCriterion = (WakeupCriterion) detected.nextElement();
            // Обработать критерий WakeupCriterion, если событием является WakeupOnAWTEvent
            if (wakeupCriterion instanceof WakeupOnAWTEvent) {
                WakeupOnAWTEvent awtEvent = (WakeupOnAWTEvent) wakeupCriterion;
                AWTEvent[] events = awtEvent.getAWTEvent();
                
                // Вызов метода moveObject с AWTEvent
                moveShape(events);
            }
        }
        
        // Перерегистрация условия wakeupCondition для реакции на следующее нажатие клавиши
        wakeupOn(wakeupCondition);
    }

    /**
     * Обработка событий AWT KeyEvents путем перемещения объекта по сцене
     * @param awtEvents Массив событий
     */
    private void moveShape(AWTEvent[] awtEvents) {
        // Обработка всех событий в массиве AWTEvent
        for (int x = 0; x < awtEvents.length; x++) {
            // Обработать, если событием AWTEvent является KeyEvent
            if (awtEvents[x] instanceof KeyEvent) {
                // Получение соответствующего события KeyEvent
                KeyEvent keyEvent = (KeyEvent) awtEvents[x];
                // Реагировать, только если KeyEvent имеет тип KEY_PRESSED
                if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                    // Получить код клавиши KeyCode, ассоциированной с событием KeyEvent
                    int keyCode = keyEvent.getKeyCode();
                    
                    Transform3D transform3D = new Transform3D();
                    
                    // Получить объект Transform3D из группы объектов TransformGroup
                    objectTransform.getTransform(transform3D);
                    
                    Vector3f translateVector = new Vector3f();
                    
                    // Извлечение вектора смещения, ассоциированного с Transform3D
                    transform3D.get(translateVector);
                    
                    // Обновление компонентов x, y, z вектора, используя информацию
                    // о нажатой клавише
                    switch (keyCode) {
                        case KeyEvent.VK_A: // движение влево
                            translateVector.x += LEFT;
                            break;
                        case KeyEvent.VK_D: // движение вправо
                            translateVector.x += RIGHT;
                            break;
                        case KeyEvent.VK_W: // движение вверх
                            translateVector.y += UP;
                            break;
                        case KeyEvent.VK_S: // движение вниз
                            translateVector.y += DOWN;
                            break;
                        case KeyEvent.VK_UP:    // движение назад
                            translateVector.z += BACKWARD;
                            break;
                        case KeyEvent.VK_DOWN:  // движение вперед
                            translateVector.z += FORWARD;
                            break;
                    }
                    
                    // Задание смещения объекта Transform3D в соответствии с
                    // обновленным вектором Vector3f
                    transform3D.setTranslation(translateVector);
                    // Задание объекта Transform3D группы TransformGroup
                    objectTransform.setTransform(transform3D);
                }
            }
        }
    }
    
}

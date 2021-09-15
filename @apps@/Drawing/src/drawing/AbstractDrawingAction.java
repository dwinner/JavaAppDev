package drawing;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

/**
 * AbstractDrawingAction - реализация интерфейса Action, которая предоставляет
 * методы set и get для свойств типовых действий
 * @author dwinner@inbox.ru
 */
public abstract class AbstractDrawingAction extends AbstractAction {

    /**
     * Построение объекта AbstractDrawingAction с заданным именем, значком, описанием
     * и клавиатурной мнемоникой
     * @param name Имя для действия
     * @param icon Значок для действия
     * @param description Описание для действия
     * @param mnemonic Клавиатурное сокращение действия
     */
    public AbstractDrawingAction(String name, Icon icon, String description, Integer mnemonic) {
        setName(name);
        setSmallIcon(icon);
        setShortDescription(description);
        setMnemonic(mnemonic);
    }

    /**
     * Задание имени действия Action
     * @param name Имя действия
     */
    public final void setName(String name) {
        putValue(Action.NAME, name);
    }
    
    /**
     * Задание значка для действия
     * @param icon Значок действия
     */
    public final void setSmallIcon(Icon icon) {
        putValue(Action.SMALL_ICON, icon);
    }
    
    /**
     * Задание краткого описания действия
     * @param description Краткое описание действия
     */
    public final void setShortDescription(String description) {
        putValue(Action.SHORT_DESCRIPTION, description);
    }
    
    /**
     * Задание клавиатурной мнемоники для действия
     * @param mnemonic Клавиатурное сокращение для действия
     */
    public final void setMnemonic(Integer mnemonic) {
        putValue(Action.MNEMONIC_KEY, mnemonic);
    }

    @Override public abstract void actionPerformed(ActionEvent ae);
    
}
package drawing;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

/**
 * AbstractDrawingAction - ���������� ���������� Action, ������� �������������
 * ������ set � get ��� ������� ������� ��������
 * @author dwinner@inbox.ru
 */
public abstract class AbstractDrawingAction extends AbstractAction {

    /**
     * ���������� ������� AbstractDrawingAction � �������� ������, �������, ���������
     * � ������������ ����������
     * @param name ��� ��� ��������
     * @param icon ������ ��� ��������
     * @param description �������� ��� ��������
     * @param mnemonic ������������ ���������� ��������
     */
    public AbstractDrawingAction(String name, Icon icon, String description, Integer mnemonic) {
        setName(name);
        setSmallIcon(icon);
        setShortDescription(description);
        setMnemonic(mnemonic);
    }

    /**
     * ������� ����� �������� Action
     * @param name ��� ��������
     */
    public final void setName(String name) {
        putValue(Action.NAME, name);
    }
    
    /**
     * ������� ������ ��� ��������
     * @param icon ������ ��������
     */
    public final void setSmallIcon(Icon icon) {
        putValue(Action.SMALL_ICON, icon);
    }
    
    /**
     * ������� �������� �������� ��������
     * @param description ������� �������� ��������
     */
    public final void setShortDescription(String description) {
        putValue(Action.SHORT_DESCRIPTION, description);
    }
    
    /**
     * ������� ������������ ��������� ��� ��������
     * @param mnemonic ������������ ���������� ��� ��������
     */
    public final void setMnemonic(Integer mnemonic) {
        putValue(Action.MNEMONIC_KEY, mnemonic);
    }

    @Override public abstract void actionPerformed(ActionEvent ae);
    
}
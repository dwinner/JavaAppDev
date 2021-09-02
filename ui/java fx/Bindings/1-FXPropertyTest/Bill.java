package pkg1.fxpropertytest;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Определение свойств по контрактам кода.
 * @author oracle_pr1
 */
public class Bill
{
    // Определяем переменную для хранения свойства.
    private DoubleProperty amountDue = new SimpleDoubleProperty();
    
    // Определяем метод доступа для значения свойства.
    public final double getAmountDue()
    {
        return amountDue.get();
    }
    
    // Определяем метод установки значения свойства
    public final void setAmountDue(double value)
    {
        amountDue.set(value);
    }
    
    // Определяем метод доступа к самому свойству
    public DoubleProperty amountDueProperty()
    {
        return amountDue;
    }
}

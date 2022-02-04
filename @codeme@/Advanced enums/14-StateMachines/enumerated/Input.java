//: enumerated/Input.java
package enumerated;
import java.util.*;

public enum Input
{
    NICKEL(5),
    DIME(10),
    QUARTER(25),
    DOLLAR(100),
    TOOTHPASTE(200),
    CHIPS(75),
    SODA(100),
    SOAP(50),
    ABORT_TRANSACTION
    {
        public int amount()
        { // Запретить
            throw new RuntimeException("ABORT.amount()");
        }
    },
    STOP
    { // Это должно быть последним экземпляром перечисления.
        public int amount()
        { // Запретить
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };
    
    int value; // Значение в центах
  
    Input(int aValue) { value = aValue; }
    Input() {}
  
    int amount() { return value; }; // В центах
  
    static Random rand = new Random(47);
  
    public static Input randomSelection()
    {
        // Не включая STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}

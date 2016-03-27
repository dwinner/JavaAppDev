
import java.awt.*;
import java.applet.*;

/**
 * ѕример, демонстрирующий работу верификатора байтового кода виртуальной машины. ¬ случае
 * использовани€ шестнаднатиричного редактора дл€ изменени€ файла класса виртуальна€ машина должна
 * быть способна обнаружить подлог.
 * <p/>
 * @version 1.00 1997-09-10
 * @author Cay Horstmann
 */
public class VerifierTest extends Applet
{
    public static void main(String[] args)
    {
        System.out.println("1 + 2 == " + fun());
    }

    /**
     * ћетод, вычисл€ющий 1 + 2.
     * <p/>
     * @return 3, если код не поврежден.
     */
    public static int fun()
    {
        int m;
        int n;
        m = 1;
        n = 2;
        // ¬ файле класса замен€ем последнее выражение на "m = 2". ƒл€ этого
        // используем редактор шестнадцатиричных значений.
        int r = m + n;
        return r;
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawString("1 + 2 == " + fun(), 20, 20);
    }
}
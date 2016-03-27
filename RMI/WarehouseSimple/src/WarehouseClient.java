import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Enumeration;

/**
 * Клиент, который вызывает удаленный метод.
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
public class WarehouseClient
{
    public static void main(String[] args)
       throws NamingException, RemoteException
    {
        Context namingContext = new InitialContext();

        System.out.println("RMI registry bindings: ");  // привязки в реестре RMI
        Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");

        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        String url = "rmi://localhost/central_warehouse";
        Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
        String descr = "Blackwell Toaster";
        double price = centralWarehouse.getPrice(descr);
        System.out.println(descr + ": " + price);
    }
}

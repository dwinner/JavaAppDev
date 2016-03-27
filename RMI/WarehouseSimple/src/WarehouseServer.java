import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Эта серверная программа создает экземпляр удаленного объекта Warehouse,
 * регистрирует его с помощью службы имен и ожидает, когда клиенты начнут
 * вызывать методы.
 *
 * @author Cay Horstmann
 * @version 1.12 2007-10-09
 */
public class WarehouseServer
{
    public static void main(String[] args)
       throws RemoteException, NamingException
    {
        System.out.println("Constructing server implementation...");
        // Создание серверной реализации.
        WarehouseImpl centralWarehouse = new WarehouseImpl();

        System.out.println("Binding server implementation to registry...");
        // Связывание серверной реализации с реестром.
        Context namingContext = new InitialContext();
        namingContext.bind("rmi:central_warehouse", centralWarehouse);

        System.out.println("Waiting for invocations from clients...");
        // Ожидание вызовов от клиентов.
    }
}

import com.horstmann.corejava.server.Warehouse;
import com.horstmann.corejava.server.WarehouseService;

import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Клиент для программы Warehouse.
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
public class WarehouseClient
{
    public static void main(String[] args)
       throws NamingException, RemoteException
    {
        WarehouseService service = new WarehouseService();
        Warehouse port = service.getPort(Warehouse.class);

        String descr = "Blackwell Toaster";
        double price = port.getPrice(descr);
        System.out.println(descr + ": " + price);
    }
}

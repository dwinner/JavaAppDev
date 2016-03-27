import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Данный класс является реализацией удаленного интерфейса Warehouse.
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse
{
    private Map<String, Double> prices;

    public WarehouseImpl() throws RemoteException
    {
        prices = new HashMap<>();
        prices.put("Blackwell Toaster", 24.95);
        prices.put("ZapExpress Microwave Oven", 49.95);
    }

    @Override
    public double getPrice(String description) throws RemoteException
    {
        Double price = prices.get(description);
        return price == null ? 0 : price;
    }
}

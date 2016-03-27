package com.horstmann.corejava;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.HashMap;
import java.util.Map;

/**
 * Этот класс отвечает за реализацию Web-службы Warehouse.
 * @version 1.0 2007-10-09
 * @author Cay Horstmann
 */
@WebService
public class Warehouse
{
    private Map<String, Double> prices;

    public Warehouse()
    {
        prices = new HashMap<>();
        prices.put("Blackwell Toaster", 24.95);
        prices.put("ZapXpress Microwave Oven", 49.95);
    }

    public double getPrice(@WebParam(name = "description") String description)
    {
        Double price = prices.get(description);
        return price == null ? 0 : price;
    }
}

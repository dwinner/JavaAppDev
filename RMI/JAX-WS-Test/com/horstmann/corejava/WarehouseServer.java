package com.horstmann.corejava;

import javax.xml.ws.Endpoint;

/**
 * Минимальная процедура развертывания web-сервиса.
 */
public class WarehouseServer
{
    public static void main(String[] args)
    {
        Endpoint.publish("http://localhost:8080/WebServices/warehouse",
                         new Warehouse());
    }
}

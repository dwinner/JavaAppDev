package ru.aztpa.a.oaa.mfg.shop.v01;


import ru.aztpa.a.oaa.mfg.shop.v01.dbconn.DataBaseConnection;

import java.sql.SQLException;
import java.util.ArrayList;

class ShopDelegate
{
    public ShopList findShopListByProfile(ShopProfile profile)
    {
        assert profile != null;
        DataBaseConnection dbConn = null;
        try
        {
            dbConn = new DataBaseConnection();
            ShopQueryExecutor qExecutor = new ShopQueryExecutor(profile, dbConn.getDbConn());
            ShopList shopList = new ShopList();
            shopList.shop = new ArrayList<Shop>();
            shopList.getShop().addAll(qExecutor.retrieveShopListByProfile());

            return shopList;
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (dbConn != null)
                try { dbConn.close(); }
                catch (SQLException e) { throw new RuntimeException(e); }
        }
    }
}

package ru.aztpa.a.oaa.mfg.wipplan.v01;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

class WipPlanQueryUnit
{
    private final static String qImportPlan = "{ call APPS.XXTPA_WIP_PLAN.Import(?) }";
    private Connection dbConn;

    WipPlanQueryUnit(Connection connection)
       throws SQLException
    {
        if (connection == null || connection.isClosed())
            throw new SQLException("Database connection is closed or not exists");
        dbConn = connection;
    }

    private WipPlanQueryUnit() { assert false; }

    public void importPlanForDepartment(String shopCode)
       throws SQLException
    {
        assert shopCode != null && !shopCode.trim().isEmpty();
        CallableStatement callStat = null;
        boolean autoCommit = dbConn.getAutoCommit();

        try
        {
            callStat = dbConn.prepareCall(qImportPlan);
            callStat.setString(1, shopCode);
            callStat.execute();
            dbConn.commit();
        }
        catch (SQLException sqlEx)
        {
            dbConn.rollback();
            throw sqlEx;
        }
        finally
        {
            dbConn.setAutoCommit(autoCommit);
            if (callStat != null && !callStat.isClosed())
                callStat.close();
        }
    }
}
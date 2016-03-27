package ru.aztpa.a.oaa.eng.bom.v01;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Класс для интеграции логики выполнения запросов к СУБД Oracle
 * @author jdeveloper@aztpa.ru
 */
class PlanQueryUnit
{
    private final static String oracleObjectTypeName = "APPS.XXTPA_MFG0024_PLAN_REC";
    private final static String oracleRecordTypeTable = "APPS.XXTPA_MFG0024_PLAN_TBL";
    private final static String qRaiseEvents = "{ call APPS.XXTPA_MFG0024_PKG.raise_events(?) }";

    /**
     * Инициирование событий по строкам плана
     * @param dbConn Объект соединения
     * @param planRecord Запись плана
     * @throws SQLException
     */
    void callRaiseEvents(Connection dbConn, PlanRecord[] planRecord) throws SQLException
    {
        assert planRecord != null && planRecord.length > 0;
        CallableStatement callStat = null;
        boolean autoCommit = dbConn.getAutoCommit();
        try
        {
            dbConn.setAutoCommit(false);
            StructDescriptor recordDescriptor =
               StructDescriptor.createDescriptor(oracleObjectTypeName, dbConn);
            ArrayDescriptor arrayDescriptor =
               ArrayDescriptor.createDescriptor(oracleRecordTypeTable, dbConn);
            callStat = dbConn.prepareCall(qRaiseEvents);
            Object[] arrayOfRecords = new Object[planRecord.length];
            for (int i = 0; i < planRecord.length; i++)
            {
                Object[] javaRecordArray = new Object[]
                   {
                      planRecord[i].getItemFigure(),
                      planRecord[i].getItemCode(),
                      planRecord[i].getItemDesc(),
                      planRecord[i].getPlanMonth(),
                      planRecord[i].getPlanName(),
                      planRecord[i].getDepartmentCode(),
                      planRecord[i].getOdpFlag(),
                      planRecord[i].getSpkFlag()
                   };
                STRUCT oracleRecord = new STRUCT(recordDescriptor, dbConn, javaRecordArray);
                arrayOfRecords[i] = oracleRecord;
            }
            ARRAY oracleArray = new ARRAY(arrayDescriptor, dbConn, arrayOfRecords);
            callStat.setObject(1, oracleArray);
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

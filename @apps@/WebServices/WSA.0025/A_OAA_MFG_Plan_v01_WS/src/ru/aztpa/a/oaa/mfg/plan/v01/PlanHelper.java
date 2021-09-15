package ru.aztpa.a.oaa.mfg.plan.v01;

import ru.aztpa.a.oaa.mfg.plan.v01.process.PlanProfileQueryBuilder;
import ru.aztpa.a.oaa.mfg.plan.v01.process.PlanQueryBuilder;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * ��������������� ����� ��� ������� ������ � ������� XXODI.XXBI_PLANS, XXODI.XXBI_PLANPOSITIONS ���� Oracle
 * @version 1.0.0 04.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class PlanHelper
{
    // FIXME: �������� ��� JNDI ��� ������������� �� ������ App-�������
    private final static String CONTEXT_STRING = "ORACLE_JNDI";
    private final static int DEFAULT_INIT_CAPACITY = 0xFF + 1;
    private final static DatatypeFactory dtFactory;
    private final static GregorianCalendar gCalendar = new GregorianCalendar();

    static
    {
        try
        {
            dtFactory = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException e)
        {
            throw new RuntimeException(e);
        }
    }
    private DataSource ds;
    private Connection conn;

    /** ������������� ��������� ������. */
    public PlanHelper()
    {
        try
        {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(CONTEXT_STRING);
        }
        catch (NamingException namingEx)
        {
            throw new RuntimeException(namingEx);
        }
    }

    /**
     * ������������ ����� �������� Web-�������
     * @param plan ������ ���� ��� ������������ "�����������" � SOAP-request
     * @return returns ru.aztpa.a.oaa.mfg.plan.v01.ActionResult
     */
    protected synchronized ActionResult doCreatePlan(Plan plan)
    {
        ActionResult actionResult = new ActionResult();
        if (plan == null)
        {
            actionResult.setErrorCode("1");
            actionResult.setMessage("No plan detected");
            actionResult.setStatus("ERROR");
            return actionResult;
        }
        PlanQueryBuilder builder = new PlanQueryBuilder(plan);

        try
        {
            try
            {
                takeConnect();
                PlanInsertUnit insertUnit = new PlanInsertUnit();
                int deletedRows = insertUnit.deletePlans(builder.buildDeletePlansQuery(),
                   builder.buildDeletePlansPositionsQuery());
                int affectedPlans = insertUnit.insertPlan(builder.buildInsertPlanQuery());
                int affectedPlanPositions =
                   insertUnit.insertPlanPositions(builder.buildInsertPlanPositionsQuery());
                /*if (deletedRows != affectedPlans + affectedPlanPositions)
                {
                    // ������!
                }*/
                actionResult.setErrorCode("0");
                actionResult.setStatus("SUCCESS");
                actionResult.setMessage(
                   "Affected: plans => " + affectedPlans + ", plan positions => " + affectedPlanPositions);
            }
            finally
            {
                releaseConnect();
            }
        }
        catch (SQLException sqlEx)
        {
            actionResult.setErrorCode("" + sqlEx.getErrorCode());
            actionResult.setStatus("ERROR");
            actionResult.setMessage(sqlEx.getMessage() + Arrays.deepToString(sqlEx.getStackTrace()));
            return actionResult;
        }

        return actionResult;
    }

    /**
     * ������������ ����� �������� Web-�������
     * @param profile
     * @return
     */
    protected synchronized PlannedFgList doFindPlannedFgListByProfile(PlannedFgProfile profile)
    {
        PlannedFgList plannedFgObject = new PlannedFgList();
        plannedFgObject.plannedFg = new ArrayList<PlannedFg>(DEFAULT_INIT_CAPACITY);
        if (profile == null)
            return plannedFgObject;
        PlanProfileQueryBuilder builder = new PlanProfileQueryBuilder(profile);

        try
        {
            try
            {
                takeConnect();
                FindByProfileUnit retrieveUnit = new FindByProfileUnit();
                List<PlannedFg> fgList =
                   retrieveUnit.retrievePlannedFgList(builder.buildFindPlannedFgListByProfileQuery());
                plannedFgObject.getPlannedFg().addAll(fgList);
            }
            finally
            {
                releaseConnect();
            }
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }

        return plannedFgObject;
    }

    /**
     * ������ ������ ���������� �� ���� �����������.
     * @throws SQLException
     */
    protected void takeConnect() throws SQLException
    {
        if (ds == null)
            throw new SQLException("No data source");
        conn = ds.getConnection();
        if (conn == null)
            throw new SQLException("No connection");
    }

    /**
     * ������������ �������� ���������� ������� � ��� �����������.
     * @throws SQLException
     */
    protected void releaseConnect() throws SQLException
    {
        if (conn != null && !conn.isClosed())
            conn.close();
    }

    /** �������� ��������� ��� �������� �������� ����������. */
    private void checkForBatchUpdateSupport()
    {
        checkForConnection();
        try
        {
            DatabaseMetaData dbmd = conn.getMetaData();
            if (!dbmd.supportsBatchUpdates())
                throw new SQLException("Bad Oracle JDBC Driver detected");
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
    }

    /** �������� ������� �����������. */
    private void checkForConnection()
    {
        if (conn == null)
            throw new RuntimeException("No connection");
        try
        {
            if (conn.isClosed())
                throw new SQLException("Connection is closed");
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
    }

    /** ���������� ����� ��� ����������� ������ ������� ������� �������� �����. */
    private class PlanInsertUnit
    {
        PlanInsertUnit() { }

        /**
         * �������� �������� �� �������� � ������ ����� ����������
         * @param deleteQueries ������� �� ��������
         * @return ���������� ��������� �����.
         */
        private int deletePlans(String... deleteQueries) throws SQLException
        {
            assert deleteQueries != null && deleteQueries.length != 0;
            checkForBatchUpdateSupport();
            int deleted = 0;
            Statement pStat = null;
            boolean autoCommit = conn.getAutoCommit();
            try
            {
                conn.setAutoCommit(false);
                pStat = conn.createStatement();
                for (String query : deleteQueries)
                    pStat.addBatch(query);
                int[] allDeleteStatuses = pStat.executeBatch();
                conn.commit();
                for (int batchType : allDeleteStatuses)
                    if (batchType > 0)
                        deleted += batchType;
            }
            catch (SQLException sqlEx)
            {
                conn.rollback();
                throw new RuntimeException(sqlEx);
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
            }
            conn.setAutoCommit(autoCommit);
            return deleted;
        }

        /**
         * ������� ������ � ������� XXODI.XXBI_PLANS.
         * @param query ������ ������� INSERT
         * @return ���������� ���������� ������� ����
         * @throws SQLException
         */
        private int insertPlan(String query) throws SQLException
        {
            assert query != null && !query.trim().isEmpty();
            checkForConnection();
            int affected = 0;
            PreparedStatement planStat = null;
            boolean autoCommit = conn.getAutoCommit();
            try
            {
                conn.setAutoCommit(false);
                planStat = conn.prepareStatement(query);
                affected = planStat.executeUpdate();
                conn.commit();
            }
            catch (SQLException sqlEx)
            {
                conn.rollback();
                throw sqlEx;
            }
            finally
            {
                if (planStat != null && !planStat.isClosed())
                    planStat.close();
                conn.setAutoCommit(autoCommit);
            }

            return affected;
        }

        /**
         * ������� ����� � ������� XXODI.XXBI_PLANPOSITIONS.
         * @param queries ������ ����� �������� INSERT
         * @return ���������� ���������� ������� ����
         * @throws SQLException
         */
        private int insertPlanPositions(String[] queries) throws SQLException
        {
            assert queries != null && queries.length != 0;
            checkForBatchUpdateSupport();
            int affected = 0;
            Statement posStat = null;
            boolean autoCommit = conn.getAutoCommit();

            try
            {
                conn.setAutoCommit(false);
                posStat = conn.createStatement();
                for (String aQuery : queries)
                    posStat.addBatch(aQuery);
                int[] allUpdateStatuses = posStat.executeBatch();
                conn.commit();
                for (int batchType : allUpdateStatuses)
                    if (batchType > 0)
                        affected += batchType;
            }
            catch (SQLException sqlEx)
            {
                conn.rollback();
                throw sqlEx;
            }
            finally
            {
                if (posStat != null && !posStat.isClosed())
                    posStat.close();
                conn.setAutoCommit(autoCommit);
            }

            return affected;
        }
    }

    /**
     * ���������� ����� ��� ����������� ������ ���������� ������� � ��������������� � ������������ ��������.
     */
    private class FindByProfileUnit
    {
        FindByProfileUnit() { }

        /**
         * ���������� ������� � ��������������� � ������������ ��������.
         * @param query DQL-������
         * @return ������ �������� PlannedFg
         * @throws SQLException
         */
        private List<PlannedFg> retrievePlannedFgList(String query) throws SQLException
        {
            assert query != null && !query.trim().isEmpty();
            checkForConnection();

            List<PlannedFg> plannedFgs = new ArrayList<PlannedFg>(DEFAULT_INIT_CAPACITY);
            PreparedStatement pStat = null;
            ResultSet rs = null;
            try
            {
                pStat = conn.prepareStatement(query);
                rs = pStat.executeQuery();
                while (rs.next())
                {
                    PlannedFg currentFg = new PlannedFg();

                    currentFg.setPlanName(rs.getString("plan_name"));
                    Date period = rs.getDate("period");
                    gCalendar.setTime(period);
                    currentFg.setCompletionDate(dtFactory.newXMLGregorianCalendar(gCalendar));

                    FinishedGood fGood = new FinishedGood();
                    fGood.setItemFigure(rs.getString("figure"));
                    fGood.setItemCode(rs.getString("item"));
                    fGood.setItemName(rs.getString("item_description"));

                    currentFg.setFinishedGood(fGood);

                    plannedFgs.add(currentFg);
                }
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
                if (rs != null && !rs.isClosed())
                    rs.close();
            }

            return plannedFgs;
        }
    }
}

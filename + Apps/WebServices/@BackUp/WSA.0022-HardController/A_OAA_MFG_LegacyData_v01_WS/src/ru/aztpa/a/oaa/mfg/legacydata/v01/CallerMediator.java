package ru.aztpa.a.oaa.mfg.legacydata.v01;

import java.sql.*;
import java.util.List;
import javax.naming.NamingException;
import ru.aztpa.a.oaa.mfg.legacydata.dbutilities.CachedDBConn;

/**
 * Вызывающий посредник Web-сервиса.
 * <p/>
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0 26.03.2012
 */
public class CallerMediator
{
    public static final String DEFAULT_NAMING_CONTEXT = "OAADataSource";
    public static final Connection dbConn;

    static
    {
        try
        {
            dbConn = CachedDBConn.getCacheConnection(DEFAULT_NAMING_CONTEXT);
        }
        catch (NamingException namingEx)
        {
            namingEx.fillInStackTrace();
            throw new RuntimeException(namingEx);
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                t.fillInStackTrace();
            }
            sqlEx.fillInStackTrace();
            throw new RuntimeException(sqlEx);
        }
    }

    /**
     * Default-конструктор посредника. Проверяет наличие доступности пакетных операций DML
     */
    public CallerMediator()
    {
        try
        {
            DatabaseMetaData dbmd = dbConn.getMetaData();
            if (!dbmd.supportsBatchUpdates())
            {
                throw new SQLException("Bad Oracle JDBC Driver detected");
            }
        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                t.fillInStackTrace();
            }
            sqlEx.fillInStackTrace();
            throw new RuntimeException(sqlEx);
        }
    }

    /**
     * Заполнение таблиц СУБД Oracle, содержащие описание тех. процессов.
     * <p/>
     * @param process Список тех. процессов
     * <p/>
     * @return Объект, инкапсулирующий результат выполнения.
     * <p/>
     * @throws SQLException
     */
    public ActionResult doUpdateProcessOperations(List<TechnologyProcess> process)
        throws SQLException
    {
        assert process != null;
        assert !process.isEmpty();

        ActionResult actionResult = new ActionResult();
        actionResult.setErrorCode(Integer.valueOf(0));
        actionResult.setMessage("");
        actionResult.setResult("");
        
        int operNoinfoCounter = 0, stepNoinfoCounter = 0;
        int operFailCounter = 0, stepFailCounter = 0;
        int operAffectedCounter = 0, stepAffectedCounter = 0;

        for (int i = 0; i < process.size(); i++)
        {
            boolean alreadyDelete = false;
            // Проход по всем тех. процессам в списке
            if (process.get(i) == null)
            {
                throw new RuntimeException("Technology process cannot be absolutely empty");
            }

            long currentProcessId = process.get(i).getProcessId();

            if (currentProcessId <= 0)
            {
                String appendMessage = "Tech. process # " + currentProcessId + " has illegal id; ";
                actionResult.setMessage(actionResult.getMessage() + appendMessage);
                continue;
            }
            
            if (process.get(i).getOperations() == null)
            {   // Операции не были заданы
                continue;
            }
            
            Operations currentOperations = process.get(i).getOperations();
            
            // Если добавлять нечего, то и удалять ничего не будем
            if (currentOperations.getOperation() == null)
            {
                continue;
            }
            if (currentOperations.getOperation().isEmpty())
            {
                continue;
            }

            Statement operStat = null;
            boolean autoCommit = dbConn.getAutoCommit();
            try
            {
                dbConn.setAutoCommit(false);
                operStat = dbConn.createStatement();
                List<Operation> operationList = currentOperations.getOperation();

                String qBaseOperationInsert = "INSERT INTO xxodi.xxtu_operations ( "
                    + " PROCESS_ID, "
                    + " OPERATION_ID, "
                    + " OPERATION_NUM, "
                    + " DEPARTMENT, "
                    + " SITE, "
                    + " OPERATION_NAME, "
                    + " OPERATION_TYPE, "
                    + " OPERATION_SUBTYPE, "
                    + " NORM_TIME, "
                    + " EQUIP_CODE, "
                    + " EQUIP_MODEL, "
                    + " EQUIP_NAME, "
                    + " LAST_UPDATE_DATE ) ";
                
                for (Operation anOperation : operationList)
                {
                    // Обработка параметов для текущей операции тех. процесса

                    int operId = anOperation.getOperId();
                    if (operId <= 0)
                    {
                        StringBuilder appendResult = new StringBuilder(0x1F + 1);
                        appendResult.append("Operation # ").append(operId).
                            append(" in tech. process ").
                            append(currentProcessId).
                            append(" has illegal id" + "; ");
                        actionResult.setResult(actionResult.getResult() + appendResult);
                        continue;
                    }
                    String operNum =
                        processStringField(anOperation.getOperNum(), 6);
                    String department =
                        processStringField(anOperation.getDepartment(), 6);
                    String site =
                        processStringField(anOperation.getSite(), 6);
                    String operName =
                        processStringField(anOperation.getOperName(), 240);
                    String operType =
                        processStringField(anOperation.getOperType(), 20);
                    String operSubType =
                        processStringField(anOperation.getOperSubtype(), 14);
                    double normType =
                        anOperation.getNormTime() == null ? 0 : anOperation.getNormTime();
                    String equipCode =
                        processStringField(anOperation.getEquipCode(), 50);
                    String equipModel =
                        processStringField(anOperation.getEquipModel(), 50);
                    String equipName =
                        processStringField(anOperation.getEquipName(), 240);
                    String oracleCurrentDate = "to_date(SYSDATE, 'DD.MM.YY')";

                    // Собираем обработанные параметры в INSERT-запрос

                    StringBuilder commandBuilder = new StringBuilder(qBaseOperationInsert);
                    commandBuilder.append(" VALUES ( ");
                    commandBuilder.append(currentProcessId).append(", ");
                    commandBuilder.append(operId).append(", ");
                    commandBuilder.append(operNum).append(", ");
                    commandBuilder.append(department).append(", ");
                    commandBuilder.append(site).append(", ");
                    commandBuilder.append(operName).append(", ");
                    commandBuilder.append(operType).append(", ");
                    commandBuilder.append(operSubType).append(", ");
                    commandBuilder.append(normType).append(", ");
                    commandBuilder.append(equipCode).append(", ");
                    commandBuilder.append(equipModel).append(", ");
                    commandBuilder.append(equipName).append(", ");
                    commandBuilder.append(oracleCurrentDate).append(')');
                    operStat.addBatch(commandBuilder.toString());

                    // TODO: Здесь нужно удалить операции, не затронув переходы

                    // Обработка переходов для текущей операции тех. процесса
                    if (anOperation.getSteps() != null)
                    {
                        Statement tripStat = null;
                        Steps steps = anOperation.getSteps();
                        String qBaseTripInsert = "INSERT INTO xxodi.xxtu_steps ( "
                            + " PROCESS_ID, "
                            + " OPERATION_ID, "
                            + " STEP_NUM, "
                            + " STEP_NAME, "
                            + " WORK_TYPE_CODE, "
                            + " NORM_TIME, "
                            + " LAST_UPDATE_DATE) ";
                        try
                        {
                            if (steps.getStep() != null)
                            {
                                if (!steps.getStep().isEmpty())
                                {
                                    tripStat = dbConn.createStatement();
                                    for (Step aStep : steps.getStep())
                                    {
                                        // Обработка параметров перехода
                                        int stepNum = aStep.getStepNum();
                                        if (stepNum <= 0)
                                        {
                                            StringBuilder appendResult
                                                = new StringBuilder(0x1F + 1);
                                            appendResult.
                                                append("A step ").
                                                append(stepNum).append(" for operation ").
                                                append(operId).
                                                append(" has illegal id " + " in tech. process ").
                                                append(currentProcessId).append("; ");
                                            actionResult.setResult(
                                                actionResult.getResult() + appendResult);
                                            continue;
                                        }
                                        
                                        // TODO: Здесь уже можно удалить переходы, или сделать это в любом случае

                                        if (!alreadyDelete)
                                        {
                                            /*
                                             * Операции и переходы есть - удаляем существующие
                                             * операции и переходы текущего тех. процесса
                                             */
                                            boolean successDelete =
                                                deleteTProcessIfExists(currentProcessId);
                                            String statusMessage = "Process # " + currentProcessId + " will be "
                                                + (successDelete ? "updated" : "inserted") + "; ";
                                            actionResult.setMessage(
                                                actionResult.getMessage() + statusMessage);
                                            alreadyDelete = true;
                                        }

                                        String stepName =
                                            processStringField(aStep.getStepName(), 240);
                                        String workTypeCode =
                                            processStringField(aStep.getWorkTypeCode(), 240);
                                        double stepNormTime = aStep.getNormTime();

                                        // Собираем обработанные данные в INSERT
                                        StringBuilder tripBuilder =
                                            new StringBuilder(qBaseTripInsert);
                                        tripBuilder.append(" VALUES ( ");
                                        tripBuilder.append(currentProcessId).append(", ");
                                        tripBuilder.append(operId).append(", ");
                                        tripBuilder.append(stepNum).append(", ");
                                        tripBuilder.append(stepName).append(", ");
                                        tripBuilder.append(workTypeCode).append(", ");
                                        tripBuilder.append(stepNormTime).append(", ");
                                        tripBuilder.append(oracleCurrentDate).append(')');
                                        tripStat.addBatch(tripBuilder.toString());
                                    }

                                    int[] tripUpdateCount = tripStat.executeBatch();

                                    // Сбор статистики пакетного обновления для переходов
                                    for (int batchType : tripUpdateCount)
                                    {
                                        if (batchType > 0)
                                        {
                                            stepAffectedCounter += batchType;
                                        }
                                        else if (batchType == Statement.SUCCESS_NO_INFO)
                                        {
                                            stepNoinfoCounter++;
                                        }
                                        else if (batchType == Statement.EXECUTE_FAILED)
                                        {
                                            stepFailCounter++;
                                        }
                                    }
                                }
                            }
                        }
                        finally
                        {
                            if (tripStat != null)
                            {
                                tripStat.close();
                            }
                        }

                    }

                }

                int[] opUpdateCount = operStat.executeBatch();

                // Сбор статистики пакетного обновления для операций.
                for (int batchType : opUpdateCount)
                {
                    if (batchType > 0)
                    {
                        operAffectedCounter += batchType;
                    }
                    else if (batchType == Statement.SUCCESS_NO_INFO)
                    {
                        operNoinfoCounter++;
                    }
                    else if (batchType == Statement.EXECUTE_FAILED)
                    {
                        operFailCounter++;
                    }
                }

                dbConn.commit();
            }
            catch (SQLException sqlEx)
            {
                dbConn.rollback();
                for (Throwable t : sqlEx)
                {
                    t.fillInStackTrace();
                }
                sqlEx.fillInStackTrace();
                throw sqlEx;
            }
            finally
            {
                dbConn.setAutoCommit(autoCommit);
                if (operStat != null)
                {
                    operStat.close();
                }
            }
        }

        StringBuilder totalResult = new StringBuilder(0x8F + 1);
        totalResult.append("Total affected operations: ").
            append(operAffectedCounter).append(", ");
        totalResult.append("Total no affected operations: ").
            append(operNoinfoCounter).append(", ");
        totalResult.append("Total foul operations: ").append(operFailCounter).
            append(System.getProperty("line.separator"));
        totalResult.append("Total affected steps: ").
            append(stepAffectedCounter).append(", ");
        totalResult.append("Total no affected steps: ").
            append(stepNoinfoCounter).append(", ");
        totalResult.append("Total foul steps: ").append(stepFailCounter);
        actionResult.setResult(actionResult.getResult() + totalResult);

        return actionResult;
    }

    /**
     * Удаление существующих операций и переходов тех. процесса, если таковой существует.
     * <p/>
     * @param processId номер тех. процесса.
     * <p/>
     * @return true, если удаление было произведено, false - в противном случае.
     */
    private boolean deleteTProcessIfExists(long processId)
    {
        String existsQuery = "select 1 from xxodi.xxtu_operations where process_id = ?";
        try
        {
            PreparedStatement pStat = null;
            ResultSet rs = null;
            try
            {
                pStat = dbConn.prepareStatement(existsQuery);
                pStat.setLong(1, processId);
                rs = pStat.executeQuery();
                if (rs.next())
                {
                    boolean autoCommit = dbConn.getAutoCommit();
                    dbConn.setAutoCommit(false);
                    String qDelOpers = "DELETE FROM xxodi.xxtu_operations WHERE process_id = ?";
                    String qDelSteps = "DELETE FROM xxodi.xxtu_steps WHERE process_id = ?";
                    PreparedStatement opersStat = null;
                    PreparedStatement stepsStat = null;
                    try
                    {
                        opersStat = dbConn.prepareStatement(qDelOpers);
                        opersStat.setLong(1, processId);
                        stepsStat = dbConn.prepareStatement(qDelSteps);
                        stepsStat.setLong(1, processId);
                        opersStat.executeUpdate();
                        stepsStat.executeUpdate();
                        dbConn.commit();

                        return true;
                    }
                    catch (SQLException sqlEx)
                    {
                        dbConn.rollback();
                        for (Throwable t : sqlEx)
                        {
                            t.fillInStackTrace();
                        }
                        sqlEx.fillInStackTrace();
                        throw sqlEx;
                    }
                    finally
                    {
                        dbConn.setAutoCommit(autoCommit);
                        if (opersStat != null)
                        {
                            opersStat.close();
                        }
                        if (stepsStat != null)
                        {
                            stepsStat.close();
                        }
                    }
                }
            }
            finally
            {
                if (pStat != null)
                {
                    pStat.close();
                }
                if (rs != null)
                {
                    rs.close();
                }
            }

        }
        catch (SQLException sqlEx)
        {
            for (Throwable t : sqlEx)
            {
                t.fillInStackTrace();
            }
            sqlEx.fillInStackTrace();
            throw new RuntimeException(sqlEx);
        }

        return false;
    }

    /**
     * Обработка поля символьного типа для передачи СУБД Oracle
     * <p/>
     * @param field     Поле для обработки
     * @param trimLimit Предельная длина поля в СУБД
     * <p/>
     * @return Строка, готовая для передачи СУБД
     */
    private static String processStringField(String field, int trimLimit)
    {
        if (field == null)
        {
            return "NULL";
        }
        String lField = field.trim();
        if (lField.length() > trimLimit)
        {
            lField = lField.substring(0, trimLimit);
        }
        return "'" + lField + "'";
    }
}

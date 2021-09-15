package ru.aztpa.a.tp.mfg.process.v01;

import ru.aztpa.a.tp.mfg.process.utilities.CachedConnection;

import javax.naming.NamingException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Calendar.*;
import static ru.aztpa.a.tp.mfg.process.utilities.CachedConnection.getCacheConnection;
import static ru.aztpa.a.tp.mfg.process.v01.JNDIContextEnum.*;

/**
 * Оболочка вызовов методов Web-сервиса.
 *
 * @author jdeveloper@aztpa.ru
 * @version 1.3.0 23.03.2012
 */
class CalledWrapper
{
    private final static DatatypeFactory dtFactory;
    private final static GregorianCalendar gCalendar = new GregorianCalendar();
    static final int OPE_FILENAME_LENGTH = 8;
    static final String OPE_FILE_EXTENSION = ".OPE";
    static final String PER_FILE_EXTENSION = ".PER";

    static
    {
        try
        {
            dtFactory = DatatypeFactory.newInstance();
        }
        catch (DatatypeConfigurationException dtEx)
        {
            throw new RuntimeException(dtEx);
        }
    }

    CalledWrapper()
    {
    }

    List<Process> doFindProcessListByProfile(Profile profile)
       throws SQLException
    {
        // Обработка входящих параметров.

        String baseQuery = new StringBuilder().append("select ").append(" nomtp, ").append(" oboz_dse, ").append(
           " naim_dse, ").append(" obozn_izd, ").append(" date_cr, ").append(" time_cr, ").append(
           " ctot(concat(nvl(date_cr,'1900-01-01'), ' ', nvl(time_cr,'00:00:00.0'))) as created ").append(
           " from kattp ").append(" where 1=1 ").toString();

        StringBuilder sqlProcess = new StringBuilder(baseQuery);

        long processId; // Обработка поля nomtp <=> processId
        if (profile.getProcessId() != null)
        {
            processId = profile.getProcessId();
            sqlProcess.append(" and nomtp = ").append(processId).append(" ");
        }

        String figDSE;  // Обработка поля oboz_dse <=> figDSE
        if (profile.getFigDSE() != null)
        {
            figDSE = profile.getFigDSE().trim();
            if (figDSE.length() > 0)
            {
                sqlProcess.append(" and oboz_dse like '").append(figDSE).append("' ");
            }
        }

        String nameDSE; // Обработка поля naim_dse <=> nameDSE
        if (profile.getNameDSE() != null)
        {
            nameDSE = profile.getNameDSE().trim();
            if (nameDSE.length() > 0)
            {
                sqlProcess.append(" and naim_dse like '").append(nameDSE).append("' ");
            }
        }

        String figFG;   // Обработка поля obozn_izd <=> figFG
        if (profile.getFigFG() != null)
        {
            figFG = profile.getFigFG().trim();
            if (figFG.length() > 0)
            {
                sqlProcess.append(" and obozn_izd like '").append(figFG).append("' ");
            }
        }

        // Обработка начальной даты диапазона
        if (profile.getCreatedAfter() != null)
        {   // PENDING: Using java.text.SimpleDateFormat
            GregorianCalendar dateAfter = profile.getCreatedAfter().toGregorianCalendar();
            StringBuilder dateAfterSb = new StringBuilder(0x1F);
            dateAfterSb.append(dateAfter.get(YEAR)).append("-");
            dateAfterSb.append(dateAfter.get(MONTH) + 1);
            dateAfterSb.append("-");
            dateAfterSb.append(dateAfter.get(DAY_OF_MONTH));
            dateAfterSb.append(" ");
            dateAfterSb.append(dateAfter.get(HOUR_OF_DAY)).append(':');
            dateAfterSb.append(dateAfter.get(MINUTE)).append(':');
            dateAfterSb.append(dateAfter.get(SECOND)).append(".0");

            sqlProcess.append(
               " and ctot(concat(nvl(date_cr,'1900-01-01'), ' ', nvl(time_cr,'00:00:00.0'))) ");
            sqlProcess.append(" > ctot('").append(dateAfterSb.toString()).append("') ");
        }

        // Обработка конечной даты диапазона
        if (profile.getCreatedBefore() != null)
        {   // PENDING: Using java.text.SimpleDateFormat
            GregorianCalendar dateBefore = profile.getCreatedBefore().toGregorianCalendar();
            StringBuilder dateBeforeSb = new StringBuilder(0x1F);
            dateBeforeSb.append(dateBefore.get(YEAR)).append("-");
            dateBeforeSb.append(dateBefore.get(MONTH) + 1).append("-");
            dateBeforeSb.append(dateBefore.get(DAY_OF_MONTH)).append(" ");
            dateBeforeSb.append(dateBefore.get(HOUR_OF_DAY)).append(':');
            dateBeforeSb.append(dateBefore.get(MINUTE)).append(':');
            dateBeforeSb.append(dateBefore.get(SECOND)).append(".0");

            sqlProcess.append(
               " and ctot(concat(nvl(date_cr,'1900-01-01'), ' ', nvl(time_cr,'00:00:00.0'))) ");
            sqlProcess.append(" < ctot('").append(dateBeforeSb.toString()).append("') ");
        }

        List<Process> processList = new ArrayList<Process>(0xAFF);

        Connection zagtpBas = null;
        PreparedStatement actionStat;
        ResultSet actionResult = null;
        try
        {
            try
            {
                zagtpBas = getCacheConnection(ZAGTP_BAS.name());

                actionStat = zagtpBas.prepareStatement(sqlProcess.toString());
                actionResult = actionStat.executeQuery();

                while (actionResult.next())
                {
                    Process currentProcess = new Process();

                    currentProcess.setProcessId(actionResult.getLong("nomtp"));
                    currentProcess.setFigDSE(actionResult.getString("oboz_dse"));
                    currentProcess.setNameDSE(actionResult.getString("naim_dse"));

                    processList.add(currentProcess);
                }
            }
            catch (NamingException namingException)
            {
                SQLException sqlEx = new SQLException(namingException);
                throw sqlEx;
            }
        }
        finally
        {
            if (actionResult != null && !actionResult.isClosed())
                actionResult.close();
            CachedConnection.closeAll();
        }

        return processList;
    }

    ProcessDetail doGetProcessDetailById(long id)
       throws SQLException
    {
        try
        {
            ProcessDetail processDetail = new ProcessDetail();

            // Получим список номеров всех тех. процессов вплоть до типового
            if (procIdList != null)
            {
                if (!procIdList.isEmpty())
                {
                    Object syncObject = new Object();
                    synchronized (syncObject)
                    {
                        procIdList.clear();
                    }
                }
            }
            Long[] procIds = allProcessIds(id).toArray(new Long[0]);

            // Запрос тех. процесса для конкретного id
            String techProcQuery = new StringBuilder().append("select nomtp, ").append(" obozn_izd, ").append(
               " oboz_dse, ").append(
               " ctot(concat(nvl(date_cr,'1900-01-01'), ' ', nvl(time_cr,'00:00:00.0'))) as created,").append(
               " naim_dse ").append(" from kattp ").append(" where nomtp = ?").toString();

            PreparedStatement techProcStat = null;
            ResultSet techProcResult = null;

            try
            {
                try
                {
                    Connection zagtpBas = getCacheConnection(ZAGTP_BAS.name());

                    techProcStat = zagtpBas.prepareStatement(techProcQuery);
                    techProcStat.setLong(1, id);
                    techProcResult = techProcStat.executeQuery();

                    if (techProcResult.next())
                    {   // Тех. процесс с таким id существует
                        processDetail.setProcessId(techProcResult.getLong("nomtp"));
                        processDetail.setFigFG(techProcResult.getString("obozn_izd"));
                        processDetail.setFigDSE(techProcResult.getString("oboz_dse"));
                        Date created = techProcResult.getDate("created");
                        gCalendar.setTime(created);
                        processDetail.setCreated(dtFactory.newXMLGregorianCalendar(gCalendar));
                        processDetail.setNameDSE(techProcResult.getString("naim_dse"));
                    }
                    else
                    {   // Данных нет
                        return processDetail;
                    }
                    if (techProcResult.next())
                    {   // Ошибка в справочнике: тех. процесс не уникален.
                        throw new RuntimeException("The technical process is not unique");
                    }
                }
                catch (NamingException namingException)
                {
                    SQLException sqlEx = new SQLException(namingException);
                    throw sqlEx;
                }
            }
            finally
            {
                if (techProcStat != null && !techProcStat.isClosed())
                    techProcStat.close();
                if (techProcResult != null && !techProcResult.isClosed())
                    techProcResult.close();
            }

            // Построим запрос для списка всех переходов для группы связанных тех. процессов
            String currentPerFName;
            String basePerQString = new StringBuilder().append("select ").append(" nomop, ").append(" dob, ").append(
               " tst, ").append(" naim, ").append(" nomper, ").append(" kvr ").toString();
            String whereClause = " where length(trim(naim)) <> 0 and nomop = ? ";
            StringBuilder perStringBuilder = new StringBuilder(0x80);
            for (int i = 0; i < procIds.length; i++)
            {
                perStringBuilder.append(basePerQString);
                currentPerFName = processIdFileName(procIds[i], PER_FILE_EXTENSION);
                perStringBuilder.append(" from \"").append(currentPerFName).append("\" ");
                perStringBuilder.append(whereClause);
                perStringBuilder.append(" union ");
            }
            perStringBuilder.delete(perStringBuilder.lastIndexOf("union"), perStringBuilder.length());
            perStringBuilder.append("order by nomper");

            PreparedStatement procOperationsStat = null;
            ResultSet procOperationsResult = null;
            Operations operations = new Operations();
            operations.operation = new ArrayList<Operation>(0x1F + 1);

            Connection zagtpOpe, zagtpPer;

            for (int i = 0; i < procIds.length; i++)
            {
                String opeQuery;
                String currentOpeFName = processIdFileName(procIds[i], OPE_FILE_EXTENSION);
                if (i == 0)
                {   // Исходный тех. процесс
                    opeQuery = new StringBuilder().append("select ncex, ").append(" kobr, ").append(" mod, ").append(
                       " naob, ").append(" tshk, ").append(" nomop, ").append(" naimop, ").append(" nomerop, ").append(
                       " kopr ").append(" from \"").append(currentOpeFName).append("\" order by nomop").toString();
                }
                else    // Промежуточный или типовой тех. процесс
                {
                    String childOpeFName = processIdFileName(procIds[i - 1], OPE_FILE_EXTENSION);
                    opeQuery = new StringBuilder().append("select ncex, ").append(" kobr, ").append(" mod, ").append(
                       " naob, ").append(" tshk, ").append(" nomop, ").append(" naimop, ").append(" nomerop, ").append(
                       " kopr ").append(" from \"").append(currentOpeFName).append("\" ").append(
                       " where nomop not in (select nomop from \"").append(childOpeFName).append("\") ").append(
                       " order by nomop").toString();
                }

                try
                {
                    PreparedStatement tripStat = null;
                    try
                    {
                        zagtpOpe = getCacheConnection(ZAGTP_OPE.name());
                        zagtpPer = getCacheConnection(ZAGTP_PER.name());

                        procOperationsStat = zagtpOpe.prepareStatement(opeQuery);
                        procOperationsResult = procOperationsStat.executeQuery();

                        tripStat = zagtpPer.prepareStatement(perStringBuilder.toString());
                        ResultSet tripResult = null;

                        while (procOperationsResult.next())
                        {   // Проход по операциям тех. процесса
                            Operation currentOperation = new Operation();
                            currentOperation.setDepartment(procOperationsResult.getString("ncex"));
                            currentOperation.setEquipCode(procOperationsResult.getString("kobr"));
                            currentOperation.setEquipModel(procOperationsResult.getString("mod"));
                            currentOperation.setEquipName(procOperationsResult.getString("naob"));
                            currentOperation.setNormTime(procOperationsResult.getDouble("tshk"));
                            currentOperation.setOperId(procOperationsResult.getInt("nomop"));
                            currentOperation.setOperName(procOperationsResult.getString("naimop"));
                            currentOperation.setOperNum(procOperationsResult.getString("nomerop"));
                            currentOperation.setOperSubtype(procOperationsResult.getString("kopr"));
                            currentOperation.setSite("");   // PENDING: Значение не определено

                            Steps opSteps = new Steps();
                            opSteps.step = new ArrayList<Step>(0xA);
                            try
                            {
                                for (int j = 1; j <= procIds.length; j++)
                                    tripStat.setInt(j, currentOperation.getOperId());
                                tripResult = tripStat.executeQuery();

                                while (tripResult.next())
                                {   // Проход по переходам каждого процесса
                                    Step currentStep = new Step();
                                    currentStep.setAddTime(tripResult.getDouble("dob"));
                                    currentStep.setNormTime(tripResult.getDouble("tst"));
                                    currentStep.setStepName(tripResult.getString("naim"));
                                    int stepNum = tripResult.getInt("nomper");
                                    currentStep.setStepNum(stepNum);
                                    currentStep.setWorkTypeCode(
                                       Integer.valueOf(tripResult.getInt("kvr")));

                                    // В переходах дочерних процессов текущий шаг мог быть
                                    // переопределен, проверим это...
                                    boolean stepNumAlreadyExists = false;
                                    for (Iterator<Step> it = opSteps.step.iterator(); it.hasNext(); )
                                    {
                                        Step aStep = it.next();
                                        int currentStepNumber = aStep.getStepNum();
                                        if (currentStepNumber == stepNum)
                                        {
                                            stepNumAlreadyExists = true;
                                            break;
                                        }
                                    }

                                    if (!stepNumAlreadyExists)
                                        opSteps.step.add(currentStep);
                                }
                            }
                            finally
                            {
                                if (tripResult != null && !tripResult.isClosed())
                                    tripResult.close();
                            }

                            currentOperation.setSteps(opSteps);
                            operations.operation.add(currentOperation);
                        }
                    }
                    catch (NamingException namingException)
                    {
                        throw new SQLException(namingException);
                    }
                    finally
                    {
                        if (tripStat != null && !tripStat.isClosed())
                            tripStat.close();
                    }
                }
                finally
                {
                    if (procOperationsStat != null && !procOperationsStat.isClosed())
                        procOperationsStat.close();
                    if (procOperationsResult != null && !procOperationsResult.isClosed())
                        procOperationsResult.close();
                }
            }

            Collections.sort(operations.operation, new Comparator<Operation>()
            {
                @Override
                public int compare(Operation first, Operation second)
                {
                    return first.getOperId() - second.getOperId();
                }
            });

            processDetail.setOperations(operations);
            return processDetail;
        }
        finally
        {
            CachedConnection.closeAll();
        }
    }

    /**
     * Рекурсивное получение всех номеров тех. процессов вплоть до типового
     * @param childId Номер самого первого тех. процесса-потомка
     * @return Связный список номеров
     */
    private synchronized LinkedList<Long> allProcessIds(long childId)
    {
        if (procIdList == null)
            procIdList = new LinkedList<Long>();

        procIdList.addLast(childId);
        Connection zagtpBas;
        ResultSet rs = null;
        try
        {
            zagtpBas = getCacheConnection(ZAGTP_BAS.name());
            String baseQuery = "select nomtp, nom_tip_tp from kattp where nomtp = ?";
            PreparedStatement pstat = zagtpBas.prepareStatement(baseQuery);
            pstat.setLong(1, childId);
            rs = pstat.executeQuery();
            long nomtp = 0, nomTipTp = 0;
            if (rs.next())
            {
                nomtp = rs.getLong(1);
                nomTipTp = rs.getLong(2);
            }
            if (nomtp == 0)
                throw new RuntimeException("Fatal Error: process id cannot be 0");
            return (nomTipTp != 0) ? allProcessIds(nomTipTp) : procIdList;
        }
        catch (NamingException namingEx)
        {
            throw new RuntimeException(namingEx);
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
        finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (SQLException sqlEx)
                {
                    throw new RuntimeException(sqlEx);
                }
            }
        }
    }

    private LinkedList<Long> procIdList;

    /**
     * Получение имени файла по идентификатору процесса
     * @param processId Номер процесса
     * @param ext       Расширение файла
     * @return Имя файла
     */
    private static String processIdFileName(long processId, String ext)
    {
        StringBuilder fileNameBuilder = new StringBuilder(0xF);
        fileNameBuilder.append("").append(processId);
        for (int i = fileNameBuilder.length(); i < OPE_FILENAME_LENGTH; i++)
            fileNameBuilder.insert(0, '0');
        fileNameBuilder.append(ext);
        return fileNameBuilder.toString();
    }
}

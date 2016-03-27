package ru.aztpa.a.oaa.mfg.shiftjob.v03;

import ru.aztpa.a.oaa.mfg.shiftjob.utilities.ConnectionTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.util.Calendar.*;

/**
 * ќболочка вызовов методов Web-сервиса.
 *
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0
 */
class CalledWrapper extends ConnectionTemplate
{
    private final static String DEFAULT_CONTEXT_NAME = "OAADataSource";
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

    CalledWrapper()
    {
        super(DEFAULT_CONTEXT_NAME);
    }

    List<WorkSheetSummary> doFindWorkSheetListByProfile(
       String workSheetNum,
       String department,
       Integer shiftNum,
       XMLGregorianCalendar afterDate,
       XMLGregorianCalendar beforeDate,
       String employeeTabNum,
       String employeeLastName)
       throws SQLException
    {
        Connection conn = doConnect();

        // „астична€ обработка параметров.

        workSheetNum = (workSheetNum == null || workSheetNum.length() == 0)
           ? "%"
           : workSheetNum.trim();

        department = (department == null || department.length() == 0)
           ? "%"
           : department.trim();

        if (shiftNum == null || shiftNum < 0)
        {    // FIXME: NumberFormatException, если из строки невозможно создать целое число.
            IllegalArgumentException argEx = new IllegalArgumentException("shiftNum must be filled");
            throw new RuntimeException(argEx);
        }

        if (afterDate == null)  // FIXME: FormatException, если дата не соответсвует локальному формату
            throw new RuntimeException(new IllegalArgumentException("afterDate must be filled"));
        GregorianCalendar fromXmlCal = afterDate.toGregorianCalendar();
        String afterDateString = MessageFormat.format("{0}.{1}.{2}", fromXmlCal.get(DAY_OF_MONTH),
                                                      fromXmlCal.get(MONTH) + 1,
                                                      fromXmlCal.get(YEAR));

        if (beforeDate == null) // FIXME: FormatException, если дата не соответсвует локальному формату
            throw new RuntimeException(new IllegalArgumentException("beforeDate must be filled"));
        fromXmlCal = beforeDate.toGregorianCalendar();
        String beforeDateString = MessageFormat.format("{0}.{1}.{2}", fromXmlCal.get(DAY_OF_MONTH),
                                                       fromXmlCal.get(MONTH) + 1,
                                                       fromXmlCal.get(YEAR));

        employeeTabNum = (employeeTabNum == null || employeeTabNum.length() == 0) ? "%" : employeeTabNum.trim();
        employeeLastName = (employeeLastName == null || employeeLastName.length() == 0) ? "%" : employeeLastName.trim().toUpperCase();

        List<WorkSheetSummary> theWorkSheetSummary = new ArrayList<WorkSheetSummary>();

        ResultSet resultSet = null;
        try
        {
            String sqlString =
               MessageFormat.format(
                  "SELECT  " +
                     " ws.worksheet_id,  " +
                     "ws.worksheet_num,  " +
                     "sj.job_date,  " +
                     "sj.shift_num,  " +
                     "p.full_name,  " +
                     "d.department_code,  " +
                     "p.employee_number " +
                  "FROM  " +
                     "xxtpa_mfg0015_worksheets ws,  " +
                     "xxtpa_mfg0003_headers sj,  " +
                     "per_all_people_f p,  " +
                     "bom_departments_v d " +
                  "WHERE " +
                     "sj.header_id = ws.header_id " +
                     "AND p.person_id = ws.executor_id " +
                     "AND d.department_id = sj.department_id " +
                     "AND sj.job_date BETWEEN p.effective_start_date AND p.effective_end_date " +
                     "AND ws.worksheet_num LIKE ''%{0}'' " +
                     "AND d.department_code LIKE ''{1}%'' " +
                     "AND sj.shift_num = {2} " +
                     "AND sj.job_date > TO_DATE(''{3}'', ''DD.MM.YYYY'') " +
                     "AND sj.job_date < TO_DATE(''{4}'', ''DD.MM.YYYY'') " +
                     "AND p.employee_number LIKE ''{5}%'' " +
                     "AND UPPER(p.full_name) LIKE ''%{6}%'' " +
                  "ORDER BY worksheet_num",
                    new Object[]
                    {
                       workSheetNum,
                       department,
                       shiftNum.intValue(),
                       afterDateString,
                       beforeDateString,
                       employeeTabNum,
                       employeeLastName
                    }
               );

            PreparedStatement stat = conn.prepareStatement(sqlString);
            resultSet = stat.executeQuery(sqlString);

            while (resultSet.next())
            {
                // »звлекаем данные текущей строки
                long currentWorkSheetId = resultSet.getLong("worksheet_id");
                String currentWorkSheetNum = resultSet.getString("worksheet_num");
                Date currentJobDate = resultSet.getDate("job_date");
                int currentShiftNum = resultSet.getInt("shift_num");
                String currentFullName = resultSet.getString("full_name");
                // String currentDepartmentCode = resultSet.getString("department_code");
                // String currentEmployeeNumber = resultSet.getString("employee_number"); 

                WorkSheetSummary currentWSSummary = new WorkSheetSummary();
                currentWSSummary.setWorkSheetId(currentWorkSheetId);
                currentWSSummary.setWorkSheetNum(currentWorkSheetNum);
                gCalendar.setTime(currentJobDate);
                currentWSSummary.setShiftDate(dtFactory.newXMLGregorianCalendar(gCalendar));
                currentWSSummary.setShiftNum(currentShiftNum);
                currentWSSummary.setExecutorName(currentFullName);

                theWorkSheetSummary.add(currentWSSummary);
            }
        }
        finally
        {
            if (resultSet != null && !resultSet.isClosed())
                resultSet.close();
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        return theWorkSheetSummary;
    }

    WorkSheetDetail doGetWorkSheetDetailById(long workSheetId)
       throws SQLException
    {
        Connection conn = doConnect();

        if (workSheetId < 0 || workSheetId > Long.MAX_VALUE)
            throw new RuntimeException(new IllegalArgumentException("workSheetId is incorrect"));

        WorkSheetDetail theWorkSheetDetail = new WorkSheetDetail();
        Worker singleWorker = new Worker();
        SheetLines sheetLines = new SheetLines();
        sheetLines.sheetLine = new ArrayList<SheetLine>();
        theWorkSheetDetail.setExecutor(singleWorker);
        theWorkSheetDetail.setSheetLines(sheetLines);

        ResultSet lineResult = null, headResult = null;
        try
        {
            String headQuery = String.format(
               new StringBuilder().append("SELECT ").append("worksheet_num, ").append("worksheet_id, ").append(
                  "header_id, ").append("job_num, ").append("department_code, ").append("site_code, ").append(
                  "job_date, ").append("shift_num, ").append("person_id, ").append("employee_number, ").append(
                  "last_name, ").append("first_name, ").append("middle_names ").append(
                  "FROM xxwsa0019_ws_headers_v ").append("WHERE worksheet_id = ?").toString()
            );

            PreparedStatement headStat = conn.prepareStatement(headQuery);
            headStat.setLong(1, workSheetId);
            headResult = headStat.executeQuery();

            // Ќайдены нужные данные
            if (headResult.next())
            {
                String workSheetNum = headResult.getString("worksheet_num");
                long lWorkSheetId = headResult.getLong("worksheet_id");
                long headerId = headResult.getLong("header_id");
                String jobNum = headResult.getString("job_num");
                String depCode = headResult.getString("department_code");
                String siteCode = headResult.getString("site_code");
                Date jobDate = headResult.getDate("job_date");
                int shiftNum = headResult.getInt("shift_num");
                long personId = headResult.getLong("person_id");
                String employeeNum = headResult.getString("employee_number");
                String lastName = headResult.getString("last_name");
                String firstName = headResult.getString("first_name");
                String middleNames = headResult.getString("middle_names");

                theWorkSheetDetail.setWorkSheetNum(workSheetNum);
                theWorkSheetDetail.setWorkSheetId(lWorkSheetId);
                theWorkSheetDetail.setShiftJobId(headerId);
                theWorkSheetDetail.setShiftJobNum(jobNum);
                theWorkSheetDetail.setDepartment(depCode);
                theWorkSheetDetail.setSite(siteCode);
                gCalendar.setTime(jobDate);
                theWorkSheetDetail.setShiftDate(dtFactory.newXMLGregorianCalendar(gCalendar));
                theWorkSheetDetail.setShiftNum(shiftNum);

                singleWorker.setPersonId(personId);
                singleWorker.setTabNum(employeeNum);
                singleWorker.setLastName(lastName);
                singleWorker.setFirstName(firstName);
                singleWorker.setMiddleName(middleNames);

                // theWorkSheetDetail.setExecutor(singleWorker);
            }
            else
            {   // TODO: ќбработать ситуацию отсутстви€ данных
                // return theWorkSheetDetail;
                throw new RuntimeException("No data");
            }

            // «апрос дл€ строк нар€да.
            String lineQuery = new StringBuilder().append("SELECT ").append("worksheet_id, ").append(
               "line_id, ").append("line_number, ").append("description, ").append("item_no, ").append(
               "description_ru, ").append("figure, ").append("planing_quantity, ").append("accepted, ").append(
               "rejected, ").append("primary_unit_of_measure, ").append("spz, ").append("defendant, ").append(
               "competence_id, ").append("competence_name, ").append("resource_id, ").append("resource_code, ").append(
               "norm_Time, ").append("for_Nuclear, ").append("re_inspection, ").append("wip_entity_id, ").append(
               "operation_seq_num, ").append("operation_desc, ").append("inventory_item_id, ").append(
               "organization_id, ").append("competence_step_value ").append("FROM xxwsa0019_ws_lines_v ").append(
               "WHERE worksheet_id = ? ").append("ORDER BY line_number").toString();

            PreparedStatement lineStat = conn.prepareStatement(lineQuery);
            lineStat.setLong(1, workSheetId);
            lineResult = lineStat.executeQuery();

            while (lineResult.next())
            {    // »звлекаем текущие строки нар€да
                SheetLine currentSheetLine = new SheetLine();

                // int worksheetId = lineResult.getInt("worksheet_id");
                long lineId = lineResult.getLong("line_id");
                int lineNumber = lineResult.getInt("line_number");
                String desc = lineResult.getString("description");
                String itemNumber = lineResult.getString("item_no");
                String descRU = lineResult.getString("description_ru");
                String figure = lineResult.getString("figure");
                double planingQuantity = lineResult.getDouble("planing_quantity");
                double accepted = lineResult.getDouble("accepted");
                double rejected = lineResult.getDouble("rejected");
                String primaryUnitOfMeasure = lineResult.getString("primary_unit_of_measure");
                String spz = lineResult.getString("spz");
                double defendant = lineResult.getDouble("defendant");
                // int competenceId = lineResult.getInt("competence_id");
                String competenceName = lineResult.getString("competence_name");
                int resourceId = lineResult.getInt("resource_id");
                String resourceCode = lineResult.getString("resource_code");
                double normTime = lineResult.getDouble("norm_Time");
                String forNuclear = lineResult.getString("for_Nuclear");
                String reInspection = lineResult.getString("re_inspection");
                // int wipEntityId = lineResult.getInt("wip_entity_id");
                // int operationSeqNum = lineResult.getInt("operation_seq_num");
                String operationDesc = lineResult.getString("operation_desc");
                // int inventoryItemId = lineResult.getInt("inventory_item_id");
                // int orgId = lineResult.getInt("organization_id");
                int competenceStepValue = lineResult.getInt("competence_step_value");

                currentSheetLine.setSheetLineId(lineId);
                currentSheetLine.setSheetLineNum(lineNumber);
                currentSheetLine.setWorkDescription(desc);

                Item anItem = new Item();
                anItem.setItemNum(itemNumber);
                anItem.setItemName(descRU);
                anItem.setDrawNum(figure);
                currentSheetLine.setItem(anItem);

                currentSheetLine.setPlannedQnt(planingQuantity);
                currentSheetLine.setAcceptedQnt(accepted);
                currentSheetLine.setRejectedQnt(rejected);
                currentSheetLine.setUom(primaryUnitOfMeasure);
                currentSheetLine.setSpz(spz);
                currentSheetLine.setGuiltyDepartment(Double.toString(defendant));
                currentSheetLine.setCompetenceName(competenceName);

                Equipment anEquipment = new Equipment();
                anEquipment.setEquipmentCode(Integer.toString(resourceId));
                anEquipment.setEquipmentName(resourceCode);
                currentSheetLine.setUsedEquipment(anEquipment);

                currentSheetLine.setNormTime(normTime);
                currentSheetLine.setForNuclearEnergetics(forNuclear);
                currentSheetLine.setReInspection(reInspection);
                currentSheetLine.setWorkDescription(operationDesc);
                currentSheetLine.setCompetenceStepValue(Integer.toString(competenceStepValue));

                sheetLines.sheetLine.add(currentSheetLine);
            }
        }
        finally
        {
            if (headResult != null && !headResult.isClosed())
                headResult.close();
            if (lineResult != null && !lineResult.isClosed())
                lineResult.close();
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        return theWorkSheetDetail;
    }

    ActionResult doUpdateQuantity(
       long workSheetId,
       int lineNum,
       Double acceptedQnt,
       Double wasteQnt)
       throws SQLException
    {
        Connection conn = doConnect();

        try
        {
            // TODO: Application logic
        }
        finally
        {
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    List<ShiftJobSummary> doFindShiftJobListByProfile(
       String departament,
       XMLGregorianCalendar dateAfter,
       XMLGregorianCalendar dateBefore,
       String shiftJobNum,
       String shiftNum)
       throws SQLException
    {
        Connection conn = doConnect();

        try
        {
            // TODO: Application logic
        }
        finally
        {
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    ShiftJob doGetShiftJobDetailByNum(String shiftJobNum)
       throws SQLException
    {
        Connection conn = doConnect();

        try
        {
            // TODO: Application logic
        }
        finally
        {
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }

    ShiftDetail doGetShiftDetailByShift(Shift shift)
       throws SQLException
    {
        Connection conn = doConnect();

        try
        {
            // TODO: Application logic
        }
        finally
        {
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        throw new UnsupportedOperationException("Not implemented yet");
    }
}

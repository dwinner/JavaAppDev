package ru.aztpa.a.oaa.mfg.plan.v01.process;

import ru.aztpa.a.oaa.mfg.plan.v01.EngResolution;
import ru.aztpa.a.oaa.mfg.plan.v01.Plan;
import ru.aztpa.a.oaa.mfg.plan.v01.PlanRow;
import ru.aztpa.a.oaa.mfg.plan.v01.PlanRows;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static ru.aztpa.a.oaa.mfg.plan.v01.process.ProcessFields.processStringField;

/**
 * Вспомогательный (Hard Coded) класс для построения запросов к СУБД
 * @version 1.0.0 04.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class PlanQueryBuilder
{
    private Plan plan;
    private static final String plansTable = "XXODI.XXBI_PLANS";
    private static final String plansPositionsTable = "XXODI.XXBI_PLANPOSITIONS";

    /**
     * Инициализация плана.
     * @param aPlan План
     */
    public PlanQueryBuilder(Plan aPlan)
    {
        assert aPlan != null;
        plan = aPlan;
    }
    
    
    /**
     * Построение запроса на удаление (может не) существующих планов с соотв. ID
     * @return Строка запроса
     */
    public String buildDeletePlansQuery()
    {
        return "DELETE FROM " + plansTable + " WHERE ID = '" + plan.getPlanIDD() + "'";
    }
    
    /**
     * Построение запроса на удаление (может не) существующих позиций планов с соотв. ID
     * @return Строка запроса
     */
    public String buildDeletePlansPositionsQuery()
    {
        return "DELETE FROM " + plansPositionsTable + " WHERE ID = '" + plan.getPlanIDD() + "'";
    }

    /**
     * Построение запроса для вставки строки в таблицу планов.
     * @return Строка, готовая для передачи СУБД.
     */
    public String buildInsertPlanQuery()
    {
        String planId = plan.getPlanIDD();
        if (planId == null || planId.trim().length() == 0)
            throw new RuntimeException("planId cannot be null or empty");
        planId = processStringField(planId);

        String planType = processStringField(plan.getPlanType());

        String approved = plan.getApprovedBySPK();
        Integer approvedInt = null;
        if (approved != null)
        {
            approved = approved.trim().toUpperCase();
            if (approved.equals("Y"))
                approvedInt = 1;
            else if (approved.equals("N"))
                approvedInt = 0;
        }

        String approvedODP = plan.getApprovedByODP();
        Integer approvedODPInt = null;
        if (approvedODP != null)
        {
            approvedODP = approvedODP.trim().toUpperCase();
            if (approvedODP.equals("Y"))
                approvedODPInt = 1;
            else if (approvedODP.equals("N"))
                approvedODPInt = 0;
        }

        XMLGregorianCalendar xmlgcPeriod = plan.getPlanPeriod();
        String periodString = null;
        if (xmlgcPeriod != null)
        {
            GregorianCalendar fromXmlCal = xmlgcPeriod.toGregorianCalendar();
            periodString = "" + fromXmlCal.get(Calendar.DAY_OF_MONTH)
               + '.' + (fromXmlCal.get(Calendar.MONTH) + 1)
               + '.' + fromXmlCal.get(Calendar.YEAR);
            periodString = "TO_DATE('" + periodString + "', 'DD.MM.YYYY')";
        }

        String planName = processStringField(plan.getPlanName());
        
        String department = processStringField(plan.getDepartment());

        String qBase = "INSERT INTO " + plansTable + " ( "
           + "ID, "
           + "PLANTYPE, "
           + "APPROVED, "
           + "APPROVEDODP, "
           + "PERIOD, "
           + "PLAN_NAME, "
           + "DEPARTMENT) ";
        StringBuilder totalQuery = new StringBuilder(qBase);
        totalQuery.append(" VALUES ( ");
        totalQuery.append(planId).append(", ");
        totalQuery.append(planType).append(", ");
        totalQuery.append(approvedInt).append(", ");
        totalQuery.append(approvedODPInt).append(", ");
        totalQuery.append(periodString).append(", ");
        totalQuery.append(planName).append(", ");
        totalQuery.append(department).append(')');

        return totalQuery.toString();
    }

    /**
     * Построение запроса для вставки строк в таблицу позиций.
     * @return Массив INSERT-строк, готовая для передачи СУБД. Может быть пустым, если вставлять нечего.
     */
    public String[] buildInsertPlanPositionsQuery()
    {
        PlanRows planRowsObject = plan.getPlanRows();
        if (planRowsObject == null || planRowsObject.getPlanRow().isEmpty())
            return new String[0];
        List<PlanRow> planRows = planRowsObject.getPlanRow();
        List<String> insertQueries = new ArrayList<String>(planRows.size());

        String qBase = "INSERT INTO " + plansPositionsTable + " ( "
           + "SHIPORDERNUM, "
           + "ITEM, "
           + "FIGURE, "
           + "MONTHQ1, "
           + "MONTHQ2, "
           + "MONTHQ3, "
           + "ID, "
           + "NUMORDERCARD, "
           + "DAYSCONSRTPREPARE, "
           + "DATECONSTR, "
           + "NUMCONSTRUCTRESOLUTION, "
           + "ITEM_DESCRIPTION) ";
        
        String planId = plan.getPlanIDD();
        if (planId == null || planId.trim().length() == 0)
            throw new RuntimeException("planId cannot be null or empty");
        planId = processStringField(planId);

        for (PlanRow planRow : planRows)
        {
            String shipOrderNum = processStringField(planRow.getShipOrderNum());

            String item = planRow.getItemCode();
            if (item == null || item.trim().isEmpty())
                throw new RuntimeException("Item code cannot be null");
            item = processStringField(item);

            String figure = planRow.getItemFigure();
            if (figure == null || figure.trim().isEmpty())
                throw new RuntimeException("Figure cannot be null");
            figure = processStringField(figure);

            double month1Quantity = planRow.getMonth1Quantity();
            double month2Quantity = planRow.getMonth2Quantity();
            double month3Quantity = planRow.getMonth3Quantity();

            String numOrderCard = processStringField(planRow.getOrderCardNum());

            EngResolution engResolution = planRow.getEngResolution();
            if (engResolution == null)
                throw new RuntimeException("Resolution cannot be null");

            int daysConsrtPrepare = engResolution.getDesignDurationInDays();

            XMLGregorianCalendar xmlgcCDays = engResolution.getResolutionDate();
            String constrDays = null;
            if (xmlgcCDays != null)
            {
                GregorianCalendar fromXmlCal = xmlgcCDays.toGregorianCalendar();
                constrDays = "" + fromXmlCal.get(Calendar.DAY_OF_MONTH)
                   + '.' + (fromXmlCal.get(Calendar.MONTH) + 1)
                   + '.' + fromXmlCal.get(Calendar.YEAR);
                constrDays = "TO_DATE('" + constrDays + "', 'DD.MM.YYYY')";
            }

            String numConstructResolution = processStringField(engResolution.getResolutionNum());
            
            String itemName = processStringField(planRow.getItemName());

            StringBuilder currInsert = new StringBuilder(qBase);
            currInsert.append(" VALUES ( ");
            currInsert.append(shipOrderNum).append(", ");
            currInsert.append(item).append(", ");
            currInsert.append(figure).append(", ");
            currInsert.append(month1Quantity).append(", ");
            currInsert.append(month2Quantity).append(", ");
            currInsert.append(month3Quantity).append(", ");
            currInsert.append(planId).append(", ");
            currInsert.append(numOrderCard).append(", ");
            currInsert.append(daysConsrtPrepare).append(", ");
            currInsert.append(constrDays).append(", ");
            currInsert.append(numConstructResolution).append(", ");
            currInsert.append(itemName).append(')');

            insertQueries.add(currInsert.toString());
        }

        return insertQueries.toArray(new String[0]);
    }
}

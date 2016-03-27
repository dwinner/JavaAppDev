package ru.aztpa.a.oaa.mfg.plan.v01.process;

import ru.aztpa.a.oaa.mfg.plan.v01.PlannedFgProfile;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Вспомогательный класс для построения запросов поиска информации о запланированных к производству изделиях.
 * @version 1.0.0 11.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class PlanProfileQueryBuilder
{
    private PlannedFgProfile profile;

    /**
     * Инициализация профиля
     * @param aProfile (План-) Профиль
     */
    public PlanProfileQueryBuilder(PlannedFgProfile aProfile)
    {
        assert aProfile != null;
        profile = aProfile;
    }

    /**
     * Построение запроса поиска
     * @return Строка, готовая для передачи СУБД.
     */
    public String buildFindPlannedFgListByProfileQuery()
    {
        String baseQuery =
           new StringBuilder().append(" SELECT ").append("plans.plan_name as plan_name, ").append(
              "plans.period as period, ").append("outer_tbl.figure as figure, ").append(
              "outer_tbl.item as item, ").append("outer_tbl.item_description as item_description ").append(
              "FROM ").append("xxodi.xxbi_planpositions outer_tbl, ").append("xxodi.xxbi_plans plans ").append(
              "WHERE outer_tbl.id = plans.id ").toString();

        String planName = profile.getPlanName();
        String planWhereClause = "";
        if (planName != null && !planName.trim().isEmpty())
            planWhereClause = " AND plan_name LIKE '" + planName.trim() + "' ";

        XMLGregorianCalendar xmlgc = profile.getCompletionDateAfter();
        String afterDateWhereClause = "";
        if (xmlgc != null)
        {
            GregorianCalendar fromXmlCal = xmlgc.toGregorianCalendar();
            String oracleDate = "" + fromXmlCal.get(Calendar.DAY_OF_MONTH)
               + '.' + (fromXmlCal.get(Calendar.MONTH) + 1)
               + '.' + fromXmlCal.get(Calendar.YEAR);
            oracleDate = " TO_DATE('" + oracleDate + "', 'DD.MM.YYYY') ";
            afterDateWhereClause = " AND period >= " + oracleDate;
        }

        xmlgc = profile.getCompletionDateBefore();
        String beforeDateWhereClause = "";
        if (xmlgc != null)
        {
            GregorianCalendar fromXmlCal = xmlgc.toGregorianCalendar();
            String oracleDate = "" + fromXmlCal.get(Calendar.DAY_OF_MONTH)
               + '.' + (fromXmlCal.get(Calendar.MONTH) + 1)
               + '.' + fromXmlCal.get(Calendar.YEAR);
            oracleDate = " TO_DATE('" + oracleDate + "', 'DD.MM.YYYY') ";
            beforeDateWhereClause = " AND period <= " + oracleDate;
        }

        StringBuilder qBuilder = new StringBuilder(baseQuery);
        qBuilder.append(planWhereClause).append(afterDateWhereClause).append(beforeDateWhereClause);
        return qBuilder.toString();
    }
}

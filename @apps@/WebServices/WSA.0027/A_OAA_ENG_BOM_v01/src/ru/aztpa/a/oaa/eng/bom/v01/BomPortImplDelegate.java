package ru.aztpa.a.oaa.eng.bom.v01;

import ru.aztpa.a.oaa.eng.bom.v01.dbconn.DataBaseConnection;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author oracle_pr1
 */
class BomPortImplDelegate
{
    /**
     *
     * @param fgList
     * @return
     */
    ActionResult makeBomForFgList(PlannedFgList fgList)
    {
        ActionResult actionResult = new ActionResult();
        if (fgList == null || fgList.getPlannedFg() == null || fgList.getPlannedFg().isEmpty())
        {
            actionResult.setMessage("No plan list");
            actionResult.setReturnCode("-1");
            actionResult.setStatus("ERROR");
            return actionResult;
        }

        try
        {
            DataBaseConnection connObj = null;
            try
            {
                List<PlannedFg> plannedFgList = fgList.getPlannedFg();
                connObj = new DataBaseConnection();
                ArrayList<PlanRecord> planRecords = new ArrayList<PlanRecord>();
                PlanQueryUnit queryType = new PlanQueryUnit();
                for (PlannedFg plannedFg : plannedFgList)
                {
                    FinishedGood finishedGood = plannedFg.getFinishedGood();
                    if (finishedGood == null)
                        throw new RuntimeException("finishedGood has not");
                    
                    if (finishedGood.getFigure() == null || finishedGood.getFigure().trim().isEmpty())
                    	throw new RuntimeException("Figure must be filled in order to wsdl");
                    String itemFigure = finishedGood.getFigure().trim();
                    
                    String itemCode = finishedGood.getItemNum() == null
                       ? ""
                       : finishedGood.getItemNum().trim();
                    String itemDesc = finishedGood.getItemName() == null
                       ? ""
                       : finishedGood.getItemName().trim();

                    if (plannedFg.getCompletionDate() == null)
                        throw new RuntimeException("Plan month must be filled by correct date");
                    XMLGregorianCalendar xmlgc = plannedFg.getCompletionDate();
                    GregorianCalendar gCal = xmlgc.toGregorianCalendar();
                    Date planMonth = new Date(gCal.getTimeInMillis());

                    if (plannedFg.getPlan() == null)
                        throw new RuntimeException("Plan must be filled");
                    Plan plan = plannedFg.getPlan();
                    String planName = plan.getPlanName() == null ? "" : plan.getPlanName();
                    String departmentCode = plan.getDepartment() == null ? "" : plan.getDepartment();

                    String odpFlagString = plan.getApprovedByODP();
                    int odpFlag = (odpFlagString == null || odpFlagString.isEmpty())
                                  ? 0
                                  : odpFlagString.trim().equalsIgnoreCase("Y") ? 1 : 0;

                    String spkFlagString = plan.getApprovedBySPK();
                    int spkFlag = (spkFlagString == null || spkFlagString.isEmpty())
                                  ? 0
                                  : spkFlagString.trim().equalsIgnoreCase("Y") ? 1 : 0;

                    planRecords.add(new PlanRecord(itemFigure, itemCode, itemDesc, planMonth, planName,
                        departmentCode, odpFlag, spkFlag)
                    );
                }

                if (!planRecords.isEmpty())
                    queryType.callRaiseEvents(connObj.getDbConn(), planRecords.toArray(new PlanRecord[0]));

                actionResult.setMessage("Business event(s) has(have) successfully raised");
                actionResult.setReturnCode("0");
                actionResult.setStatus("SUCCESS");
            }
            finally
            {
                if (connObj != null)
                    connObj.close();
            }
        }
        catch (SQLException sqlEx)
        {
            actionResult.setMessage("SQL Error message: " + sqlEx.getMessage());
            actionResult.setReturnCode("SQL Error code: " + sqlEx.getErrorCode());
            actionResult.setStatus("ERROR");
        }

        return actionResult;
    }
}
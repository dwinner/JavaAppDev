package ru.aztpa.a.oaa.mfg.wiptransfers.v01;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.ws.Holder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

class WipTransfersHelper
{
   private static GregorianCalendar gCalendar = new GregorianCalendar();
   private static DatatypeFactory datatypeFactory = null;
   private final static String DEFAULT_ORG_CODE = "71";
   private final static String MOVEMENT_VIEW_NAME = "APPS.XXTPA_MFG0027_MOVEMENT_V";

   static
   {
      try
      {
         datatypeFactory = DatatypeFactory.newInstance();
      }
      catch (DatatypeConfigurationException ex)
      {
         throw new RuntimeException(ex);
      }
   }

   void findTransferListByProfile(TransferProfile profile,
                                  Holder<String> status,
                                  Holder<String> returnCode,
                                  Holder<String> message,
                                  Holder<TransferDocs> transferDocs)
   {
      final StringBuilder baseSelect = new StringBuilder("SELECT movement_num, " +
                                                           "transaction_date, " +
                                                           "src_shop_code, " +
                                                           "src_shop_desc, " +
                                                           "src_subinventory_code, " +
                                                           "src_subinventory_desc, " +
                                                           "dst_shop_code, " +
                                                           "dst_shop_desc, " +
                                                           "dst_subinventory_code, " +
                                                           "dst_subinventory_desc, " +
                                                           "last_update_date, " +
                                                           "last_name || ' ' || first_name || ' ' || middle_name AS dispatcher, " +
                                                           "site_code " +
                                                           " FROM " + MOVEMENT_VIEW_NAME + " WHERE organization_code = '" + DEFAULT_ORG_CODE + "' ");

      // XMLGregorianCalendar xmlCal;
      DataBaseConnection dbConn = null;
      PreparedStatement statement = null;
      ResultSet resultSet = null;
      try
      {
         dbConn = new DataBaseConnection();

         if (profile == null)
            throw new RuntimeException("profile is null");

         if (profile.getLastUpdatedAfter() != null)
         {
            gCalendar = profile.getLastUpdatedAfter().toGregorianCalendar();
            String formattedDate = String.format("%d.%d.%d %d:%d:%d", gCalendar.get(Calendar.DAY_OF_MONTH),
                                                 gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.YEAR),
                                                 gCalendar.get(Calendar.HOUR), gCalendar.get(Calendar.MINUTE),
                                                 gCalendar.get(Calendar.SECOND));
            baseSelect.append(String.format(" AND last_update_date >= to_date('%s', 'DD.MM.YYYY HH24:MI:SS') ",
                                            formattedDate));
         }

         if (profile.getLastUpdatedBefore() != null)
         {
            gCalendar = profile.getLastUpdatedBefore().toGregorianCalendar();
            String formattedDate = String.format("%d.%d.%d %d:%d:%d", gCalendar.get(Calendar.DAY_OF_MONTH),
                                                 gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.YEAR),
                                                 gCalendar.get(Calendar.HOUR), gCalendar.get(Calendar.MINUTE),
                                                 gCalendar.get(Calendar.SECOND));
            baseSelect.append(String.format(" AND last_update_date <= to_date('%s', 'DD.MM.YYYY HH24:MI:SS') ",
                                            formattedDate));
         }

         if (profile.getDocNum() != null && !profile.getDocNum().trim().isEmpty())
            baseSelect.append(" AND movement_num LIKE '" + profile.getDocNum().trim() + "' ");

         if (profile.getSourceDepartment() != null && !profile.getSourceDepartment().trim().isEmpty())
            baseSelect.append(" AND src_shop_code LIKE '" + profile.getSourceDepartment().trim() + "' ");

         if (profile.getDestinationDepartment() != null && !profile.getDestinationDepartment().trim().isEmpty())
            baseSelect.append(" AND dst_shop_code LIKE '" + profile.getDestinationDepartment().trim() + "' ");

         if (profile.getDocDateAfter() != null)
         {
            gCalendar = profile.getDocDateAfter().toGregorianCalendar();
            String formattedDate = String.format("%d.%d.%d %d:%d:%d", gCalendar.get(Calendar.DAY_OF_MONTH),
                                                 gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.YEAR),
                                                 gCalendar.get(Calendar.HOUR), gCalendar.get(Calendar.MINUTE),
                                                 gCalendar.get(Calendar.SECOND));
            baseSelect.append(String.format(" AND creation_date >= to_date('%s', 'DD.MM.YYYY HH24:MI:SS') ",
                                            formattedDate));
         }

         if (profile.getDocDateBefore() != null)
         {
            gCalendar = profile.getDocDateBefore().toGregorianCalendar();
            String formattedDate = String.format("%d.%d.%d %d:%d:%d", gCalendar.get(Calendar.DAY_OF_MONTH),
                                                 gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.YEAR),
                                                 gCalendar.get(Calendar.HOUR), gCalendar.get(Calendar.MINUTE),
                                                 gCalendar.get(Calendar.SECOND));
            baseSelect.append(String.format(" AND creation_date <= to_date('%s', 'DD.MM.YYYY HH24:MI:SS') ",
                                            formattedDate));
         }

         baseSelect.append(" ORDER BY TO_NUMBER(movement_num) ");

         statement = dbConn.getDbConn().prepareStatement(baseSelect.toString());
         resultSet = statement.executeQuery();

         transferDocs.value = new TransferDocs();
         transferDocs.value.transferDoc = new ArrayList<TransferDoc>();
         while (resultSet.next())
         {
            TransferDoc transferDoc = new TransferDoc();
            transferDoc.setDocNum(resultSet.getString("movement_num"));
            gCalendar.setTime(resultSet.getDate("transaction_date"));
            transferDoc.setDocDate(datatypeFactory.newXMLGregorianCalendar(gCalendar));

            Department sourceDepartment = new Department();
            sourceDepartment.setDepCode(resultSet.getString("src_shop_code"));
            sourceDepartment.setDepName(resultSet.getString("src_shop_desc"));
            transferDoc.setSource(sourceDepartment);

            Department destinationDepartment = new Department();
            destinationDepartment.setDepCode(resultSet.getString("dst_shop_code"));
            destinationDepartment.setDepName(resultSet.getString("dst_shop_desc"));
            transferDoc.setDestination(destinationDepartment);

            gCalendar.setTime(resultSet.getDate("last_update_date"));
            transferDoc.setLastUpdateDate(datatypeFactory.newXMLGregorianCalendar(gCalendar));

            transferDoc.setDispatcher(resultSet.getString("dispatcher"));
            transferDoc.setSite(resultSet.getString("site_code"));

            transferDoc.setDocLines(selectDocLinesByDocNum(transferDoc.getDocNum(), dbConn.getDbConn()));

            transferDocs.value.transferDoc.add(transferDoc);
         }

         status.value = "SUCCESS";
         returnCode.value = "-1";
         message.value = "Transfers documents have found (of course if found) successfully";
      }
      catch (SQLException sqlEx)
      {
         // throw new RuntimeException(sqlEx);
         status.value = "ERROR";
         returnCode.value = "" + sqlEx.getErrorCode();
         message.value = "Error occurred: " + sqlEx.getLocalizedMessage();
      }
      finally
      {
         try
         {
            if (resultSet != null && !resultSet.isClosed())
               resultSet.close();
            if (statement != null && !statement.isClosed())
               statement.close();
            dbConn.close();
         }
         catch (SQLException e)
         {
            throw new RuntimeException(e);
         }
      }
   }

   private DocLines selectDocLinesByDocNum(String docNum, Connection currentConnection)
     throws SQLException
   {
      String docLinesSelect =
        " select item_num, " +
          "uit_num, " +
          "item_desc, " +
          "transaction_quantity, " +
          "item_cost, " +
          "material, " +
          "mark, " +
          "fusion, " +
          "phisicalProperties, " +
          "chemicalProperties, " +
          "unit_from_blank, " +
          "zag_ovk, " +
          "ves_ovk, " +
          "sertificate, " +
          "razm_det" +
          " FROM " + MOVEMENT_VIEW_NAME + " WHERE movement_num = ? ";

      DocLines docLines = new DocLines();
      docLines.docLine = new ArrayList<DocLine>();

      PreparedStatement statement = null;
      ResultSet resultSet = null;

      try
      {
         statement = currentConnection.prepareStatement(docLinesSelect);
         statement.setString(1, docNum);
         resultSet = statement.executeQuery();
         while (resultSet.next())
         {
            DocLine aDocLine = new DocLine();
            aDocLine.setItemCode(resultSet.getString("item_num"));
            aDocLine.setLegacyItemCode(resultSet.getString("uit_num"));
            aDocLine.setItemName(resultSet.getString("item_desc"));
            aDocLine.setQuantity(resultSet.getDouble("transaction_quantity"));
            aDocLine.setItemCost(resultSet.getDouble("item_cost"));
            aDocLine.setMaterialCode(resultSet.getString("material"));
            aDocLine.setMaterialName("");
            aDocLine.setMaterialSort(resultSet.getString("mark"));
            aDocLine.setMeltCode(resultSet.getString("fusion"));
            aDocLine.setPhysicalProperties(resultSet.getString("phisicalProperties"));
            aDocLine.setChemicalProperties(resultSet.getString("chemicalProperties"));
            aDocLine.setItemsPerBillet(resultSet.getInt("unit_from_blank"));
            aDocLine.setOutsourceItemCode(resultSet.getString("zag_ovk"));
            aDocLine.setOutsourceItemName("");
            aDocLine.setOutsourceItemWeight(resultSet.getDouble("ves_ovk"));
            aDocLine.setFinishedGoodFigure("");
            aDocLine.setCertificate(resultSet.getString("sertificate"));
            aDocLine.setItemSize(resultSet.getString("razm_det"));

            docLines.docLine.add(aDocLine);
         }
      }
      finally
      {
         if (resultSet != null && !resultSet.isClosed())
            resultSet.close();
         if (statement != null && !statement.isClosed())
            statement.close();
      }

      return docLines;
   }
}

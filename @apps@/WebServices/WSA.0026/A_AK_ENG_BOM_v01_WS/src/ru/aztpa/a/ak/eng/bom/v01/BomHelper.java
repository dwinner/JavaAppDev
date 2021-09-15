package ru.aztpa.a.ak.eng.bom.v01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import static ru.aztpa.a.ak.eng.bom.v01.dbconn.ConnectionManager.getConnection;

/**
 * Вспомогательный класс для операций Web-сервиса
 * @version 1.0.2   10.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class BomHelper
{
    private static BomQuery bomQuery = null;
    private Connection conn = null;

    public BomHelper() { assert true; }

    /**
     * Спецификации, удовлетворяющие заданным критериям.
     * @param profile Профиль спецификации.
     * @return Спецификации
     */
    public BomList doFindBomByProfile(BomProfile profile)
    {
        try
        {
            try
            {
                conn = getConnection();
                if (bomQuery == null)
                    bomQuery = new BomQuery();
                return bomQuery.selectByBomProfile(profile);
            }
            catch (IOException ex)
            {
                throw new SQLException(ex);
            }
            finally
            {
                if (conn != null && !conn.isClosed())
                    conn.close();
            }
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
    }

    /**
     * Подсчет спецификаций, удовлетворяющих заданным критериям.
     * @param profile Профиль спецификации.
     * @return Количество спецификаций
     */
    public int doCountBomByProfile(BomProfile profile)
    {
        try
        {
            try
            {
                conn = getConnection();
                if (bomQuery == null)
                    bomQuery = new BomQuery();
                return bomQuery.countBoms(profile);
            }
            catch (IOException ex)
            {
                throw new SQLException(ex);
            }
            finally
            {
                if (conn != null && !conn.isClosed())
                    conn.close();
            }
        }
        catch (SQLException sqlEx)
        {
            throw new RuntimeException(sqlEx);
        }
    }

    /**
     * Тип, интегрирующий логику формирования запросов к справочникам.
     */
    private class BomQuery
    {
        private final static int DEFAULT_INIT_CAPACITY = 0xFF;

        // ----------------------------- Запросы к справочникам ------------------------------------
        private final static String qForT01ByAssemblyFeature =
           "SELECT " +
                "P001, P049, P057, P028, H681 " +
                "FROM t02 " +
                "WHERE P001 LIKE ? " +
                "ORDER BY P049, p057, p028";

        private final static String qForAssemblyName =
           " SELECT " +
           "    nvl(rtrim(P004 || ' ' || P0041 || ' ' || P0042 || ' ' || P0043), '') as assemblyName " +
           " FROM sp_org WHERE P001 = ?";

        private final static String qAllAssemblyFeatures =
           "SELECT P001, COUNT(P001) AS CNT FROM t02 GROUP BY P001";

        private final static String qCountAll = "select count(distinct p001) from t02";

        private final static String qCountByAssemblyFeature =
           "select count(distinct p001) FROM t02 where p001 LIKE ?";

        private final static String qExistsAssemblyFeature =
           "select 1 from t02 where p001 LIKE ?";

        BomQuery()
        {
            checkForConnection();
        }

        /**
         * Проверка наличия подключения.
         */
        private void checkForConnection()
        {
            try
            {
                if (conn == null || conn.isClosed())
                    throw new SQLException("Connection is lost");
            }
            catch (SQLException sqlEx)
            {
                throw new RuntimeException(sqlEx);
            }
        }

        /**
         * Подсчет количества спецификаций.
         * @param profile Профиль Bom
         * @return Количество спецификаций (либо всех, либо по конкретному разделу)
         * @throws SQLException
         */
        private int countBoms(BomProfile profile) throws SQLException
        {
            assert profile != null;
            String assemblyFeature = profile.getAssemblyFigure();
            boolean emptyFeature = (assemblyFeature == null) ? true : false;
            if (assemblyFeature != null)
                emptyFeature = (assemblyFeature.trim().isEmpty()) ? true : false;
            if (!emptyFeature)
                assemblyFeature = assemblyFeature.trim();
            PreparedStatement pStat = null;
            ResultSet rs = null;
            try
            {
                pStat = emptyFeature
                        ? conn.prepareStatement(qCountAll)
                        : conn.prepareStatement(qCountByAssemblyFeature);
                if (!emptyFeature)
                    pStat.setString(1, assemblyFeature);
                rs = pStat.executeQuery();
                int bomCount = (rs.next()) ? rs.getInt(1) : 0;
                return bomCount;
            }
            catch (SQLException sqlEx)
            {
                throw sqlEx;
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
                if (rs != null && !rs.isClosed())
                    rs.close();
            }
        }

        /**
         * Выбор спецификаций (BOM) по профилю (BOM)
         * @param profile BOM-профиль
         * @return Список спецификаций (BOM)
         * @throws SQLException
         */
        private BomList selectByBomProfile(BomProfile profile) throws SQLException
        {
            assert profile != null;
            String assemblyFeature = profile.getAssemblyFigure();
            BomList bomList = new BomList();
            bomList.bom = new ArrayList<Bom>(DEFAULT_INIT_CAPACITY);

            if (assemblyFeature == null || assemblyFeature.trim().isEmpty())
                bomList.getBom().addAll(retrieveBoms());
            else
            {
                assemblyFeature = assemblyFeature.trim();
                if (existsAssemblyFeature(assemblyFeature))
                {
                    bomList.getBom().add(retrieveBom(assemblyFeature));
                }
            }

            return bomList;
        }

        /**
         * Проверка, существует ли переданное обозначение изделия.
         * @param assemblyFeature Обозначение изделия
         * @return true, если такое изделие есть, false в противном случае
         * @throws SQLException
         */
        private boolean existsAssemblyFeature(String assemblyFeature) throws SQLException
        {
            PreparedStatement pStat = null;
            ResultSet rs = null;
            try
            {
                pStat = conn.prepareStatement(qExistsAssemblyFeature);
                pStat.setString(1, assemblyFeature);
                rs = pStat.executeQuery();
                return rs.next();
            }
            catch (SQLException sqlEx)
            {
                throw sqlEx;
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
                if (rs != null && !rs.isClosed())
                    rs.close();
            }
        }

        /**
         * Извлечение списка всех спецификаций
         * @return Список спецификаций.
         * @throws SQLException
         */
        private List<? extends Bom> retrieveBoms() throws SQLException
        {
            String[] assemblyFeatures = retrieveAssemblyFeatures();
            List<Bom> boms = new ArrayList<Bom>(DEFAULT_INIT_CAPACITY * 4);
            for (String assemblyFeature : assemblyFeatures)
                boms.add(retrieveBom(assemblyFeature));

            return boms;
        }

        /**
         * Извлечение объекта спецификации по обозначению изделия.
         * @param assemblyFeature Обозначение изделия
         * @return Объект BOM
         * @throws java.sql.SQLException
         */
        private Bom retrieveBom(String assemblyFeature) throws SQLException
        {
            Bom bom = new Bom();
            bom.setAssemblyFigure(assemblyFeature);
            bom.setAssemblyName(retrieveAssemblyNameByFigure(assemblyFeature));
            BomSections bomSections = new BomSections();
            bomSections.section = new ArrayList<BomSection>(DEFAULT_INIT_CAPACITY);
            bomSections.getSection().addAll(retrieveBomSections(assemblyFeature));
            bom.setSections(bomSections);

            return bom;
        }

        /**
         * Извлечение списка разделов для спецификации
         * @param assemblyFeature Раздел спецификации.
         * @return Список разделов.
         * @throws SQLException
         */
        private List<? extends BomSection> retrieveBomSections(String assemblyFeature)
           throws SQLException
        {
            List<ProductRecord> productRecords = retrieveProductRecords(assemblyFeature);
            List<BomSection> bomSections = new ArrayList<BomSection>(DEFAULT_INIT_CAPACITY);
            Integer[] sectionOrders = obtainSectionOrders(productRecords);
            for (int i : sectionOrders)
            {
                BomSection bomSection = new BomSection();
                bomSection.setSectionOrder(i);
                bomSection.setSectionName(ProductRecord.obtainSectionName(i));
                BomLines bomLines = new BomLines();
                bomLines.line = new ArrayList<BomLine>(DEFAULT_INIT_CAPACITY / 0xF);
                bomLines.getLine().addAll(retrieveBomLines(i, productRecords));
                bomSection.setLines(bomLines);
                bomSections.add(bomSection);
            }
            return bomSections;
        }

        /**
         * Извлечение списка всех строк спецификации по номеру раздела
         * @param i              Номер раздела
         * @param productRecords Список записей ProductRecord
         * @return Список строк спецификации.
         * @throws SQLException
         */
        private List<? extends BomLine> retrieveBomLines(int i, List<ProductRecord> productRecords)
           throws SQLException
        {
            List<BomLine> bomLines = new ArrayList<BomLine>(DEFAULT_INIT_CAPACITY / 0xF);
            for (ProductRecord productRecord : productRecords)
            {
                if (productRecord.getSectionName() == i)
                {
                    BomLine bomLine = new BomLine();
                    bomLine.setLineNum(productRecord.getLineNum());
                    bomLine.setCompFigure(productRecord.getCompFigure());
                    bomLine.setCompName(findCompName(productRecord));
                    bomLine.setQuantity(productRecord.getQuantity());
                    bomLines.add(bomLine);
                }
            }
            return bomLines;
        }

        /**
         * Поиск наименования конкретного компонента по справочникам.
         * @param productRecord Запись об изделии
         * @return Наименование компонента.
         * @throws SQLException
         */
        private String findCompName(ProductRecord productRecord) throws SQLException
        {
            String tableName = ProductRecord.obtainTableName(productRecord.getSectionName());
            CompNameFinder compNameFinder = new CompNameFinder(productRecord);
            if (tableName.equals(ProductRecord.ASSEMBLY_UNITS))
                return compNameFinder.findInAssemblyUnits();
            else if (tableName.equals(ProductRecord.STANDARD_PRODUCTS))
                return compNameFinder.findInStandartProducts();
            else if (tableName.equals(ProductRecord.OTHER_PRODUCTS))
                return compNameFinder.findInOtherProducts();
            else if (tableName.equals(ProductRecord.MATERIALS))
                return compNameFinder.findInMaterials();
            else // FIXME: Подходящего справочника не найдено, вероятно поиск существенно усложнится
                return ProductRecord.FAIL_COMP_NAME;
        }

        /**
         * Тип, интегрирующий логику поиска имени компонента (compName) в справочниках.
         */
        private class CompNameFinder
        {
            private ProductRecord record;

            // -------------- Запрос к справочнику сборочных единиц и/или деталей ------------------
            private static final String qForAssemblyUnits =
               "select " +
               "    nvl(rtrim(p004||' '||p0041||' '||p0042||' '||p0043), '') as compName " +
               " from sp_org where p001 = ?";
            // -------------- Запросы к справочнику стандартных изделий ----------------------------
            private static final String qForStandartProducts1 =
               "select naimp from sp_gost where kvdet = substr(?, 1, 2)";
            private static final String qForStandartProducts2 =
               "select trim(p0047 || ' ' || p00471) as compName2 from sp_st where p001 = ?";
            // -------------- Запрос к справочнику прочих изделий ----------------------------------
            private static final String qForOtherProducts =
               " select " +
               "    nvl(rtrim(p002 || ' ' || p0021 || ' ' || p0022), '') as compName from sp_pr " +
               " where substr(p001, 2, 4) = substr(?, 1, 4)";
            // -------------- Запрос к справочнику материалов --------------------------------------
            private static final String qForMaterials =
               " select " +
               "    nvl(rtrim(p004 || ' ' || p0042 || ' ' || p0043), '') as compName " +
               " from sp_mat where p001 = ?";

            /**
             * Инициализатор записи ProductRecor
             * @param productRecord Запись о составе изделия.
             */
            private CompNameFinder(ProductRecord productRecord) { record = productRecord; }

            /**
             * Поиск в справочнике сборочных единиц и/или деталей. (sp_org)
             * @return compName - Имя компонента.
             * @throws SQLException
             */
            private String findInAssemblyUnits() throws SQLException
            {
                PreparedStatement pStat = null;
                ResultSet rs = null;
                try
                {
                    pStat = conn.prepareStatement(qForAssemblyUnits);
                    pStat.setString(1, record.getCompFigure());
                    rs = pStat.executeQuery();
                    String compName = (rs.next()) ? rs.getString("compName") : "";
                    return compName;
                }
                catch (SQLException sqlEx)
                {
                    throw sqlEx;
                }
                finally
                {
                    if (pStat != null && !pStat.isClosed())
                        pStat.close();
                    if (rs != null && !rs.isClosed())
                        rs.close();
                }
            }

            /**
             * Поиск в справочнике стандартных изделий. (sp_st)
             * @return Имя компонента.
             * @throws SQLException
             */
            private String findInStandartProducts() throws SQLException
            {
                final char SPACE = ' ';
                PreparedStatement pStat = null;
                ResultSet rs = null;
                String compName = "";
                try
                {
                    pStat = conn.prepareStatement(qForStandartProducts1);
                    pStat.setString(1, record.getCompFigure());
                    rs = pStat.executeQuery();
                    compName += ((!rs.next()) ? "" : rs.getString("naimp") + SPACE);
                    pStat.clearParameters();
                    pStat = conn.prepareStatement(qForStandartProducts2);
                    pStat.setString(1, record.getCompFigure());
                    rs = pStat.executeQuery();
                    compName += (rs.next()) ? rs.getString("compName2") : "";
                    return compName;
                }
                catch (SQLException sqlEx)
                {
                    throw sqlEx;
                }
                finally
                {
                    if (pStat != null && !pStat.isClosed())
                        pStat.close();
                    if (rs != null && !rs.isClosed())
                        rs.close();
                }
            }

            /**
             * Поиск в справочнике прочих изделий. (sp_pr)
             * @return compName - Имя компонента.
             * @throws SQLException
             */
            private String findInOtherProducts() throws SQLException
            {
                PreparedStatement pStat = null;
                ResultSet rs = null;
                try
                {
                    pStat = conn.prepareStatement(qForOtherProducts);
                    pStat.setString(1, record.getAssemblyFigure());
                    rs = pStat.executeQuery();
                    String compName = (rs.next()) ? rs.getString("compName") : "";
                    return compName;
                }
                catch (SQLException sqlEx)
                {
                    throw sqlEx;
                }
                finally
                {
                    if (pStat != null && !pStat.isClosed())
                        pStat.close();
                    if (rs != null && !rs.isClosed())
                        rs.close();
                }
            }

            /**
             * Поиск в справочнике материалов.
             * @return compName - Имя компонента.
             * @throws SQLException
             */
            private String findInMaterials() throws SQLException
            {
                PreparedStatement pStat = null;
                ResultSet rs = null;
                try
                {
                    pStat = conn.prepareStatement(qForMaterials);
                    pStat.setString(1, record.getCompFigure());
                    rs = pStat.executeQuery();
                    String compName = (rs.next()) ? rs.getString("compName") : "";
                    return compName;
                }
                catch (SQLException sqlEx)
                {
                    throw sqlEx;
                }
                finally
                {
                    if (pStat != null && !pStat.isClosed())
                        pStat.close();
                    if (rs != null && !rs.isClosed())
                        rs.close();
                }
            }
        }

        /**
         * Получение списка уникальных номеров разделов спецификаций.
         * @param productRecords Список записей компонентов об одном изделии.
         * @return Массив чисел с номерами разделов.
         */
        private Integer[] obtainSectionOrders(List<ProductRecord> productRecords)
        {
            SortedSet<Integer> sectionSet = new TreeSet<Integer>();
            for (ProductRecord productRecord : productRecords)
                sectionSet.add(productRecord.getSectionName());
            return sectionSet.toArray(new Integer[0]);
        }

        /**
         * Извлечение списка записей о составе одного изделия.
         * @param assemblyFeature Раздел спецификации.
         * @return Список записей компонентов об одном изделии.
         * @throws SQLException
         */
        private List<ProductRecord> retrieveProductRecords(String assemblyFeature)
           throws SQLException
        {
            List<ProductRecord> productRecords = new ArrayList<ProductRecord>(DEFAULT_INIT_CAPACITY / 0x4);
            PreparedStatement pStat = null;
            ResultSet rs = null;
            try
            {
                pStat = conn.prepareStatement(qForT01ByAssemblyFeature);
                pStat.setString(1, assemblyFeature);
                rs = pStat.executeQuery();
                while (rs.next())
                {
                    String assemblyFigure = rs.getString("P001");
                    assemblyFigure = assemblyFigure == null ? "" : assemblyFigure.trim();

                    int sectionName;
                    String sectionNameString = rs.getString("P049");
                    if (sectionNameString == null || sectionNameString.trim().isEmpty())
                        sectionName = -1;
                    else
                    {
                        try
                        {
                            sectionName = Integer.parseInt(sectionNameString.trim());
                        }
                        catch (NumberFormatException nfEx)
                        {
                            sectionName = -1;
                        }
                    }

                    String lineNumberString = rs.getString("P057");
                    int lineNumber;
                    if (lineNumberString == null || lineNumberString.trim().isEmpty())
                        lineNumber = ProductRecord.FAIL_LINE_NUM;
                    else
                    {
                        try
                        {
                            lineNumber = Integer.parseInt(lineNumberString.trim());
                        }
                        catch (NumberFormatException nfEx)
                        {
                            lineNumber = ProductRecord.FAIL_LINE_NUM;
                        }
                    }

                    String compFigure = rs.getString("P028");
                    compFigure = compFigure == null || compFigure.trim().isEmpty()
                       ? ""
                       : compFigure.trim();

                    double quantity;
                    String quantityString = rs.getString("H681");
                    if (quantityString == null || quantityString.trim().isEmpty())
                        quantity = 0.0;
                    else
                    {
                        try
                        {
                            quantity = Double.parseDouble(quantityString.trim());
                        }
                        catch (NumberFormatException nfEx)
                        {
                            quantity = 0.0;
                        }
                    }

                    ProductRecord productRecord =
                       new ProductRecord(assemblyFigure, sectionName, lineNumber, compFigure, quantity);
                    productRecords.add(productRecord);
                }

                return productRecords;
            }
            catch (SQLException sqlEx)
            {
                throw sqlEx;
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
                if (rs != null && !rs.isClosed())
                    rs.close();
            }
        }

        /**
         * Извлечение имени изделия по обозначению.
         * @param assemblyFigure Обозначение изделия.
         * @return Обозначение изделия.
         * @throws SQLException
         */
        private String retrieveAssemblyNameByFigure(String assemblyFigure) throws SQLException
        {
            PreparedStatement pStat = null;
            ResultSet rs = null;
            String assemblyName = "";
            try
            {
                pStat = conn.prepareStatement(qForAssemblyName);
                pStat.setString(1, assemblyFigure);
                rs = pStat.executeQuery();
                if (rs.next())
                {
                    assemblyName = rs.getString("assemblyName");
                    assemblyName = assemblyName == null ? "" : assemblyName.trim();
                }
                return assemblyName;
            }
            catch (SQLException sqlEx)
            {
                throw sqlEx;
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
                if (rs != null && !rs.isClosed())
                    rs.close();
            }
        }

        /**
         * Извлечение уникального множества обозначений изделий.
         * @return Множество всех обозначений.
         * @throws SQLException
         */
        private String[] retrieveAssemblyFeatures() throws SQLException
        {
            List<String> asmFeatures = new ArrayList<String>(DEFAULT_INIT_CAPACITY);
            PreparedStatement pStat = null;
            ResultSet rs = null;
            try
            {
                pStat = conn.prepareStatement(qAllAssemblyFeatures);
                rs = pStat.executeQuery();
                while (rs.next())
                {
                    String assemblyFeature = rs.getString("P001");
                    if (assemblyFeature == null || assemblyFeature.trim().isEmpty())
                        continue;
                    asmFeatures.add(assemblyFeature.trim());
                }
                return asmFeatures.toArray(new String[0]);
            }
            catch (SQLException sqlEx)
            {
                throw sqlEx;
            }
            finally
            {
                if (pStat != null && !pStat.isClosed())
                    pStat.close();
                if (rs != null && !rs.isClosed())
                    rs.close();
            }
        }
    }
}

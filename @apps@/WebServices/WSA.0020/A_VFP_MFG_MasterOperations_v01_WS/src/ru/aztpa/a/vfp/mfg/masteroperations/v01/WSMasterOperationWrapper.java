package ru.aztpa.a.vfp.mfg.masteroperations.v01;

import ru.aztpa.a.vfp.mfg.masteroperations.NdetopErrorEnum;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static ru.aztpa.a.vfp.mfg.masteroperations.utils.OperationValidator.*;

/**
 * �����-Wrapper �������� Web-�������.
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0
 */
class WSMasterOperationWrapper
{
    private DataSource dbSource;
    private String contextName;
    private final static String DEFAULT_CONTEXT_NAME = "OTIZ_DBF";
    private final static Logger logger =
        Logger.getLogger("ru.aztpa.a.vfp.mfg.masteroperations.v01");

    WSMasterOperationWrapper()
    {
        this(DEFAULT_CONTEXT_NAME);
    }

    WSMasterOperationWrapper(String aContextName)
    {
        assert aContextName == null ? false : true;
        contextName = aContextName;
        try
        {
            Context ctx = new InitialContext();
            dbSource = (DataSource) ctx.lookup(contextName);
        }
        catch (NamingException ex)
        {
            logger.log(Level.SEVERE, "DataSource lookup failed", ex);
        }
    }

    /**
     * Wrapper ��� �������� �������� �����������.
     * @param in
     * @return SOAP-Response ����������������� � ������� Result
     */
    Result doValidate(String in) throws SQLException
    {
        // ������������ ��������� ������
        if (dbSource == null)
            throw new SQLException("No data source");
        Connection conn = dbSource.getConnection();
        if (conn == null)
            throw new SQLException("No connection");
        conn.setAutoCommit(false);

        // ������� �������������� �������
        Result theResult = new Result();
        Errors allErrors = new Errors();
        boolean errorsExist = false;
        int errorCounter = 0;
        int gcCyclicCallerCounter = 0x1000;

        try
        {
            Statement stat = conn.createStatement();
            ResultSet resultSet = stat.executeQuery("SELECT NDETOP FROM NDETOP");

            // ��������� ��� �������� ������������ ��������.
            Set<String> operationSet = new HashSet<String>(0x3E80);

            // ����� ������
            EnumMap<NdetopErrorEnum, Error> errorMap =
                new EnumMap<NdetopErrorEnum, Error>(NdetopErrorEnum.class);

            while (resultSet.next())
            {	// ������ �� ���� ��������� �����������.
                String currentOperation = resultSet.getString(1);
                if (currentOperation == null || currentOperation.length() == 0)
                    continue;

                for (NdetopErrorEnum errorType : NdetopErrorEnum.values())
                {	// ������ �� ���� ��������� ������� ������� ��������
                    switch (errorType)
                    {
                        case ERROR_101:	// ������� � ������ ������������ ��������
                            if (checkForBeginningGaps(currentOperation))
                                continue;
                            break;
                        case ERROR_102:	// ������� � ����� ������������ ��������
                            if (checkForEndingGaps(currentOperation))
                                continue;
                            break;
                        case ERROR_103:	// ��������� ������� � ������������ �������� (��� � ������)
                            if (checkForTwoOrMoreSpaces(currentOperation))
                                continue;
                            break;
                        case ERROR_104:	// ������ ������� ���� ����������� � ������������ ��������
                            if (checkForReplacementRuLetters(currentOperation))
                                continue;
                            break;
                        case ERROR_105:	// ������������ �������� �� ���������
                            if (checkForUniqueOperation(currentOperation, operationSet))
                                continue;
                            break;
                        default:    // ������� �������������� ������
                            EnumConstantNotPresentException notPresentEx =
                                new EnumConstantNotPresentException(
                                    errorType.getDeclaringClass(),
                                    errorType.name()
                                );
                            logger.log(Level.SEVERE, notPresentEx.toString(), notPresentEx);
                            throw new RuntimeException(notPresentEx);
                    }

                    errorsExist = true;
                    if (++errorCounter % gcCyclicCallerCounter == 0)
                        System.gc();

                    if (!errorMap.containsKey(errorType))
                    {
                        Error theError = new Error();
                        theError.setErrorCode(errorType.getErrorCode());
                        theError.setErrorDescription(errorType.getInfo());
                        errorMap.put(errorType, theError);
                    }
                    Error theError = errorMap.get(errorType);
                    if (theError.getWrongOperations() == null)
                    {
                        WrongOperationList opList = new WrongOperationList();
                        theError.setWrongOperations(opList);
                    }
                    theError.getWrongOperations().getWrongOperation().add(currentOperation);
                }
            }

            // ��������� ���� ������ � ������� �����������
            List<Error> errorListView = new ArrayList<Error>(errorMap.values());
            Collections.sort(errorListView, new Comparator<Error>()
            {
                @Override
                public int compare(Error err1, Error err2)
                {   // FIXME: Overflow
                    return (err1.getErrorCode() - err2.getErrorCode());
                }
            });
            allErrors.error = errorListView;

            if (errorsExist)
            {
                theResult.errors = allErrors;
                theResult.resultCode = 1;
                theResult.resultDescription = "Errors are detected: " + errorCounter + " total errors";
            }
            else
            {
                theResult.resultCode = 0;
                theResult.resultDescription = "Errors are not detected";
            }
        }
        finally
        {
            if (conn != null && !conn.isClosed())
                conn.close();
        }

        return theResult;
    }
}

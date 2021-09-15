package ru.aztpa.a.vfp.mfg.masteroperations.utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��������������� ����� ��� �������� �������� �����������
 *
 * @author jdeveloper@aztpa.ru
 * @version 1.1.0
 */
public class OperationValidator
{
    private final static String moreThanTwoSpaceString = "\\p{Space}{2,}";
    private final static Pattern moreThanTwoSpacePattern = Pattern.compile(moreThanTwoSpaceString);

    private final static String asciiAndCyrillicRegExp = "([\u0410-\u044F]+)|(\\p{Alpha}+)";
    private final static Pattern asciiAndCyrillic = Pattern.compile(asciiAndCyrillicRegExp);

    private final static String delimetersRegExp = "(\\p{Punct}+)|(\\p{Space}+)|[\\d]+";
    private final static Pattern delimeters = Pattern.compile(delimetersRegExp);

    private OperationValidator()
    {
        assert false;
    }

    /**
     * �������� ������� �������� � ������ ������ (Error �101).
     *
     * @param aString ������ ��� ��������.
     * @return true ���� �������� �� ���������, false � ��������� ������.
     */
    public static boolean checkForBeginningGaps(String aString)
    {
        char first = aString.charAt(0);
        return Character.isWhitespace(first) ? false : true;
    }

    /**
     * �������� ������� �������� � ����� ������ (Error �102).
     *
     * @param aString ������ ��� ��������.
     * @return true ���� �������� �� ���������, false � ��������� ������.
     */
    public static boolean checkForEndingGaps(String aString)
    {
        char last = aString.charAt(aString.length() - 1);
        return Character.isWhitespace(last) ? false : true;
    }

    /**
     * �������� ������� ���� � ����� ���������� �������� � �������� ������ (��� ����� ��������� �
     * ��������) (Error �103).
     *
     * @param aString ������ ��� ��������..
     * @return true, ���� ����� ������ ����������� ������� �� ���������, false � ��������� ������.
     */
    public static boolean checkForTwoOrMoreSpaces(String aString)
    {
        String trimString = aString.trim();
        Matcher theMatcher = moreThanTwoSpacePattern.matcher(trimString);
        return theMatcher.find() ? false : true;
    }

    /**
     * ��������, ���� �� ������ ������� ���� ����������� � ������ ��������� �����, ����������� ��
     * ���������� ��������. (Error �104).
     *
     * @param aString ������ ��� ��������.
     * @return true, ���� ���������� �������� ASCII � ���������� ������� �� ��������� �� ���������,
     *         false � ��������� ������.
     */
    public static boolean checkForReplacementRuLetters(String aString)
    {
        if (aString == null || aString.trim().isEmpty())
            return true;
        aString = aString.trim();

        String[] words = delimeters.split(aString);
        for (String aWord : words)
        {   // ������ �� ����������� ������
            Matcher theMatcher = asciiAndCyrillic.matcher(aWord);
            boolean isAsciiWasFound = false;
            boolean isCyrillicWasFound = false;
            while (theMatcher.find())
            {
                int cyrStart = theMatcher.start(1);
                int cyrEnd = theMatcher.end(1);
                if (cyrEnd > cyrStart)
                    isCyrillicWasFound = true;
                int asciiStart = theMatcher.start(2);
                int asciiEnd = theMatcher.end(2);
                if (asciiEnd > asciiStart)
                    isAsciiWasFound = true;
                if (isAsciiWasFound && isCyrillicWasFound)  // ������������ �����
                    return false;
            }
        }

        return true;
    }

    /**
     * �������� �������� �� ������������ (Error �105)
     *
     * @param aString ������ ��� ��������.
     * @param aSet    (����� Hash) ��������� ���������� ��������.
     * @return true, ���� �������� ���������, false � ��������� ������.
     */
    public static boolean checkForUniqueOperation(String aString, Set<String> aSet)
    {
        // ������ ������ ���������� ������� �� ������������.
        String upperOperation = aString.toUpperCase().trim();
        StringBuilder theBuilder = new StringBuilder(0xFF / 2);
        for (String str : upperOperation.split("\\p{Space}+"))
        {
            theBuilder.append(str);
            theBuilder.append(" ");
        }
        theBuilder.deleteCharAt(theBuilder.length() - 1);

        String readyOperation = theBuilder.toString();
        boolean isUnique = aSet.add(readyOperation);
        return isUnique;
    }
}

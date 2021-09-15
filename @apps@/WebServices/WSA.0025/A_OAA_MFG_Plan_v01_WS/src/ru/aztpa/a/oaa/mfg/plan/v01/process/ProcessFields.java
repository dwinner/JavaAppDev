package ru.aztpa.a.oaa.mfg.plan.v01.process;

/**
 * Utility-класс для обработки ввода перед передачей СУБД
 * @version 1.0.0 04.05.2012
 * @author jdeveloper@aztpa.ru
 */
public class ProcessFields
{
    private ProcessFields() { assert false; }

    /**
     * Обработка поля символьного типа для передачи СУБД с контролем длины полей.
     * @param field     Поле для обработки
     * @param trimLimit Предельная длина поля в СУБД
     * @return Строка, готовая для передачи СУБД
     */
    public static String processStringField(String field, int trimLimit)
    {
        if (field == null)
            return "NULL";
        String lField = field.trim();
        if (lField.length() > trimLimit)
            lField = lField.substring(0, trimLimit);
        return "'" + lField + "'";
    }

    /**
     * Обработка поля символьного типа для передачи СУБД без обработки длины полей.
     * @param field Поле для обработки
     * @return Строка, готовая для передачи СУБД
     */
    public static String processStringField(String field)
    {
        if (field == null)
            return "NULL";
        return "'" + field + "'";
    }

    /**
     * Обработка строки и преобразование в целое значение
     * @param field Поле для обработки
     * @return Число типа int, готовое для передачи СУБД
     */
    public static int processStringToInteger(String field)
    {
        if (field == null)
        {
            NullPointerException npEx = new NullPointerException("This case is wrong");
            throw new RuntimeException(npEx);
        }
        if (0 != (field = field.trim()).length())
        {
            int intValue;
            try
            {
                intValue = Integer.parseInt(field);
            }
            catch (NumberFormatException nfEx)
            {
                throw new RuntimeException(nfEx);
            }
            return intValue;
        }
        throw new RuntimeException("Value to parse must be not empty");
    }
}

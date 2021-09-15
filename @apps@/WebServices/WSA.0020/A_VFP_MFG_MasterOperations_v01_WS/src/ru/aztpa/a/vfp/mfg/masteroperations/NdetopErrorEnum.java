package ru.aztpa.a.vfp.mfg.masteroperations;

import java.util.HashMap;
import java.util.Map;

/**
 * Всмопогательный класс с картой ассоциаций {Номер ошибки} => {Описание ошибки}
 * Описание представлено в "сыром" unicode (use native2ascii)
 * @author jdeveloper@aztpa.ru
 *
 */
class ErrorStrings
{
    static final Map<Integer, String> unicodeErrorStrings = new HashMap<Integer, String>(5);

    static
    {
        unicodeErrorStrings.put(101,
            "\u041f\u0440\u043e\u0431\u0435\u043b\u044b \u0432 \u043d\u0430\u0447\u0430\u043b\u0435 \u043d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438");
        unicodeErrorStrings.put(102,
            "\u041f\u0440\u043e\u0431\u0435\u043b\u044b \u0432 \u043a\u043e\u043d\u0446\u0435 \u043d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u044f \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438");
        unicodeErrorStrings.put(103,
            "\u0421\u0434\u0432\u043e\u0435\u043d\u043d\u044b\u0435 \u043f\u0440\u043e\u0431\u0435\u043b\u044b \u0432 \u043d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438");
        unicodeErrorStrings.put(104,
            "\u0417\u0430\u043c\u0435\u043d\u0430 \u0440\u0443\u0441\u0441\u043a\u0438\u0445 \u0431\u0443\u043a\u0432 \u0430\u043d\u0433\u043b\u0438\u0439\u0441\u043a\u0438\u043c\u0438 \u0432 \u043d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438");
        unicodeErrorStrings.put(105,
            "\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u043d\u0435 \u0443\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u043e");
    }
}

/**
 * Перечисление возможных кодов и описаний ошибок справочника Ndetop
 * <p/>
 * @author jdeveloper@aztpa.ru
 * @version 1.0.0
 */
public enum NdetopErrorEnum
{
    ERROR_101(101),
    ERROR_102(102),
    ERROR_103(103),
    ERROR_104(104),
    ERROR_105(105);
    
    private int errorCode;

    public int getErrorCode()
    {
        return errorCode;
    }

    private NdetopErrorEnum(int anErrorCode)
    {
        errorCode = anErrorCode;
    }

    public String getInfo()
    {
        return ErrorStrings.unicodeErrorStrings.get(Integer.parseInt(toString()));
    }

    @Override
    public String toString()
    {
        String errorId = name();
        String lErrorCode = errorId.substring(errorId.indexOf('_') + 1);
        return lErrorCode;
    }
}

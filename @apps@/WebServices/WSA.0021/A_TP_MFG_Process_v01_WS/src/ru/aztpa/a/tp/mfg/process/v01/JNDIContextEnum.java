package ru.aztpa.a.tp.mfg.process.v01;

/**
 * Перечисление имен для зарегистрированных контекстов
 * <p/>
 * @author jdeveloper@aztpa.ru 15.03.2012
 * @version 1.0
 */
public enum JNDIContextEnum
{
    ZAGTP_BAS("ZAGTP_BAS"), // Список технологических процессов
    ZAGTP_OPE("ZAGTP_OPE"), // Список операций для каждого процесса (процесс <=> dbf-файл)
    ZAGTP_PER("ZAGTP_PER"); // Список переходов для каждого процесса (процесс <=> dbf-файл)

    private JNDIContextEnum(String contextName)
    {
        this.contextName = contextName;
    }
    private String contextName;
}

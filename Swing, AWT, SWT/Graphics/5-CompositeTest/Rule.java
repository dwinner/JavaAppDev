import java.awt.AlphaComposite;

/**
 * Класс, описывающий правило Портера-Даффа.
 */
public class Rule
{
    private String name;
    private String porterDuff1;
    private String porterDuff2;

    /**
     * Создание правила Портера-Даффа.
     * <p/>
     * @param name        Имя правила
     * @param porterDuff1 Первая строка квадрата Портера-Даффа
     * @param porterDuff2 Вторая строка квадрата Портера-Даффа
     */
    public Rule(String name, String porterDuff1, String porterDuff2)
    {
        this.name = name;
        this.porterDuff1 = porterDuff1;
        this.porterDuff2 = porterDuff2;
    }

    /**
     * Метод, получающий объяснение поведения данного правила.
     * <p/>
     * @return Объяснение
     */
    public String getExplanation()
    {
        StringBuilder r = new StringBuilder("Source ");
        switch (porterDuff2)
        {
            case "  ":
                r.append("clears");
                break;
            case " S":
                r.append("overwrites");
                break;
            case "DS":
                r.append("blends with");
                break;
            case " D":
                r.append("alpha modifies");
                break;
            case "D ":
                r.append("alpha complement modifies");
                break;
            case "DD":
                r.append("does not affect");
                break;
        }

        r.append(" destination");
        if (porterDuff1.equals(" S"))
        {
            r.append(" and overwrites empty pixels");
        }
        r.append(".");
        return r.toString();
    }

    @Override
    public String toString()
    {
        return name;
    }

    /**
     * Метод, получающий значение данного правила в классе AlphaComposite.
     * <p/>
     * @return Константа класса AlphaComposite или -1, если подходящей константы нет.
     */
    public int getValue()
    {
        try
        {
            try
            {
                return (int) AlphaComposite.class.getField(name).get(null);
            }
            catch (IllegalArgumentException | IllegalAccessException ex)
            {
                return -1;
            }
        }
        catch (NoSuchFieldException | SecurityException ex)
        {
            return -1;
        }
    }
}

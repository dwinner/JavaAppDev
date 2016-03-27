/**
 * Информация, необходимая для вычисления пенсионного плана.
 */
public class RetireInfo
{
    private double savings;
    private double contrib;
    private double income;
    private int currentAge;
    private int retireAge;
    private int deathAge;
    private double inflationPercent;
    private double investPercent;
    private int age;
    private double balance;

    /**
     * Получение ежегодного состояния счета.
     * <p/>
     * @param year Год, для которого вычисляется состояние счета
     * <p/>
     * @return Сумма, доступная (или требуемая) в этом году
     */
    public double getBalance(int year)
    {
        if (year < currentAge)
        {
            return 0;
        }
        else if (year == currentAge)
        {
            age = year;
            balance = savings;
            return balance;
        }
        else if (year == age)
        {
            return balance;
        }
        if (year != age + 1)
        {
            getBalance(year - 1);
        }
        age = year;
        if (age < retireAge)
        {
            balance += contrib;
        }
        else
        {
            balance -= income;
        }
        balance *= (1 + (investPercent - inflationPercent));

        return balance;
    }

    /**
     * Получение ежегодной суммы отчислений на пенсионный счет.
     * <p/>
     * @return Сумма отчислений
     */
    public double getContrib()
    {
        return contrib;
    }

    /**
     * Установка ежегодной суммы отчислений на пенсионный счет.
     * <p/>
     * @param newValue Сумма отчислений
     */
    public void setContrib(double newValue)
    {
        contrib = newValue;
    }

    /**
     * Получение текущего возраста.
     * <p/>
     * @return Текущий возраст
     */
    public int getCurrentAge()
    {
        return currentAge;
    }

    /**
     * Установка текущего возраста
     * <p/>
     * @param newValue Текущий возраст
     */
    public void setCurrentAge(int newValue)
    {
        currentAge = newValue;
    }

    /**
     * Получение предполагаемой продолжительности жизни.
     * <p/>
     * @return Возраст
     */
    public int getDeathAge()
    {
        return deathAge;
    }

    /**
     * Установка предполагаемой продолжительности жизни
     * <p/>
     * @param newValue Возраст
     */
    public void setDeathAge(int newValue)
    {
        deathAge = newValue;
    }

    /**
     * Получение информации о ежегодном доходе
     * <p/>
     * @return Ежегодный доход
     */
    public double getIncome()
    {
        return income;
    }

    /**
     * Установка величины ежегодного дохода.
     * <p/>
     * @param newValue
     */
    public void setIncome(double newValue)
    {
        income = newValue;
    }

    /**
     * Получение предполагаемой величины инфляции.
     * <p/>
     * @return Инфляция в процентах
     */
    public double getInflationPercent()
    {
        return inflationPercent;
    }

    /**
     * Установка предполагаемой величины инфляции
     * <p/>
     * @param newValue Инфляция в процентах
     */
    public void setInflationPercent(double newValue)
    {
        inflationPercent = newValue;
    }

    /**
     * Получение предполагаемого объема вложений.
     * <p/>
     * @return Объем в процентах
     */
    public double getInvestPercent()
    {
        return investPercent;
    }

    /**
     * Установка предполагаемого объема вложений.
     * <p/>
     * @param newValue Объем в процентах
     */
    public void setInvestPercent(double newValue)
    {
        investPercent = newValue;
    }

    /**
     * Получение возраста выхода на пенсию.
     * <p/>
     * @return Возраст
     */
    public int getRetireAge()
    {
        return retireAge;
    }

    /**
     * Установка возраста выхода на пенсию
     * <p/>
     * @param newValue
     */
    public void setRetireAge(int newValue)
    {
        retireAge = newValue;
    }

    /**
     * Получение суммы предыдущих сбережений.
     * <p/>
     * @return Объем сбережений
     */
    public double getSavings()
    {
        return savings;
    }

    /**
     * Установка суммы предыдущих сбережений.
     * <p/>
     * @param newValue Объем сбережений
     */
    public void setSavings(double newValue)
    {
        savings = newValue;
    }
}

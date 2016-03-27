public class Manager extends Employee
{
    private Employee secretary;

    /**
     * Создание менеджера без секретаря
     * <p/>
     * @param n     Имя сотрудника
     * @param s     Зарплата
     * @param year  Год приема на работу
     * @param month Месяц приема на работу
     * @param day   День приема на работу
     */
    public Manager(String n, double s, int year, int month, int day)
    {
        super(n, s, year, month, day);
        secretary = null;
    }

    public void setSecretary(Employee s)
    {
        secretary = s;
    }

    @Override
    public String toString()
    {
        return super.toString()
            + "[secretary = " + secretary + ']';
    }
}

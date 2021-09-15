package statistics;

/**
 *  Этот класс содержит данные регрессионного анализа.
 * @author dwinner@inbox.ru
 */
public class RegData {
    
    private static final long serialVersionUID = 014L;
    private double a;
    private double b;
    private double cor;
    private String equation;

    public RegData(double i, double j, double k, String str) {
        a = i;
        b = j;
        cor = k;
        equation = str;
    }

    /**
     * @return the a
     */
    protected double getA() {
        return a;
    }

    /**
     * @param a the a to set
     */
    protected void setA(double a) {
        this.a = a;
    }

    /**
     * @return the b
     */
    protected double getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    protected void setB(double b) {
        this.b = b;
    }

    /**
     * @return the cor
     */
    protected double getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    protected void setCor(double cor) {
        this.cor = cor;
    }

    /**
     * @return the equation
     */
    protected String getEquation() {
        return equation;
    }

    /**
     * @param equation the equation to set
     */
    protected void setEquation(String equation) {
        this.equation = equation;
    }
    
}

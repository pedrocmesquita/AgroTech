package Domain;

/**
 * The type Cabazes.
 */
public class Cabazes {

    private String cp1;
    private String dia;

    private int produtos[];


    public Cabazes(String cp1, String dia, int produtos[]) {
        this.cp1 = cp1;
        this.dia = dia;
        this.produtos=produtos;

    }

    /**
     * Gets cp 1.
     *
     * @return the cp 1
     */
    public String getCp1() {
        return cp1;
    }

    /**
     * Sets cp 1.
     *
     * @param cp1 the cp 1
     */
    public void setCp1(String cp1) {
        this.cp1 = cp1;
    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * Sets dia.
     *
     * @param dia the dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * Gets prod 1.
     *
     * @return the prod 1
     */


}
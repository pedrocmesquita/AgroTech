package Domain;

import Shared.BST.BST;

import java.util.List;

/**
 * The type Cabazes.
 */
public class Cabazes implements Comparable<Cabazes> {

    private Local local;
    private int dia;
    private float[] produtos;


    public Cabazes(Local local, int dia, float[] produtos) {
        this.local = local;
        this.dia = dia;
        this.produtos=produtos;

    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets dia.
     *
     * @param dia the dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public int compareTo(Cabazes o) {
        int thisDay = this.dia;
        int oDay = o.dia;

        if (thisDay < oDay) {
            return -1;
        } else if (thisDay > oDay) {
            return 1;
        } else {
            return 0;
        }
    }
}
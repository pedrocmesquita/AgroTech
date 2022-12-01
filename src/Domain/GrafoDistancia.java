package Domain;

import java.util.ArrayList;

public class GrafoDistancia {

    public String loc1;
    public String loc2;
    public int idLoc1;
    public int idLoc2;
    public int distancia;

    public ArrayList<GrafoDistancia> arrayList = new ArrayList<>();

    public GrafoDistancia() {}

    public GrafoDistancia(String loc1, String loc2, int idLoc1, int idLoc2, int distancia) {
        this.loc1 = loc1;
        this.loc2 = loc2;
        this.idLoc1 = idLoc1;
        this.idLoc2 = idLoc2;
        this.distancia = distancia;
    }

    public String getLoc1() {
        return loc1;
    }

    public void setLoc1(String loc1) {
        this.loc1 = loc1;
    }

    public String getLoc2() {
        return loc2;
    }

    public void setLoc2(String loc2) {
        this.loc2 = loc2;
    }

    public int getIdLoc1() {
        return idLoc1;
    }

    public void setIdLoc1(int idLoc1) {
        this.idLoc1 = idLoc1;
    }

    public int getIdLoc2() {
        return idLoc2;
    }

    public void setIdLoc2(int idLoc2) {
        this.idLoc2 = idLoc2;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return "GrafoDistancia{" +
                "loc1='" + loc1 + '\'' +
                ", loc2='" + loc2 + '\'' +
                ", idLoc1=" + idLoc1 +
                ", idLoc2=" + idLoc2 +
                ", distancia=" + distancia +
                '}';
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
}

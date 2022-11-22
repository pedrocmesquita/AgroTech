package Domain;

public class Quinta {

    private int idQuinta;
    private int idUtilizador;

    public Quinta(int idQuinta, int idUtilizador) {
        this.idQuinta = idQuinta;
        this.idUtilizador = idUtilizador;
    }

    public Quinta(int idQuinta) {
        this.idQuinta = idQuinta;
    }

    public int getIdQuinta() {
        return idQuinta;
    }

    public void setIdQuinta(int idQuinta) {
        this.idQuinta = idQuinta;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }
}

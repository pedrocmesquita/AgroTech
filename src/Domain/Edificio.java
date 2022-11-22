package Domain;

public class Edificio extends Quinta {

    private int numeroEdificio;

    public Edificio(int idQuinta, int idUtilizador) {
        super(idQuinta, idUtilizador);
    }

    public Edificio(int idQuinta) {
        super(idQuinta);
    }

    public Edificio(int idQuinta, int idUtilizador, int numeroEdificio) {
        super(idQuinta, idUtilizador);
        this.numeroEdificio = numeroEdificio;
    }

    public int getNumeroEdificio() {
        return numeroEdificio;
    }

    public void setNumeroEdificio(int numeroEdificio) {
        this.numeroEdificio = numeroEdificio;
    }
}

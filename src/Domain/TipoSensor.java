package Domain;

public class TipoSensor extends Sensor{

    private int nSensoresTipo;
    private String tipo;

    public TipoSensor(int id_estacao, int idSensor, int nSensoresTipo, String tipo) {
        super(id_estacao, idSensor);
        this.nSensoresTipo = nSensoresTipo;
        this.tipo = tipo;
    }

    public int getnSensoresTipo() {
        return nSensoresTipo;
    }

    public void setnSensoresTipo(int nSensoresTipo) {
        this.nSensoresTipo = nSensoresTipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

package Domain;

public class Sensor extends EstacaoMeteorologica{

    private int idSensor;

    public Sensor(int id_estacao, int idSensor) {
        super(id_estacao);
        this.idSensor = idSensor;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }
}

package Domain;

public class Cultura {

    private int idCultura;
    private String objetivo;
    private String tipo;

    public Cultura(int idCultura, String objetivo, String tipo) {
        this.idCultura = idCultura;
        this.objetivo = objetivo;
        this.tipo = tipo;
    }

    public int getIdCultura() {
        return idCultura;
    }

    public void setIdCultura(int idCultura) {
        this.idCultura = idCultura;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

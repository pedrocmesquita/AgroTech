package Domain;

public class SistemaRega {

    private int id_sistema;
    private String tipoSolucao;

    public SistemaRega(int id_sistema, String tipoSolucao) {
        this.id_sistema = id_sistema;
        this.tipoSolucao = tipoSolucao;
    }

    public SistemaRega(int id_sistema) {
        this.id_sistema = id_sistema;
    }

    public int getId_sistema() {
        return id_sistema;
    }

    public void setId_sistema(int id_sistema) {
        this.id_sistema = id_sistema;
    }

    public String getTipoSolucao() {
        return tipoSolucao;
    }

    public void setTipoSolucao(String tipoSolucao) {
        this.tipoSolucao = tipoSolucao;
    }
}

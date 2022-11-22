package Domain;

public class CadernoCampo extends Quinta{

    private int idCaderno;
    private String tipoFertilizacao;
    private String registoColheita;
    private String dadosSensores;
    private String dadosRegas;

    public CadernoCampo(int idQuinta, int idUtilizador, int idCaderno, String tipoFertilizacao, String registoColheita, String dadosSensores, String dadosRegas) {
        super(idQuinta, idUtilizador);
        this.idCaderno = idCaderno;
        this.tipoFertilizacao = tipoFertilizacao;
        this.registoColheita = registoColheita;
        this.dadosSensores = dadosSensores;
        this.dadosRegas = dadosRegas;
    }

    public int getIdCaderno() {
        return idCaderno;
    }

    public void setIdCaderno(int idCaderno) {
        this.idCaderno = idCaderno;
    }

    public String getTipoFertilizacao() {
        return tipoFertilizacao;
    }

    public void setTipoFertilizacao(String tipoFertilizacao) {
        this.tipoFertilizacao = tipoFertilizacao;
    }

    public String getRegistoColheita() {
        return registoColheita;
    }

    public void setRegistoColheita(String registoColheita) {
        this.registoColheita = registoColheita;
    }

    public String getDadosSensores() {
        return dadosSensores;
    }

    public void setDadosSensores(String dadosSensores) {
        this.dadosSensores = dadosSensores;
    }

    public String getDadosRegas() {
        return dadosRegas;
    }

    public void setDadosRegas(String dadosRegas) {
        this.dadosRegas = dadosRegas;
    }
}

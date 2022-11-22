package Domain;

public class FatorProducao {

    private int idFator;
    private String classificacao;
    private String nome;
    private String formulacao;
    private String fichaTecnica;
    private String tipoFertilizacao;

    public FatorProducao(int idFator, String classificacao, String nome, String formulacao, String fichaTecnica, String tipoFertilizacao) {
        this.idFator = idFator;
        this.classificacao = classificacao;
        this.nome = nome;
        this.formulacao = formulacao;
        this.fichaTecnica = fichaTecnica;
        this.tipoFertilizacao = tipoFertilizacao;
    }

    public int getIdFator() {
        return idFator;
    }

    public void setIdFator(int idFator) {
        this.idFator = idFator;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFormulacao() {
        return formulacao;
    }

    public void setFormulacao(String formulacao) {
        this.formulacao = formulacao;
    }

    public String getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(String fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public String getTipoFertilizacao() {
        return tipoFertilizacao;
    }

    public void setTipoFertilizacao(String tipoFertilizacao) {
        this.tipoFertilizacao = tipoFertilizacao;
    }
}

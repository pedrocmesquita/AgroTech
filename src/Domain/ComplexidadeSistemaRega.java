package Domain;

public class ComplexidadeSistemaRega extends SistemaRega{

    private String variedade;
    private int quantidadeAgua;
    private String qualidadeAgua;
    private int nCulturas;
    private int nParcelas;
    private int dimensaoExploracao;

    public ComplexidadeSistemaRega(int id_sistema, String tipoSolucao) {
        super(id_sistema, tipoSolucao);
    }

    public ComplexidadeSistemaRega(int id_sistema, String tipoSolucao, String variedade, int quantidadeAgua, String qualidadeAgua, int nCulturas, int nParcelas, int dimensaoExploracao) {
        super(id_sistema, tipoSolucao);
        this.variedade = variedade;
        this.quantidadeAgua = quantidadeAgua;
        this.qualidadeAgua = qualidadeAgua;
        this.nCulturas = nCulturas;
        this.nParcelas = nParcelas;
        this.dimensaoExploracao = dimensaoExploracao;
    }

    public ComplexidadeSistemaRega(int id_sistema, String variedade, int quantidadeAgua, String qualidadeAgua, int nCulturas, int nParcelas, int dimensaoExploracao) {
        super(id_sistema);
        this.variedade = variedade;
        this.quantidadeAgua = quantidadeAgua;
        this.qualidadeAgua = qualidadeAgua;
        this.nCulturas = nCulturas;
        this.nParcelas = nParcelas;
        this.dimensaoExploracao = dimensaoExploracao;
    }

    public String getVariedade() {
        return variedade;
    }

    public void setVariedade(String variedade) {
        this.variedade = variedade;
    }

    public int getQuantidadeAgua() {
        return quantidadeAgua;
    }

    public void setQuantidadeAgua(int quantidadeAgua) {
        this.quantidadeAgua = quantidadeAgua;
    }

    public String getQualidadeAgua() {
        return qualidadeAgua;
    }

    public void setQualidadeAgua(String qualidadeAgua) {
        this.qualidadeAgua = qualidadeAgua;
    }

    public int getnCulturas() {
        return nCulturas;
    }

    public void setnCulturas(int nCulturas) {
        this.nCulturas = nCulturas;
    }

    public int getnParcelas() {
        return nParcelas;
    }

    public void setnParcelas(int nParcelas) {
        this.nParcelas = nParcelas;
    }

    public int getDimensaoExploracao() {
        return dimensaoExploracao;
    }

    public void setDimensaoExploracao(int dimensaoExploracao) {
        this.dimensaoExploracao = dimensaoExploracao;
    }
}

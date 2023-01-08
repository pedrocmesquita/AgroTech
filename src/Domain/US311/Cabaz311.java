package Domain.US311;

public class Cabaz311 {

    private int nprodutostotal;
    private int nprodutosparcial;
    private int nprodutosnaosatisfeito;
    private int percentagem;
    private int nprodutor;
    public Cabaz311(int nprodutostotal, int nprodutosparcial, int nprodutosnaosatisfeito, int percentagem, int nprodutor) {
        this.nprodutostotal = nprodutostotal;
        this.nprodutosparcial = nprodutosparcial;
        this.nprodutosnaosatisfeito = nprodutosnaosatisfeito;
        this.percentagem = percentagem;
        this.nprodutor = nprodutor;
    }
    public int getNprodutostotal() {
        return nprodutostotal;
    }
    public void setNprodutostotal(int nprodutostotal) {
        this.nprodutostotal = nprodutostotal;
    }
    public int getNprodutosparcial() {
        return nprodutosparcial;
    }
    public void setNprodutosparcial(int nprodutosparcial) {
        this.nprodutosparcial = nprodutosparcial;
    }
    public int getNprodutosnaosatisfeito() {
        return nprodutosnaosatisfeito;
    }
    public void setNprodutosnaosatisfeito(int nprodutosnaosatisfeito) {
        this.nprodutosnaosatisfeito = nprodutosnaosatisfeito;
    }
    public int getPercentagem() {
        return percentagem;
    }
    public void setPercentagem(int percentagem) {
        this.percentagem = percentagem;
    }
    public int getNprodutor() {
        return nprodutor;
    }
    public void setNprodutor(int nprodutor) {
        this.nprodutor = nprodutor;
    }


}

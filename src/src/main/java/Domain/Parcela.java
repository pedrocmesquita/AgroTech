package Domain;

public class Parcela extends Quinta {

    private int designacao;
    private int area;


    public Parcela(int idQuinta, int idUtilizador, int designacao, int area) {
        super(idQuinta, idUtilizador);
        this.designacao = designacao;
        this.area = area;
    }

    public int getDesignacao() {
        return designacao;
    }

    public void setDesignacao(int designacao) {
        this.designacao = designacao;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }
}

package Domain;

import java.util.Date;

public class Cabaz extends Colheita {

    private int idCabaz;
    private String produto;
    private int preco;

    public Cabaz(int numeroColheita, String parcelas, Date date, String produto, int quantidade, int idCabaz, String produto1, int preco) {
        super(numeroColheita, parcelas, date, produto, quantidade);
        this.idCabaz = idCabaz;
        this.produto = produto1;
        this.preco = preco;
    }

    public int getIdCabaz() {
        return idCabaz;
    }

    public void setIdCabaz(int idCabaz) {
        this.idCabaz = idCabaz;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}

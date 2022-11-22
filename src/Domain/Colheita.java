package Domain;

import java.util.Date;

public class Colheita {

    private int numeroColheita;
    private String parcelas;
    private Date date;
    private String produto;
    private int quantidade;

    public Colheita(int numeroColheita, String parcelas, Date date, String produto, int quantidade) {
        this.numeroColheita = numeroColheita;
        this.parcelas = parcelas;
        this.date = date;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public int getNumeroColheita() {
        return numeroColheita;
    }

    public void setNumeroColheita(int numeroColheita) {
        this.numeroColheita = numeroColheita;
    }

    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

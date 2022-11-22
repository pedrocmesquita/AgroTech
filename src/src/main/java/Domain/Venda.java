package Domain;

import java.util.Date;

public class Venda extends Hub {

    private int id_venda;
    private int id_cliente;
    private int quantidadeCabazes;
    private Date dataVenda;
    private int precoTotal;

    public Venda(int idHub, int id_venda, int id_cliente, int quantidadeCabazes, Date dataVenda, int precoTotal) {
        super(idHub);
        this.id_venda = id_venda;
        this.id_cliente = id_cliente;
        this.quantidadeCabazes = quantidadeCabazes;
        this.dataVenda = dataVenda;
        this.precoTotal = precoTotal;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getQuantidadeCabazes() {
        return quantidadeCabazes;
    }

    public void setQuantidadeCabazes(int quantidadeCabazes) {
        this.quantidadeCabazes = quantidadeCabazes;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(int precoTotal) {
        this.precoTotal = precoTotal;
    }
}

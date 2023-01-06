package Domain;

import java.util.List;

/**
 * The type Produtos.
 */
public class CabazExpedicao {

    private Destinatário destinatario;
    private List<float[]> produtos;
    private int dia;

    /**
     * Instantiates a new Produtos.
     *
     * @param produtos          the produtos
     * @param dia               the dia
     */
    public CabazExpedicao(Destinatário destinatario, List<float[]> produtos, int dia) {
        this.destinatario = destinatario;
        this.produtos = produtos;
        this.dia = dia;
    }

    public Destinatário getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Destinatário destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Gets produtos.
     *
     * @return the produtos
     */
    public List<float[]> getProdutos() {
        return produtos;
    }

    /**
     * Sets produtos.
     *
     * @param produtos the produtos
     */
    public void setProdutos(List<float[]> produtos) {
        this.produtos = produtos;
    }

    /**
     * Gets dia.
     *
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets dia.
     *
     * @param dia the dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }
}

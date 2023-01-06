package Domain;

import java.util.List;

/**
 * The type Produtos.
 */
public class CabazExpedicao {

    private String nomeDestinatario;
    private String localDestinatario;
    private List<float[]> produtos;
    private int dia;

    /**
     * Instantiates a new Produtos.
     *
     * @param nomeDestinatario  the nome destinatario
     * @param localDestinatario the local destinatario
     * @param produtos          the produtos
     * @param dia               the dia
     */
    public CabazExpedicao(String nomeDestinatario, String localDestinatario, List<float[]> produtos, int dia) {
        this.nomeDestinatario = nomeDestinatario;
        this.produtos = produtos;
        this.dia = dia;
        this.localDestinatario = localDestinatario;
    }

    /**
     * Gets nome destinatario.
     *
     * @return the nome destinatario
     */
    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    /**
     * Sets nome destinatario.
     *
     * @param nomeDestinatario the nome destinatario
     */
    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    /**
     * Gets local destinatario.
     *
     * @return the local destinatario
     */
    public String getLocalDestinatario() {
        return localDestinatario;
    }

    /**
     * Sets local destinatario.
     *
     * @param localDestinatario the local destinatario
     */
    public void setLocalDestinatario(String localDestinatario) {
        this.localDestinatario = localDestinatario;
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

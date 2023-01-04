package Domain;

import java.util.List;

/**
 * The type Produtos.
 */
public class Produtos {

    private String nomeDestinatario;
    private List<float[]> produtos;

    /**
     * Instantiates a new Produtos.
     *
     * @param nomeDestinatario the nome destinatario
     * @param produtos         the produtos
     */
    public Produtos(String nomeDestinatario, List<float[]> produtos) {
        this.nomeDestinatario = nomeDestinatario;
        this.produtos = produtos;
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
}

package Domain.US304;

import Domain.Local;

/**
 * The type Par cliente hub.
 */
public class ParClienteHub {

    private Local cliente;
    private Local hub;
    private Integer distancia;

    /**
     * Instantiates a new Par cliente hub.
     *
     * @param cliente   the cliente
     * @param empresa   the empresa
     * @param distancia the distancia
     */
    public ParClienteHub(Local cliente, Local empresa, Integer distancia) {
        this.cliente = cliente;
        this.hub = empresa;
        this.distancia = distancia;
    }

    /**
     * Gets cliente.
     *
     * @return the cliente
     */
    public Local getCliente() {
        return cliente;
    }

    /**
     * Gets empresa.
     *
     * @return the empresa
     */
    public Local getEmpresa() {
        return hub;
    }

    /**
     * Gets distancia.
     *
     * @return the distancia
     */
    public Integer getDistancia() {
        return distancia;
    }

    /**
     * Sets cliente.
     *
     * @param cliente the cliente
     */
    public void setCliente(Local cliente) {
        this.cliente = cliente;
    }

    /**
     * Sets empresa.
     *
     * @param empresa the empresa
     */
    public void setEmpresa(Local empresa) {
        this.hub = empresa;
    }

    /**
     * Sets distancia.
     *
     * @param distancia the distancia
     */
    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return String
                .format("\nO hub mais próxima do Cliente " + this.cliente.getDestinatário() + " é o hub " + this.hub.getDestinatário() + " e está a " + this.distancia + " metros.\n");
    }
}

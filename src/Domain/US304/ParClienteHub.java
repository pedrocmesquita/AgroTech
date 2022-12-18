package Domain.US304;

import Domain.Local;

public class ParClienteHub {

    private Local cliente;
    private Local hub;
    private Integer distancia;

    public ParClienteHub(Local cliente, Local empresa, Integer distancia) {
        this.cliente = cliente;
        this.hub = empresa;
        this.distancia = distancia;
    }

    public Local getCliente() {
        return cliente;
    }

    public Local getEmpresa() {
        return hub;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setCliente(Local cliente) {
        this.cliente = cliente;
    }

    public void setEmpresa(Local empresa) {
        this.hub = empresa;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return String
                .format("\nO hub mais próxima do Cliente " + this.cliente.getDestinatário() + " é o hub " + this.hub.getDestinatário() + " e está a " + this.distancia + " metros.\n");
    }
}

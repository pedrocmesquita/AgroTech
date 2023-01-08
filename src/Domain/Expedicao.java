package Domain;

public class Expedicao {

    private Destinatário cliente;
    private Destinatário produtor;
    private float quantidadePedida;
    private float quantidadeFornecida;
    private float quantidadeSobra;
    private int numeroProduto;
    private int dia;

    private Local hub;

    public Expedicao(Destinatário cliente, Destinatário produtor, float quantidadePedida, float quantidadeFornecida, float quantidadeSobra, int numeroProduto, int dia, Local hub) {
        this.cliente = cliente;
        this.produtor = produtor;
        this.quantidadePedida = quantidadePedida;
        this.quantidadeFornecida = quantidadeFornecida;
        this.quantidadeSobra = quantidadeSobra;
        this.numeroProduto = numeroProduto;
        this.dia = dia;
        this.hub = hub;
    }

    public Destinatário getCliente() {
        return cliente;
    }

    public void setCliente(Destinatário cliente) {
        this.cliente = cliente;
    }

    public Destinatário getProdutor() {
        return produtor;
    }

    public void setProdutor(Destinatário produtor) {
        this.produtor = produtor;
    }

    public float getQuantidadePedida() {
        return quantidadePedida;
    }

    public void setQuantidadePedida(float quantidadePedida) {
        this.quantidadePedida = quantidadePedida;
    }

    public float getQuantidadeFornecida() {
        return quantidadeFornecida;
    }

    public void setQuantidadeFornecida(float quantidadeFornecida) {
        this.quantidadeFornecida = quantidadeFornecida;
    }

    public float getQuantidadeSobra() {
        return quantidadeSobra;
    }

    public void setQuantidadeSobra(float quantidadeSobra) {
        this.quantidadeSobra = quantidadeSobra;
    }

    public int getNumeroProduto() {
        return numeroProduto;
    }

    public void setNumeroProduto(int numeroProduto) {
        this.numeroProduto = numeroProduto;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Local getHub() {
        return hub;
    }
}

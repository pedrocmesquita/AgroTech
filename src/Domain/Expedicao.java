package Domain;

public class Expedicao {

    private Destinatário cliente;
    private Destinatário produtor;
    private float quantidadePedida;
    private float quantidadeAFornecer;
    private float quantidadeSobra;
    private int numeroProduto;
    private int dia;

    private Local hub;

    public Expedicao(Destinatário cliente, Destinatário produtor, float quantidadePedida, float quantidadeFornecida, float quantidadeSobra, int numeroProduto, int dia, Local hub) {
        this.cliente = cliente;
        this.produtor = produtor;
        this.quantidadePedida = quantidadePedida;
        this.quantidadeAFornecer = quantidadeFornecida;
        this.quantidadeSobra = quantidadeSobra;
        this.numeroProduto = numeroProduto;
        this.dia = dia;
        this.hub = hub;
    }

    public Expedicao(Destinatário cliente, Destinatário produtor, float quantidadePedida, float quantidadeFornecida, float quantidadeSobra, int numeroProduto, int dia) {
        this.cliente = cliente;
        this.produtor = produtor;
        this.quantidadePedida = quantidadePedida;
        this.quantidadeAFornecer = quantidadeFornecida;
        this.quantidadeSobra = quantidadeSobra;
        this.numeroProduto = numeroProduto;
        this.dia = dia;
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

    public float getQuantidadeAFornecer() {
        return quantidadeAFornecer;
    }

    public void setQuantidadeAFornecer(float quantidadeAFornecer) {
        this.quantidadeAFornecer = quantidadeAFornecer;
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
    public String toString(){

        return "Cliente: " + cliente + " Produtor: " + produtor + " Quantidade Pedida: " + quantidadePedida + " Quantidade a Fornecer: " + quantidadeAFornecer + " Quantidade Sobra: " + quantidadeSobra + " Numero Produto: " + numeroProduto + " Dia: " + dia;
    }
}

package Domain;

public class Expedicao {

    private String nomeCliente;
    private String nomeProdutor;
    private float quantidadePedida;
    private float quantidadeFornecida;
    private float quantidadeSobra;
    private int numeroProduto;
    private int dia;

    public Expedicao(String nomeCliente, String nomeProdutor, float quantidadePedida, float quantidadeFornecida, float quantidadeSobra,int numeroProduto, int dia) {
        this.nomeCliente = nomeCliente;
        this.nomeProdutor = nomeProdutor;
        this.quantidadePedida = quantidadePedida;
        this.quantidadeFornecida = quantidadeFornecida;
        this.quantidadeSobra = quantidadeSobra;
        this.numeroProduto = numeroProduto;
        this.dia = dia;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProdutor() {
        return nomeProdutor;
    }

    public void setNomeProdutor(String nomeProdutor) {
        this.nomeProdutor = nomeProdutor;
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
}

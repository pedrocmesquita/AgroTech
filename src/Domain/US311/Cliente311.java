package Domain.US311;

public class Cliente311 {
    private int ctotal;
    private int cparcial;
    private int fornecedores;
    public Cliente311(int ctotal, int cparcial, int fornecedores) {
        this.ctotal = ctotal;
        this.cparcial = cparcial;
        this.fornecedores = fornecedores;
    }
    public int getCtotal() {
        return ctotal;
    }
    public void setCtotal(int ctotal) {
        this.ctotal = ctotal;
    }
    public int getCparcial() {
        return cparcial;
    }
    public void setCparcial(int cparcial) {
        this.cparcial = cparcial;
    }
    public int getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(int fornecedores) {
        this.fornecedores = fornecedores;
    }

    public String toString() {
        return "Cliente{" +
                "nº de cabazes totalmente satisfeitos=" + ctotal +
                ", nº de cabazes parcialmente satisfeitos=" + cparcial +
                ", nº defornecedores=" + fornecedores +
                '}';
    }
}

package Domain.US311;

public class Hub311 {
    private int nclientes;
    private int nprodutor;
    public Hub311(int nclientes, int nprodutor) {
        this.nclientes = nclientes;
        this.nprodutor = nprodutor;
    }
    public int getNclientes() {
        return nclientes;
    }
    public void setNclientes(int nclientes) {
        this.nclientes = nclientes;
    }
    public int getNprodutor() {
        return nprodutor;
    }
    public void setNprodutor(int nprodutor) {
        this.nprodutor = nprodutor;
    }
}

package Domain.US311;

public class Produtor311 {
      /*US311 - Para uma lista de expedição calcular estatísticas:
• por cabaz: nº de produtos totalmente satisfeitos, nº de produtos parcialmente satisfeitos, nº
de produtos não satisfeitos, percentagem total do cabaz satisfeito, nº de produtores que
forneceram o cabaz.
• por cliente: nº de cabazes totalmente satisfeitos, nº de cabazes parcialmente satisfeitos, nº
de fornecedores distintos que forneceram todos os seus cabazes
• por produtor: nº de cabazes fornecidos totalmente, nº de cabazes fornecidos parcialmente,
nº de clientes distintos fornecidos, nº de produtos totalmente esgotados, nº de hubs
fornecidos.
• por hub: nº de clientes distintos que recolhem cabazes em cada hub, nº de produtores
distintos que fornecem cabazes para o hub.
*/
    private int ncabazestotal;
    private int ncabazesparcial;
    private int nclientes;
    private int nprodutoesgotado;
    private int nhub;
    public Produtor311(int ncabazestotal, int ncabazesparcial, int nclientes, int nprodutoesgotado, int nhub) {
        this.ncabazestotal = ncabazestotal;
        this.ncabazesparcial = ncabazesparcial;
        this.nclientes = nclientes;
        this.nprodutoesgotado = nprodutoesgotado;
        this.nhub = nhub;
    }
    public int getNcabazestotal() {
        return ncabazestotal;
    }
    public void setNcabazestotal(int ncabazestotal) {
        this.ncabazestotal = ncabazestotal;
    }
    public int getNcabazesparcial() {
        return ncabazesparcial;
    }
    public void setNcabazesparcial(int ncabazesparcial) {
        this.ncabazesparcial = ncabazesparcial;
    }
    public int getNclientes() {
        return nclientes;
    }
    public void setNclientes(int nclientes) {
        this.nclientes = nclientes;
    }
    public int getNprodutoesgotado() {
        return nprodutoesgotado;
    }
    public void setNprodutoesgotado(int nprodutoesgotado) {
        this.nprodutoesgotado = nprodutoesgotado;
    }
    public int getNhub() {
        return nhub;
    }
    public void setNhub(int nhub) {
        this.nhub = nhub;
    }
    public String toString() {
        return "Produtor{" +
                "nº de cabazes totalmente satisfeitos=" + ncabazestotal +
                ", nº de cabazes parcialmente satisfeitos =" + ncabazesparcial +
                ", nº de clientes=" + nclientes +
                ", nº de produtos esgotados=" + nprodutoesgotado +
                ", nº de hubs=" + nhub +
                '}';
    }

}

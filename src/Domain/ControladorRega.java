package Domain;

import java.time.LocalDate;

public class ControladorRega {
    private String horasRega;
    private int duracao;
    private String parcela;
    private String regularidade;
    private LocalDate startDate;
    // Regularidade : t = Todos dias; i =dias impares; p = dias pares

    public ControladorRega(String horasRega, String parcela,int duracao, String regularidade) {
        this.horasRega = horasRega;
        this.duracao = duracao;
        this.parcela = parcela;
        this.regularidade = regularidade;
        this.startDate = LocalDate.now();
    }

    public String getParcela() {
        return parcela;
    }
    public String getRegularidade() {
        return regularidade;
    }
    public String getHorasRega() {
        return horasRega;
    }
    public int getDuracao() {
        return duracao;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
}

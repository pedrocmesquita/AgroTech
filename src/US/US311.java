package US;

import Domain.Expedicao;
import Domain.US311.Cabaz311;
import Domain.US311.Cliente311;
import Domain.US311.Hub311;
import Domain.US311.Produtor311;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class US311 {
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


    public static ArrayList<Object> CalcularEstatisticas(List<Expedicao> expedicao) {

        String produtor1 = "";
        ArrayList<Object> output = new ArrayList<Object>();
        for (Expedicao lista : expedicao) {
            int ctotal = 0, cparcial = 0, cnaosatisfeito = 0, nclientes = 0, i = 0, nprodutor = 1, ptotal = 0, pparcial = 0, pnaosatisfeito = 0, nprodutorcabaz = 0;
            int ptotalcabaz = 0, pparcialcabaz = 0, nprodutoesgotado = 0, nhub = 1, percentagem = 0;
            String produtor = lista.getProdutor().getName();
            String cliente = lista.getCliente().getName();

            //por hub
            String hub = lista.getHub().getName();
            // por produtor
            produtor1 = "";
            String empresa = "";
            String produtor2 = "";
            int day = lista.getDia();
            for (Expedicao lista1 : expedicao) {
                if (lista1.getHub().getName().equals(hub)) {
                    if (lista1.getCliente().getName().equals(cliente) && i != 1) {
                        nclientes++;
                        i = 1;
                        nprodutor++;
                    } else if (!(lista1.getCliente().getName().equals(cliente)) && i == 1) {
                        nclientes++;
                        if (!produtor.equals(produtor1)) {
                            produtor1 = produtor;
                            nprodutor++;
                        }

                    }
                    output.add(new Hub311(nclientes, nprodutor));
                }
                String nome = lista.getCliente().getName();
                    if (lista1.getCliente().getName().equals(nome)) {
                        if (lista1.getQuantidadePedida() == lista1.getQuantidadeAFornecer()) {
                            ctotal++;
                        }
                        if (lista1.getQuantidadePedida() < lista1.getQuantidadeAFornecer()) {
                            cparcial++;
                        }
                        if (lista1.getQuantidadePedida() > lista1.getQuantidadeAFornecer()) {
                            cnaosatisfeito++;
                        }
                        if (!produtor.equals(produtor2)) {
                            produtor2 = produtor;
                            nprodutor++;
                        }
                        output.add(new Cliente311(ctotal, cparcial, nprodutor));
                        //por cabaz
                        if (lista1.getDia() == day) {
                            if (lista1.getQuantidadePedida() == lista1.getQuantidadeAFornecer()) {
                                ptotal++;
                            }
                            if (lista1.getQuantidadePedida() < lista1.getQuantidadeAFornecer()) {
                                pparcial++;
                            }
                            if (lista1.getQuantidadePedida() > lista1.getQuantidadeAFornecer()) {
                                pnaosatisfeito++;
                            }
                            nprodutorcabaz++;
                        }
                        percentagem = (ptotal * 100) / (ptotal + pparcial + pnaosatisfeito);
                        output.add(new Cabaz311(ptotal, pparcial, pnaosatisfeito,percentagem, nprodutorcabaz));
                    }
                    if (lista1.getProdutor().getName().equals(produtor)) {
                        if (lista1.getQuantidadePedida() == lista1.getQuantidadeAFornecer()) {
                            ptotal++;
                        }
                        if (lista1.getQuantidadePedida() < lista1.getQuantidadeAFornecer()) {
                            pparcial++;
                        }
                        if (lista1.getQuantidadePedida() > lista1.getQuantidadeAFornecer()) {
                            pnaosatisfeito++;
                        }
                        if (!lista1.getCliente().getName().equals(nome)) {
                            nome = lista1.getCliente().getName();
                            nclientes++;
                        }
                        if (!Objects.equals(hub, lista1.getHub().getName())) {
                            nhub++;
                        }

                    }
                output.add(new Produtor311(ptotal, pparcial, nclientes, nprodutoesgotado, nhub));
            }

    }
        return output;
}
    }





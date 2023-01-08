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


    public static List<Object> CalcularEstatisticas(List<Expedicao> expedicao) {

        String produtor1 = "";
        List<Object> output = new ArrayList<Object>();
        int a = 0;
        for (Expedicao lista : expedicao) { // corre a lista
            int ctotal = 0, cparcial = 0, cnaosatisfeito = 0, nclientes = 0, i = 0, nprodutor = 1, ptotal = 0, pparcial = 0, pnaosatisfeito = 0, nprodutorcabaz = 0;
            int ptotalcabaz = 0, pparcialcabaz = 0, nprodutoesgotado = 0, nhub = 1, percentagem = 0;

            String produtor = lista.getProdutor().getName();
            String cliente = lista.getCliente().getName();  //guarda valores da lista para comparaçao
            String hub = lista.getHub().getName();

            produtor1 = "";
            String empresa = ""; //inicializa variaveis para guardar valores previos
            String produtor2 = "";
            int day = lista.getDia();
            for (Expedicao lista1 : expedicao) { //corre a lista para comparar valores
                if (lista1.getHub().getName().equals(hub)) { //se hub é igual
                    if (lista1.getCliente().getName().equals(cliente) && i != 1) { //se cliente é igual e i!=1 (para nao contar o mesmo cliente mais que uma vez)
                        nclientes++;
                        i = 1;
                        nprodutor++;
                    } else if (!(lista1.getCliente().getName().equals(cliente)) && i == 1) { //se cliente é diferente e i=1 (para nao contar o mesmo cliente ou produtor mais que uma vez)
                        nclientes++;
                        if (!produtor.equals(produtor1)) { //se produtor é diferente
                            produtor1 = produtor;
                            nprodutor++;
                        }

                    }
                    output.add(a, new Hub311(nclientes, nprodutor)); //adiciona o hub a lista de output
                    a++;
                }
                String nome = lista.getCliente().getName();
                if (lista1.getCliente().getName().equals(nome)) { //se cliente é igual
                    if (lista1.getQuantidadePedida() == lista1.getQuantidadeAFornecer()) { //se quantidade pedida é igual a quantidade fornecida
                        ctotal++;
                    }
                    if (lista1.getQuantidadePedida() < lista1.getQuantidadeAFornecer()) { // se quantidade pedida é menor que quantidade fornecida
                        cparcial++;
                    }
                    if (lista1.getQuantidadePedida() > lista1.getQuantidadeAFornecer()) { // se quantidade pedida é maior que quantidade fornecida
                        cnaosatisfeito++;
                    }
                    if (!produtor.equals(produtor2)) { //se produtor é diferente
                        produtor2 = produtor;
                        nprodutor++;
                    }
                    output.add(a, new Cliente311(ctotal, cparcial, nprodutor));
                    a++;
                    //por cabaz
                    if (lista1.getDia() == day) { //se dia é igual
                        if (lista1.getQuantidadePedida() == lista1.getQuantidadeAFornecer()) { //se quantidade pedida é igual a quantidade fornecida
                            ptotal++;
                        }
                        if (lista1.getQuantidadePedida() < lista1.getQuantidadeAFornecer()) { // se quantidade pedida é menor que quantidade fornecida
                            pparcial++;
                        }
                        if (lista1.getQuantidadePedida() > lista1.getQuantidadeAFornecer()) { // se quantidade pedida é maior que quantidade fornecida
                            pnaosatisfeito++;
                        }
                        nprodutorcabaz++;

                        percentagem = (ptotal * 100) / (ptotal + pparcial + pnaosatisfeito); //calcula percentagem

                        output.add(a, new Cabaz311(ptotal, pparcial, pnaosatisfeito, percentagem, nprodutorcabaz)); //adiciona cabaz a lista de output
                        a++;
                    }
                }
                if (lista1.getProdutor().getName().equals(produtor)) { //se produtor é igual
                    if (lista1.getQuantidadePedida() == lista1.getQuantidadeAFornecer()) { //se quantidade pedida é igual a quantidade fornecida
                        ptotal++;
                    }
                    if (lista1.getQuantidadePedida() < lista1.getQuantidadeAFornecer()) { // se quantidade pedida é menor que quantidade fornecida
                        pparcial++;
                    }
                    if (lista1.getQuantidadePedida() > lista1.getQuantidadeAFornecer()) { // se quantidade pedida é maior que quantidade fornecida
                        pnaosatisfeito++;
                    }
                    if (!lista1.getCliente().getName().equals(nome)) { //se cliente é diferente
                        nome = lista1.getCliente().getName();
                        nclientes++;
                    }
                    if (!Objects.equals(hub, lista1.getHub().getName())) { //se hub é diferente
                        nhub++;
                    }

                }
                output.add(a, new Produtor311(ptotal, pparcial, nclientes, nprodutoesgotado, nhub)); //adiciona o produtor a lista de output
                a++;
            }

        }
        return output;
    }
}





package US;

import Domain.Destinatário;
import Domain.CabazExpedicao;
import Domain.Expedicao;
import Domain.Local;
import Domain.US304.ParClienteHub;
import Shared.GraphCommon.Graph;

import java.util.*;

public class US308 {

    public void gerarListaClientesEProdutores(Map<Integer, Map<Destinatário, List<float[]>>> cabazesMap, List<CabazExpedicao> produtores, List<CabazExpedicao> clientes) throws Exception {

        if (cabazesMap.isEmpty()) {
            throw new Exception("MAP IS NULL");
        }

        for (Map.Entry<Integer, Map<Destinatário, List<float[]>>> entry : cabazesMap.entrySet()) {

            for (Map.Entry<Destinatário, List<float[]>> entry2 : entry.getValue().entrySet()) {
                char produtor = entry2.getKey().getName().charAt(0);
                CabazExpedicao expedicao = new CabazExpedicao(entry2.getKey(), entry2.getValue(), entry.getKey());

                if (produtor == 'P') {
                    produtores.add(expedicao);
                } else {
                    clientes.add(expedicao);
                }
            }

        }
    }

    public List<Expedicao> gerarLista(List<CabazExpedicao> clientes, List<CabazExpedicao> produtores, Graph<Local, Integer> map) {
        List<Expedicao> lista = new ArrayList<>();
        List<Expedicao> listaFinal = new ArrayList<>();

        for (CabazExpedicao c : clientes) {
            List<ParClienteHub> delivery_hub = US304.findNearestHubs(map, 1);
            for (CabazExpedicao p : produtores) {

                if (p.getDia() == c.getDia()) {

                    List<float[]> produtosCliente = c.getProdutos();
                    List<float[]> produtosProdutor = p.getProdutos();

                    for (int i = 0; i < produtosCliente.size(); i++) {
                        float[] arrC = produtosCliente.get(i);
                        float[] arrP = produtosProdutor.get(i);

                        for (int j = 0; j < arrC.length; j++) {

                            if (arrC[j] <= arrP[j] && arrC[j] != 0) {
                                lista.add(new Expedicao(c.getDestinatario(), p.getDestinatario(), arrC[j], arrP[j], arrP[j] - arrC[j], j + 1, c.getDia(),delivery_hub.get(i).getEmpresa()));
                                //lista.add(new Expedicao(c.getDestinatario(), p.getDestinatario(), arrC[j], arrP[j], arrP[j] - arrC[j], j + 1, c.getDia()));
                            }
                        }
                    }
                }
            }
        }

        updateProdutos(lista);

        for (Expedicao expedicao : lista) {
            if (expedicao.getQuantidadeSobra() >= 0) {
                listaFinal.add(expedicao);
            }
        }
        return listaFinal;
    }

    public void updateProdutos(List<Expedicao> lista) {
        int size = lista.size();
        float sobra;

        for (int i = 0; i < size; i++) {

            sobra = lista.get(i).getQuantidadeSobra();

            //sobras para o proximo dia
            if (sobra > 0) {
                for (int j = 1; j < size - 1; j++) {
                    if (lista.get(i).getCliente() == lista.get(j).getCliente()) {
                        if (lista.get(i).getProdutor() == lista.get(j).getProdutor()) {
                            if (lista.get(i).getNumeroProduto() == lista.get(j).getNumeroProduto()) {
                                if (lista.get(j).getDia() - lista.get(i).getDia() == 1) {
                                    lista.get(j).setQuantidadeAFornecer(lista.get(j).getQuantidadeAFornecer() + sobra);
                                    if (lista.get(j).getQuantidadeAFornecer() - lista.get(j).getQuantidadePedida() >= 0) {
                                        lista.get(j).setQuantidadeSobra(lista.get(j).getQuantidadeAFornecer() - lista.get(j).getQuantidadePedida());
                                    }

                                    //sobras para dia 2
                                    if (sobra > lista.get(j).getQuantidadePedida()) {
                                        for (int k = 2; k < size - 2; k++) {
                                            if (lista.get(k).getCliente() == lista.get(j).getCliente()) {
                                                if (lista.get(j).getProdutor() == lista.get(k).getProdutor()) {
                                                    if (lista.get(j).getNumeroProduto() == lista.get(k).getNumeroProduto()) {
                                                        if (lista.get(k).getDia() - lista.get(j).getDia() == 1) {
                                                            lista.get(k).setQuantidadeAFornecer(lista.get(k).getQuantidadeAFornecer() + sobra);
                                                            if (lista.get(k).getQuantidadeAFornecer() - lista.get(k).getQuantidadePedida() >= 0) {
                                                                lista.get(k).setQuantidadeSobra(lista.get(k).getQuantidadeAFornecer() - lista.get(k).getQuantidadePedida());
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void printList(List<Expedicao> expedicao, int dia) {
        System.out.println("\n\n>>>>> Lista de Expedição de Cabazes para o dia " + dia + " <<<<<\n");

        for (Expedicao lista : expedicao) {
            if (lista.getDia() == dia) {
                System.out.println(">> Cliente: " + lista.getCliente().getName());
                System.out.println("Produto " + lista.getNumeroProduto() + " --> Produtor " + lista.getProdutor().getName());
                System.out.println("Quantidade pedida = " + lista.getQuantidadePedida() + " --> Quantidade Possivel de Ser Expedida " + lista.getQuantidadeAFornecer());
                System.out.println("Sobrou: " + lista.getQuantidadeSobra());
                System.out.println("Hub: " + lista.getHub());
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}

package US;

import Domain.Destinatário;
import Domain.CabazExpedicao;
import Domain.Expedicao;
import Domain.Local;
import Domain.US304.ParClienteHub;
import Shared.GraphCommon.Graph;

import java.util.*;

public class US308 {

    static Scanner sc = new Scanner(System.in);

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
                                lista.add(new Expedicao(c.getDestinatario(), p.getDestinatario(), arrC[j], arrP[j], arrP[j] - arrC[j], j + 1, c.getDia(),delivery_hub.get(0).getEmpresa()));
                            }
                        }
                    }
                }
            }
        }
        return lista;
    }

    public void printList(List<Expedicao> expedicao, int dia) {
        System.out.println("\n\n>>>>> Lista de Expedição de Cabazes para o dia " + dia + " <<<<<\n");

        for (Expedicao lista : expedicao) {
            if (lista.getDia() == dia) {
                System.out.println(">> Cliente: " + lista.getCliente().getName());
                System.out.println("Produto " + lista.getNumeroProduto() + " --> Produtor " + lista.getProdutor().getName());
                System.out.println("Quantidade pedida = " + lista.getQuantidadePedida() + " --> Quantidade Expedida " + lista.getQuantidadeFornecida());
                System.out.println("Sobrou: " + lista.getQuantidadeSobra());
                System.out.println("------------------------------------------------------------");
            }
        }
    }
}

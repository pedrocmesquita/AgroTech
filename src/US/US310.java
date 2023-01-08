package US;

import Domain.Destinatário;
import Domain.CabazExpedicao;
import Domain.Expedicao;
import Domain.Local;
import Domain.US304.ParClienteHub;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Graph;

import java.util.*;

/**
 * US310
 * Para uma lista de expedição diária gerar o percurso de entrega que minimiza a distância total percorrida.
 * Critério de Aceitação: Indicar os pontos de passagem do percurso (produtores e hubs), cabazes entregues em
 * cada hub, distância entre todos os pontos do percurso e a distância total.
 *
 * @author Pedro Mesquita 12111171
 */
public class US310 {
    static void minPath(List<ParClienteHub> parClienteHubs, List<Local> hubs, List<Local> produtores, List<Expedicao> exp, Graph<Local, Integer> map){
        int nProdutores = produtores.size();
        int nHubs = hubs.size();
        List<Local> ProdutoresPassados = new LinkedList<>();
        List<Local> HubsPassados = new LinkedList<>();
        List<Local> caminho = new ArrayList<>();
        List<Map.Entry<String, Integer>> pontosDistancia = new ArrayList<>();
        Integer distanciaTotal = 0;

        ProdutoresPassados.add(produtores.get(0));
        caminho.add(produtores.get(0));

        while(nProdutores>0) {
            Integer minDist = getDistMinProdutor(map, ProdutoresPassados.get(ProdutoresPassados.size() - 1), ProdutoresPassados, produtores, pontosDistancia, caminho);

            distanciaTotal += minDist;

            nProdutores--;
        }

        while(nHubs>0){
            Integer minDist =0;

            distanciaTotal += minDist;

            nHubs--;
        }
    }

    private static Integer getDistMinHub(Graph<Local, Integer> map, Local produtor, List<Local> produtoresPassados, List<Local> produtores, List<Map.Entry<String, Integer>> pontosDistancia, List<Local> caminho) {

        return 0;
    }

    private static Integer getDistMinProdutor(Graph<Local, Integer> map, Local produtor, List<Local> produtoresPassados, List<Local> produtores, List<Map.Entry<String, Integer>> pontosDistancia, List<Local> caminho) {

        ArrayList<Integer> distancias = new ArrayList<>();

        Integer minDist = Integer.MAX_VALUE;
        Local prodMaisProx = null;

        ArrayList<LinkedList<Local>> paths = new ArrayList<>();

        Algorithms.shortestPaths(
                map,
                produtor,
                Integer::compare,
                Integer::sum,
                0,
                paths,
                distancias
        );

        for(Local prod : produtores){
            if(!produtoresPassados.contains(prod)){
                if(distancias.get(map.key(prod)) < minDist){
                    prodMaisProx = prod;
                    minDist = distancias.get(map.key(prod));
                }
            }
        }

        LinkedList<Local> path = paths.get(map.key(prodMaisProx));

        caminho.addAll(path.subList(1, path.size()));

        for (int i = 0; i < path.size() - 1; i++) {
            Integer distanciaEntreDoisPontos = Algorithms.shortestPath(
                    map,
                    path.get(i),
                    path.get(i+1),
                    Integer::compare,
                    Integer::sum,
                    0,
                    new LinkedList<>()
            );

            String key = path.get(i).getName() + "-" + path.get(i+1).getName();
            Map.Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>(key, distanciaEntreDoisPontos);
            pontosDistancia.add(entry);
        }


        return minDist;
    }
}

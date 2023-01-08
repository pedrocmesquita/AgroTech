package US;

import CsvReader.CsvReader;
import Domain.*;
import Domain.US304.ParClienteHub;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Graph;
import Shared.constants.Files;

import java.io.File;
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
    static void US310call(int dia, int numeroHubs, int numeroProdutores, Graph<Local, Integer> map) throws Exception {
        CsvReader readFiles = new CsvReader();
        File file3 = new File(Files.s_cabazes);
        final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();
        readFiles.ReadCabaz(file3, ",", cabazes);//mapa dos Cabazes
        List<Local> hubs = US303.findHubs(map, numeroHubs);
        List<Expedicao> expList;
        List<CabazExpedicao> produtores = new ArrayList<>();
        List<CabazExpedicao> clientes = new ArrayList<>();
        List<Expedicao> lista;
        List<Local> produtores1 = map.vertices().stream().filter(p -> p.getName().charAt(0) == 'P').toList();

        US308 us308 = new US308();

        us308.gerarListaClientesEProdutores(cabazes, produtores, clientes);

        if(numeroProdutores == 0){
            expList = new US308().gerarLista(clientes, produtores, map);
            assert hubs != null;
            minPath(hubs, produtores1, expList, map);
        }else{
            expList = US309.generateExpeditionListNClosestProd(clientes,produtores,map,dia);
            assert hubs != null;
            minPath(hubs, produtores1, expList, map);

        }
    }


    static void minPath(List<Local> hubs, List<Local> produtores, List<Expedicao> exp, Graph<Local, Integer> map){
        int nProdutores = produtores.size();
        int nHubs = hubs.size();
        List<Local> ProdutoresPassados = new LinkedList<>();
        List<Local> HubsPassados = new LinkedList<>();
        LinkedList<Local> caminho = new LinkedList<>();
        List<Map.Entry<String, Integer>> pontosDistancia = new ArrayList<>();
        Integer distanciaTotal = 0;

        ProdutoresPassados.add(produtores.get(0));
        caminho.add(produtores.get(0));

        while(nProdutores>0) {
            Integer minDist = getDistMinProdutor(map, caminho.getLast(), ProdutoresPassados, produtores, pontosDistancia, caminho);

            distanciaTotal += minDist;

            nProdutores--;
        }

        while(nHubs>0){
            Integer minDist =getDistMinHub(map, caminho.getLast(), HubsPassados, hubs, pontosDistancia, caminho);

            distanciaTotal += minDist;

            nHubs--;
        }

        Map<Local, List<Expedicao>> hubComOsSeusProdutos = new HashMap<>();

        for (Expedicao e : exp) {

            Local hub = e.getHub();

            if(!hubComOsSeusProdutos.containsKey(hub)){

                hubComOsSeusProdutos.put(hub, new ArrayList<>());
                hubComOsSeusProdutos.get(hub).add(e);

            }else{

                List<Expedicao> contemKey = hubComOsSeusProdutos.get(hub);
                contemKey.add(e);

            }

        }



        System.out.println("Caminho: \n");
        for (int i = 0; i < caminho.size(); i++) {
            if(i != caminho.size() - 1)
                System.out.printf(caminho.get(i).getName() + " -> ");
            else
                System.out.printf(caminho.get(i).getName());
        }
        System.out.println();

        System.out.println("Distancia Total: " + distanciaTotal);

        System.out.println("Distancias Singulares: \n");
        for (int i = 0; i < pontosDistancia.size(); i++) {
            System.out.println(pontosDistancia.get(i).getKey() + ": " + pontosDistancia.get(i).getValue() + " m");
        }
        System.out.println();

        for(Local hub : hubComOsSeusProdutos.keySet()){
            System.out.println("\nHub: " + hub.getName() + "\n");

            List<Expedicao> produtosEntregues = hubComOsSeusProdutos.get(hub);

            for(Expedicao produto : produtosEntregues){
                System.out.println("\tQuantidade Entregue: " + produto.getQuantidadeAFornecer() + " | Quantidade Pedida: " + produto.getQuantidadePedida());
            }
            System.out.println();
        }


    }

    private static Map<Local, Local> transformListIntoMap(List<ParClienteHub> clienteHubs) {
        Map<Local, Local> toReturn = new HashMap<>();

        for(ParClienteHub clienteHub : clienteHubs){
            toReturn.put(clienteHub.getCliente(), clienteHub.getEmpresa());
        }

        return toReturn;
    }

    private static Integer getDistMinHub(Graph<Local, Integer> map, Local hub, List<Local> HubsPassados, List<Local> hubs, List<Map.Entry<String, Integer>> pontosDistancia, List<Local> caminho) {
        ArrayList<Integer> distancias = new ArrayList<>();

        Integer minDist = Integer.MAX_VALUE;
        Local hubMaisProx = null;

        ArrayList<LinkedList<Local>> paths = new ArrayList<>();

        Algorithms.shortestPaths(
                map,
                hub,
                Integer::compare,
                Integer::sum,
                0,
                paths,
                distancias
        );

        for(Local h : hubs){
            if(!HubsPassados.contains(hub)){
                if(distancias.get(map.key(hub)) < minDist){
                    hubMaisProx = hub;
                    minDist = distancias.get(map.key(hub));
                }
            }
        }

        LinkedList<Local> path = paths.get(map.key(hubMaisProx));

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

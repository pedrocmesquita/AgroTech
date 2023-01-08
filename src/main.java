import CsvReader.CsvReader;
import Domain.CabazExpedicao;
import Domain.Destinatário;
import Domain.Expedicao;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Domain.Local;
import Shared.constants.Files;
import US.US304;
import US.US308;
import US.US309;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class main {
    public static void main(String[] arg) throws Exception {

        CsvReader readFiles = new CsvReader();
        //File1-distancias...File2-clientesProdutores..File3-cabazes

        File file1 = new File(Files.s_distancias);
        File file2 = new File(Files.s_clientes_produtores);
        File file3 = new File(Files.s_cabazes);

        final Graph<Local, Integer> map = new MapGraph<>(false);

        //MAPA DOS CABAZES
        /* Cada KEY INTEGER representa um dia, cada dia vai ter um mapa onde as KEYS sao os DESTINATÁRIOS, cada DESTINATÁRIO vai ter uma lista que contem ARRAYS DE FLOATS..
        (LISTA DE ARRAYS DE FLOATS TORNA MAIS FACIL A DIFERENCIAÇAO DOS PRODUTOS DE DIAS ANTERIORES QUE AINDA PODEM SER VENDIDOS NO DIA)
         */
        final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();


        BST<Local> locais = readFiles.ReadClientesProdutores(file2, ",");//arvore dos locais

        BST<Destinatário> destinatários = readFiles.getDestinatários();//arvore dos destinatários

        readFiles.ReadDistancias(file1, file2, ",", map, locais);//grafo (vertices-locais)

        readFiles.ReadCabaz(file3, ",", cabazes);//mapa dos Cabazes

/*

        float array[];
        List<float []> lista;
        for (Integer dia: cabazes.keySet()){
            System.out.println("\n"+dia);
            for (Destinatário dest:cabazes.get(dia).keySet()){
                System.out.println("\n"+dest.getName());
                lista=cabazes.get(dia).get(dest);
                //array=cabazes.get(dia).get(dest);
                for (float [] ll:lista){
                    for (float  produtos:ll){
                        System.out.print(" "+produtos);
                    }
                }
            }
        }

        US302 us=new US302(map);

        Map<String, Map<String,Integer>> lista=us.ligaçoesMinimas();


        for (String key1:lista.keySet()){
            System.out.println(key1);
            for (String key2:lista.get(key1).keySet()){
                System.out.println("numero minimo de ligaçoes para "+key2+"->"+lista.get(key1).get(key2));
            }

        }


        //VERTICES
        for (Local loc:map.vertices()){
            System.out.println(loc.getName()+"   "+"  "+loc.getLng()+"   "+"  "+loc.getLat()+"  "+loc.getDestinatário());
        }


        //Edges
        for (Edge<Local,Integer> ed:map.edges()){
            System.out.println(ed.getVOrig().getName()+"----"+ed.getVDest().getName()+"  ->"+ed.getWeight());
        }

    }

        /*
        //US 303
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais=readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        List<Local> hubs = US303.findHubs(graph,3);
    
        for (Local x : hubs)
        {
            System.out.println(x.getName() + " - " + x.getDestinatário());
        }
        */
/*
        US308 us308 = new US308();
        List<CabazExpedicao> produtores = new ArrayList<>();
        List<CabazExpedicao> clientes = new ArrayList<>();
        List<Expedicao> lista;

        us308.gerarListaClientesEProdutores(cabazes, produtores, clientes);
        lista = us308.gerarLista(clientes, produtores, map);
        us308.printList(lista, 1);
 */
        
        /*
        //US309
        US308 us308 = new US308();
        List<CabazExpedicao> producers = new ArrayList<>();
        List<CabazExpedicao> clients = new ArrayList<>();
        List<Expedicao> lista;

        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        lista = US309.generateExpeditionListNClosestProd(clients, producers, map,0);
        us308.printList(lista, 1);
        */
    }
}
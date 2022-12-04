import CsvReader.CsvReader;
import Domain.Destinatário;
import Domain.GrafoDistancia;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Domain.Local;
import US.US302;
import US.US305;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class main {
    public static void main(String[] arg) throws FileNotFoundException {

        CsvReader readFiles=new CsvReader();

        File file1=new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        File file2=new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");

        final Graph<Local,Integer> map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)


        US302 us=new US302(map);

        Map<String, Map<String,Integer>> lista=us.ligaçoesMinimas();


        for (String key1:lista.keySet()){
            System.out.println(key1);
            for (String key2:lista.get(key1).keySet()){
                System.out.println("numero minimo de ligaçoes para "+key2+"->"+lista.get(key1).get(key2));
            }

        }



/*
        //VERTICES
        for (Local loc:map.vertices()){
            System.out.println(loc.getName()+"   "+"  "+loc.getLng()+"   "+"  "+loc.getLat()+"  "+loc.getDestinatário());
        }


        //Edges
        for (Edge<Local,Integer> ed:map.edges()){
            System.out.println(ed.getVOrig().getName()+"----"+ed.getVDest().getName()+"  ->"+ed.getWeight());
        }
*/


        System.out.println("\n\n");
        GrafoDistancia grafo = new GrafoDistancia(map);
        US305 us305 = new US305(grafo);
        us305.toStringMinimumGraph();

    }


}
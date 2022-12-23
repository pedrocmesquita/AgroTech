import CsvReader.CsvReader;
import Domain.Destinatário;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.GraphCommon.Edge;
import Shared.MapGraphs.MapGraph;
import Domain.Local;
import US.US302;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class main {
    public static void main(String[] arg) throws FileNotFoundException {

        CsvReader readFiles=new CsvReader();
        //File1-distancias...File2-clientesProdutores..File3-cabazes

        File file1=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        File file2=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");
        File file3=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\cabazes_small.csv");

        final Graph<Local,Integer> map=new MapGraph<>(false);

        //MAPA DOS CABAZES
        /* Cada KEY INTEGER representa um dia, cada dia vai ter um mapa onde as KEYS sao os DESTINATÁRIOS, cada DESTINATÁRIO vai ter uma lista que contem ARRAYS DE FLOATS..
        (LISTA DE ARRAYS DE FLOATS TORNA MAIS FACIL A DIFERENCIAÇAO DOS PRODUTOS DE DIAS ANTERIORES QUE AINDA PODEM SER VENDIDOS NO DIA)
         */
        final Map<Integer,Map<Destinatário,List<float []>>> cabazes=new HashMap<>();




        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais

        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários

        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        readFiles.ReadCabaz(file3,",",cabazes);//mapa dos Cabazes




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









/*

        US302 us=new US302(map);

        Map<String, Map<String,Integer>> lista=us.ligaçoesMinimas();


        for (String key1:lista.keySet()){
            System.out.println(key1);
            for (String key2:lista.get(key1).keySet()){
                System.out.println("numero minimo de ligaçoes para "+key2+"->"+lista.get(key1).get(key2));
            }

        }

*/


        //VERTICES
        for (Local loc:map.vertices()){
            System.out.println(loc.getName()+"   "+"  "+loc.getLng()+"   "+"  "+loc.getLat()+"  "+loc.getDestinatário());
        }


        //Edges
        for (Edge<Local,Integer> ed:map.edges()){
            System.out.println(ed.getVOrig().getName()+"----"+ed.getVDest().getName()+"  ->"+ed.getWeight());
        }
















    }


}
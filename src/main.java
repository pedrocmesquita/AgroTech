import CsvReader.CsvReader;
import Domain.Destinatário;
import Domain.GrafoDistancia;
import Shared.BST.BST;
import Shared.Graphs.Edge;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;
import Domain.Local;
import US.US305;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] arg) throws FileNotFoundException {

        CsvReader readFiles=new CsvReader();

        File file1=new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        File file2=new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Big\\clientes-produtores_big.csv");

        final Graph<Local,Integer> map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)
        System.out.println(readFiles);

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


        // US305
        //Scanner sc = new Scanner(System.in);
        //int v = 5, e = 7;
        ArrayList<GrafoDistancia> grafoDistancias = readFiles.ReadDistancias(file1,file2,",");
        int v = map.numVertices(), e = map.numEdges()/2;
        US305 us305 = new US305(v,e);
        /*
        for(int i = 0; i < e; i++){

            System.out.println("Enter source value for edge["+ i +"]");
            us305.arrayEdges[i].origem= sc.nextInt();

            System.out.println("Enter destination value for edge["+ i +"]");
            us305.arrayEdges[i].destino = sc.nextInt();

            System.out.println("Enter weight for edge["+i+"]");
            us305.arrayEdges[i].peso = sc.nextInt();
        }


         */


        us305.controller(v,e, grafoDistancias);
        //us305.KruskalAlgo();

    }
}

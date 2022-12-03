import CsvReader.CsvReader;
import Domain.Destinatário;
import Domain.GrafoDistancia;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Domain.Local;
import Shared.MapGraphs.MapGraph;
import US.US305;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] arg) throws Exception {

        CsvReader readFiles=new CsvReader();

        File file1=new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Big\\distancias_big.csv");
        File file2=new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Big\\clientes-produtores_big.csv");

        final Graph<Local,Integer> map=new MapGraph<>(true);


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
        US305.getMinimunPath(map);
        /*
        ArrayList<GrafoDistancia> grafoDistancia = readFiles.ReadDistancias(file1,",");
        int v = map.numVertices(), e = map.numEdges()/2;
        if (map.isDirected()){
            e = map.numEdges();
        }

        US305 grafo = new US305(v, e);

        for (int i = 0; i < e ; i++){
            grafo.arrayEdges[i].origem = grafoDistancia.get(i).getIdLoc1();
            grafo.arrayEdges[i].destino = grafoDistancia.get(i).getIdLoc2();
            grafo.arrayEdges[i].peso = grafoDistancia.get(i).getDistancia();
        }
        grafo.KruskalAlgo();

         */


    }

}

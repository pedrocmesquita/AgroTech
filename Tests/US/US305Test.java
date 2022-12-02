package US;

import CsvReader.CsvReader;
import Domain.GrafoDistancia;
import Domain.Local;
import Shared.BST.BST;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class US305Test {
    static final Graph<Local,Integer> map=new MapGraph<>(false);


    @Test
    void kruskalAlgo() {
        US305 grafo = new US305(0,0);
        assertEquals("The graph does not exists.", "The graph does not exists.");
    }

    @Test
    void setOfElement() {
    }

    @Test
    void union() {
    }

    @Test
    void test1() throws Exception {

        US305 grafo = new US305(5,7);

        grafo.arrayEdges[0].origem = 0;
        grafo.arrayEdges[0].destino = 1;
        grafo.arrayEdges[0].peso = 8;

        grafo.arrayEdges[1].origem = 0;
        grafo.arrayEdges[1].destino = 2;
        grafo.arrayEdges[1].peso = 5;

        grafo.arrayEdges[2].origem = 1;
        grafo.arrayEdges[2].destino = 2;
        grafo.arrayEdges[2].peso = 9;

        grafo.arrayEdges[3].origem = 1;
        grafo.arrayEdges[3].destino = 3;
        grafo.arrayEdges[3].peso = 11;

        grafo.arrayEdges[4].origem = 2;
        grafo.arrayEdges[4].destino = 3;
        grafo.arrayEdges[4].peso = 15;

        grafo.arrayEdges[5].origem = 2;
        grafo.arrayEdges[5].destino = 4;
        grafo.arrayEdges[5].peso = 10;

        grafo.arrayEdges[6].origem = 4;
        grafo.arrayEdges[6].destino = 3;
        grafo.arrayEdges[6].peso = 7;

        grafo.KruskalAlgo();

        assertEquals("0 - 2: 5\n" +
                "4 - 3: 7\n" +
                "0 - 1: 8\n" +
                "2 - 4: 10", "0 - 2: 5\n" +
                "4 - 3: 7\n" +
                "0 - 1: 8\n" +
                "2 - 4: 10");
    }

}
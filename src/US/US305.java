package US;

import Domain.GrafoDistancia;
import Domain.Local;
import Shared.Graphs.Edge;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * The type Us 305.
 */
public class US305 {

    /**
     * Edge do grafo
     */
    public static class Edges implements Comparable<Edges> {

        public int origem;
        public int destino;
        public int peso;

        public int compareTo(Edges edgesToCompare) {
            return this.peso - edgesToCompare.peso;
        }
    }

    /**
     * The type Subset.
     */
    static class Subset {
        int parent, caminho;
    }

    int vertices , edges;
    public Edges[] arrayEdges;


    /**
     * construir o grafo para todos os edges da leitura
     *
     * @param vertices the vertices
     * @param edges    the edges
     */
    public US305(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        arrayEdges = new Edges[this.edges];

        for (int i = 0; i < edges; i++)
            arrayEdges[i] = new Edges();
    }


    public void KruskalAlgo() throws Exception {
        if (vertices == 0 || edges == 0) throw new Exception("The graph does not exists.");

        Edges[] resultadoFinal = new Edges[vertices];

        for (int i = 0; i < vertices; i++)
            resultadoFinal[i] = new Edges();

        Arrays.sort(arrayEdges);

        Subset[] subsetArray = new Subset[vertices];
        // aloocate memory to create vertices subsets
        for (int i = 0; i < vertices; i++)
            subsetArray[i] = new Subset();

        // it is used to create subset with single element
        for (int vertex = 0; vertex < vertices; vertex++) {
            subsetArray[vertex].parent = vertex;
            subsetArray[vertex].caminho = 0;
        }
        int i = 0; // reset

        // ‘loop’ para os caminhos mais curtos
        int newEdge = 0;
        while (newEdge < vertices - 1) {

            Edges nextEdge = arrayEdges[i++]; //proximo edge

            int proximoInicio = SetOfElement(subsetArray, nextEdge.origem);
            int proximoDestino = SetOfElement(subsetArray, nextEdge.destino);

            //se nao houver ligacao entre edges, cria-se e adiciona-se ao resultado
            if (proximoInicio != proximoDestino) {
                resultadoFinal[newEdge++] = nextEdge;
                Union(subsetArray, proximoInicio, proximoDestino);
            }
        }
        for (i = 0; i < newEdge; ++i)
            System.out.println(resultadoFinal[i].origem + " - " + resultadoFinal[i].destino + ": " + resultadoFinal[i].peso);
    }

    /**
     * get set of element
     *
     * @param subsetArray the subset array
     * @param nextEdge           the
     * @return the int
     */

    int SetOfElement(Subset[] subsetArray, int nextEdge) {
        if (subsetArray[nextEdge].parent != nextEdge) subsetArray[nextEdge].parent = SetOfElement(subsetArray, subsetArray[nextEdge].parent);
        return subsetArray[nextEdge].parent;
    }

    /**
     * Une dois subsets com os caminhos mais curtos
     *
     * @param subsetArray     the subset array
     * @param inicio      the source root
     * @param destino the destination root
     */
    void Union(Subset[] subsetArray, int inicio, int destino) throws Exception {

        int proximoInicio = SetOfElement(subsetArray, inicio);
        int proximoDestino = SetOfElement(subsetArray, destino);

        if (subsetArray[proximoInicio].caminho < subsetArray[proximoDestino].caminho)
            subsetArray[proximoInicio].parent = proximoDestino;

        else if (subsetArray[proximoInicio].caminho > subsetArray[proximoDestino].caminho)
            subsetArray[proximoDestino].parent = proximoInicio;

        else {
            subsetArray[proximoDestino].parent = proximoInicio;
            subsetArray[proximoInicio].caminho++;
        }
    }


    public static Graph<Local, Double> mstGraph(MapGraph mapGraph) {

        MapGraph<Local, Double> mst = new MapGraph<>(mapGraph.isDirected());
        ArrayList<Edge<Local, Double>> edgesList = new ArrayList<>();
        LinkedList<Local> connected;

        for (Object vertex : mapGraph.vertices()) {
            mst.addVertex((Local) vertex);
        }
        for (Object edge: mapGraph.edges()) {
            edgesList.add((Edge<Local, Double>) edge);
        }

        //Collections.sort(edgesList);

        //falta class algoritmo
        return mst;
    }
    /*
    public int[][] vertexNameToIntPosition(Graph<Local,Integer> map){

        int[][] vertexPositions = new int[edges][3];

        int i = 0;
        for (Edge<Local, Integer> ed: map.edges()) {

            String strOri = ed.getVOrig().getName();
            String strDest = ed.getVDest().getName();
            vertexPositions[i][0] = Integer.parseInt(strOri.substring(strOri.indexOf("CT") + 2));
            vertexPositions[i][1] = Integer.parseInt(strDest.substring(strDest.indexOf("CT") + 2));
            vertexPositions[i][2] = Integer.parseInt(String.valueOf(ed.getWeight()));

            i++;
        }

        //System.out.println(vertexPositions[2][0] + ":" + vertexPositions[2][1] + "-->" + vertexPositions[2][2]);
        return vertexPositions;
    }

     */
}

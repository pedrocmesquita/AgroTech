package US;

import Domain.Local;
import Shared.Graphs.Edge;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;

import java.util.Arrays;

/**
 * The type Us 305.
 */
public class US305 {

    /**
     * Edge do grafo
     */
    public class Edges implements Comparable<Edges> {

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
    class Subset {
        int parent, caminho;
    }

    int vertices, edges;
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

        for (int i = 0; i < edges; ++i)
            arrayEdges[i] = new Edges();
    }

    public US305() {}

    /**
     * Apply kruskal.
     */

    public void KruskalAlgo() {

        Edges resultadoFinal[] = new Edges[vertices];
        int newEdge = 0;

        for (int i = 0; i < vertices; ++i)
            resultadoFinal[i] = new Edges();

        Arrays.sort(arrayEdges);

        Subset subsetArray[] = new Subset[vertices];
        // aloocate memory to create vertices subsets
        for (int i = 0; i < vertices; ++i)
            subsetArray[i] = new Subset();

        // it is used to create subset with single element
        for (int vertex = 0; vertex < vertices; ++vertex) {
            subsetArray[vertex].parent = vertex;
            subsetArray[vertex].caminho = 0;
        }
        int i = 0; // reset

        // ‘loop’ para os caminhos mais curtos
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
     * @param i           the
     * @return the int
     */

    int SetOfElement(Subset subsetArray[], int i) {
        if (subsetArray[i].parent != i)
            subsetArray[i].parent = SetOfElement(subsetArray, subsetArray[i].parent);
        return subsetArray[i].parent;
    }

    /**
     * Une dois subsets com os caminhos mais curtos
     *
     * @param subsetArray     the subset array
     * @param inicio      the source root
     * @param destino the destination root
     */
    void Union(Subset subsetArray[], int inicio, int destino) {

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

    public void controller(){

        Graph<Local, Integer> map = new MapGraph<>(true);
        int numberVertices = map.numVertices();
        int numberEdges = map.numEdges();
        US305 grafo = new US305(numberVertices,numberEdges);

        for (Edge<Local, Integer> edge : map.edges()){
            int i = 0;

            //grafo.arrayEdges[i].origem = edge.getVOrig().getName();
            //grafo.arrayEdges[i].destino =edge.getVDest().getName();
            grafo.arrayEdges[i].peso = edge.getWeight();
        }

        grafo.KruskalAlgo();
    }
}

package US;

import Domain.Local;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Edge;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;

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

        /**
         * The Origem.
         */
        public int origem;
        /**
         * The Destino.
         */
        public int destino;
        /**
         * The Peso.
         */
        public int peso;

        public int compareTo(Edges edgesToCompare) {
            return this.peso - edgesToCompare.peso;
        }
    }

    /**
     * The type Subset.
     */
    static class Subset {
        /**
         * The Parent.
         */
        int parent, /**
         * The Caminho.
         */
        caminho;
    }

    /**
     * The Vertices.
     */
    int vertices , /**
     * The Edges.
     */
    edges;
    /**
     * The Array edges.
     */
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


    /**
     * Kruskal algo.
     *
     * @throws Exception the exception
     */
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
     * @param nextEdge    the
     * @return the int
     */
    int SetOfElement(Subset[] subsetArray, int nextEdge) {
        if (subsetArray[nextEdge].parent != nextEdge) subsetArray[nextEdge].parent = SetOfElement(subsetArray, subsetArray[nextEdge].parent);
        return subsetArray[nextEdge].parent;
    }

    /**
     * Une dois subsets com os caminhos mais curtos
     *
     * @param subsetArray the subset array
     * @param inicio      the source root
     * @param destino     the destination root
     * @throws Exception the exception
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


    /**
     * Minimun spanning tree que conecta todos os vertices pelo o menor caminho.
     *
     * @param mapGraph the map graph
     * @return the graph
     */
    public static Graph<Local, Integer> mstGraph(Graph<Local,Integer> mapGraph) {

        MapGraph<Local, Integer> mst = new MapGraph<>(mapGraph.isDirected());
        ArrayList<Edge<Local, Integer>> edgesList = new ArrayList<>();
        LinkedList<Local> connected;

        for (Local vertex : mapGraph.vertices()) {
            mst.addVertex(vertex);
        }
        for (Edge<Local, Integer> edge: mapGraph.edges()) {
            edgesList.add(edge);
        }

        Collections.sort(edgesList);

        for (Edge<Local, Integer> edge : edgesList){
            connected = Algorithms.DepthFirstSearch(mst, edge.getVOrig());

            if (!connected.contains(edge.getVDest())){
                mst.addEdge(edge.getVOrig(), edge.getVDest(), edge.getWeight());
            }
        }
        return mst;
    }


    /**
     * get caminho minimo entre clientes e produtores
     *
     * @param mapGraph the map graph
     * @return the minimun path
     */
    public static Graph<Local, Integer> getMinimunPath(Graph<Local,Integer> mapGraph) {
        Graph<Local, Integer> grafo = mstGraph(mapGraph);
        Graph<Local, Integer> result = new MapGraph<>(mapGraph.isDirected());

        Edge<Local, Integer> aux;
        double pesoFinal= 0;

        for (Edge<Local, Integer> edge : grafo.edges()){

            if (edge.getVDest().getName().startsWith("E")){ //se comecar por E(mpresa)
                for (Local l : grafo.adjVertices(edge.getVDest())){

                    aux = grafo.edge(edge.getVDest(), l);

                    if (!(aux.getVDest() == edge.getVOrig())) {
                        result.addEdge(edge.getVOrig(), l, edge.getWeight() + aux.getWeight()); //adiciona caminho mais que curto com empresa como intermediario
                    }
                }
            } else {
                result.addEdge(edge.getVOrig(), edge.getVDest(), edge.getWeight());
            }

        }


        for (Edge<Local, Integer> edge : result.edges()){
            pesoFinal += edge.getWeight();
        }

        System.out.println("Minimum cost = " + pesoFinal);
        return result;
    }
}

package Domain;

import java.util.Arrays;

/**
 * The type Us 305.
 */
public class Kruskall {

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
    public Kruskall(int vertices, int edges) {
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
}

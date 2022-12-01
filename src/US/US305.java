package US;

import Domain.GrafoDistancia;
import java.util.ArrayList;
import java.util.Arrays;

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


    public void KruskalAlgo() {

        Edges[] resultadoFinal = new Edges[vertices];
        int newEdge = 0;

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
    void Union(Subset[] subsetArray, int inicio, int destino) {

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

    public void controller(int v, int e, ArrayList<GrafoDistancia> grafoDistancia){

        US305 grafo = new US305(v,e);

        for (int i = 0; i < e ; i++){

            grafo.arrayEdges[i].origem = grafoDistancia.get(i).getIdLoc1();
            grafo.arrayEdges[i].destino = grafoDistancia.get(i).getIdLoc2();
            grafo.arrayEdges[i].peso = grafoDistancia.get(i).getDistancia();

        }
        grafo.KruskalAlgo();
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

package US;

import java.util.Arrays;

/**
 * The type Us 305.
 */
public class US305 {

    private int vertices;
    private int edge;
    private Edge arrEdge[];


    /**
     * The type Edge.
     */
// CLASS EDGE PARA CRIAR EDGE DO GRAFO COM COMPARABLE
    class Edge implements Comparable<Edge>{

        private int inicio;
        private int destino;
        private int tamanho;

        @Override
        public int compareTo(Edge o) {
            return this.tamanho - o.tamanho;
        }
    }

    /**
     * The type Subset
     */
    class Subset {
        private int parent;
        private int valor;
    }

    /**
     * Find -
     *
     * @param subsets the subsets
     * @param aux     the aux
     * @return the int
     */
    int find(Subset subsets[], int aux) {
        if (subsets[aux].parent != aux) subsets[aux].parent = find(subsets, subsets[aux].parent);
        return subsets[aux].parent;
    }

    /**
     * Une dois sets consoante o menor valor
     *
     * @param subsets the subsets
     * @param x       the x
     * @param y       the y
     */
    void union(Subset subsets[], int x, int y) {
        int proximoInicio = find(subsets, x);
        int proximoDestino = find(subsets, y);

        if (subsets[proximoInicio].valor < subsets[proximoDestino].valor)
            subsets[proximoInicio].parent = proximoDestino;

        else if (subsets[proximoInicio].valor > subsets[proximoDestino].valor)
            subsets[proximoDestino].parent = proximoInicio;

        else {
            subsets[proximoDestino].parent = proximoInicio;
            subsets[proximoInicio].valor++;
        }
    }

    /**
     * Instantiates a new Us 305.
     *
     * @param vertice the vertice
     * @param edge    the edge
     */
    public US305(int vertice, int edge) {
        this.vertices = vertice;
        this.edge = edge;
        arrEdge = new Edge[this.edge];

        for (int i = 0; i < edge; i++) {
            arrEdge[i] = new Edge();
        }
    }

    /**
     * método que implementa o algoritmo de kruskal
     */
    void kruskal(){
        Edge resultado[] = new Edge[vertices];
        int aux = 0;
        for (int i = 0; i < vertices; i++) {
            resultado[i] = new Edge();
        }

        Arrays.sort(arrEdge);
        Subset subset[] = new Subset[vertices];
        for (int i = 0; i < vertices; i++) {
            subset[i] = new Subset();

            for (int j = 0; j < vertices; j++) {
                subset[j].parent = j;
                subset[j].valor = 0;
            }

            i = 0;

            while ( aux < vertices - 1){
                Edge proximo;
                proximo = arrEdge[i++];

                int x = find(subset, proximo.inicio), y = find(subset, proximo.destino);
                if (x != y ){
                    resultado[aux++] = proximo;
                    union(subset, x, y);
                }
            }

            // imprime o resultado final
            for (int j = 0; j < aux; j++) {
                System.out.println(resultado[i].inicio + "-" + resultado[i].destino + " = " + resultado[i].tamanho);
            }
        }
    }
}

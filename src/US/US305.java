package US;

import Domain.GrafoDistancia;
import Domain.Local;
import Shared.GraphCommon.Algorithms;
import Shared.MapGraphs.MapGraph;


public class US305 {
    private GrafoDistancia grafoDistancia;
    private Algorithms algorithms;

    public US305(GrafoDistancia grafoDistancia) {
        this.grafoDistancia = grafoDistancia;
        this.algorithms = new Algorithms();
    }

    public MapGraph<Local, Integer> minimumGraph(){

        MapGraph<Local, Integer> graph;

        graph = algorithms.mstGraph(grafoDistancia.getMapGraph());

        return graph;
    }

    public void toStringMinimumGraph(){

        MapGraph<Local, Integer> graph;

        graph = minimumGraph();

        System.out.println(">>>>> VERTICES: " + graph.numVertices() + "\n>>>> EDGES: " + graph.numEdges() + "\n" + graph.edges());
    }

}

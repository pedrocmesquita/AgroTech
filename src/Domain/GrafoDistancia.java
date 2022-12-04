package Domain;

import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;


public class GrafoDistancia {


        public Graph<Local, Integer> graph;

        public GrafoDistancia(Graph graph) { this.graph = new MapGraph(false); }

        public Graph getMapGraph() { return graph; }

        public void setMapGraph(Graph graph) { this.graph = graph; }

}

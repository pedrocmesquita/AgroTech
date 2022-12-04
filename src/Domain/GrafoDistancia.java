package Domain;

import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;


public class GrafoDistancia {


        public Graph<Local, Integer> graph;

        public GrafoDistancia(Graph<Local, Integer> graph) {
                this.graph = graph;
        }

        public Graph getMapGraph() { return graph; }

        public void setMapGraph(Graph graph) { this.graph = graph; }

}

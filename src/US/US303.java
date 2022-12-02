package US;

import java.util.*;

import Domain.Hub;
import Domain.Local;
import Shared.BST.BST;
import Shared.Graphs.Edge;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;
import US.US305;

/**
 * Definir os hubs da rede de distribuição, ou seja, encontrar as N empresas mais próximas de
 * todos os pontos da rede (clientes e produtores agrícolas). A medida de proximidade deve ser
 * calculada como a média do comprimento do caminho mais curto de cada empresa a todos os
 * clientes e produtores agrícolas. (small, N=3)
 *
 * @author Miguel Marques 1201078
 */
public class US303
{
    public List<Hub> findHubs(int n)
    {
        final Graph<Local, Integer> map = new MapGraph<>(true);
        BST<Local> locais = new BST<>();
        
        ArrayList<Local> comps = new ArrayList<>();
        ArrayList<Local> dest = new ArrayList<>();
        
        //get all companies
        for (Local l : map.vertices())
        {
            if (l.getName().charAt(0) == 'E')
            {
                comps.add(l);
            }
        }
        
        //get all clients and producers
        for (Local l : map.vertices())
        {
            if ((l.getName().charAt(0) == 'C') || (l.getName().charAt(0) == 'P'))
            {
                dest.add(l);
            }
        }
        
        //for each company in the graph, calculate proximity measure
        for (Local x : comps)
        {
            //use kruskall's algorithm to compute shortest path to all clients and producers
        }
        
        //order the list and return first N companies
        return null;
    }
    
    /*
    public <V, E> Graph<V, E> kruskall(Graph<Local, String> g)
    {
        ArrayList<V> mst = new ArrayList<V>(g.vertices());
    
        ArrayList<E> lstEdges = new ArrayList<E>(g.edges());
    
        Collections.sort(mst);
        Collections.sort(lstEdges); //in ascending order of weight
        
        for (E e : lstEdges)
        {
            LinkedList<V> connectedVerts = graph.Algorithms.DepthFirstSearch(mst, e.getVOrig());
            if (!connectedVerts.contains(e.getVDest()))
            {
                mst.add(e);
            }
        }
        return mst;
    }*/
}
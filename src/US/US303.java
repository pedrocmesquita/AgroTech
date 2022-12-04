package US;

import java.util.*;
import java.util.function.BinaryOperator;

import Domain.Local;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Graph;

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
    public static List<Local> findHubs(Graph<Local, Integer> map, int n)
    {
        //invalid size
        if (n <= 0)
        {
            return null;
        }
        
        //Graph <Local,Integer> map = new MapGraph<Local,Integer>(mapGraph.isDirected());
        //BST<Local> locais = new BST<>();
        
        ArrayList<Local> comps = new ArrayList<>();
        ArrayList<Local> dest = new ArrayList<>();
        
        for (Local l : map.vertices())
        {
            //get all companies
            if (l.getDestinatário().charAt(0) == 'E')
            {
                comps.add(l);
            }
            
            //get all clients and producers
            else if ((l.getDestinatário().charAt(0) == 'C') || (l.getDestinatário().charAt(0) == 'P'))
            {
                dest.add(l);
            }
        }
        
        if ((comps.size() == 0) || (dest.size() == 0))
        {
            return null;
        }
        
        Map<Local, Integer> company_sum = new HashMap<>();
        
        //for each company
        for (Local c : comps)
        {
            int sum_dist = 0;
            
            //for each client/producer
            //for (Local d : dest)
            //{
            //System.out.println(d.getDestinatário());
            
            //Comparator<Integer> ce = Comparator.comparingInt(o -> o);
            //BinaryOperator<Integer> sum = Integer::sum;
            
            ArrayList<LinkedList<Local>> shortPath = new ArrayList<>();
            ArrayList<Integer> dists = new ArrayList<>();
            boolean temp = shortestPaths(map, c, java.lang.Integer::compare, java.lang.Integer::sum, 0, shortPath, dists);
                
                /*
                if (temp != null)
                {
                    sum_dist += temp;
                }
                */
            
            //vertice c exists
            if (temp)
            {
                for (Integer x : dists)
                {
                    if (x != null)
                    {
                        sum_dist += x;
                    }
                }
            }
            //}
            
            //System.out.println(c.getDestinatário());
            
            //calculate proximity measure
            company_sum.put(c, sum_dist / dest.size());
        }
        
        //convert map into list
        List<Map.Entry<Local, Integer>> compLst = new LinkedList<>(company_sum.entrySet());
        
        //sort elements by ascending order
        compLst.sort(Map.Entry.comparingByValue());
        
        List<Local> hubs = new ArrayList<>();
        
        //subList() will throw an oob exception if n given is bigger than number of companies
        if (compLst.size() < n)
        {
            n = compLst.size();
        }
        
        //create a sublist from elements 0 to n
        for (Map.Entry<Local, Integer> entry : compLst.subList(0, n))
        {
            //System.out.println(entry.getKey().getDestinatário());
            hubs.add(entry.getKey());
        }
        
        //order the list and return first N companies
        return hubs;
    }
    
    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig, Comparator<E> ce, BinaryOperator<E> sum, E zero, ArrayList<LinkedList<V>> paths, ArrayList<E> dists)
    {
        if (! g.validVertex(vOrig))
        {
            return false;
        }
        
        paths.clear();
        dists.clear();
        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts]; //default value: false
        @SuppressWarnings("unchecked")
        V[] pathKeys = (V[]) new Object[numVerts];
        @SuppressWarnings("unchecked")
        E[] dist = (E[]) new Object[numVerts];
        Algorithms.initializePathDist(numVerts, pathKeys, dist);
        
        Algorithms.shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);
        
        dists.clear();
        paths.clear();
        for (int i = 0 ; i < numVerts ; i++)
        {
            paths.add(null);
            dists.add(null);
        }
        
        for (V vDst : g.vertices())
        {
            if (((Local) vDst).getDestinatário().charAt(0) != 'E')
            {
                int i = g.key(vDst);
                if (dist[i] != null)
                {
                    LinkedList<V> shortPath = new LinkedList<>();
                    Algorithms.getPath(g, vOrig, vDst, pathKeys, shortPath);
                    paths.set(i, shortPath);
                    dists.set(i, dist[i]);
                }
            }
        }
        return true;
    }
}
package US;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;

import Domain.Hub;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Edge;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
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
    public static List<Local> findHubs(Graph<Local, Integer> map, int n)
    {
        //invalid size
        if(n <= 0)
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
            for (Local d : dest)
            {
                //System.out.println(d.getDestinatário());
                
                //Comparator<Integer> ce = Comparator.comparingInt(o -> o);
                //BinaryOperator<Integer> sum = Integer::sum;
                
                LinkedList<Local> shortPath = new LinkedList<>();
                Integer temp = Algorithms.shortestPath(map, c, d, Integer::compare, Integer::sum, 0, shortPath);
                
                if (temp != null)
                {
                    sum_dist += temp;
                }
            }
    
            //System.out.println(c.getDestinatário());
            
            //calculate proximity measure
            company_sum.put(c, sum_dist/dest.size());
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
        for (Map.Entry<Local, Integer> entry : compLst.subList(0,n))
        {
            //System.out.println(entry.getKey().getDestinatário());
            hubs.add(entry.getKey());
        }
        
        //order the list and return first N companies
        return hubs;
    }
}
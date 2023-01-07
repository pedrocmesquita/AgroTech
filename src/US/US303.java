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
        if (n <= 0)
        {
            return null;
        }
        
        ArrayList<Local> comps = new ArrayList<>();
        
        for (Local l : map.vertices())
        {
            if (l.getDestinatário().charAt(0) == 'E')
            {
                comps.add(l);
            }
        }
        
        if ((comps.size() == 0))
        {
            return null;
        }
        
        Map<Local, Double> company_sum = new HashMap<>();
        
        for (Local c : comps)
        {
            int sum_dist = 0;
            int counter = 0;
            
            ArrayList<LinkedList<Local>> shortPaths = new ArrayList<>();
            ArrayList<Integer> distances = new ArrayList<>();
            
            Algorithms.shortestPaths(map, c, Integer::compare, Integer::sum, 0, shortPaths, distances);
            
            for (Integer value : distances)
            {
                sum_dist += value;
                counter++;
            }
            
            if (counter != 0)
            {
                double average = sum_dist * 1.0 / counter;
                company_sum.put(c, average);
            }
            
        }
        
        List<Map.Entry<Local, Double>> compLst = new LinkedList<>(company_sum.entrySet());
        
        compLst.sort(Map.Entry.comparingByValue());
        
        List<Local> hubs = new ArrayList<>();
        
        if (compLst.size() < n)
        {
            n = compLst.size();
        }
        
        for (Map.Entry<Local, Double> entry : compLst.subList(0, n))
        {
            hubs.add(entry.getKey());
        }
        
        return hubs;
    }
}
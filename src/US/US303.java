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

        ArrayList<Local> comps = new ArrayList<>();


        for (Local l : map.vertices())
        {
            //get all companies
            if (l.getDestinatário().charAt(0) == 'E')
            {
                comps.add(l);
            }

        }

        //if there are no companies
        if ((comps.size() == 0))
        {
            return null;
        }

        //map to store Companies/avg distance
        Map<Local, Double> company_sum = new HashMap<>();

        //for each company
        for (Local c : comps)
        {
            Double sum_dist = 0.0;
            Double counter = 0.0;

            ArrayList<LinkedList<Local>>  shortPaths = new ArrayList<>();
            ArrayList<Integer> distances = new ArrayList<>();

            Algorithms.shortestPaths(map, c, Integer::compare, Integer::sum, 0, shortPaths, distances);


            for(Integer value : distances){
                sum_dist += value;
                counter++;
            }



            if(counter != 0){
                Double average = sum_dist/counter;
                company_sum.put(c, average);
            }


            //System.out.println(c.getDestinatário());

            //calculate proximity measure

        }

        //convert map into list
        List<Map.Entry<Local, Double>> compLst = new LinkedList<>(company_sum.entrySet());

        //sort elements by ascending order
        compLst.sort(Map.Entry.comparingByValue());

        List<Local> hubs = new ArrayList<>();

        //subList() will throw an oob exception if n given is bigger than number of companies
        if (compLst.size() < n)
        {
            n = compLst.size();
        }

        //create a sublist from elements 0 to n
        for (Map.Entry<Local, Double> entry : compLst.subList(0,n))
        {
            //System.out.println(entry.getKey().getDestinatário());
            hubs.add(entry.getKey());
        }

        //order the list and return first N companies
        return hubs;
    }
}
package US;

import Domain.CabazExpedicao;
import Domain.Destinatário;
import Domain.Expedicao;
import Domain.Local;
import Domain.US304.ParClienteHub;
import Shared.BST.BST;
import Shared.GraphCommon.Algorithms;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Shared.constants.Files;
import CsvReader.CsvReader;

import java.io.File;
import java.util.*;

/**
 * Gerar uma lista de expedição para um determinado dia que forneça apenas com os N
 * produtores agrícolas mais próximos do hub de entrega do cliente.
 * Critério de Aceitação: A lista de expedição deve indicar para cada cliente/cabaz, os produtos,
 * quantidade encomendada/expedida e o produtor que forneceu o produto.
 *
 * @author Miguel Marques 1201078
 */
public class US309
{
    private static List<String> findNClosestProducersToHub(Graph<Local, Integer> map, Local hub, int n)
    {
        if (n <= 0)
        {
            return null;
        }
        
        ArrayList<Local> prods = new ArrayList<>();
        
        for (Local l : map.vertices())
        {
            //get all companies
            if (l.getDestinatário().charAt(0) == 'P')
            {
                prods.add(l);
            }
        }
        
        if (prods.isEmpty())
        {
            return null;
        }
        
        Map<Local, Integer> hub_distance = new HashMap<>();
        
        for (Local p : prods)
        {
            LinkedList<Local> shortPath = new LinkedList<>();
            Integer dist = Algorithms.shortestPath(map, hub, p, Integer::compare, Integer::sum, 0, shortPath);
            
            if (dist != null)
            {
                hub_distance.put(p, dist);
            }
        }
        
        List<Map.Entry<Local, Integer>> hubLst = new LinkedList<>(hub_distance.entrySet());
        
        hubLst.sort(Map.Entry.comparingByValue());
        
        List<String> closestHubs = new ArrayList<>();
        
        if (hubLst.size() < n)
        {
            n = hubLst.size();
        }
        
        for (Map.Entry<Local, Integer> entry : hubLst.subList(0, n))
        {
            closestHubs.add(entry.getKey().getDestinatário());
        }
        
        return closestHubs;
    }
    
    public static List<Expedicao> generateExpeditionListNClosestProd(List<CabazExpedicao> clients, List<CabazExpedicao> producers, Graph<Local, Integer> map, int n)
    {
        List<Expedicao> lista = new ArrayList<>();
        
        for (CabazExpedicao c : clients)
        {
            List<ParClienteHub> delivery_hub = US304.findNearestHubs(map, 1);
            
            List<String> nClosestProd = findNClosestProducersToHub(map, delivery_hub.get(0).getEmpresa(), n);
            
            if ((nClosestProd == null) || nClosestProd.isEmpty())
            {
                return null;
            }
            
            for (CabazExpedicao p : producers)
            {
                if (nClosestProd.contains(p.getDestinatario().getName()))
                {
                    if (p.getDia() == c.getDia())
                    {
                        List<float[]> produtosCliente = c.getProdutos();
                        List<float[]> produtosProdutor = p.getProdutos();
                        
                        for (int i = 0 ; i < produtosCliente.size() ; i++)
                        {
                            float[] arrC = produtosCliente.get(i);
                            float[] arrP = produtosProdutor.get(i);
                            
                            for (int j = 0 ; j < arrC.length ; j++)
                            {
                                if (arrC[j] <= arrP[j] && arrC[j] != 0)
                                {
                                    lista.add(new Expedicao(c.getDestinatario(), p.getDestinatario(), arrC[j], arrP[j], arrP[j] - arrC[j], j + 1, c.getDia()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return lista;
    }
}
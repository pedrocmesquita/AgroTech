package US;

import CsvReader.CsvReader;
import Domain.CabazExpedicao;
import Domain.Destinatário;
import Domain.Expedicao;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Shared.constants.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


class US309Test
{
    CsvReader readFiles = new CsvReader();
    
    @Test
     void test_empty() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);
    
        US308 us308 = new US308();
        List<CabazExpedicao> producers = new ArrayList<>();
        List<CabazExpedicao> clients = new ArrayList<>();
    
        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        List<Expedicao> actual = US309.generateExpeditionListNClosestProd(clients, producers, graph,0);
        
        assertNull(actual);
    }
    @Test
    void test1() throws Exception{
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);

        US308 us308 = new US308();
        List<CabazExpedicao> producers = new ArrayList<>();
        List<CabazExpedicao> clients = new ArrayList<>();

        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        List<Expedicao> actual = US309.generateExpeditionListNClosestProd(clients, producers, graph,1);
        String expected = "P2";
        assertEquals(expected,actual.get(1).getProdutor().getName());
    }
    @Test
    void test2() throws Exception{
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);

        US308 us308 = new US308();
        List<CabazExpedicao> producers = new ArrayList<>();
        List<CabazExpedicao> clients = new ArrayList<>();

        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        List<Expedicao> actual = US309.generateExpeditionListNClosestProd(clients, producers, graph,1);
        String expected = "CT6";
        assertEquals(expected,actual.get(2).getProdutor().getLocal());
    }

}
package US;

import CsvReader.CsvReader;
import Domain.Hub;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Shared.constants.Files;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;


class US303Test
{
    CsvReader readFiles = new CsvReader();
    
    @Test
     void test_empty() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 0);
        //List<Local> expected = null;
        
        assertNull(actual);
    }
    
    @Test
    void test_overflow() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 6);
        List<Local> expected = new ArrayList<>();
        
        expected.add(new Local("CT5","39.823","-7.4931","E3"));
        expected.add(new Local("CT9","40.5364","-7.2683","E4"));
        expected.add(new Local("CT11","39.3167","-7.4167","E2"));
        expected.add(new Local("CT14","38.5243","-8.8926","E1"));
        expected.add(new Local("CT4","41.8","-6.75","E5"));
        
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        
        for (int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getLat(), actual.get(i).getLat());
            assertEquals(expected.get(i).getLng(), actual.get(i).getLng());
            assertEquals(expected.get(i).getDestinatário(), actual.get(i).getDestinatário());
            i++;
        }
    }
    
    @Test
    void test1() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 1);
        List<Local> expected = new ArrayList<>();
    
        expected.add(new Local("CT5","39.823","-7.4931","E3"));
        
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        
        for (int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getLat(), actual.get(i).getLat());
            assertEquals(expected.get(i).getLng(), actual.get(i).getLng());
            assertEquals(expected.get(i).getDestinatário(), actual.get(i).getDestinatário());
            i++;
        }
    }
    
    @Test
    void test2() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 2);
        List<Local> expected = new ArrayList<>();
        
        expected.add(new Local("CT5","39.823","-7.4931","E3"));
        expected.add(new Local("CT9","40.5364","-7.2683","E4"));
        
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        
        for (int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getLat(), actual.get(i).getLat());
            assertEquals(expected.get(i).getLng(), actual.get(i).getLng());
            assertEquals(expected.get(i).getDestinatário(), actual.get(i).getDestinatário());
            i++;
        }
    }
    
    /*
    @Test
    void test3() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.b_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.b_distancias), new File(Files.b_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 3);
        List<Local> expected = new ArrayList<>();
        
        expected.add(new Local("CT146","40.1125","-8.2469","E49"));
        expected.add(new Local("CT142","40.2594","-8.3168","E71"));
        expected.add(new Local("CT33","39.9167","-8.4333","E86"));
        
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        
        for (int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getLat(), actual.get(i).getLat());
            assertEquals(expected.get(i).getLng(), actual.get(i).getLng());
            assertEquals(expected.get(i).getDestinatário(), actual.get(i).getDestinatário());
            i++;
        }
    }
    */
    
    @Test
    void test3() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 3);
        List<Local> expected = new ArrayList<>();
        
        expected.add(new Local("CT5","39.823","-7.4931","E3"));
        expected.add(new Local("CT9","40.5364","-7.2683","E4"));
        expected.add(new Local("CT11","39.3167","-7.4167","E2"));
        
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        
        for (int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getLat(), actual.get(i).getLat());
            assertEquals(expected.get(i).getLng(), actual.get(i).getLng());
            assertEquals(expected.get(i).getDestinatário(), actual.get(i).getDestinatário());
            i++;
        }
    }
    
    @Test
    void test4() throws Exception
    {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.b_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.b_distancias), new File(Files.b_clientes_produtores), ",", graph, locais);
        
        List<Local> actual = US303.findHubs(graph, 4);
        List<Local> expected = new ArrayList<>();
        
        expected.add(new Local("CT146","40.1125","-8.2469","E49"));
        expected.add(new Local("CT142","40.2594","-8.3168","E71"));
        expected.add(new Local("CT209","40.2667","-8.2667","E40"));
        expected.add(new Local("CT33","39.9167","-8.4333","E86"));

        
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        
        for (int i = 0; i < expected.size(); i++)
        {
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getLat(), actual.get(i).getLat());
            assertEquals(expected.get(i).getLng(), actual.get(i).getLng());
            assertEquals(expected.get(i).getDestinatário(), actual.get(i).getDestinatário());
            i++;
        }
    }
}
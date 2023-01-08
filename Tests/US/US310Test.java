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
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class US310Test {
    CsvReader readFiles = new CsvReader();
    US308 us308 = new US308();

    File file1 = new File(Files.s_distancias);
    File file2 = new File(Files.s_clientes_produtores);
    File file3 = new File(Files.s_cabazes);
    //File1-distancias...File2-clientesProdutores..File3-cabazes

    Graph<Local,Integer> graph=new MapGraph<>(false);
    final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();
    List<CabazExpedicao> producers = new ArrayList<>();
    List<CabazExpedicao> clients = new ArrayList<>();
    List<Expedicao> lista;
    final Graph<Local, Integer> map = new MapGraph<>(false);

    @Test
    public void testempty(){

    }
    @Test
    public void test1() throws Exception {
        final Graph<Local,Integer> graph = new MapGraph<>(false);
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.b_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.b_distancias), new File(Files.b_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);
        int numeroHubs = 2;
        List<Local> hubs = US303.findHubs(map, numeroHubs);
        List<Local> produtores1 = map.vertices().stream().filter(p -> p.getName().charAt(0) == 'P').toList();
        US308 us308 = new US308();
        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        lista = us308.gerarLista(clients, producers,graph);
        assert hubs != null;
        US310.minPath(hubs,produtores1,lista,map);
    }
    @Test
    public void test2() throws Exception {
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);
        assertNotNull(1);
    }

}

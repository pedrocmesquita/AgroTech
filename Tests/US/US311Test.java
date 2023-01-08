package US;

import CsvReader.CsvReader;
import Domain.CabazExpedicao;
import Domain.Destinatário;
import Domain.Expedicao;
import Domain.Local;
import Domain.US311.Cliente311;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import Shared.constants.Files;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class US311Test {
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

    @Test
    public void testempty() throws Exception{
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);
        US308 us308 = new US308();
        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        lista = us308.gerarLista(clients, producers,graph);
        List <Object> actual = US311.CalcularEstatisticas(lista);
        assertNotNull(actual.get(1));
    }
    @Test
    public void test1() throws Exception{
        BST<Local> locais = readFiles.ReadClientesProdutores(new File(Files.s_clientes_produtores),",");
        readFiles.ReadDistancias(new File(Files.s_distancias), new File(Files.s_clientes_produtores), ",", graph, locais);
        readFiles.ReadCabaz(new File(Files.s_cabazes), ",", cabazes);
        US308 us308 = new US308();
        us308.gerarListaClientesEProdutores(cabazes, producers, clients);
        lista = us308.gerarLista(clients, producers,graph);
        List <Object> actual = US311.CalcularEstatisticas(lista);
        String expected = "Cliente{nº de cabazes totalmente satisfeitos=0, nº de cabazes parcialmente satisfeitos=1, nº defornecedores=3}";
        assertEquals(expected,actual.get(1).toString());
    }
}

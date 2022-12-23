package US;

import CsvReader.CsvReader;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import org.junit.Test;


import java.io.File;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;


public class US302Test {
    CsvReader readFiles = new CsvReader();
    File file1 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
    File file2 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");
    File file3 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Big\\distancias_big.csv");
    File file4 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Big\\clientes-produtores_big.csv");
    final Graph<Local, Integer> map = new MapGraph<>(false);
    US302 US302 = new US302(map);

    @Test
    public void test_conexosmall() throws Exception {
        BST<Local> locais = readFiles.ReadClientesProdutores(file2, ",");//arvore dos locais
        readFiles.ReadDistancias(file1, file2, ",", map, locais);//grafo (vertices-locais)

        Map<String, Map<String, Integer>> actual = US302.ligaçoesMinimas();


        assertNotNull(actual);
    }

    @Test
    public void test_conexobig() throws Exception {
        BST<Local> locais = readFiles.ReadClientesProdutores(file4, ",");//arvore dos locais
        readFiles.ReadDistancias(file3, file4, ",", map, locais);//grafo (vertices-locais)

        Map<String, Map<String, Integer>> actual = US302.ligaçoesMinimas();

        assertNotNull(actual);
    }
}
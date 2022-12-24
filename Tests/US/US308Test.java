package US;

import CsvReader.CsvReader;
import Domain.Destinatário;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class US308Test {

        CsvReader readFiles = new CsvReader();
        US308 us308 = new US308();
        ArrayList<String> lista = new ArrayList<>();
        //File1-distancias...File2-clientesProdutores..File3-cabazes

        File file1 = new File("C:\\Users\\Ruben\\LEI\\2ANO\\1SEMESTRE\\LAPR3\\projeto\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        File file2 = new File("C:\\Users\\Ruben\\LEI\\2ANO\\1SEMESTRE\\LAPR3\\projeto\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");
        File file3 = new File("C:\\Users\\Ruben\\LEI\\2ANO\\1SEMESTRE\\LAPR3\\projeto\\src\\FICHEIROS_LEITURA\\Small\\cabazes_small.csv");

        final Graph<Local, Integer> map = new MapGraph<>(false);
        final Map<Integer, Map<Destinatário, List<float[]>>> cabazes = new HashMap<>();


        BST<Local> locais = readFiles.ReadClientesProdutores(file2, ",");//arvore dos locais
        BST<Destinatário> destinatários = readFiles.getDestinatários();//arvore dos destinatários

    public US308Test() throws FileNotFoundException {
    }


    @Test
    public void teste1() throws FileNotFoundException {
        readFiles.ReadCabaz(file3,",",cabazes);
        lista = us308.getCabazesAtSomeDay(cabazes,1);
        us308.printList(lista,1);
    }
    @Test
    public void teste2() throws FileNotFoundException {
        readFiles.ReadCabaz(file3,",",cabazes);
        lista = us308.getCabazesAtSomeDay(cabazes,2);
        us308.printList(lista,2);
    }
    @Test
    public void teste3() throws FileNotFoundException {
        readFiles.ReadCabaz(file3,",",cabazes);
        lista = us308.getCabazesAtSomeDay(cabazes,3);
        us308.printList(lista,3);
    }
    @Test
    public void teste4() throws FileNotFoundException {
        readFiles.ReadCabaz(file3,",",cabazes);
        lista = us308.getCabazesAtSomeDay(cabazes,4);
        us308.printList(lista,4);
    }
    @Test
    public void teste5() throws FileNotFoundException {
        readFiles.ReadCabaz(file3,",",cabazes);
        lista = us308.getCabazesAtSomeDay(cabazes,5);
        us308.printList(lista,5);
    }
}

package US;

import CsvReader.CsvReader;
import Domain.Destinatário;
import Domain.Local;
import Domain.CabazExpedicao;
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
import static org.junit.Assert.assertEquals;

public class US308Test {

    CsvReader readFiles = new CsvReader();
    US308 us308 = new US308();
    List<CabazExpedicao> lista = new ArrayList<>();

    File file1 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
    File file2 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");
    File file3 = new File("D:\\Ambiente de trabalho\\ISEP\\2ANO\\LAPR3\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\Small\\cabazes_small.csv");
    //File1-distancias...File2-clientesProdutores..File3-cabazes

    final Graph<Local,Integer> map=new MapGraph<>(false);

    final Map<Integer,Map<Destinatário,List<float []>>> cabazes=new HashMap<>();

    BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais

    BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários

    ArrayList<String> listaProdutores = new ArrayList<>();
    int dia = 0;

    public US308Test() throws FileNotFoundException {
    }


    @Test
    public void teste1() throws FileNotFoundException {
        dia = 1;
        readFiles.ReadDistancias(file1,file2,",",map,locais);
        readFiles.ReadCabaz(file3,",",cabazes);
        listaProdutores = new ArrayList<>();
        lista = us308.getCabazesADay(cabazes, dia);
        us308.printList(lista, dia);
    }
    @Test
    public void teste2() throws FileNotFoundException {
        dia = 2;
        readFiles.ReadCabaz(file3,",",cabazes);
        listaProdutores = new ArrayList<>();
        lista = us308.getCabazesADay(cabazes, dia);
        us308.printList(lista, dia);
    }
    @Test
    public void teste3() throws FileNotFoundException {
        dia = 3;
        readFiles.ReadCabaz(file3,",",cabazes);
        listaProdutores = new ArrayList<>();
        lista = us308.getCabazesADay(cabazes, dia);
        us308.printList(lista, dia);
    }
    @Test
    public void teste4() throws FileNotFoundException {
        dia = 4;
        readFiles.ReadCabaz(file3,",",cabazes);
        listaProdutores = new ArrayList<>();
        lista = us308.getCabazesADay(cabazes, dia);
        us308.printList(lista, dia);
    }
    @Test
    public void teste5() throws FileNotFoundException {
        dia = 5;
        readFiles.ReadCabaz(file3,",",cabazes);
        listaProdutores = new ArrayList<>();
        lista = us308.getCabazesADay(cabazes, dia);
        us308.printList(lista, dia);
    }
    @Test
    public void teste6(){
        Map<Integer,Map<Destinatário,List<float []>>> cabazesNull = new HashMap<>();
        dia = 1;
        assertEquals(null, lista = us308.getCabazesADay(cabazesNull, dia));
    }
}

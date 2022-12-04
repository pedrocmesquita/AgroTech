package US;

import Domain.Destinatário;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import org.junit.Test;

import CsvReader.CsvReader;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;


public class US301Test {

    CsvReader readFiles;
    Graph<Local,Integer> map=null;
    File file2,file1;
    @BeforeEach
    void before(){
        readFiles=new CsvReader();
        file1=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        file2=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");

        map=new MapGraph<>(false);

    }

    @Test
    void teste1() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        file2=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        assertEquals(map.numVertices(),17);


    }

    void teste2() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        file2=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        assertEquals(map.numEdges(),33);


    }

}

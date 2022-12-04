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

import static Shared.constants.Files.*;
import static org.junit.Assert.assertEquals;


public class US301Test {

    CsvReader readFiles;
    Graph<Local,Integer> map=null;
    File file2,file1;


    @Test
    void teste1() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File(s_distancias);
        file2=new File(s_clientes_produtores);

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        assertEquals(map.numVertices(),17);


    }

    void teste2() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File(s_distancias);
        file2=new File(s_clientes_produtores);

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        assertEquals(map.numEdges(),66);


    }

    void teste3() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File(b_distancias);
        file2=new File(b_clientes_produtores);

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        assertEquals(map.numVertices(),323);


    }

    void teste4() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File(b_distancias);
        file2=new File(b_clientes_produtores);

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        assertEquals(map.numEdges(),1566);


    }

    void teste5() throws FileNotFoundException {

        readFiles=new CsvReader();
        file1=new File(s_distancias);
        file2=new File(s_clientes_produtores);

        map=new MapGraph<>(false);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)


        Local ct2=null;
        for (Local key1:map.vertices()){
            if (key1.getName().equals("CT2")){
                ct2=key1;
                break;
            }

        }

        assertEquals(map.adjVertices(ct2).size(),4);


    }

}

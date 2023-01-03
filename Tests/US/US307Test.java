package US;

import CsvReader.CsvReader;
import Domain.Destinatário;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Shared.constants.Files.*;

public class US307Test {






    @Test
    public void teste1() throws FileNotFoundException {
        CsvReader readFiles=new CsvReader();
        //File1-distancias...File2-clientesProdutores..File3-cabazes

        File file1=new File(s_distancias);
        File file2=new File(s_clientes_produtores);
        File file3=new File(s_cabazes);

        final Graph<Local,Integer> map=new MapGraph<>(false);

        final Map<Integer, Map<Destinatário, List<float []>>> cabazes=new HashMap<>();

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais

        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários

        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        readFiles.ReadCabaz(file3,",",cabazes);//mapa dos Cabazes

        Assert.assertEquals(cabazes.keySet().size(),5);


    }

    @Test
    public void teste2() throws FileNotFoundException {
        CsvReader readFiles=new CsvReader();
        //File1-distancias...File2-clientesProdutores..File3-cabazes

        File file1=new File(b_distancias);
        File file2=new File(b_clientes_produtores);
        File file3=new File(b_cabazes);

        final Graph<Local,Integer> map=new MapGraph<>(false);

        final Map<Integer, Map<Destinatário, List<float []>>> cabazes=new HashMap<>();

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais

        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários

        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)

        readFiles.ReadCabaz(file3,",",cabazes);//mapa dos Cabazes

        Assert.assertEquals(cabazes.keySet().size(),5);


    }



}

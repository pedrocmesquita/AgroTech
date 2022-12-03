package US;

import CsvReader.CsvReader;
import Domain.GrafoDistancia;
import Domain.Hub;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;
import Shared.MapGraphs.MapGraph;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class US303Test
{
    static final Graph<Local, Integer> map = new MapGraph<>(false);
    CsvReader readFiles = new CsvReader();
    readFiles.ReadDistancias(file1,file2,",",map,null);//grafo (vertices-locais)
    
    @Test
    void test1() throws Exception
    {
        List<Hub> actual = US303.findHubs(map, 3);
        List<Hub> expected = new ArrayList<>();
        
        assertEquals(actual, expected);
    }
}
import CsvReader.CsvReader;
import Domain.Colheita;
import Domain.EstacaoMeteorologica;
import CsvReader.CsvReader;
import Shared.Graphs.Edge;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;
import Domain.Local;
import Shared.Graphs.MapVertex;

import java.io.File;
import java.io.FileNotFoundException;

public class main {
    public static void main(String[] arg) throws FileNotFoundException {

        final Graph<Local,Integer> map=new MapGraph<>(true);
        File file1=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\distancias_small.csv");
        File file2=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Small\\clientes-produtores_small.csv");

        CsvReader.ReadDistancias(file1,file2,",",map);


        //VERTICES
        for (Local loc:map.vertices()){
            System.out.println(loc.getName()+"   "+"  "+loc.getLng()+"   "+"  "+loc.getLat());
        }


        //Edges
        for (Edge<Local,Integer> ed:map.edges()){
            System.out.println(ed.getVOrig().getName()+"----"+ed.getVDest().getName()+"  ->"+ed.getWeight());
        }












    }


}

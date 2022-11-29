import CsvReader.CsvReader;
import Domain.Colheita;
import Domain.Destinatário;
import Domain.EstacaoMeteorologica;
import CsvReader.CsvReader;
import Shared.BST.BST;
import Shared.Graphs.Edge;
import Shared.Graphs.Graph;
import Shared.Graphs.MapGraph;
import Domain.Local;
import Shared.Graphs.MapVertex;

import java.io.File;
import java.io.FileNotFoundException;

public class main {
    public static void main(String[] arg) throws FileNotFoundException {

        CsvReader readFiles=new CsvReader();

        File file1=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Big\\distancias_big.csv");
        File file2=new File("C:\\Users\\pnsri\\OneDrive\\Ambiente de Trabalho\\Nova pasta\\Sem3pi\\src\\FICHEIROS_LEITURA\\Big\\clientes-produtores_big.csv");

        final Graph<Local,Integer> map=new MapGraph<>(true);

        BST<Local> locais=readFiles.ReadClientesProdutores(file2,",");//arvore dos locais
        BST<Destinatário> destinatários=readFiles.getDestinatários();//arvore dos destinatários
        readFiles.ReadDistancias(file1,file2,",",map,locais);//grafo (vertices-locais)
/*

        //VERTICES
        for (Local loc:map.vertices()){
            System.out.println(loc.getName()+"   "+"  "+loc.getLng()+"   "+"  "+loc.getLat()+"  "+loc.getDestinatário());
        }


        //Edges
        for (Edge<Local,Integer> ed:map.edges()){
            System.out.println(ed.getVOrig().getName()+"----"+ed.getVDest().getName()+"  ->"+ed.getWeight());
        }

*/










    }


}

package US;

import CsvReader.CsvReader;
import Domain.ControladorRega;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class US306Test {
    CsvReader readFiles = new CsvReader();
    File file1 = new File("C:\\Users\\Zé\\IdeaProjects\\sem3pi2022_23_g064\\src\\FICHEIROS_LEITURA\\rega_exemplo.csv");
    @Test
    void test() throws FileNotFoundException {
        ArrayList<ControladorRega> a = readFiles.readControlador(file1, ",");
        String [] actual = US306.Rega(a.get(0));
        assertNotNull (actual);
        /*
        US é para comparar os dias de criaçao do objeto (controlador) com os dias atual,
         como ambos tao a ser criados no mesmo dia, nao poderia criar testes sem alterar codigo fonte
         */
    }
}

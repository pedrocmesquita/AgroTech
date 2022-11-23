package CsvReader;


import Shared.Graphs.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {



    public static void ReadDistancias(File file, String separatorRegex, Graph<String,Integer> graph) throws FileNotFoundException {
        Scanner reader = new Scanner(file);
        String header = reader.nextLine();
        int c=0;
        System.out.println(c);
        while (reader.hasNextLine()) {

            String[] split = reader.nextLine().split(separatorRegex);
            try {
                for (String s:split) {
                    cleanString(s);
                    //System.out.printf(s+" ");
                }
                //System.out.println();

                graph.addEdge(split[0],split[1],Integer.parseInt(split[2]));


            }
            catch (NumberFormatException e) {
                //System.out.println(e);
            }

        }
    }

    private static String cleanString(String s){
        s = s.replace("\"", "");
        s = s.replace("'", "");
        return s;
    }

}

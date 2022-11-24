package CsvReader;


import Domain.Local;
import Shared.Graphs.Graph;
import Shared.BST.BST;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CsvReader {



    public void ReadDistancias(File file1,File file2, String separatorRegex, Graph<Local,Integer> graph) throws FileNotFoundException {
        Scanner reader = new Scanner(file1);
        String header = reader.nextLine();
        BST<Local> locais=ReadClientesProdutores(file2,",");
        int c=0;






        while (reader.hasNextLine()) {

            String[] split = reader.nextLine().split(separatorRegex);
            try {
                for (String s:split) {
                    cleanString(s);
                    //System.out.printf(s+" ");
                }
                //System.out.println();






                graph.addEdge(findLocal(locais,split[0]),findLocal(locais,split[1]),Integer.parseInt(split[2]));
                //System.out.println("adicio");

            }
            catch (NumberFormatException e) {

            }

        }
    }

    private BST<Local> ReadClientesProdutores(File file, String separatorRegex) throws FileNotFoundException {
        BST<Local> locais=new BST<>();
        Scanner reader = new Scanner(file);
        String header = reader.nextLine();
        int c=0;
        //System.out.println(c);
        while (reader.hasNextLine()) {

            String[] split = reader.nextLine().split(separatorRegex);
            try {
                for (String s:split) {
                    cleanString(s);
                    //System.out.printf(s+" ");
                }
                //System.out.println();

                locais.insert(new Local(split[0],split[1],split[2],split[3]));
                //System.out.println("insert"+split[0]);



            }
            catch (NumberFormatException e) {

            }

        }
        return locais;
    }

    private String cleanString(String s){
        s = s.replace("\"", "");
        s = s.replace("'", "");
        return s;
    }

    private Local findLocal(BST<Local> locais,String str){
        return findLocal(locais.root,str);
    }

    private Local findLocal(BST.Node<Local> node, String str){
        //int a=comp(node.getElement(),str);
       // System.out.println("---"+str+"  "+node.getElement().getName());
        //System.out.println(comp(node.getElement(),str));
        if (comp(node.getElement(),str)==0)return node.getElement();
        if (comp(node.getElement(),str)>0){
            return findLocal(node.getRight(),str);
        }
        else{
            return findLocal(node.getLeft(),str);
        }


    }



    private static int comp(Local o,String str) {
        if (str.compareTo(o.getName())==0)return 0;
        if (str.compareTo(o.getName())>0){
            return 1;
        }
        else{
            return -1;
        }
    }





}

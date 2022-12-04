package CsvReader;


import Domain.ControladorRega;
import Domain.Destinatário;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CsvReader {

    private BST<Destinatário> destinatários=new BST<>();



    public void ReadDistancias(File file1, File file2, String separatorRegex, Graph<Local,Integer> graph, BST<Local> local) throws FileNotFoundException {
        Scanner reader = new Scanner(file1);
        String header = reader.nextLine();
        BST<Local> locais=local;
        int c=0;

        while (reader.hasNextLine()) {

            String[] split = reader.nextLine().split(separatorRegex);
            try {
                for (String s:split) {
                    cleanString(s);
                    //System.out.printf(s+" ");
                }
                //System.out.println();

                graph.addEdge(findLocal(locais,split[0]),findLocal(locais,split[1]), parseInt(split[2]));
                //System.out.println("adicio");

            }
            catch (NumberFormatException e) {

            }

        }
    }


    public BST<Local> ReadClientesProdutores(File file, String separatorRegex) throws FileNotFoundException {
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
                destinatários.insert(new Destinatário(split[0],split[3]));

                //System.out.println("insert"+split[0]+"cliente"+split[3]);



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
    public ArrayList<ControladorRega> readControlador(File file3, String separatorRegex) throws FileNotFoundException {
        ArrayList<ControladorRega> controladores=new ArrayList<>();
        Scanner reader = new Scanner(file3);
        String Horas = reader.nextLine();
        int c=0;
        //System.out.println(c);
        while (reader.hasNextLine()) {
            String[] split = reader.nextLine().split(separatorRegex);
            try {
                for (String s:split) {
                    cleanString(s);
                    //System.out.printf(s+" ");
                }
                controladores.add(new ControladorRega(Horas, split[0], parseInt(split[1]), split[2]));
                //System.out.println();
            }
            catch (NumberFormatException e) {

            }
    }
        return controladores;
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


    public BST<Destinatário> getDestinatários() {
        return destinatários;
    }
}

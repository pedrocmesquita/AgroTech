package CsvReader;


import Domain.Cabazes;
import Domain.Destinatário;
import Domain.GrafoDistancia;
import Domain.Local;
import Shared.GraphCommon.Graph;
import Shared.BST.BST;

import java.io.*;
import java.util.*;

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

                graph.addEdge(findLocal(locais,split[0]),findLocal(locais,split[1]),Integer.parseInt(split[2]));
                //System.out.println("adicio");

            }
            catch (NumberFormatException e) {

            }

        }
    }

    public ArrayList<GrafoDistancia> ReadDistancias(File file1, String separatorRegex) throws FileNotFoundException {
        Scanner reader = new Scanner(file1);
        String header = reader.nextLine();
        GrafoDistancia grafoDistancia;
        ArrayList<GrafoDistancia> arrayList = new ArrayList<>();

        while (reader.hasNextLine()) {

            String[] split = reader.nextLine().split(separatorRegex);
            try {
                for (String s:split) {
                    cleanString(s);

                }
                grafoDistancia = new GrafoDistancia(split[0], split[1], Integer.parseInt(split[0].substring(split[0].indexOf("CT") + 2)), Integer.parseInt(split[1].substring(split[1].indexOf("CT") + 2)), Integer.parseInt(split[2]));
                arrayList.add(grafoDistancia);

            }
            catch (NumberFormatException e) {
            }
        }
        return arrayList;
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

    public List<Cabazes> ReadCabazes(String fileName){
        List<Cabazes> cabazesList = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            br.readLine();
            String line = br.readLine();

            while (line != null){
                String[] coluna = line.split(",");
                String codigo = coluna[0];
                String dia = coluna[1];
                int prod1 = Integer.parseInt(coluna[2]);
                int prod2 = Integer.parseInt(coluna[3]);
                int prod3 = Integer.parseInt(coluna[4]);
                int prod4 = Integer.parseInt(coluna[5]);
                int prod5 = Integer.parseInt(coluna[6]);
                int prod6 = Integer.parseInt(coluna[7]);
                int prod7 = Integer.parseInt(coluna[8]);
                int prod8 = Integer.parseInt(coluna[9]);
                int prod9 = Integer.parseInt(coluna[10]);
                int prod10 = Integer.parseInt(coluna[11]);
                int prod11 = Integer.parseInt(coluna[12]);
                int prod12 = Integer.parseInt(coluna[13]);
                int prod13 = Integer.parseInt(coluna[14]);
                int prod14 = Integer.parseInt(coluna[15]);
                int prod15 = Integer.parseInt(coluna[16]);
                int prod16 = Integer.parseInt(coluna[17]);
                int prod17 = Integer.parseInt(coluna[18]);
                int prod18 = Integer.parseInt(coluna[19]);
                int prod19 = Integer.parseInt(coluna[20]);
                int prod20 = Integer.parseInt(coluna[21]);

                Cabazes cabazes = new Cabazes(codigo, dia, prod1, prod2, prod3, prod4, prod5,prod6,prod7,prod8,prod9,prod10,prod11,prod12,
                        prod13,prod14,prod15,prod16,prod17,prod18,prod19,prod20);

                cabazesList.add(cabazes);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cabazesList;
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


    public BST<Destinatário> getDestinatários() {
        return destinatários;
    }
}

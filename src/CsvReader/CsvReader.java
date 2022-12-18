package CsvReader;


import Domain.ControladorRega;
import Domain.Destinatário;
import Domain.Local;
import Shared.BST.BST;
import Shared.GraphCommon.Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
                destinatários.insert(new Destinatário(split[3],split[0]));

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
        return locais.find(new Local(str,null,null,null),locais.root).getElement();
    }


    public Destinatário findDestinatário(String str){
        return destinatários.find(new Destinatário(str,null),destinatários.root).getElement();
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


    public void ReadCabaz(File file, String separatorRegex, Map<Integer,Map<Destinatário,float []>> cabazesMap) throws FileNotFoundException {

        Destinatário destinatário=null;
        Scanner reader = new Scanner(file);
        String header = reader.nextLine();
        String dest;
        float produtos[]=new float[20];
        int c=0,d,dia;
        Map<Destinatário,float[]> mapa;
        for (d=0;d<20;d++){
            produtos[d]=0;
        }

        while (reader.hasNextLine()) {

            String[] split = reader.nextLine().split(separatorRegex);


            try {
                for (String s:split) {
                    cleanString(s);
                    //System.out.printf(s+" ");
                }



               for (d=2;d<split.length;d++){
                    produtos[d-2]=Float.parseFloat(split[d]);
                   //System.out.println(split[d]+"--"+d);
               }

               dest=split[0].substring(1,split[0].length()-1);
               dia=Integer.parseInt(split[1]);
               mapa=cabazesMap.get(dia);
               if (mapa==null){

                   cabazesMap.put(dia,new HashMap<>());
                   cabazesMap.get(dia).put(findDestinatário(dest),produtos);
               }
               else{
                   mapa.put(findDestinatário(dest),produtos.clone());

               }


            }
            catch (NumberFormatException e) {

            }

        }
        return;
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

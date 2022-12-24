package US;

import Domain.Cabazes;
import Domain.Destinatário;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class US308 {

    static Scanner sc  = new Scanner(System.in);

    public void getCabazesAtSomeDay(Map<Integer, Map<Destinatário,List<float []>>> cabazesMap){
        System.out.println("Em que dia deseja obter a expedição de cabazes?");
        int dia = sc.nextInt();

        ArrayList<String> lista = getCabazesAtSomeDay(cabazesMap, dia);

        printList(lista, dia);
    }

    ArrayList<String> getCabazesAtSomeDay(Map<Integer, Map<Destinatário,List<float []>>> cabazesMap, int dia){
        ArrayList<String> lista = new ArrayList<>();
        List<float[]> listaProdutos;

        if (cabazesMap.isEmpty()){
            throw new RuntimeException("MAP IS NULL!");
        }


        for (Destinatário destinatário : cabazesMap.get(dia).keySet()){
            lista.add(destinatário.getName());
            listaProdutos = cabazesMap.get(dia).get(destinatário);

            for (float[] ll:listaProdutos){
                for (float  produtos:ll){
                    lista.add(String.valueOf(produtos));
                }
            }
        }
        return lista;
    }

    void printList(ArrayList<String> lista, int dia){
        System.out.println(">>>>> Lista de Expedição de Cabazes <<<<<\n--> Dia " + dia);

        for (int i = 0; i < lista.size(); i++) {
            char produtor = lista.get(i).charAt(0);

            if (produtor >= 'A'&& produtor <= 'Z'){
                System.out.print("\n\nCliente-Produtor " + lista.get(i) + ":\n");

            }else {
                System.out.print(lista.get(i).toUpperCase() + ", ");
            }
        }
    }
}

package US;

import Domain.Cabazes;
import Domain.Destinatário;
import Domain.Local;
import Domain.Produtos;

import java.util.*;

public class US308 {

    static Scanner sc = new Scanner(System.in);


    public List<float[]> getCabazesAtSomeDay(Map<Integer, Map<Destinatário, List<float[]>>> cabazesMap, ArrayList<String> lista, int dia) {
        List<float[]> listaProdutos = new ArrayList<>();

        if (cabazesMap.isEmpty()) {
            System.out.println("MAP IS NULL");
            return null;
        }

        // outer map
        for (Map.Entry<Integer, Map<Destinatário, List<float[]>>> entry : cabazesMap.entrySet()) {

            if (entry.getKey() == dia) {

                // inner map
                for (Map.Entry<Destinatário, List<float[]>> entry2 : entry.getValue().entrySet()) {
                    char produtor = entry2.getKey().getName().charAt(0);

                    if (produtor == 'P') {
                        listaProdutos.addAll(entry2.getValue());
                        lista.add(entry2.getKey().getName());
                    }
                }
            }
        }
        return listaProdutos;

    }

    public List<Produtos> getCabazesADay (Map<Integer, Map<Destinatário, List<float[]>>> cabazesMap, int dia){
        List<Produtos> produtos = new ArrayList<>();

        for (Map.Entry<Integer, Map<Destinatário, List<float[]>>> entry : cabazesMap.entrySet()) {

            if (entry.getKey() == dia) {

                // inner map
                for (Map.Entry<Destinatário, List<float[]>> entry2 : entry.getValue().entrySet()) {
                    char produtor = entry2.getKey().getName().charAt(0);

                    if (produtor == 'P') {
                        produtos.add(new Produtos(entry2.getKey().getName(), entry2.getValue()));
                    }
                }
            }
        }
        return produtos;
    }

    public void printList(List<Produtos> produtos, int dia) {
        System.out.println("\n\n>>>>> Lista de Expedição de Cabazes <<<<<\n--> Dia " + dia);

        for (Produtos prod : produtos){
            System.out.println(prod.getNomeDestinatario() + ": ");
            for (float[] arr : prod.getProdutos()){
                for (float p : arr){
                    System.out.print(p + ", ");
                }
                System.out.println();
            }
        }
    }
}

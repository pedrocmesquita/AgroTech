package US;

import Domain.Cabazes;
import Domain.Destinatário;
import Domain.Local;

import java.util.*;

public class US308 {

    static Scanner sc = new Scanner(System.in);


    public List<float[]> getCabazesAtSomeDay(Map<Integer, Map<Destinatário, List<float[]>>> cabazesMap, int dia) {
        ArrayList<String> lista = new ArrayList<>();
        List<float[]> listaProdutos = new ArrayList<>();

        if (cabazesMap.isEmpty()) {
            throw new RuntimeException("MAP IS NULL!");
        }

        // outer map
        for (Map.Entry<Integer, Map<Destinatário, List<float[]>>> entry : cabazesMap.entrySet()) {

            if (entry.getKey() == dia) {

                // inner map
                for (Map.Entry<Destinatário, List<float[]>> entry2 : entry.getValue().entrySet()) {
                    char produtor = entry2.getKey().getName().charAt(0);

                    if (produtor != 'P') {
                        listaProdutos.addAll(entry2.getValue());
                    }
                }

            }
        }
        return listaProdutos;

    }

    void printList(ArrayList<String> lista, int dia) {
        System.out.println("\n\n>>>>> Lista de Expedição de Cabazes <<<<<\n--> Dia " + dia);

        for (int i = 0; i < lista.size(); i++) {
            char produtor = lista.get(i).charAt(0);

            if (produtor >= 'A' && produtor <= 'Z') {
                System.out.print("\nCliente-Produtor " + lista.get(i) + ":\n");

            } else {
                System.out.print(lista.get(i).toUpperCase() + ", ");
            }
        }
    }
}

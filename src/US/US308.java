package US;

import Domain.Destinatário;
import Domain.CabazExpedicao;

import java.util.*;

public class US308 {

    static Scanner sc = new Scanner(System.in);

    public List<CabazExpedicao> getCabazesADay (Map<Integer, Map<Destinatário, List<float[]>>> cabazesMap, int dia){
        List<CabazExpedicao> produtos = new ArrayList<>();

        if (cabazesMap.isEmpty()) {
            System.out.println("MAP IS NULL");
            return null;
        }

        for (Map.Entry<Integer, Map<Destinatário, List<float[]>>> entry : cabazesMap.entrySet()) {

            if (entry.getKey() == dia) {

                // inner map
                for (Map.Entry<Destinatário, List<float[]>> entry2 : entry.getValue().entrySet()) {
                    char produtor = entry2.getKey().getName().charAt(0);
                    new CabazExpedicao(entry2.getKey().getName(), entry2.getKey().getLocal(), entry2.getValue(),dia);

                    if (produtor == 'P') {
                        produtos.add(new CabazExpedicao(entry2.getKey().getName(), entry2.getKey().getLocal(), entry2.getValue(), dia));
                    }
                }
            }
        }
        return produtos;
    }

    public void printList(List<CabazExpedicao> produtos, int dia) {
        System.out.println("\n\n>>>>> Lista de Expedição de Cabazes <<<<<\n--> Dia " + dia);

        for (CabazExpedicao prod : produtos){
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

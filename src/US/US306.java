package US;

import Domain.ControladorRega;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.Integer.parseInt;

public class US306 {


    public static String[] Rega(ControladorRega ctrl1) {
        String[] Horas = ctrl1.getHorasRega().split(",", 0);
        String[] array = {"","",""};
        if (ctrl1.getStartDate().plusMonths(1).isAfter(LocalDate.now())) {
            switch (ctrl1.getRegularidade()) {
                case "t" -> {
                    //todos os dias
                    for (String s : Horas) {
                        String[] a = (s.split(":", 0));
                        if (parseInt(a[0]) + ctrl1.getDuracao() > LocalTime.now().getHour()) {
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((parseInt(a[0]) + ctrl1.getDuracao()) - LocalTime.now().getHour()) + ":" + Integer.toString(parseInt(a[1]) - LocalTime.now().getMinute());
                            break;
                        } else if (parseInt(a[1])> LocalTime.now().getMinute()){
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((parseInt(a[0]) + ctrl1.getDuracao()) - LocalTime.now().getHour()) + ":" + Integer.toString(parseInt(a[1]) - LocalTime.now().getMinute());
                            break;
                        }
                    }
                    array[0] = "Não está a regar";
                }
                case "i" -> {
                    //dias impares
                    if (LocalDate.now().getDayOfMonth() % 2 == 0) {
                        System.out.println("Não rega");
                        break;
                    }
                    for (String hora : Horas) {
                        String[] a = (hora.split(":", 0));
                        if (parseInt(a[0]) + ctrl1.getDuracao() > LocalTime.now().getHour()) {
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((parseInt(a[0]) + ctrl1.getDuracao()) - LocalTime.now().getHour()) + ":" + Integer.toString(parseInt(a[1]) - LocalTime.now().getMinute());
                            break;
                        }else if (parseInt(a[1])> LocalTime.now().getMinute()){
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((parseInt(a[0]) + ctrl1.getDuracao()) - LocalTime.now().getHour()) + ":" + Integer.toString(parseInt(a[1]) - LocalTime.now().getMinute());
                            break;}
                    }
                    array[0] = "Não está a regar";
                }
                case "p" -> {
                    //dias pares
                    if (LocalDate.now().getDayOfMonth() % 2 != 0) {
                        array[0] = "Não rega";
                        break;
                    }
                    for (String hora : Horas) {
                        String[] a = (hora.split(":", 0));
                        if (parseInt(a[0]) + ctrl1.getDuracao() > LocalTime.now().getHour()) {
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((parseInt(a[0]) + ctrl1.getDuracao()) - LocalTime.now().getHour()) + ":" + Integer.toString(parseInt(a[1]) - LocalTime.now().getMinute());
                            break;
                        }else if (parseInt(a[1])> LocalTime.now().getMinute()){
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((parseInt(a[0]) + ctrl1.getDuracao()) - LocalTime.now().getHour()) + ":" + Integer.toString(parseInt(a[1]) - LocalTime.now().getMinute());
                            break;}
                    }
                    array[0] = "Não rega";
                }
            }
            } return array;
        }
    }

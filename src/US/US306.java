package US;

import Domain.ControladorRega;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.lang.Integer.parseInt;

public class US306 {


    public static String[] Rega(ControladorRega ctrl1) {
        String[] Horas = ctrl1.getHorasRega().split(",", 0);
        String[] array = {"","",""};
        if (ctrl1.getStartDate().plusMonths(1).isBefore(LocalDate.now())) {
            switch (ctrl1.getRegularidade()) {
                case "t" -> {
                    //todos os dias
                    for (String s : Horas) {
                        if (LocalTime.parse(s).getHour() + ctrl1.getDuracao() > LocalTime.now().getHour()) {
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((LocalTime.parse(s).getHour() + ctrl1.getDuracao()) - LocalTime.now().getHour());
                            break;
                        }
                    }
                    System.out.println("Não rega");
                }
                case "i" -> {
                    //dias impares
                    if (LocalDate.now().getDayOfMonth() % 2 == 0) {
                        System.out.println("Não rega");
                        break;
                    }
                    for (String hora : Horas) {
                        if (parseInt(hora) + ctrl1.getDuracao() > LocalTime.now().getHour()) {
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((LocalTime.parse(hora).getHour() + ctrl1.getDuracao()) - LocalTime.now().getHour());
                            break;
                        }
                    }
                    System.out.println("Não rega");
                }
                case "p" -> {
                    //dias pares
                    if (LocalDate.now().getDayOfMonth() % 2 != 0) {
                        array[0] = "Não rega";
                        break;
                    }
                    for (String hora : Horas) {
                        if (parseInt(hora) + ctrl1.getDuracao() > LocalTime.now().getHour()) {
                            array[0] = "A regar";
                            array[1] = ctrl1.getParcela();
                            array[2] = Integer.toString((LocalTime.parse(hora).getHour() + ctrl1.getDuracao()) - LocalTime.now().getHour());
                            break;
                        }
                    }
                    array[0] = "Não rega";
                }
            }
            } return array;
        }
    }

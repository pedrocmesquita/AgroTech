package US;

import Domain.Cabazes;
import Domain.Destinatário;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class US307 {
    public List<Cabazes> getCabazesAtSomeDay(Map<Integer, Map<Destinatário,List<float []>>> cabazesMap, int dia){
        ArrayList<Cabazes> cabazesList = new ArrayList<Cabazes>();

        if (cabazesMap.isEmpty()){
            throw new RuntimeException("MAP IS NULL!");
            //return null;
        }

        for (int i = 0; i < cabazesMap.size(); i++) {
            //if (dia == cabazesMap.get(i).)
        }
return null;
    }
}

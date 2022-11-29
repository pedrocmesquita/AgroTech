package Domain;

import java.util.Comparator;

public class Destinatário implements Comparable<Destinatário> {
    private String name;

    private String local;

    public Destinatário(String name, String local) {
        this.name = name;
        this.local = local;
    }

    public String getName() {
        return name;
    }

    public String getLocal() {
        return local;
    }

    public int compareTo(Destinatário o) {

        if (this.name.compareTo(o.name)==0)return 0;
        if (this.name.compareTo(o.name)>0){
            return 1;
        }
        else{
            return -1;
        }
    }
}

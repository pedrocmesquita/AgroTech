package Domain;


import java.util.ArrayList;
import java.util.List;

public class Local implements Comparable<Local> {
    private String name;
    private String lat;
    private String lng;
    private String destinatário;
    private String categoria;

    final String PRODUCTOR = "P";
    final String COMPANY = "E";
    final String CLIENT = "C";

    List<Double> produtos;

    public Local(String name, String lat, String lng,String destinatário) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.destinatário = destinatário;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getDestinatário() {
        return destinatário;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private String getCategory(String element) {
        if (element.contains(PRODUCTOR)) {
            return PRODUCTOR;
        }
        if (element.contains(COMPANY)) {
            return COMPANY;
        }
        if (element.contains(CLIENT)) {
            return CLIENT;
        }
        return null;
    }

    @Override
    public int compareTo(Local o) {

        if (this.name.compareTo(o.name)==0)return 0;
        if (this.name.compareTo(o.name)>0){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "Local{" +
                "name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", destinatário='" + destinatário + '\'' +
                '}';
    }
}

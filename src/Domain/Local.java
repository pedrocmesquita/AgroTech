package Domain;

public class Local implements Comparable<Local> {
    private String name;
    private String lat;
    private String lng;

    public Local(String name, String lat, String lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
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
}

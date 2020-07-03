package jdbcdemo.domain;

public class Klub implements HaveId {

    private int id;
    private String nazwa;
    private int trener;
    private int stadion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getTrener() {
        return trener;
    }

    public void setTrener(int trener) {
        this.trener = trener;
    }

    public int getStadion() {
        return stadion;
    }

    public void setStadion(int stadion) {
        this.stadion = stadion;
    }



}

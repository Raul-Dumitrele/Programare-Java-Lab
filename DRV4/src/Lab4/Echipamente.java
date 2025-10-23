package Lab4;

public class Echipamente {
    public String denumire;
    public Integer nr_inv;
    public Double pret;
    public String zona_mag;
    public Stare stare;

    public Echipamente(String denumire, Integer nr_inv, Double pret, String zona_mag, Stare stare) {
        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
        this.stare = stare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Integer getNr_inv() {
        return nr_inv;
    }

    public void setNr_inv(Integer nr_inv) {
        this.nr_inv = nr_inv;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public String getZona_mag() {
        return zona_mag;
    }

    public void setZona_mag(String zona_mag) {
        this.zona_mag = zona_mag;
    }

    public Stare getStare() {
        return stare;
    }

    public void setStare(Stare stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        return "Echipamente{" +
                "denumire='" + denumire + '\'' +
                ", nr_inv=" + nr_inv +
                ", pret=" + pret +
                ", zona_mag='" + zona_mag + '\'' +
                ", stare=" + stare +
                '}';
    }
}

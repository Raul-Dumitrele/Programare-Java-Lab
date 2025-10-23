package Lab4;

public class Imprimante extends Echipamente {
    public double ppm;
    public int rezolutie;
    public int p_car;
    public Tiparire tiparire;

    public Imprimante(String denumire, Integer nr_inv, Double pret, String zona_mag, Stare stare, double ppm, int rezolutie, Tiparire tiparire, int p_car) {
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.ppm = ppm;
        this.rezolutie = rezolutie;
        this.tiparire = tiparire;
        this.p_car = p_car;
    }

    public double getPpm() {
        return ppm;
    }

    public void setPpm(double ppm) {
        this.ppm = ppm;
    }

    public int getRezolutie() {
        return rezolutie;
    }

    public void setRezolutie(int rezolutie) {
        this.rezolutie = rezolutie;
    }

    public int getP_car() {
        return p_car;
    }

    public void setP_car(int p_car) {
        this.p_car = p_car;
    }

    public Tiparire getTiparire() {
        return tiparire;
    }

    public void setTiparire(Tiparire tiparire) {
        this.tiparire = tiparire;
    }

    @Override
    public String toString() {
        return "Imprimante{" +
                "ppm=" + ppm +
                ", rezolutie=" + rezolutie +
                ", p_car=" + p_car +
                ", tiparire=" + tiparire +
                "} " + super.toString();
    }
}

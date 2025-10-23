public class Echipament {
    private String denumire;
    private int nr_inv;
    private int pret;
    private String zona_mag;
    private Situatie situatie;
    public Echipament(String denumire, int nr_inv, int pret, String zona_mag) {
        this.denumire = denumire;
        this.nr_inv = nr_inv;
        this.pret = pret;
        this.zona_mag = zona_mag;
    }
}

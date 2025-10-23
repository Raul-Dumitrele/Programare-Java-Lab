public class Imprimante extends Echipament{
    private int ppm;
    private int dpi;
    private int p_car;
    private ModDeTiparire mod_tip;
    public Imprimante(int ppm, int dpi, int p_car, String mod_tip, String denumire, int nr_inv, int pret, String zona_mag) {
        super(denumire, nr_inv, pret, zona_mag);
        this.ppm = ppm;
        this.dpi = dpi;
        this.p_car = p_car;
        this.mod_tip = ModDeTiparire.valueOf(mod_tip);
    }
}
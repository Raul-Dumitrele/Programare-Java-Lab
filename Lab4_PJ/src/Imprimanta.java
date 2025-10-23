public class Imprimanta extends Echipamente
{
    public Double ppm;
    public Integer rezolutie;
    public Integer p_car;
    public Tiparire tiparire;
    public Imprimanta(String denumire, Integer nr_inv, Double pret, String zona_mag, Situatii situatii, String tip, Double ppm, Integer rezolutie, Integer p_car, Tiparire tiparire)
    {
        super(denumire, nr_inv, pret, zona_mag, situatii, tip);
        this.ppm=ppm;
        this.rezolutie=rezolutie;
        this.p_car=p_car;
        this.tiparire=tiparire;
    }
}

public class Copiator extends Echipamente{
    public Integer p_ton;
    public Format format;
    public Copiator(String denumire, Integer nr_inv, Double pret, String zona_mag, String tip, Situatii situatii, Integer p_ton, Format format)
    {
        super(denumire, nr_inv, pret, zona_mag, situatii, tip);
        this.p_ton=p_ton;
        this.format=format;
    }
}
public class Copiatoare extends Echipament{
    private int p_ton;
    private Format format;
    public Copiatoare(int p_ton, String format, String denumire, int nr_inv, int pret, String zona_mag) {
        super(denumire, nr_inv, pret, zona_mag);
        this.p_ton = p_ton;
        this.format = Format.valueOf(format);
    }
}

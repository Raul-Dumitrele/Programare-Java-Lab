package Lab4;

import java.text.Format;

public class Copiatoare extends Echipamente {
    public double p_ton;
    public Format format;

    public Copiatoare(String denumire, Integer nr_inv, Double pret, String zona_mag, Stare stare, double p_ton, Format format) {
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.p_ton = p_ton;
        this.format = format;
    }

    public double getP_ton() {
        return p_ton;
    }

    public void setP_ton(double p_ton) {
        this.p_ton = p_ton;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "Copiatoare{" +
                "p_ton=" + p_ton +
                ", format=" + format +
                "} " + super.toString();
    }
}

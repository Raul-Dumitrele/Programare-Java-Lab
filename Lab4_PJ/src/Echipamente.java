public class Echipamente
{
    public String denumire;
    public Integer nr_inv;
    public Double pret;
    public String zona_mag;
    public Situatii situatii;
    private String tip;
    public Echipamente(String denumire, Integer nr_inv, Double pret, String zona_mag, Situatii situatii, String tip)
    {
        this.denumire=denumire;
        this.nr_inv=nr_inv;
        this.pret=pret;
        this.zona_mag=zona_mag;
        this.situatii=situatii;
        this.tip=tip;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}

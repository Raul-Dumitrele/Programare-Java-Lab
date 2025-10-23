public class SistemCalcul extends Echipamente{
    public String tip_mon;
    public Double vit_proc;
    public Integer c_hdd;
    public Sistem sistem;
    public SistemCalcul(String denumire, Integer nr_inv, Double pret, String zona_mag, String tip, Situatii situatii, String tip_mon, Double vit_proc, Integer c_hdd, Sistem sistem)
    {
        super(denumire, nr_inv, pret, zona_mag, situatii, tip);
        this.tip_mon=tip_mon;
        this.vit_proc=vit_proc;
        this.c_hdd=c_hdd;
        this.sistem=sistem;

    }
}

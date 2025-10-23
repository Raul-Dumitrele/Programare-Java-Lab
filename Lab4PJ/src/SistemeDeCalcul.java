public class SistemeDeCalcul extends Echipament{
    private String tip_mon ;
    private String vit_proc ;
    private String c_hdd ;
    private Os os;
    public SistemeDeCalcul(String tip_mon, String vit_proc, String c_hdd, String os, String denumire, int nr_inv, int pret, String zona_mag) {
        super(denumire, nr_inv, pret, zona_mag);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.os = Os.valueOf(os);
    }
}
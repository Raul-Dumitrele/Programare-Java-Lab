package Lab4;

public class SistemeCalcul extends Echipamente{
    public String tip_mon;
    public Double vit_proc;
    public Integer c_hdd;
    public Sistem sistem;

    public SistemeCalcul(String denumire, Integer nr_inv, Double pret, String zona_mag, Stare stare, String tip_mon, Double vit_proc, Integer c_hdd, Sistem sistem) {
        super(denumire, nr_inv, pret, zona_mag, stare);
        this.tip_mon = tip_mon;
        this.vit_proc = vit_proc;
        this.c_hdd = c_hdd;
        this.sistem = sistem;
    }

    public String getTip_mon() {
        return tip_mon;
    }

    public void setTip_mon(String tip_mon) {
        this.tip_mon = tip_mon;
    }

    public Double getVit_proc() {
        return vit_proc;
    }

    public void setVit_proc(Double vit_proc) {
        this.vit_proc = vit_proc;
    }

    public Integer getC_hdd() {
        return c_hdd;
    }

    public void setC_hdd(Integer c_hdd) {
        this.c_hdd = c_hdd;
    }

    public Sistem getSistem() {
        return sistem;
    }

    public void setSistem(Sistem sistem) {
        this.sistem = sistem;
    }

    @Override
    public String toString() {
        return "SistemeCalcul{" +
                "tip_mon='" + tip_mon + '\'' +
                ", vit_proc=" + vit_proc +
                ", c_hdd=" + c_hdd +
                ", sistem=" + sistem +
                "} " + super.toString();
    }
}

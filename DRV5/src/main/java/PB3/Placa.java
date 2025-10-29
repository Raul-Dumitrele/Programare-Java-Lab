package PB3;

import PB3.Orientare;

import java.util.Arrays;

public class Placa {
    private String descriere;
    private int lungime;
    private int latime;
    private Orientare orientare;
    private boolean[] canturi;
    private int nr_bucati;

    public Placa() {} // obligatoriu pentru Jackson

    public Placa(String descriere, int lungime, int latime, Orientare orientare, boolean[] canturi, int nr_bucati) {
        this.descriere = descriere;
        this.lungime = lungime;
        this.latime = latime;
        this.orientare = orientare;
        this.canturi = canturi;
        this.nr_bucati = nr_bucati;
    }

    public String getDescriere() { return descriere; }
    public int getLungime() { return lungime; }
    public int getLatime() { return latime; }
    public Orientare getOrientare() { return orientare; }
    public boolean[] getCanturi() { return canturi; }
    public int getNr_bucati() { return nr_bucati; }

    public double calculeazaArie() {
        return (double) lungime * latime * nr_bucati;
    }

    @Override
    public String toString() {
        return String.format("Placa[%s]: %dx%d mm, orientare=%s, bucăți=%d, canturi=%s",
                descriere, lungime, latime, orientare, nr_bucati, Arrays.toString(canturi));
    }
}

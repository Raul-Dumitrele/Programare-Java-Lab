package PB3;

import PB3.Placa;

import java.util.List;

public class Mobilier {
    private String nume;
    private List<Placa> placi;

    public Mobilier() {} // Jackson

    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }

    public String getNume() { return nume; }
    public List<Placa> getPlaci() { return placi; }

    public double calculeazaArieTotala() {
        return placi.stream().mapToDouble(Placa::calculeazaArie).sum();
    }

    @Override
    public String toString() {
        return "Mobilier{" +
                "nume='" + nume + '\'' +
                ", nr_placi=" + (placi != null ? placi.size() : 0) +
                '}';
    }
}

package com.exemplu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "masini")
public class Masini {

	@Id
	private String nr_inmatriculare;
	private String marca;
	private int anul_fabricatiei;
	private String culoare;
	private int nr_kilometri;

	public Masini() {
	}

	public Masini(String nr_inmatriculare, String marca, int anul_fabricatiei, String culoare, int nr_kilometri) {
		this.nr_inmatriculare = nr_inmatriculare;
		this.marca = marca;
		this.anul_fabricatiei = anul_fabricatiei;
		this.culoare = culoare;
		this.nr_kilometri = nr_kilometri;
	}

	public String getNr_inmatriculare() {
		return nr_inmatriculare;
	}

	public void setNr_inmatriculare(String nr_inmatriculare) {
		this.nr_inmatriculare = nr_inmatriculare;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getAnul_fabricatiei() {
		return anul_fabricatiei;
	}

	public void setAnul_fabricatiei(int anul_fabricatiei) {
		this.anul_fabricatiei = anul_fabricatiei;
	}

	public String getCuloare() {
		return culoare;
	}

	public void setCuloare(String culoare) {
		this.culoare = culoare;
	}

	public int getNr_kilometri() {
		return nr_kilometri;
	}

	public void setNr_kilometri(int nr_kilometri) {
		this.nr_kilometri = nr_kilometri;
	}

	@Override
	public String toString() {
		return "Masini [nr_inmatriculare=" + nr_inmatriculare + ", marca=" + marca + ", anul_fabricatiei="
				+ anul_fabricatiei + ", culoare=" + culoare + ", nr_kilometri=" + nr_kilometri + "]";
	}
}

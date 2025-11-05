package lab6;

import java.time.LocalDate;

public class Angajat {
    private String nume;
    private String post;
    private LocalDate data_angajarii;
    private int salariu;

    public Angajat() {
    }

    public Angajat(String nume, String post, LocalDate data_angajarii, int salariu) {
        this.nume = nume;
        this.post = post;
        this.data_angajarii = data_angajarii;
        this.salariu = salariu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public LocalDate getData_angajarii() {
        return data_angajarii;
    }

    public void setData_angajarii(LocalDate data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        return nume + " - " + post + " - " + data_angajarii + " - " + salariu;
    }
}

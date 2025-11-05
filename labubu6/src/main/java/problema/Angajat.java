package problema;

import java.time.LocalDate;

public class Angajat {
    private String nume;
    private String post;
    private LocalDate data_angajarii;
    private float salariu;

    // Constructor fără parametri – necesar pentru Jackson
    public Angajat() {
    }

    // Constructor cu parametri
    public Angajat(String nume, String post, LocalDate data_angajarii, float salariu) {
        this.nume = nume;
        this.post = post;
        this.data_angajarii = data_angajarii;
        this.salariu = salariu;
    }

    // Getteri și setteri (Jackson are nevoie de ei)
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getPost() { return post; }
    public void setPost(String post) { this.post = post; }

    public LocalDate getData_angajarii() { return data_angajarii; }
    public void setData_angajarii(LocalDate data_angajarii) { this.data_angajarii = data_angajarii; }

    public float getSalariu() { return salariu; }
    public void setSalariu(float salariu) { this.salariu = salariu; }

    @Override
    public String toString() {
        return nume + " - " + post + " - " + data_angajarii + " - " + salariu;
    }
}

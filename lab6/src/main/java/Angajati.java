import java.time.LocalDate;
public class Angajati {
    private String nume;
    private String post;
    private LocalDate data_angajarii;
    private float salariu;

    public Angajati(){
    }

    public Angajati(String nume, String post, LocalDate data_angajarii, float salariu)
    {
        this.nume=nume;
        this.post=post;
        this.data_angajarii=data_angajarii;
        this.salariu=salariu;
    }

    public String getNume(String nume)
    {
        return nume;
    }
    public void setNume(String nume)
    {
        this.nume=nume;
    }

    public String getPost(String post)
    {
        return post;
    }
    public void setPost(String post)
    {
        this.post=post;
    }

    public LocalDate getData_angajarii(LocalDate data_angajarii)
    {
        return data_angajarii;
    }
    public void setData_angajarii(LocalDate data_angajarii)
    {
        this.data_angajarii=data_angajarii;
    }

    public float getSalariu(float salariu)
    {
        return salariu;
    }
    public void setSalariu(float salariu)
    {
        this.salariu=salariu;
    }

    @Override
    public String toString()
    {
        return nume+" - "+post+" - "+data_angajarii+" - "+salariu;
    }
}

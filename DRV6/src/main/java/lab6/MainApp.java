package lab6;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {
    public static void scriere(List<Angajat>lista) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File file = new File("src/main/resources/angajati.json");
            mapper.writeValue(file, lista);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Angajat> citire()
    {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File file = new File("src/main/resources/angajati.json");
            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(file, new TypeReference<List<Angajat>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Angajat> angajati=citire();
        angajati.add(new Angajat("Ion Popescu","director", LocalDate.of(2002,3,30),7500));
        angajati.add(new Angajat("Maria Ionescu", "sef departament", LocalDate.of(2024,4,30) , 4200));
        angajati.add(new Angajat("Andrei Georgescu","programator",LocalDate.of(2024,04,30), 3100));
        angajati.add(new Angajat("Elena Dobre", "analist",LocalDate.of(2024,04,10) , 2700));
        angajati.add(new Angajat("Mihai Stan",  "operator",LocalDate.of(2000,2,2), 2400));
        scriere(angajati);

        List<Angajat>angajat=citire();
            if(angajat==null||angajat.isEmpty()){
                System.out.println("Nu sunt angajati afisati");
                return;
            }

            System.out.println("\nLista angajati");
            angajat.forEach(System.out::println);

            System.out.println("\nAngajati cu salariul > 2500 RON:");
            List<Angajat> angajatifiltrati = angajati.stream().filter(a -> a.getSalariu()>2500).collect(Collectors.toList());

            angajatifiltrati.forEach(System.out::println);

            System.out.println("\nAngajatii din luna aprilie:");
            List<Angajat> angajatiaprilie = angajati.stream()
                    .filter(a -> a.getData_angajarii().getYear()==LocalDate.now().getYear()-1)
                    .filter(a -> a.getData_angajarii().getMonthValue()==4)
                    .filter(a -> a.getPost().toLowerCase().contains("sef departament")
                            || a.getPost().toLowerCase().contains("director"))
                    .collect(Collectors.toList());
            angajatiaprilie.forEach(System.out::println);
    }
}


package problema;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp {

    public static void scriere(List<Angajat> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File file = new File("src/main/resources/angajati.json");
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Angajat> citire() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            File file = new File("src/main/resources/angajati.json");
            return mapper.readValue(file, new TypeReference<List<Angajat>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Angajat> ang = citire();
        if (ang == null || ang.isEmpty()) {
            System.out.println("Nu sunt angajați de afișat.");
            return;
        }

        System.out.println("Lista angajaților:");
        ang.forEach(System.out::println);

        System.out.println("\nAngajați cu salariul > 2500 RON:");
        List<Angajat> angajatiFiltrati = ang.stream()
                .filter(a -> a.getSalariu() > 2500)
                .collect(Collectors.toList());

        angajatiFiltrati.forEach(System.out::println);
    }
}





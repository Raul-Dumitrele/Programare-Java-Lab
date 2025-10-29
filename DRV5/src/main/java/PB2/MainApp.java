package PB2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainApp {
    public static void scriere(List<PerecheNumere> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/perechi.json");
            mapper.writeValue(file, lista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<PerecheNumere> citire() {
        try {
            File file = new File("src/main/resources/perechi.json");
            ObjectMapper mapper = new ObjectMapper();
            List<PerecheNumere> numere = mapper.readValue(file, new TypeReference<List<PerecheNumere>>() {
            });
            return numere;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<PerecheNumere> numere = citire();
        System.out.println(numere);
        for (PerecheNumere n : numere) {
            System.out.println(n);
        }
        numere.add(new PerecheNumere(3,33));
        scriere(numere);


    }

}

package LAB7;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static void scriere(Map<Integer, Carte> carti) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/carti.json");
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, carti);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Map<Integer, Carte> citire() {
        try {
            File file = new File("src/main/resources/carti.json");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void main(String[] args) {

        var carti = citire();
        System.out.println("\nLista carti:");
        carti.forEach((id, carte) -> System.out.println(id + " - " + carte));

        System.out.println("\nStergem cartea cu id-ul 2");
        carti.remove(2);
        System.out.println("\nLista carti:");
        carti.forEach((id, carte) -> System.out.println(id + " - " + carte));


        System.out.println("\nAdaugam o carte noua");
        var carteNoua = new Carte("BOBO la facultate", "Ghinea Dimitrie", 2025);
        carti.putIfAbsent(7, carteNoua);

        scriere(carti);
        System.out.println("\nS-au facut modificari in fisierul JSON");

        System.out.println("\nLista carti:");
        carti.forEach((id, carte) -> System.out.println(id + " - " + carte));

        System.out.println("\nCerinta 5:");
        System.out.println("\nCartile autorului Harari:");
        Set<Carte> cartiHarari = carti.values().stream().filter(carte -> carte.autor()
                .equalsIgnoreCase("Yuval Noah Harari"))
                .collect(Collectors.toSet());
        cartiHarari.forEach(System.out::println);


        System.out.println("\nCerinta 6:");
        System.out.println("\nCartile autorului Harari ordonate:");
        cartiHarari.stream().sorted(Comparator.comparing(Carte::titlu))
                .forEach(System.out::println);
    }
}

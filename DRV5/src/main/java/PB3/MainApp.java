package PB3;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    private static final String CALE_FISIER = "src/main/resources/mobilier.json";

    public static void scriere(List<Mobilier> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(CALE_FISIER);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, lista);
            System.out.println("Datele au fost scrise în fișierul JSON!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Mobilier> citire() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(CALE_FISIER);
            return mapper.readValue(file, new TypeReference<List<Mobilier>>() {});
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        List<Mobilier> mobilierList = citire();

        if (mobilierList.isEmpty()) {
            System.out.println("Fișierul este gol sau nu există!");
            return;
        }

        System.out.println("=== Lista pieselor de mobilier ===");
        for (Mobilier m : mobilierList) {
            System.out.println(m);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntrodu numele piesei de mobilier pentru detalii: ");
        String nume = sc.nextLine();

        Mobilier mobilierSelectat = mobilierList.stream()
                .filter(m -> m.getNume().equalsIgnoreCase(nume))
                .findFirst()
                .orElse(null);

        if (mobilierSelectat == null) {
            System.out.println("Mobilierul nu a fost găsit.");
            return;
        }

        System.out.println("\n=== Plăcile componente pentru " + mobilierSelectat.getNume() + " ===");
        for (Placa p : mobilierSelectat.getPlaci()) {
            System.out.println("  - " + p);
        }

        double ariaTotala = mobilierSelectat.calculeazaArieTotala();
        double ariaCoala = 2800 * 2070;
        double nrColi = Math.ceil(ariaTotala / ariaCoala);
        System.out.printf("%nPentru piesa '%s' sunt necesare aproximativ %.0f coli de PAL.%n",
                mobilierSelectat.getNume(), nrColi);

        // Exemplu de adăugare a unui nou mobilier
        System.out.print("\nVrei să adaugi un mobilier nou? (da/nu): ");
        String raspuns = sc.nextLine();

        if (raspuns.equalsIgnoreCase("da")) {
            List<Placa> placiNou = new ArrayList<>();
            placiNou.add(new Placa("test", 1000, 500, Orientare.LUNGIME,
                    new boolean[]{true, false, false, true}, 1));

            mobilierList.add(new Mobilier("mobilier nou", placiNou));
            scriere(mobilierList);
        }
    }
}

}

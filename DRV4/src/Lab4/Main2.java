import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    
    // Numele fisierului binar pentru serializare/deserializare
    private static final String FISIER_BINAR = "echip.bin"; 

    public static void main(String[] args) {

        // 1. INCARCAREA DATELOR IN LISTA
        List<Echipamente> echipamente = incarcaEchipamenteDinFisier("echipamente.txt");

        if (echipamente.isEmpty()) {
            System.out.println("Nu s-au putut incarca echipamente din fisier. Programul se opreste.");
            return;
        }

        System.out.println("---------------------------------------------------------");
        
        // 2. REZOLVAREA CERINȚELOR DE AFISARE SI MODIFICARE
        
        // Cerința 1: Afișarea tuturor echipamentelor
        afisareEchipamente(echipamente, "1. Toate Echipamentele");

        // Cerința 2: Afișarea pe categorii
        afisareEchipamentePeClasa(echipamente, Imprimante.class, "2. Imprimante");
        afisareEchipamentePeClasa(echipamente, Copiatoare.class, "2. Copiatoare");
        afisareEchipamentePeClasa(echipamente, SistemeCalcul.class, "2. Sisteme de Calcul");
        
        // Cerința 3: Modificarea stării unui echipament (exemplu pe primul)
        modificaStareEchipament(echipamente.get(0), Stare.VANDUT); 

        // Cerința 4-6: Setări specifice
        seteazaModDeScriere(echipamente); 
        seteazaFormatCopiere(echipamente);
        instaleazaSistemOperare(echipamente);
        
        // Cerința 7: Afișarea echipamentelor vândute
        afisareEchipamenteVandute(echipamente); 
        
        // 3. SERIALIZARE/DESERIALIZARE
        System.out.println("\n---------------------------------------------------------");
        
        // Serializare (scriere in fisier binar)
        scrie(echipamente, FISIER_BINAR); 

        // Deserializare (citire inapoi)
        List<Echipamente> echipamenteCitite = (List<Echipamente>) citeste(FISIER_BINAR);
        if (echipamenteCitite != null) {
             System.out.println("✅ Deserializare reusita. " + echipamenteCitite.size() + " obiecte citite.");
        }
    }
    
    // =========================================================
    // GRUP DE METODE PENTRU INCARCAREA DATELOR
    // =========================================================
    
    // Metoda principala care citeste din fisier
    private static List<Echipamente> incarcaEchipamenteDinFisier(String numeFisier) {
        List<Echipamente> echipamente = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(numeFisier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Adaugă echipamentul citit în listă
                Echipamente e = parseazaLinie(line);
                if (e != null) {
                    echipamente.add(e);
                }
            }
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        return echipamente;
    }
    
    // Metoda care parsează o singură linie și creează obiectul (simplifică mult metoda main)
    private static Echipamente parseazaLinie(String line) {
        String[] parti = line.split(";");
        if (parti.length < 6) return null;

        try {
            String denumire = parti[0].trim();
            Integer nr_inv = Integer.valueOf(parti[1].trim());
            double pret = Double.parseDouble(parti[2].trim());
            String zona_mag = parti[3].trim();
            Stare stare = Stare.valueOf(parti[4].trim());
            String tip = parti[5].trim();

            switch (tip) {
                case "imprimante":
                    return new Imprimante(denumire, nr_inv, pret, zona_mag, stare, 
                        Integer.parseInt(parti[6].trim()), 
                        Integer.parseInt(parti[7].trim()), 
                        Integer.parseInt(parti[8].trim()), 
                        Tiparire.valueOf(parti[9].trim().toUpperCase()));
                case "copiatoare":
                    return new Copiatoare(denumire, nr_inv, pret, zona_mag, stare, 
                        Integer.parseInt(parti[6].trim()), 
                        Format.valueOf(parti[7].trim().toUpperCase()));
                case "sisteme":
                    return new SistemeCalcul(denumire, nr_inv, pret, zona_mag, stare, 
                        parti[6].trim(), 
                        Double.parseDouble(parti[7].trim()), 
                        Integer.parseInt(parti[8].trim()), 
                        Sistem.valueOf(parti[9].trim().toUpperCase()));
                default:
                    return null;
            }
        } catch (Exception e) {
            System.err.println("Eroare la parsarea liniei: " + line);
            return null;
        }
    }

    // =========================================================
    // GRUP DE METODE PENTRU AFISARE
    // =========================================================
    
    // Cerința 1: Afisarea tuturor
    public static void afisareEchipamente(List<Echipamente> lista, String titlu) {
        System.out.println("\n--- " + titlu + " (" + lista.size() + " Elemente) ---");
        lista.forEach(System.out::println);
    }
    
    // Cerința 2: Afisare pe clase
    public static void afisareEchipamentePeClasa(List<Echipamente> lista, Class<?> clasa, String titlu) {
        System.out.println("\n--- " + titlu + " ---");
        // Folosim clasa::isInstance (Referință la Metodă)
        lista.stream()
             .filter(clasa::isInstance)
             .forEach(System.out::println);
    }
    
    // Cerința 7: Afișarea echipamentelor vândute
    public static void afisareEchipamenteVandute(List<Echipamente> lista) {
        System.out.println("\n--- 7. Echipamente Vândute ---");
        lista.stream()
            .filter(e -> e.getStare() == Stare.VANDUT) 
            .forEach(System.out::println); 
    }

    // =========================================================
    // GRUP DE METODE PENTRU MODIFICARI (Cerinte 3, 4, 5, 6)
    // =========================================================

    // Cerința 3: Modificare Stare
    private static void modificaStareEchipament(Echipamente e, Stare nouaStare) {
        if (e == null) return;
        System.out.println("\n--- 3. Modificare Stare ---");
        System.out.println("Starea initiala a " + e.getDenumire() + ": " + e.getStare());
        e.setStare(nouaStare); 
        System.out.println("Noua stare: " + e.getStare());
    }

    // Cerința 4: Setare Mod Scriere
    private static void seteazaModDeScriere(List<Echipamente> lista) {
         System.out.println("\n--- 4. Setare Mod Scriere (Imprimantă) ---");
         lista.stream()
            .filter(e -> e instanceof Imprimante)
            .findFirst()
            .ifPresent(e -> {
                Imprimante imp = (Imprimante) e;
                imp.setModScriere(ModScriere.HIGH_QUALITY); 
                System.out.println("Setat " + imp.getModScriere() + " pentru " + imp.getDenumire());
            });
    }
    
    // Cerința 5: Setare Format Copiere
    private static void seteazaFormatCopiere(List<Echipamente> lista) {
         System.out.println("\n--- 5. Setare Format Copiere (Copiator) ---");
         lista.stream()
            .filter(e -> e instanceof Copiatoare)
            .findFirst()
            .ifPresent(e -> {
                Copiatoare cop = (Copiatoare) e;
                cop.setFormat(Format.A3); 
                System.out.println("Setat " + cop.getFormat() + " pentru " + cop.getDenumire());
            });
    }
    
    // Cerința 6: Instalare OS
    private static void instaleazaSistemOperare(List<Echipamente> lista) {
         System.out.println("\n--- 6. Instalare Sistem de Operare (Sistem Calcul) ---");
         lista.stream()
            .filter(e -> e instanceof SistemeCalcul)
            .findFirst()
            .ifPresent(e -> {
                SistemeCalcul sys = (SistemeCalcul) e;
                sys.setOs(Sistem.LINUX); 
                System.out.println("Setat " + sys.getOs() + " pentru " + sys.getDenumire());
            });
    }

    // =========================================================
    // GRUP DE METODE PENTRU SERIALIZARE (Cerinta 8)
    // =========================================================
    
    // Metoda Statica pentru Serializare Binara (Scriere)
    public static void scrie(Object o, String fis) {
        try (FileOutputStream f = new FileOutputStream(fis);
             ObjectOutputStream oos = new ObjectOutputStream(f)) {
            oos.writeObject(o);
            System.out.println("Serializare reusita in " + fis);
        } catch (IOException e) {
            System.err.println("Eroare la serializare: " + e.getMessage());
        }
    }

    // Metoda Statica pentru Deserializare Binara (Citire)
    public static Object citeste(String fis) {
        try (FileInputStream f = new FileInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(f)) {
            return ois.readObject();
        } catch (IOException e) {
            System.err.println("Eroare la deserializare (IO): " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Eroare la deserializare: Clasa obiectului nu a fost gasita.");
        }
        return null;
    }
}

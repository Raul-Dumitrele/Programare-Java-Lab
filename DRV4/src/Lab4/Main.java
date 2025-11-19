import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Echipamente> echipamente = new ArrayList<>();
        
        // Citirea datelor din fișier (Blocul existent)
        try (BufferedReader reader = new BufferedReader(new FileReader("echipamente.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parti = line.split(";");
                if (parti.length < 6) continue;

                String denumire = parti[0].trim();
                // ATENTIE: Folosim Integer.valueOf pentru consistenta cu constructorii
                Integer nr_inv = Integer.valueOf(parti[1].trim()); 
                double pret = Double.parseDouble(parti[2].trim());
                String zona_mag = parti[3].trim();
                Stare stare = Stare.valueOf(parti[4].trim());
                String tip = parti[5].trim();

                switch (tip) {
                    case "imprimante":
                        if (parti.length < 10) continue;
                        int ppm = Integer.parseInt(parti[6].trim());
                        int rezolutie = Integer.parseInt(parti[7].trim());
                        int p_car = Integer.parseInt(parti[8].trim());
                        Tiparire tiparire = Tiparire.valueOf(parti[9].trim().toUpperCase());
                        // Presupune ca Imprimante are constructor corect
                        echipamente.add(new Imprimante(denumire, nr_inv, pret, zona_mag, stare, ppm, rezolutie, p_car, tiparire));
                        break;

                    case "copiatoare":
                        if (parti.length < 8) continue;
                        int p_ton = Integer.parseInt(parti[6].trim());
                        Format format = Format.valueOf(parti[7].trim().toUpperCase());
                        // Presupune ca Copiatoare are constructor corect
                        echipamente.add(new Copiatoare(denumire, nr_inv, pret, zona_mag, stare, p_ton, format));
                        break;

                    case "sisteme":
                        if (parti.length < 10) continue;
                        String tip_mon = parti[6].trim();
                        double vit_proc = Double.parseDouble(parti[7].trim());
                        int c_hdd = Integer.parseInt(parti[8].trim());
                        Sistem os = Sistem.valueOf(parti[9].trim().toUpperCase());
                        // Presupune ca SistemeCalcul are constructor corect
                        echipamente.add(new SistemeCalcul(denumire, nr_inv, pret, zona_mag, stare, tip_mon, vit_proc, c_hdd, os));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Eroare: Fisierul 'echipamente.txt' nu a fost gasit.");
        }
        
        // =========================================================
        // IMPLEMENTAREA CERINȚELOR DE AFISARE ȘI MODIFICARE
        // =========================================================
        
        // 1. Afișarea tuturor echipamentelor
        afisareEchipamente(echipamente, "1. Toate Echipamentele");

        // 2. Afișarea pe categorii (Imprimante, Copiatoare, Sisteme de calcul)
        afisareEchipamentePeClasa(echipamente, Imprimante.class, "2. Imprimante");
        afisareEchipamentePeClasa(echipamente, Copiatoare.class, "2. Copiatoare");
        afisareEchipamentePeClasa(echipamente, SistemeCalcul.class, "2. Sisteme de Calcul");
        
        // 3. Modificarea stării în care se află un echipament (Exemplu pe primul echipament)
        System.out.println("\n--- 3. Modificare Stare ---");
        echipamente.stream().findFirst().ifPresent(e -> {
            System.out.println("Starea initiala a " + e.getDenumire() + ": " + e.getStare());
            e.setStare(Stare.VANDUT); // Schimbăm starea in VANDUT
            System.out.println("Noua stare: " + e.getStare());
        });

        // 4. Setarea unui anumit mod de scriere pentru o imprimantă
        System.out.println("\n--- 4. Setare Mod Scriere (Imprimantă) ---");
        echipamente.stream()
            .filter(e -> e instanceof Imprimante)
            .findFirst()
            .ifPresent(e -> {
                Imprimante imp = (Imprimante) e;
                // Presupunem ModScriere.HIGH_QUALITY exista
                imp.setModScriere(ModScriere.HIGH_QUALITY); 
                System.out.println("Mod scriere setat la: " + imp.getModScriere() + " pentru " + imp.getDenumire());
            });
            
        // 5. Setarea unui format de copiere pentru copiatoare
         System.out.println("\n--- 5. Setare Format Copiere (Copiator) ---");
         echipamente.stream()
            .filter(e -> e instanceof Copiatoare)
            .findFirst()
            .ifPresent(e -> {
                Copiatoare cop = (Copiatoare) e;
                // Presupunem Format.A3 exista
                cop.setFormat(Format.A3); 
                System.out.println("Format copiere setat la: " + cop.getFormat() + " pentru " + cop.getDenumire());
            });

        // 6. Instalarea unui anumit sistem de operare pe un sistem de calcul
        System.out.println("\n--- 6. Instalare Sistem de Operare ---");
        echipamente.stream()
            .filter(e -> e instanceof SistemeCalcul)
            .findFirst()
            .ifPresent(e -> {
                SistemeCalcul sys = (SistemeCalcul) e;
                // Presupunem Sistem.LINUX exista
                sys.setOs(Sistem.LINUX); 
                System.out.println("OS setat la: " + sys.getOs() + " pentru " + sys.getDenumire());
            });

        // 7. Afișarea echipamentelor vândute
        System.out.println("\n--- 7. Echipamente Vândute ---");
        echipamente.stream()
            .filter(e -> e.getStare() == Stare.VANDUT) 
            .forEach(System.out::println); 

        // 8. Serializare / Deserializare
        String fisierBinar = "echip.bin";
        System.out.println("\n--- 8. Serializare / Deserializare ---");
        
        scrie(echipamente, fisierBinar); // Serializare: Salveaza lista in fisier

        // Deserializare: Citeste inapoi
        List<Echipamente> echipamenteCitite = (List<Echipamente>) citeste(fisierBinar);
        if (echipamenteCitite != null) {
             System.out.println("Deserializare reusita! Numar de echipamente citite din " + fisierBinar + ": " + echipamenteCitite.size());
        }
    }
    
    // ====================================================================
    // METODE STATICE AJUTATOARE (AFIȘARE ȘI PERSISTENȚĂ)
    // ====================================================================

    // Functia de afisare generica (Cerința 1)
    public static void afisareEchipamente(List<Echipamente> lista, String titlu) {
        System.out.println("\n--- " + titlu + " (" + lista.size() + " Elemente) ---");
        lista.forEach(System.out::println);
    }
    
    // Functia de afisare pe categorii (Cerința 2)
    public static void afisareEchipamentePeClasa(List<Echipamente> lista, Class<?> clasa, String titlu) {
        System.out.println("\n--- " + titlu + " ---");
        // Folosim clasa::isInstance (Referință la Metodă) echivalent cu e -> clasa.isInstance(e)
        lista.stream()
             .filter(clasa::isInstance)
             .forEach(System.out::println);
    }
    
    // Metoda Statica pentru Serializare Binara (Scriere in echip.bin)
    public static void scrie(Object o, String fis) {
        try (FileOutputStream f = new FileOutputStream(fis);
             ObjectOutputStream oos = new ObjectOutputStream(f)) {
            oos.writeObject(o);
            System.out.println("Serializare reusita in " + fis);
        } catch (IOException e) {
            System.err.println("Eroare la serializare: " + e.getMessage());
        }
    }

    // Metoda Statica pentru Deserializare Binara (Citire din echip.bin)
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

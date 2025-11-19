package ro.utcluj.pja.lab6;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    
    // Functia de citire a datelor din JSON
    public static List<Angajat> citesteAngajatiDinJson(String filePath) {
        // var este folosit pentru inferenta tipului
        var mapper = new ObjectMapper();
        
        // Inregistrarea modulului pentru a suporta LocalDate
        mapper.registerModule(new JavaTimeModule());

        // Citirea listei de angajati
        try {
            // ObjectMapper.readValue nu poate citi direct List<Angajat> asa ca folosim TypeReference
            // Desi nu este cerut explicit, e cea mai corecta modalitate
            // Pentru simplitate (ca pentru incepatori) o sa folosim citirea ca Array
            var angajatiArray = mapper.readValue(new File(filePath), Angajat[].class);
            return List.of(angajatiArray);
        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului JSON: " + e.getMessage());
            return List.of(); // Returneaza lista goala in caz de eroare
        }
    }
    
    // Functia de scriere a datelor in JSON (nu e ceruta direct, dar utila)
    public static void scrieAngajatiInJson(List<Angajat> angajati, String filePath) {
        var mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        
        // Scrierea LocalDate sub forma de sir de caractere (nu vector [an,luna,zi])
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        try {
            mapper.writeValue(new File(filePath), angajati);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea fisierului JSON: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // var pentru inferenta tipului local
        var jsonPath = "src/main/resources/angajati.json";
        var angajati = citesteAngajatiDinJson(jsonPath);

        if (angajati.isEmpty()) {
            System.out.println("Nu s-au putut citi datele angajatilor. Programul se opreste.");
            return;
        }

        System.out.println("--- Evidenta Angajati Firma ---");
        
        // 1. Afișarea listei de angajați folosind referințe la metode.
        System.out.println("\n1. Lista tuturor angajatilor (Referință la Metodă):");
        angajati.forEach(System.out::println); 

        // 2. Afișarea angajaților cu salariul peste 2500 RON (Stream și Lambda).
        System.out.println("\n2. Angajați cu salariul peste 2500 RON (Stream și Lambda):");
        angajati.stream()
                // Predicate (Interfata Functionala) implementata prin Expresie Lambda
                .filter(a -> a.getSalariul() > 2500.0f)
                .forEach(System.out::println);

        // 3. Crearea listei cu angajații din aprilie, anul trecut, cu funcție de conducere.
        // Se va folosi var pentru inferenta tipului la variabilele locale.
        var anulCurent = LocalDate.now().getYear();
        var anulTrecut = anulCurent - 1;
        var lunaAprilie = 4;
        
        System.out.println("\n3. Angajați angajați în Aprilie " + anulTrecut + " cu funcție de conducere:");
        var angajatiFiltru = angajati.stream()
                .filter(a -> a.getData_angajarii().getYear() == anulTrecut)
                .filter(a -> a.getData_angajarii().getMonthValue() == lunaAprilie)
                // Postul contine "sef" sau "director" (ignorand majusculele)
                .filter(a -> a.getPostul().toLowerCase().contains("sef") || 
                             a.getPostul().toLowerCase().contains("director"))
                // Operatie terminala collect (Collectors.toList())
                .collect(Collectors.toList());
        
        if (angajatiFiltru.isEmpty()) {
            System.out.println("Niciun angajat nu corespunde criteriilor.");
        } else {
             angajatiFiltru.forEach(System.out::println);
        }
       
        // 4. Angajații fără funcție de conducere, descrescător după salariu.
        System.out.println("\n4. Angajați fără funcție de conducere, descrescător după salariu:");
        angajati.stream()
                // Angajati care NU au functie de conducere
                .filter(a -> !a.getPostul().toLowerCase().contains("director") && 
                             !a.getPostul().toLowerCase().contains("sef"))
                // Sortare descrescătoare a salariului (Lambda)
                .sorted((a1, a2) -> Float.compare(a2.getSalariul(), a1.getSalariul()))
                .forEach(System.out::println);
                
        // 5. Lista de String-uri cu numele angajaților cu majuscule.
        System.out.println("\n5. Numele angajaților cu MAJUSCULE:");
        var numeMajuscule = angajati.stream()
                // Metoda map() pentru a transforma obiectul Angajat în String (nume)
                .map(a -> a.getNume().toUpperCase())
                // Operatia terminala collect()
                .collect(Collectors.toList());
        
        numeMajuscule.forEach(System.out::println);

        // 6. Afișarea salariilor < 3000 RON (doar salariile).
        System.out.println("\n6. Salariile mai mici de 3000 RON (Referință la Metodă și Stream):");
        angajati.stream()
                .filter(a -> a.getSalariul() < 3000.0f)
                // Metoda map() pentru a extrage doar salariul
                .map(Angajat::getSalariul) // Referință la Metodă
                .forEach(System.out::println);

        // 7. Afișarea datelor primului angajat (cel cu data de angajare minimă) - Optional.
        System.out.println("\n7. Datele primului angajat al firmei (cea mai veche data_angajarii):");
        var primulAngajat = angajati.stream()
                // Determinarea minimului folosind Comparator (Lambda)
                .min(Comparator.comparing(Angajat::getData_angajarii));
                // Echivalent cu: .min((a1, a2) -> a1.getData_angajarii().compareTo(a2.getData_angajarii()));

        // Verificarea containerului Optional si afisarea
        if (primulAngajat.isPresent()) {
            System.out.println("Primul Angajat: " + primulAngajat.get());
        } else {
            System.out.println("Nu exista angajati in lista.");
        }

        // 8. Statistici salariale (media, min, max).
        System.out.println("\n8. Statistici referitoare la salariul angajaților:");
        var stats = angajati.stream()
                // Operația terminală collect(Collectors.summarizingDouble())
                .collect(Collectors.summarizingDouble(Angajat::getSalariul));
        
        System.out.println("Salariul Mediu: " + String.format("%.2f", stats.getAverage()) + " RON");
        System.out.println("Salariul Minim: " + String.format("%.2f", stats.getMin()) + " RON");
        System.out.println("Salariul Maxim: " + String.format("%.2f", stats.getMax()) + " RON");
        
        // 9. Verificarea existenței unui angajat numit "Ion" (findAny() și Optional).
        System.out.println("\n9. Verificarea existentei unui angajat numit \"Ion\":");
        var ion = angajati.stream()
                .filter(a -> a.getNume().contains("Ion"))
                // findAny() returneaza un Optional<Angajat>
                .findAny(); 
        
        // Utilizarea ifPresentOrElse din Optional
        ion.ifPresentOrElse(
            a -> System.out.println("Firma are cel puțin un Ion angajat: " + a.getNume()), 
            () -> System.out.println("Firma nu are nici un Ion angajat.")
        );
        
        // 10. Afișarea numărului de persoane angajate în vara anului precedent.
        System.out.println("\n10. Numărul de persoane angajate în vara anului precedent:");
        var nrAngajatiVaraAnulTrecut = angajati.stream()
                .filter(a -> a.getData_angajarii().getYear() == anulTrecut)
                // Vara: lunile Iunie (6), Iulie (7), August (8)
                .filter(a -> {
                    var luna = a.getData_angajarii().getMonthValue();
                    return luna >= 6 && luna <= 8;
                })
                .count(); // Metoda count() din Stream
        
        System.out.println("Numărul de angajați în vara lui " + anulTrecut + ": " + nrAngajatiVaraAnulTrecut);
    }
}

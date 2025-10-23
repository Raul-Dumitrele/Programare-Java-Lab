package Lab4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("echipamente.txt"));
        List<Echipamente> echipament = new ArrayList<>();
        String linie;
        while((linie=reader.readLine())!=null){
            String[] parti = linie.split(";");
            String denumire = parti[0].trim();
            int nr_inv=Integer.parseInt(parti[1].trim());
            double pret = Double.parseDouble(parti[2].trim());
            String zona_mag=parti[3].trim();
            String stare=parti[4].trim();
            String tip = parti[5].trim().toLowerCase();

            switch (tip){
                case "imprimante":
                    int ppm=Integer.parseInt(parti[6].trim());
                    int rezolutie = Integer.parseInt(parti[7].trim());
                    int p_car = Integer.parseInt(parti[8].trim());
                    Tiparire tiparire = Tiparire.valueOf(parti[9].trim());
                    echipament.add(new Imprimante(denumire, nr_inv, pret, zona_mag, stare, ppm, rezolutie, tiparire, p_car));
            }


        }

    }
}

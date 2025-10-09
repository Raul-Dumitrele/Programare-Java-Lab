package Ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> listajudete=new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("src/Ex1/judete_in.txt"));
            while(scanner.hasNextLine()){
                String judet = scanner.nextLine().toLowerCase().trim();
                listajudete.add(judet);
            }
            String[] judete=listajudete.toArray(new String[listajudete.size()]);
            Arrays.sort(judete);

            for(String j : judete){
                System.out.println(j);
            }

            Scanner sc = new Scanner(System.in);                    //Creez un obiect Scanner care citește de la tastatură (System.in).
            System.out.println("Judetul cautat:");                  //Afișează mesajul "Judetul cautat:" în consolă.
            String judetcautat = sc.nextLine().toLowerCase().trim();//Salveaza in judetcautat ce am citit de la tastatura

            System.out.println("Ati cautat judetul:"+judetcautat);

            int pozitiejudet=Arrays.binarySearch(judete,judetcautat);
            System.out.println("Pozitia judetului "+ judetcautat + " este:"+pozitiejudet);




        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

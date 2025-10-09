package exercitiu1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainApp
{
    public static void main(String[] args)
    {
        ArrayList<String> listaJudete = new ArrayList<>();
        try
        {
            Scanner scanner = new Scanner(new File("src/exercitiu1/judete_in"));
            while (scanner.hasNextLine())
            {
                String judet = scanner.nextLine().toLowerCase().trim();
                listaJudete.add(judet);
            }

            String[] judete = listaJudete.toArray(new String[listaJudete.size()]);
            Arrays.sort(judete);

            for(String j : judete)
                System.out.println(j);

            Scanner sc = new Scanner(System.in);
            System.out.println("Judetul cautat: ");
            String judetCautat = sc.nextLine().toLowerCase().trim();

            int pozitieJudet = Arrays.binarySearch(judete, judetCautat);
            System.out.println("Judetul " + judetCautat + " se afla pe pozitia: " + (pozitieJudet+1));

        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }


    }
}

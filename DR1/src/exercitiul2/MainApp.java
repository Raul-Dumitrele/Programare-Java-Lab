package exercitiul2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws FileNotFoundException {
        int suma = 0, count = 0, minim = Integer.MAX_VALUE, maxim = Integer.MIN_VALUE;

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("src/exercitiul2/in.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextInt()) {
            int x = scanner.nextInt();
            suma += x;
            count += 1;
            if (x > maxim) maxim = x;
            if (x < minim) minim = x;

        }

        double media = (double) suma / count;
        scanner.close();

        System.out.println("Suma " + suma);
        System.out.println("Media " + media);
        System.out.println("Minim " + minim);
        System.out.println("Maxim " + maxim);

        PrintWriter pw = new PrintWriter("out.txt");
        {
            pw.println("Suma " + suma);
            pw.println("Media " + media);
            pw.println("Minim " + minim);
            pw.println("Maxim " + maxim);
        }
        pw.close();
    }
}

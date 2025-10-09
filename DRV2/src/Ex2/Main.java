package Ex2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Gruparea aleasa");
        String grupare = sc.nextLine();


        try {
            Scanner scanner = new Scanner(new File("src/Ex2/cantec_in.txt"));
            while (scanner.hasNextLine()){
                String rand = scanner.nextLine().trim();
                if(!rand.isEmpty()){
                    Vers vers = new Vers(rand);
                    String detalli= vers.detalii(grupare);
                    System.out.println(detalli);
                    OutputStream g=new OutputStream("date1.out");
                    PrintStream gchar=new PrintStream(g);
                    gchar.println(n*n);

                }
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

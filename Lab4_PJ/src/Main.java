import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void Main(String[] args)
    {
        List<Echipamente> listaE = new ArrayList<>();
        String[] text;
        //Echipamente ec = new Echipamente("aaa", 20, 100.0, "Spate", Situatii.achizitionat, "Imprimanta");
        Scanner scanner = new Scanner("electronice.txt");
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            text = line.split(", ");
            if (text[5] == "Imprimanta")
            {
                Imprimanta I = new Imprimanta(text[0], Integer.parseInt(text[1]), Double.parseDouble(text[2]), text[3], Situatii.valueOf(text[4]), text[5], Double.parseDouble(text[6]), Integer.parseInt(text[7]), Integer.parseInt(text[8]), Tiparire.valueOf(text[9]));
            }
        }
    }
}

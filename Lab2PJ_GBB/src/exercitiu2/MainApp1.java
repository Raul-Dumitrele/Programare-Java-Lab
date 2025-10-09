package exercitiu2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Vers
{
    private String text;
    public Vers (String text)
    {
        this.text = text;
    }

    public  int numarVocale()
    {
        int nrVocale = 0;
        for(int i=0; i<text.length();i++)
        {
            char c= Character.toLowerCase(text.charAt(i));
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u')
            {
                nrVocale++;
            }
        }
        return  nrVocale;
    }

    public int numarCuvinte()
    {
        if(text.isEmpty())
            return 0;
        String[] cuvinte = text.split("\\s+");
        return cuvinte.length;
    }

    public boolean endsWith(String gruparea )
    {
        return text.toLowerCase().endsWith(gruparea.toLowerCase());
    }

    public String seTerminaCu(String gruparea)
    {
        if(endsWith(gruparea))
            return text+" *";
        return text;
    }

    public String verificaMajuscule()
    {
        double random = Math.random();
        if(random<0.1)
            return text.toUpperCase();
        return text;
    }

    public String proceseazaVers(String gruparea)
    {
        return seTerminaCu(gruparea)+" Cuvinte: "+ numarCuvinte()+ " | Vocale: "+ numarVocale();
    }
}

public class MainApp1
{
    public static void main(String[] args)
    {
        try
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Gruparea aleasa: ");
            String grupare = sc.nextLine();

            Scanner scanner = new Scanner( new File("src/exercitiu2/cantec_in.txt"));

            while (scanner.hasNextLine())
            {
                String rand = scanner.nextLine().trim();
                if(!rand.isEmpty())
                {
                    Vers vers = new Vers(rand);
                    String rezultat = vers.proceseazaVers(grupare);
                    System.out.println(rezultat);
                }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
package Ex2;

public class Vers {
    private final String text;

    public Vers(String text){   //constructorul clasei
        this.text=text;
    }

    public int numarVocale(){
        int nrVocale=0;
        for(int i=0;i<text.length();i++){
            char c = Character.toLowerCase(text.charAt(i)); //text.charAt(i) — ia caracterul de la poziția i din șirul text.
            if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
                nrVocale++;
            }
        }
        return nrVocale;
    }

    public int numarCuvinte(){
        if(text.isEmpty()){
            return 0;
        }
        String[] cuvinte=text.split("\\s+");    //text.split("\\s+") împarte șirul de caractere text în mai multe sub-șiruri, folosind ca delimitator un spațiu alb (sau mai multe spații, tab-uri, newline etc.).
                                                      //\\s+ este o expresie regulată care înseamnă „unul sau mai multe caractere de spațiu alb” (spațiu, tab, newline).Rezultatul este un array de stringuri, fiecare element fiind un „cuvânt” extras din text, separat de spații.
        return cuvinte.length;
    }

    public boolean endsWith(String gruparea)
    {
        return text.endsWith(gruparea);
    }

    public String Steluta(String gruparea){
        if(endsWith(gruparea))
            return text+" * ";
        return text;
    }

    public String detalii(String gruparea){
        return Steluta(gruparea) +" NrCuvinte:" +numarCuvinte() + " NrVocale:" + numarVocale();
    }





    public String detalii(){
        String detalii ="NrCuvinte"+ numarCuvinte() + "NrVocale" + numarVocale();
        return detalii;
    }




}

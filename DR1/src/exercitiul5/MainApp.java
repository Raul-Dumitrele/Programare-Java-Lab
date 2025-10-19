package exercitiul5;

import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        Random random=new Random();
        int a=random.nextInt(20)+1;
        System.out.printf("Numarul generat este "+a +"\n");

        if(esteFibbonaci(a)){
            System.out.printf(a+" apartine sirului Fibonacci");
        }
        else{
            System.out.printf(a+" nu apartine sirului Fibonacci");
        }

    }

    public static boolean esteFibbonaci(int n){
        int a=0;
        int b=1;
        while(b<n){
            int c=a+b;
            a=b;
            b=c;
        }
        return b==n;
    }
}

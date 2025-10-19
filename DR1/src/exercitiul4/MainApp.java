package exercitiul4;

import java.util.Random;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Random random=new Random();

        int a=random.nextInt(30)+1;
        int b=random.nextInt(30)+1;

        System.out.printf("Numerele generate sunt %d și %d\n", a, b);


        int cmmdc = calculeazaCmmdc(a,b);
        System.out.printf("CMMDC-ul este:" +cmmdc);


    }

    static int calculeazaCmmdc(int a,int b){
        while (a!=b){
            if(a>b)
                a=a-b;
            else
                b=b-a;
        }
        return a;
    }
}

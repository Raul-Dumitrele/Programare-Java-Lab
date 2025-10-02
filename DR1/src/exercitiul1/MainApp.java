package exercitiul1;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("a=");
        int a = scanner.nextInt();
        System.out.println("b=");
        int b = scanner.nextInt();

        int perimetrul=a*2+b*2;
        int aria=a*b;


        System.out.println("Perimetrul="+perimetrul);
        System.out.println("Aria="+aria);

        scanner.close();
    }
}



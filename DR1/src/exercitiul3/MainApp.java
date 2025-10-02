package exercitiul3;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("a=");
        int a = scanner.nextInt();
        System.out.println("Divizorii numarului: "+a);

        int k=1;
        int count=0;
        while(k<=a){
            if(a%k==0){
                count+=1;
                System.out.println(k);
            }
            k+=1;
        }
        if(count == 2){
            System.out.println("Numarul este prim!");
        }

    }
}

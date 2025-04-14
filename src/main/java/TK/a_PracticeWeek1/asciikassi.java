package TK.a_PracticeWeek1;

import java.util.Scanner;

public class asciikassi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); 
        System.out.println("+" + "-".repeat(n) + "+");
        
        for (int i = 0; i < n; i++) {
            System.out.println("|" + " ".repeat(n) + "|");
        }
        System.out.println("+" + "-".repeat(n) + "+");
        
        scanner.close(); 
    }
}

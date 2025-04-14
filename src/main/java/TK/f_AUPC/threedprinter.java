package TK.f_AUPC;

import java.util.Scanner;

public class threedprinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int printer = 1;
        int days = 0;
        for (int i = 1; printer < n; i++) {
            printer *= 2;
            days = i;
        }
        System.out.println(days + 1);
        scanner.close();
    }
}
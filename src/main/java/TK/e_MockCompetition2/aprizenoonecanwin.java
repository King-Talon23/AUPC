package TK.e_MockCompetition2;

import java.util.Arrays;
import java.util.Scanner;

public class aprizenoonecanwin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        int i = 1;
        for (; i < n; i++) {
            if (a[i] + a[i - 1] > x) {
                break;
            }
        }
        System.out.println(i);
        scanner.close();
    }
}
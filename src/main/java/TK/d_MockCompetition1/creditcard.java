package TK.d_MockCompetition1;

import java.util.Scanner;

public class creditcard {
    public static int creditCard(Scanner scanner) {
        double r = scanner.nextDouble();
        double b = scanner.nextDouble();
        double m = scanner.nextDouble();
        int cnt = 0;
        
        while (true) {
            double interest = (r * b) / 100.0;
            interest = Math.round(interest * 100.0) / 100.0;
            b += interest;
            b -= m;
            b = Math.round(b * 100.0) / 100.0;
            cnt++;
            
            if (b <= 0) return cnt;
            if (cnt >= 1200) return -1;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        
        while (t-- > 0) {
            int res = creditCard(scanner);
            if (res == -1) System.out.println("impossible");
            else System.out.println(res);
        }
        
        scanner.close();
    }
}
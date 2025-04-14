package TK.d_MockCompetition1;

import java.util.Scanner;

public class peningar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read input
        int n = scanner.nextInt();
        long d = scanner.nextLong();
        
        int[] money = new int[n + 1]; // 1-indexed to match problem description
        for (int i = 1; i <= n; i++) {
            money[i] = scanner.nextInt();
        }
        
        // Calculate GCD of n and d to determine cycle length
        long gcd = gcd(n, d);
        int cycleLength = (int)(n / gcd);
        
        // Calculate total money
        long totalMoney = 0;
        int currentPosition = 1;
        
        // We only need to make cycleLength moves as we'll revisit the same cells after that
        for (int i = 0; i < cycleLength; i++) {
            totalMoney += money[currentPosition];
            
            // Move d cells forward
            currentPosition = (int)(((currentPosition - 1 + d) % n) + 1);
        }
        
        System.out.println(totalMoney);
        
        scanner.close();
    }
    
    // Calculate greatest common divisor using Euclidean algorithm
    private static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
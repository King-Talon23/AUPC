package TK.c_PracticeWeek3;

import java.util.*;

class pie {
    public static boolean isPossible(double[] pies, double V, int totalPeople) {
        int count = 0;
        for (int i = 0; i < pies.length; i++) {
            double volume = Math.PI * pies[i] * pies[i];
            count += (int) (volume / V);
        }
        return count >= totalPeople;
    }

    public static void printing(int testcases, Scanner sc) {
        double[] results = new double[testcases];

        for (int i = 0; i < testcases; i++) {
            int pie = sc.nextInt();
            int friends = sc.nextInt();
            double[] pies = new double[pie];
            for (int j = 0; j < pie; j++) {
                pies[j] = sc.nextDouble();
            }

            int totalPeople = friends + 1;
            double left = 0;
            double right = 0;

            for (int j = 0; j < pies.length; j++) {
                double volume = Math.PI * pies[j] * pies[j];
                right = Math.max(right, volume);
            }
            while (right - left > 1e-7) {
                double mid = (left + right) / 2;
                if (isPossible(pies, mid, totalPeople)) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            results[i] = left;
        }

        for (int i = 0; i < testcases; i++) {
            System.out.println(results[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testcases = sc.nextInt();
        printing(testcases, sc);
        sc.close();
    }
}

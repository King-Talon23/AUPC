package TK.e_MockCompetition2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class matchsticks {

    private static final Map<Integer, String> SMALL_CASES = new HashMap<>();
    private static final String[] LOOKUP = {"108", "188", "200", "208", "288", "688", "888"};

    static {
        SMALL_CASES.put(2, "1");
        SMALL_CASES.put(3, "7");
        SMALL_CASES.put(4, "4");
        SMALL_CASES.put(5, "2");
        SMALL_CASES.put(6, "6");
        SMALL_CASES.put(7, "8");
        SMALL_CASES.put(8, "10");
        SMALL_CASES.put(9, "18");
        SMALL_CASES.put(10, "22");
        SMALL_CASES.put(11, "20");
        SMALL_CASES.put(12, "28");
        SMALL_CASES.put(13, "68");
        SMALL_CASES.put(14, "88");
        SMALL_CASES.put(15, "108");
    }

    public static String getMin(int n) {
        if (n <= 15) {
            return SMALL_CASES.get(n);
        }
        int q = (n - 15) / 7;
        int r = (n - 15) % 7;
        StringBuilder res = new StringBuilder(LOOKUP[r]);
        for (int i = 0; i < q; i++) {
            res.append('8');
        }
        return res.toString();
    }

    public static String getMax(int n) {
        StringBuilder res = new StringBuilder();
        if (n % 2 == 1) {
            int howManyOnes = (n - 3) / 2;
            res.append('7');
            for (int i = 0; i < howManyOnes; i++) {
                res.append('1');
            }
        } else {
            int howManyOnes = n / 2;
            for (int i = 0; i < howManyOnes; i++) {
                res.append('1');
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int i = 0; i < T; i++) {
            int n = scanner.nextInt();
            System.out.println(getMin(n) + " " + getMax(n));
        }
        scanner.close();
    }
}
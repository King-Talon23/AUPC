package TK.c_PracticeWeek3;

import java.util.*;

public class reportcard {
    static final int M = 1000000007;
    static final int OFFSET = 9000;

    static Map<Character, int[]> R = new HashMap<>();
    static {
        R.put('A', new int[]{0, 0, -3});
        R.put('B', new int[]{3, 0, -3});
        R.put('C', new int[]{3, 0, -3});
        R.put('D', new int[]{3, 0, -3});
        R.put('F', new int[]{0});
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine(); // consume the newline
        String input = sc.nextLine();

        int[] Z = new int[18001];
        Z[OFFSET] = 1;

        for (char i : input.toCharArray()) {
            int[] X = new int[18001];
            for (int j : R.get(i)) {
                for (int k = 0; k < 18001; k++) {
                    if (0 <= k + j && k + j < 18001) {
                        X[k + j] = (X[k + j] + Z[k]) % M;
                    }
                }
            }
            Z = X;
        }

        long result = 0;
        for (int i = OFFSET + 1; i < 18001; i++) {
            result = (result + Z[i]) % M;
        }

        System.out.println(result);
    }
}

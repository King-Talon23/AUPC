package TK.b_PracticeWeek2;

import java.util.*;

 public class fridgedp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] li = new int[10];
        String s = sc.next();

        for (int i = 0; i < s.length(); i++) {
            li[Character.getNumericValue(s.charAt(i))]++;
        }

        // Create a DP array to store the minimum number of digits needed
        int[] dp = new int[200];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < 200; i++) {
            for (int j = 0; j < 10; j++) {
                if (li[j] >= i) {
                    dp[i] = Math.min(dp[i], dp[i - 1] + 1);
                }
            }
        }

        for (int i = 1; i < 200; i++) {
            if (i > 1 && li[0] < i - 1) {
                System.out.println("1" + "0".repeat(i - 1));
                System.exit(0);
            }
            for (int j = 1; j < 10; j++) {
                if (li[j] < i) {
                    System.out.println(String.valueOf(j).repeat(i));
                    System.exit(0);
                }
            }
        }
    }
}

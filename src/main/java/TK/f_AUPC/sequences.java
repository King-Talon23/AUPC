package TK.f_AUPC;

import java.util.Scanner;

public class sequences {

    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        StringBuilder sb = new StringBuilder(s).reverse();
        s = sb.toString();

        int n1 = (s.charAt(0) == '1') ? 1 : 0;
        int nq = (s.charAt(0) == '?') ? 1 : 0;
        long[] p = new long[500010];
        long[] dp = new long[500010];

        p[0] = 1;
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            p[i] = (2 * p[i - 1]) % MOD;
        }

        for (int cur = 1; cur < s.length(); cur++) {
            if (s.charAt(cur) == '0') {
                dp[cur] = dp[cur - 1];
            } else {
                long sum;
                if (nq == 0) {
                    sum = cur - n1;
                } else {
                    sum = (((cur - n1) * p[nq]) % MOD + MOD - (nq * p[nq - 1]) % MOD) % MOD;
                }

                if (s.charAt(cur) == '1') {
                    dp[cur] = (dp[cur - 1] + sum) % MOD;
                    n1++;
                } else {
                    dp[cur] = (2 * dp[cur - 1] + sum) % MOD;
                    nq++;
                }
            }
        }

        System.out.println(dp[s.length() - 1]);
        scanner.close();
    }
}
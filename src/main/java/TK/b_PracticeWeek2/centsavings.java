package TK.b_PracticeWeek2;

import java.util.*;

public class centsavings {
    static int n, INF = 1000000000, d;
    static int[] arr, ori;
    static int[][] memo = new int[2002][21];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = sc.nextInt();
        arr = new int[n];
        ori = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            ori[i] = arr[i];  // only if preserving initial array
            if (i > 0) arr[i] += arr[i - 1];
        }
        for (int[] row : memo) Arrays.fill(row, -1);
        System.out.println(dp(0, d));
    }

    static int rsq(int l, int r) {
        if (l < 0 || r >= n) return -INF;
        if (l == 0) return arr[r];
        return arr[r] - arr[l - 1];
    }

    static int roundToNearest10(int x) {
        return (x + 5) / 10 * 10;
    }

    static int dp(int idx, int k) {
        if (idx == n) return 0;
        if (memo[idx][k] != -1) return memo[idx][k];
        int ans = INF;
        if (k == 0) {
            return memo[idx][k] = roundToNearest10(rsq(idx, n - 1));
        }
        for (int i = idx + 1; i <= n; i++) {
            int cur = dp(i, k - 1) + roundToNearest10(rsq(idx, i - 1));
            ans = Math.min(ans, cur);
        }
        return memo[idx][k] = ans;
    }
}

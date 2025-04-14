package TK.c_PracticeWeek3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class monopoly {
    static long[] dp;
    static long[] val;
    static ArrayList<Integer>[] adj;
    static int n, m, k, sa, sb, b, r, valu;

    // Fast input method using BufferedReader and StringTokenizer
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // Read n (number of nodes), m (number of edges), k (some value related to nodes)
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        sa = Integer.parseInt(st.nextToken()) - 1; // starting node
        sb = Integer.parseInt(st.nextToken()) - 1; // ending node

        dp = new long[n];
        val = new long[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Read edges (connections between nodes)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            adj[u].add(v);
        }

        // Read the property or salary values for each node
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String typ = st.nextToken();
            if (typ.equals("PROPERTY")) {
                b = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
            } else {
                valu = Integer.parseInt(st.nextToken());
                if (typ.equals("SALARY")) {
                    val[i] = valu;
                } else {
                    val[i] = -valu;
                }
            }
        }

        // Initialize dp array and calculate results
        Arrays.fill(dp, Long.MIN_VALUE);
        long res1 = cdp(sa);
        Arrays.fill(dp, Long.MIN_VALUE);
        long res2 = cdp(sb);

        System.out.println(res1 + " " + res2);

        br.close();
    }

    // Recursive function to calculate maximum value
    static long cdp(int u) {
        if (dp[u] > Long.MIN_VALUE)
            return dp[u];
        if (adj[u].isEmpty())
            return dp[u] = 0;
        for (int v : adj[u]) {
            dp[u] = Math.max(dp[u], val[v] + cdp(v));
        }
        return dp[u];
    }
}

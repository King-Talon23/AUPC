package TK.f_AUPC;

import java.io.*;

public class ilovestrings {

    static final String VOW = "aoeiuAOEIU";
    static final String CON = "pyfgcrldhtnsjkxbmwvqzPYFGCRLDHTNSJKXBMWVQZ";
    static final long MOD = 1000000007;

    static int n;
    static int[] a = new int[100000];
    static long[][] l = new long[10][42];
    static long[][] r = new long[10][42];
    static long[] lc = new long[10];
    static long[] rc = new long[10];
    static long[] ry = new long[10];
    static long[] rx = new long[42];
    static long rs;
    static long ans;

    public static void main(String[] args) throws IOException {
        solve();
    }

    static void read() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s.startsWith("-")) {
            s = br.readLine();
        }
        n = s.length();
        for (int i = 0; i < n; i++) {
            int c = VOW.indexOf(s.charAt(i));
            if (c != -1) {
                a[i] = c + 42;
            } else {
                a[i] = CON.indexOf(s.charAt(i));
            }
        }
    }

    static void solve() throws IOException {
        read();

        for (int i = 0; i < n; i++) {
            int c = a[i];
            if (c >= 42) {
                lc[c - 42]++;
            } else {
                for (int y = 0; y < 10; y++) {
                    l[y][c] += lc[y];
                }
            }
        }

        for (int i = n - 1; i > 0; i--) {
            int current = a[i];
            if (current >= 42) {
                int c = current - 42;
                lc[c]--;
                rc[c]++;
                for (int y = 0; y < 10; y++) {
                    if (y != c) {
                        for (int x = 0; x < 42; x++) {
                            ans = (ans + l[y][x] * (rs - ry[y] - rx[x] + r[y][x] - ry[c] + r[c][x]) % MOD) % MOD;
                        }
                    }
                }
            } else {
                int x = current;
                for (int y = 0; y < 10; y++) {
                    l[y][x] -= lc[y];
                    r[y][x] += rc[y];
                    ry[y] += rc[y];
                    rx[x] += rc[y];
                    rs += rc[y];
                }
            }
        }
        System.out.println(ans);
    }
}
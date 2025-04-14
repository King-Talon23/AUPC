package TK.f_AUPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class safe {

    static int[][] tb = new int[3][3];
    static int[] dp = new int[300000];

    static void fetch(int d) {
        for (int i = 2; i >= 0; i--) {
            for (int j = 2; j >= 0; j--) {
                tb[i][j] = d % 4;
                d /= 4;
            }
        }
    }

    static int store() {
        int re = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                re *= 4;
                re += tb[i][j];
            }
        }
        return re;
    }

    static void add(int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == x || j == y) {
                    tb[i][j]--;
                    if (tb[i][j] < 0) tb[i][j] += 4;
                }
            }
        }
    }

    static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        Arrays.fill(dp, -1);
        dp[0] = 0;
        que.offer(0);
        while (!que.isEmpty()) {
            int cur = que.poll();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    fetch(cur);
                    add(i, j);
                    int stored = store();
                    if (dp[stored] == -1) {
                        dp[stored] = dp[cur] + 1;
                        que.offer(stored);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        bfs();

        for (int i = 0; i < 3; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int j = 0; j < 3; j++) {
                tb[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        System.out.println(dp[store()]);
        reader.close();
    }
}
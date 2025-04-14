package TK.f_AUPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class cats {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            M -= C;

            if (M < 0) {
                System.out.println("no");
                continue;
            }

            int[][] g = new int[C][C];
            boolean[] b = new boolean[C];
            int E = C * (C - 1) / 2;

            while (E-- > 0) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());
                g[i][j] = D;
                g[j][i] = D;
            }

            PriorityQueue<int[]> q = new PriorityQueue<>((a, b1) -> a[0] - b1[0]);
            q.offer(new int[]{0, 0});
            boolean f = false;
            int cnt = 0;

            while (!q.isEmpty()) {
                int[] curr = q.poll();
                int cv = curr[1];
                int ck = curr[0];

                if (b[cv]) continue;
                if (cnt == C) break;

                b[cv] = true;
                ++cnt;
                M -= ck;

                for (int i = 0; i < C; ++i) {
                    if (!b[i]) {
                        q.offer(new int[]{g[cv][i], i});
                    }
                }

                if (M >= 0) continue;
                f = true;
                break;
            }

            System.out.println(f ? "no" : "yes");
        }
    }
}
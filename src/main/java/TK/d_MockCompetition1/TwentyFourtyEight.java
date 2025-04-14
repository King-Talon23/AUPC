package TK.d_MockCompetition1;

import java.util.Scanner;

public class TwentyFourtyEight {
    private static final int MAXN = 10;
    private static final int[] MX = {0, -1, 0, 1};
    private static final int[] MY = {-1, 0, 1, 0};

    private static int[][] puzzle = new int[MAXN][MAXN];
    private static int[][] ans = new int[MAXN][MAXN];
    private static boolean[][] merge = new boolean[MAXN][MAXN];
    private static int m;

    private static void play(int x, int y) {
        if (puzzle[x][y] == 0) {
            return;
        }
        int num = puzzle[x][y];
        int nx = x, ny = y;
        boolean FT = true;
        while (FT || ans[nx][ny] == 0) {
            FT = false;
            nx += MX[m];
            ny += MY[m];
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                nx -= MX[m];
                ny -= MY[m];
                break;
            }
        }
        ans[x][y] = 0;
        if (ans[nx][ny] == num && !merge[nx][ny]) {
            ans[nx][ny] *= 2;
            merge[nx][ny] = true;
        } else if (ans[nx][ny] == 0) {
            ans[nx][ny] = num;
        } else {
            nx -= MX[m];
            ny -= MY[m];
            ans[nx][ny] = num;
        }
    }

    private static void output() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j != 0) {
                    System.out.print(" ");
                }
                System.out.print(ans[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                puzzle[i][j] = scanner.nextInt();
                ans[i][j] = puzzle[i][j];
                merge[i][j] = false;
            }
        }
        
        m = scanner.nextInt();
        
        if (m == 0 || m == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    play(i, j);
                }
            }
        } else {
            for (int i = 3; i >= 0; i--) {
                for (int j = 3; j >= 0; j--) {
                    play(i, j);
                }
            }
        }
        
        output();
        scanner.close();
    }
}
package TK.b_PracticeWeek2;

import java.util.*;

public class mailbox {
  static int[][][] memo = new int[11][102][102];
  static int UNKNOWN = -1;

  public static void main(String[] args) {
    for (int i = 0; i <= 10; i++)
      for (int j = 0; j <= 101; j++)
        for (int k = 0; k <= 101; k++)
          memo[i][j][k] = UNKNOWN;

    Scanner in = new Scanner(System.in);
    int N = in.nextInt();

    for (int i = 0; i < N; i++) {
      int M = in.nextInt(), K = in.nextInt();
      System.out.println(bestCost(M, 0, K+1));
    }
  }

  static int bestCost(int mailboxes, int highestSafe, int lowestExploded) {

    if (lowestExploded == highestSafe + 1) {
      return 0;
    }

    if (memo[mailboxes][highestSafe][lowestExploded] != UNKNOWN)
      return memo[mailboxes][highestSafe][lowestExploded];

    if (mailboxes == 1) {
      int res = 0;
      for (int i = highestSafe + 1; i < lowestExploded; i++) {
        res += i;
      }
      memo[mailboxes][highestSafe][lowestExploded] = res;
      return res;
    }

    int best = Integer.MAX_VALUE;
    for (int numFireworks = highestSafe + 1; numFireworks < lowestExploded; numFireworks++) {
      int cost = numFireworks + Math.max(
        bestCost(mailboxes-1, highestSafe, numFireworks),
        bestCost(mailboxes, numFireworks, lowestExploded));
      best = Math.min(best, cost);
    }

    memo[mailboxes][highestSafe][lowestExploded] = best;
    return best;
  }
}
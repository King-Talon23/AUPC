package TK.c_PracticeWeek3;

import java.util.*;

public class hindex {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] citations = new int[n];
        for (int i = 0; i < n; i++) {
            citations[i] = sc.nextInt();
        }
        Arrays.sort(citations);
        int hIndex = 0;
        for (int i = 0; i < n; i++) {
            if (citations[n - i - 1] >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }
        System.out.println(hIndex);
    }
}

package TK.b_PracticeWeek2;

import java.util.*;

public class fridge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] li = new int[10];
        String s = sc.next();

        for (int i = 0; i < s.length(); i++) {
            li[Character.getNumericValue(s.charAt(i))]++;
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

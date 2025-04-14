package TK.c_PracticeWeek3;

import java.util.*;
import java.lang.Math;

public class santaklas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int A = sc.nextInt();

        if (A <= 180) {
            System.out.println("TK.f_AUPC.safe");
        } else {
            System.out.println((int) Math.floor(H / Math.sin(Math.toRadians(A - 180))));
        }
    }
}

package TK.b_PracticeWeek2;

import java.util.Scanner;

public class sevenwondersdp {
    public static void main(String[] args) {
        String mySeq = enterCards();
        int[] myCards = calculationEachCards(mySeq);
        int myPoints = getPoints(myCards);
        System.out.println(myPoints);
    }

    private static int getPoints(int[] points) {
        int myMin = Math.min(Math.min(points[0], points[1]), points[2]);
        int mySq = points[0] * points[0] + points[1] * points[1] + points[2] * points[2];
        return mySq + myMin * 7;
    }

    private static int[] calculationEachCards(String points) {
        int[] cardCounts = new int[3]; // 0: tablet, 1: gear, 2: compass
        for (int p = 0; p < points.length(); p++) {
            char pivot = points.charAt(p);
            if (pivot == 'T') cardCounts[0]++;
            else if (pivot == 'G') cardCounts[1]++;
            else if (pivot == 'C') cardCounts[2]++;
        }
        return cardCounts;
    }

    private static String enterCards() {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        try {
            str = scanner.nextLine();
            if (!stringConditions(str)) throw new IllegalArgumentException("Invalid input. Please enter a valid sequence.");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return enterCards();
        }
        return str;
    }

    private static boolean stringConditions(String str) {
        if (str.length() < 1 || str.length() > 50) return false;
        for (int k = 0; k < str.length(); k++) {
            char pivot = str.charAt(k);
            if (pivot != 'T' && pivot != 'C' && pivot != 'G') return false;
        }
        return true;
    }
}

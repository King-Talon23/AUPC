package TK.e_MockCompetition2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class multiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<int[]> testCases = new ArrayList<>();

        while (true) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a == 0 && b == 0) {
                break;
            }
            testCases.add(new int[]{a, b});
        }

        for (int[] tc : testCases) {
            int a = tc[0];
            int b = tc[1];
            String aString = String.valueOf(a);
            String bString = String.valueOf(b);
            int aDigits = aString.length();
            int bDigits = bString.length();
            long ab = (long) a * b;
            String abString = String.valueOf(ab);
            int abDigits = abString.length();
            String abPadded = String.format("%" + (aDigits + bDigits) + "s", abString);

            String topBorder = "+-" + "----".repeat(aDigits) + "--+";
            StringBuilder firstLine = new StringBuilder("| ");
            for (int i = 0; i < aDigits; i++) {
                firstLine.append(String.format("  %s ", aString.charAt(i)));
            }
            firstLine.append("  |");
            String innerBorder = "| " + "+---".repeat(aDigits) + "+ |";
            StringBuilder grid = new StringBuilder();
            for (int i = 0; i < bDigits; i++) {
                if (bDigits - i < abDigits - aDigits) {
                    grid.append("|/");
                } else {
                    grid.append("| ");
                }
                for (int j = 0; j < aDigits; j++) {
                    grid.append(String.format("|%d /", (int) Math.floor((double) (aString.charAt(j) - '0') * (bString.charAt(i) - '0') / 10)));
                }
                grid.append("| |\n");
                grid.append("| ").append("| / ".repeat(aDigits)).append(String.format("|%s|\n", bString.charAt(i)));
                grid.append(String.format("|%s", abPadded.charAt(i)));
                for (int j = 0; j < aDigits; j++) {
                    grid.append(String.format("|/ %d", ((aString.charAt(j) - '0') * (bString.charAt(i) - '0')) % 10));
                }
                grid.append("| |\n");
                grid.append(innerBorder).append("\n");
            }

            StringBuilder bottomLine = new StringBuilder("|");
            if (abDigits > aDigits) {
                bottomLine.append("/ ");
            } else {
                bottomLine.append("  ");
            }
            for (int i = 0; i < aDigits; i++) {
                bottomLine.append(abPadded.charAt(bDigits + i));
                if (i < aDigits - 1) {
                    bottomLine.append(" / ");
                } else {
                    bottomLine.append("    |");
                }
            }

            String output = topBorder + "\n" + firstLine + "\n" + innerBorder + "\n" + grid + bottomLine + "\n" + topBorder;
            System.out.println(output);
        }
        scanner.close();
    }
}
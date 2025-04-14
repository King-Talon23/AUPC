package TK.e_MockCompetition2;

import java.util.Scanner;

public class asciiaddition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] digits = {
            "xxxxxx...xx...xx...xx...xx...xxxxxx",
            "....x....x....x....x....x....x....x",
            "xxxxx....x....xxxxxxx....x....xxxxx",
            "xxxxx....x....xxxxxx....x....xxxxxx",
            "x...xx...xx...xxxxxx....x....x....x",
            "xxxxxx....x....xxxxx....x....xxxxxx",
            "xxxxxx....x....xxxxxx...xx...xxxxxx",
            "xxxxx....x....x....x....x....x....x",
            "xxxxxx...xx...xxxxxxx...xx...xxxxxx",
            "xxxxxx...xx...xxxxxx....x....xxxxxx",
            ".......x....x..xxxxx..x....x......."
        };

        String line1 = scanner.next();
        String line2 = scanner.next();
        String line3 = scanner.next();
        String line4 = scanner.next();
        String line5 = scanner.next();
        String line6 = scanner.next();
        String line7 = scanner.next();

        String expression = "";
        for (int i = 0; i < line1.length(); i += 6) {
            String segment = line1.substring(i, i + 5) + line2.substring(i, i + 5) + line3.substring(i, i + 5) + line4.substring(i, i + 5) + line5.substring(i, i + 5) + line6.substring(i, i + 5) + line7.substring(i, i + 5);
            for (int j = 0; j < digits.length; j++) {
                if (segment.equals(digits[j])) {
                    if (j < 10) {
                        expression += j;
                    } else {
                        expression += "+";
                    }
                    break;
                }
            }
        }

        int operand1 = Integer.parseInt(expression.substring(0, expression.indexOf("+")));
        int operand2 = Integer.parseInt(expression.substring(expression.indexOf("+") + 1));
        String result = String.valueOf(operand1 + operand2);

        String[] asciiResult = new String[result.length()];
        for (int i = 0; i < result.length(); i++) {
            int digit = Integer.parseInt(result.charAt(i) + "");
            asciiResult[i] = digits[digit];
        }

        for (int i = 0; i < asciiResult[0].length(); i += 5) {
            StringBuilder row = new StringBuilder();
            for (String asciiDigit : asciiResult) {
                row.append(asciiDigit.substring(i, i + 5)).append(".");
            }
            System.out.println(row.substring(0, row.length() - 1));
        }

        scanner.close();
    }
}
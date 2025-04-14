package TK.b_PracticeWeek2;

import java.util.Scanner;

public class parsinghex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            while (inputLine.contains("0x") || inputLine.contains("0X")) {
                int indexLower = inputLine.contains("0x") ? inputLine.indexOf("0x") : 100;
                int indexUpper = inputLine.contains("0X") ? inputLine.indexOf("0X") : 100;
                int index = indexLower < indexUpper ? indexLower : indexUpper;
                StringBuilder temp = new StringBuilder(inputLine);
                temp.setCharAt(index, ' ');
                inputLine = temp.toString();
                String hexNumber = "0x";
                char[] chars = inputLine.toCharArray();
                for (index += 2; index < chars.length && ((chars[index] >= 'a' && chars[index] <= 'f')
                        || (chars[index] >= 'A' && chars[index] <= 'F') || (chars[index] >= '0' && chars[index] <= '9')); index++)
                    hexNumber += chars[index];
                System.out.println(hexNumber + " " + Long.decode(hexNumber));
            }
        }
        scanner.close();
    }
}

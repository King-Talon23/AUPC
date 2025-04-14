package TK.c_PracticeWeek3;

import java.util.Scanner;

public class progressivescramble {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        for (int index = 0; index < count; index++) {
            String line = scanner.nextLine();
            int[] numbers = convertToNumbers(line.substring(2));

            if (line.charAt(0) == 'e') {
                int[] encodedNumbers = encodeNumbers(numbers);
                convertToLetters(encodedNumbers);
            } else {
                int[] decodedNumbers = decodeNumbers(numbers);
                convertToLetters(decodedNumbers);
            }
        }
        scanner.close();
    }

    public static int[] convertToNumbers(String line) {
        int[] numberArray = new int[line.length()];
        for (int index = 0; index < line.length(); index++) {
            if (line.charAt(index) == ' ') {
                numberArray[index] = 0;
            } else {
                int asciiValue = line.charAt(index) - 96;
                numberArray[index] = asciiValue;
            }
        }
        return numberArray;
    }

    public static void convertToLetters(int[] numberArray) {
        StringBuilder result = new StringBuilder();
        for (int number : numberArray) {
            if (number == 0) {
                result.append(" ");
            } else {
                result.append((char) (number + 96));
            }
        }
        System.out.println(result.toString());
    }

    public static int[] decodeNumbers(int[] numberArray) {
        int[] intermediateArray = new int[numberArray.length];
        intermediateArray[0] = numberArray[0];
        for (int index = 1; index < numberArray.length; index++) {
            while (numberArray[index] < intermediateArray[index - 1]) {
                numberArray[index] += 27;
            }
            intermediateArray[index] = numberArray[index];
        }
        int[] resultArray = new int[intermediateArray.length];
        resultArray[0] = intermediateArray[0];
        for (int index = 1; index < intermediateArray.length; index++) {
            resultArray[index] = intermediateArray[index] - intermediateArray[index - 1];
        }
        return resultArray;
    }

    public static int[] encodeNumbers(int[] numberArray) {
        int[] intermediateArray = new int[numberArray.length];
        intermediateArray[0] = numberArray[0];
        for (int index = 1; index < numberArray.length; index++) {
            intermediateArray[index] = intermediateArray[index - 1] + numberArray[index];
        }
        int[] resultArray = new int[intermediateArray.length];
        resultArray[0] = intermediateArray[0];
        for (int index = 1; index < intermediateArray.length; index++) {
            resultArray[index] = intermediateArray[index] % 27;
        }
        return resultArray;
    }
}

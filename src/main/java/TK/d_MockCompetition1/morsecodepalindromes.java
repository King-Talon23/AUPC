package TK.d_MockCompetition1;

import java.util.HashMap;
import java.util.Scanner;

public class morsecodepalindromes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        HashMap<Character, String> morseMap = new HashMap<>();
        morseMap.put('A', "01");
        morseMap.put('B', "1000");
        morseMap.put('C', "1010");
        morseMap.put('D', "100");
        morseMap.put('E', "0");
        morseMap.put('F', "0010");
        morseMap.put('G', "110");
        morseMap.put('H', "0000");
        morseMap.put('I', "00");
        morseMap.put('J', "0111");
        morseMap.put('K', "101");
        morseMap.put('L', "0100");
        morseMap.put('M', "11");
        morseMap.put('N', "10");
        morseMap.put('O', "111");
        morseMap.put('P', "0110");
        morseMap.put('Q', "1101");
        morseMap.put('R', "010");
        morseMap.put('S', "000");
        morseMap.put('T', "1");
        morseMap.put('U', "001");
        morseMap.put('V', "0001");
        morseMap.put('W', "011");
        morseMap.put('X', "1001");
        morseMap.put('Y', "1011");
        morseMap.put('Z', "1100");
        morseMap.put('0', "11111");
        morseMap.put('1', "01111");
        morseMap.put('2', "00111");
        morseMap.put('3', "00011");
        morseMap.put('4', "00001");
        morseMap.put('5', "00000");
        morseMap.put('6', "10000");
        morseMap.put('7', "11000");
        morseMap.put('8', "11100");
        morseMap.put('9', "11110");
        
        String input = scanner.nextLine();
        scanner.close();
        
        StringBuilder morseCode = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (Character.isDigit(ch) || Character.isLetter(ch)) {
                morseCode.append(morseMap.get(Character.toUpperCase(ch)));
            }
        }
        
        if (morseCode.length() == 0) {
            System.out.println(0);
            return;
        }
        
        String original = morseCode.toString();
        String reversed = morseCode.reverse().toString();
        
        if (original.equals(reversed)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
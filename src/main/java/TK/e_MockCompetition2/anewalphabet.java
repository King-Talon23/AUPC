package TK.e_MockCompetition2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class anewalphabet {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine().toLowerCase();
        inputScanner.close();

        Map<Character, String> translationMap = new HashMap<>();
        translationMap.put('a', "@");
        translationMap.put('b', "8");
        translationMap.put('c', "(");
        translationMap.put('d', "|)");
        translationMap.put('e', "3");
        translationMap.put('f', "#");
        translationMap.put('g', "6");
        translationMap.put('h', "[-]");
        translationMap.put('i', "|");
        translationMap.put('j', "_|");
        translationMap.put('k', "|<");
        translationMap.put('l', "1");
        translationMap.put('m', "[]\\/[]");
        translationMap.put('n', "[]\\[]");
        translationMap.put('o', "0");
        translationMap.put('p', "|D");
        translationMap.put('q', "(,)");
        translationMap.put('r', "|Z");
        translationMap.put('s', "$");
        translationMap.put('t', "']['");
        translationMap.put('u', "|_|");
        translationMap.put('v', "\\/");
        translationMap.put('w', "\\/\\/");
        translationMap.put('x', "}{");
        translationMap.put('y', "`/");
        translationMap.put('z', "2");

        StringBuilder translatedOutput = new StringBuilder();
        for (char character : userInput.toCharArray()) {
            translatedOutput.append(translationMap.getOrDefault(character, String.valueOf(character)));
        }

        System.out.println(translatedOutput);
    }
}
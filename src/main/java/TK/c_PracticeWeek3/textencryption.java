package TK.c_PracticeWeek3;

import java.util.Scanner;

public class textencryption {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int keyLength;
        while ((keyLength = inputScanner.nextInt()) != 0) {
            inputScanner.nextLine();
            String plainMessage = inputScanner.nextLine().replace(" ", "").toUpperCase();
            char[] encryptedMessage = new char[plainMessage.length()];
            int messageIdx = 0;
            int cipherIdx = 0;

            while (messageIdx < plainMessage.length() && cipherIdx < encryptedMessage.length) {
                for (int i = cipherIdx; i < encryptedMessage.length; i += keyLength) {
                    if (messageIdx == plainMessage.length()) break;
                    encryptedMessage[i] = plainMessage.charAt(messageIdx++);
                }
                cipherIdx++;
            }

            System.out.println(new String(encryptedMessage));
        }
        inputScanner.close();
    }
}

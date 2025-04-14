package TK.b_PracticeWeek2;

import java.util.Scanner;

 public class luhnchecksum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  
        scanner.nextLine();  
        
        for (int i = 0; i < n; i++) {
            String number = scanner.nextLine();  
            if (passesLuhnChecksum(number)) {
                System.out.println("PASS");
            } else {
                System.out.println("FAIL");
            }
        }
    }

    public static boolean passesLuhnChecksum(String number) {
        int sum = 0;
        boolean alternate = false;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));
            
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;  }
            }
            sum += digit;
            alternate = !alternate;  
        }

        return sum % 10 == 0;
    }
}

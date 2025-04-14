package TK.a_PracticeWeek1;

import java.util.Scanner;

public class keysphonewallet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        boolean hasKeys = false, hasPhone = false, hasWallet = false;

        int n = scanner.nextInt();
        scanner.nextLine(); 
        for (int i = 0; i < n; i++) {
            String item = scanner.nextLine().trim();
            if (item.equals("keys")) {
                hasKeys = true;
            } else if (item.equals("phone")) {
                hasPhone = true;
            } else if (item.equals("wallet")) {
                hasWallet = true;
            }
        }
        
        if (hasKeys && hasPhone && hasWallet) {
            System.out.println("ready");
        } else {
            if (!hasKeys) System.out.println("keys");
            if (!hasPhone) System.out.println("phone");
            if (!hasWallet) System.out.println("wallet");
        }

        scanner.close();
    }
}

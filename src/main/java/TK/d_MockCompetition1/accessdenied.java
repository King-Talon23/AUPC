package TK.d_MockCompetition1;

import java.util.Scanner;

public class accessdenied {
    private static int N;
    private static String P = "";
    private static Scanner scanner = new Scanner(System.in);

    private static int guess(String pass) {
        System.out.println(pass);
        String res = scanner.nextLine();
        int loc1 = res.indexOf("(");
        int loc2 = res.indexOf(" ms");
        if (loc1 == -1 || loc2 == -1) System.exit(0);
        return Integer.parseInt(res.substring(loc1 + 1, loc2));
    }

    private static void solve() {
        N = 0;
        StringBuilder curr = new StringBuilder();
        int lastTime = 0;
        int res;
        
        for (int i = 1; i < 21; i++) {
            curr.append("A");
            N++;
            res = guess(curr.toString());
            if (res != 5) {
                // Correct length
                lastTime = res;
                break;
            }
        }

        // Find characters, one at a time
        for (int i = 0; i < N; i++) {
            for (int d = 0; d < 62; d++) {
                char c;
                if (d < 26) c = (char)('A' + d);
                else if (d < 52) c = (char)('a' + (d - 26));
                else c = (char)('0' + (d - 52));
                
                curr.setCharAt(i, c);
                res = guess(curr.toString());
                
                if (res < lastTime) {
                    // A was the right character
                    P += 'A';
                    curr.setCharAt(i, 'A');
                    break;
                } else if (res > lastTime) {
                    // This is the right character
                    P += c;
                    lastTime = res;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
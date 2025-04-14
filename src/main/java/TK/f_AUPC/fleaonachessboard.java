package TK.f_AUPC;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class fleaonachessboard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            long s = scanner.nextLong();
            long xo = scanner.nextLong();
            long yo = scanner.nextLong();
            long a = scanner.nextLong();
            long b = scanner.nextLong();

            if (s == 0) {
                break;
            }

            long x = xo % (s << 1);
            long y = yo % (s << 1);
            Set<Long> visited = new HashSet<>();

            int ans = 0;
            while (true) {
                if (x > s && 0 < y && y < s || 0 < x && x < s && y > s) {
                    break;
                }
                visited.add(x * (s << 1) + y);

                x = (x + a) % (2 * s);
                y = (y + b) % (2 * s);
                ans++;

                if (visited.contains(x * (s << 1) + y)) {
                    ans = -1;
                    break;
                }
            }

            if (ans == -1) {
                System.out.println("The flea cannot escape from black squares.");
            } else {
                System.out.println("After " + ans + " jumps the flea lands at (" + (xo + ans * a) + ", "
                        + (yo + ans * b) + ").");
            }
        }
        scanner.close();
    }
}
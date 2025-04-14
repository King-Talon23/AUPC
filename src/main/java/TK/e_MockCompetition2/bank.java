package TK.e_MockCompetition2;

import java.util.Scanner;
import java.util.stream.IntStream;

public class bank {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numCustomers = input.nextInt();
        int timeSlots = input.nextInt();
        int[] maxCashPerSlot = new int[timeSlots];

        for (int i = 0; i < numCustomers; i++) {
            int cashAmount = input.nextInt();
            int arrivalTime = input.nextInt();

            if (maxCashPerSlot[arrivalTime] == 0) {
                maxCashPerSlot[arrivalTime] = cashAmount;
            } else {
                for (int j = arrivalTime; j >= 0; j--) {
                    if (maxCashPerSlot[j] < cashAmount) {
                        int temp = maxCashPerSlot[j];
                        maxCashPerSlot[j] = cashAmount;
                        cashAmount = temp;
                    }
                }
            }
        }

        System.out.println(IntStream.of(maxCashPerSlot).sum());
        input.close();
    }
}
package TK.b_PracticeWeek2;

import java.util.Scanner;

public class isithalloween {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String date = input.nextLine();

        if (date.equals("OCT 31") || date.equals("DEC 25")){
            System.out.println("yup");
        } else {
            System.out.println("nope");
        }
        input.close();
    }
}
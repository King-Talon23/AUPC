package TK.a_PracticeWeek1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class guessWho {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int Q = scanner.nextInt();

        List<String[]> people = new ArrayList<>();
        HashMap<String[],Integer> map = new HashMap<>();

        for (int i = 0;i<N;i++){
            String[] temp = scanner.next().split("");
            people.add(temp);
            map.put(temp,i + 1);
        }
        for (int i = 0;i<Q;i++){
            int index = scanner.nextInt();
            String characteristic = scanner.next();
            for (int j = 0;j<people.size();j++){
                if (people.get(j)[index - 1].charAt(0) != (characteristic.charAt(0)) ) {
                    people.remove(j);
                    j--;
                }
            }
        }
        if (people.size() > 1){
            System.out.println("ambiguous");
            System.out.println(people.size());
        }else {
            System.out.println("unique");
            System.out.println(map.get(people.get(0)));
        }
    }
}

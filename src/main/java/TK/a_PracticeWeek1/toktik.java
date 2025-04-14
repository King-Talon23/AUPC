package TK.a_PracticeWeek1;

import java.util.HashMap;
import java.util.Scanner;

public class toktik {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        scanner.nextLine(); 
        
        HashMap<String, Integer> viewsMap = new HashMap<>();
        
        String mostPopular = null;
        int maxViews = 0;

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String tikker = parts[0];
            int views = Integer.parseInt(parts[1]);
            
            int totalViews = viewsMap.getOrDefault(tikker, 0) + views;
            viewsMap.put(tikker, totalViews);
            
            if (totalViews > maxViews) {
                maxViews = totalViews;
                mostPopular = tikker;
            }
        }

        System.out.println(mostPopular);
        
        scanner.close(); 
    }
}

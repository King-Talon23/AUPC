package TK.f_AUPC;

import java.io.*;
import java.util.*;

public class arraysmoothening {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int k = Integer.parseInt(firstLine[1]);
        
        Map<Integer, Integer> freq = new HashMap<>();
        String[] values = br.readLine().split(" ");
        
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(values[i]);
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int v : freq.values()) {
            pq.add(v);
        }
        
        for (int i = 0; i < k; i++) {
            int val = pq.poll();
            val--;
            if (val > 0) {
                pq.add(val);
            }
        }
        
        if (!pq.isEmpty()) {
            out.println(pq.peek());
        } else {
            out.println(0);
        }
        
        out.flush();
        br.close();
        out.close();
    }
}
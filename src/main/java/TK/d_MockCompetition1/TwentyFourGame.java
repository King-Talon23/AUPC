package TK.d_MockCompetition1;

import java.util.*;

public class TwentyFourGame {

    interface Operation {
        float apply(float x, float y);
    }

    static Operation ADD = Float::sum;
    static Operation SUB = (x, y) -> x - y;
    static Operation MUL = (x, y) -> x * y;
    static Operation DIV = (x, y) -> x / y;

    static List<Operation> OPS = Arrays.asList(MUL, DIV, ADD, SUB);

    static Map<Operation, String> M = Map.of(
            ADD, "+",
            SUB, "-",
            MUL, "*",
            DIV, "/"
    );

    static Map<String, Boolean> memo = new HashMap<>();

    static String reconstruct(int k, List<Tuple<Integer, Integer, Operation>> parent, List<Pair<Float, Integer>> nums) {
        if (k < nums.size() && nums.get(k).second == 1) {
            return String.valueOf((int) nums.get(k).first.floatValue());
        }

        Tuple<Integer, Integer, Operation> p = parent.get(k);
        return "(" + reconstruct(p.first, parent, nums) + M.get(p.third) + reconstruct(p.second, parent, nums) + ")";
    }

    static void solve(List<Pair<Float, Integer>> numbers,
                       List<Tuple<Integer, Integer, Operation>> parent,
                       List<Boolean> visited,
                       int c, float t) {

        String state = getState(numbers, visited);
        if (memo.containsKey(state)) {
            return;
        }
        memo.put(state, true);

        for (int i = 0; i < numbers.size(); i++) {
            if (visited.get(i)) continue;

            for (int j = 0; j < numbers.size(); j++) {
                if (i == j || visited.get(j)) continue;

                visited.set(i, true);
                visited.set(j, true);
                float x = numbers.get(i).first;
                float y = numbers.get(j).first;

                for (Operation op : OPS) {
                    if (op == DIV && Math.abs(y) < 1e-9) {
                        continue;
                    }
                    
                    // Pruning: Skip operations that can't reach the target
                    if (op == SUB && x < y) continue;
                    if (op == DIV && x < y) continue;
                    
                    // Skip redundant operations using symmetry
                    if (op == ADD || op == MUL) {
                        if (i > j) continue;
                    }

                    float z = op.apply(x, y);
                    int zc = numbers.get(i).second + numbers.get(j).second;
                    parent.add(new Tuple<>(i, j, op));

                    if (zc == c && Math.abs(z - t) < 1e-4) {
                        System.out.println(reconstruct(numbers.size(), parent, numbers));
                        System.exit(0);
                    }

                    visited.add(false);
                    numbers.add(new Pair<>(z, zc));
                    solve(numbers, parent, visited, c, t);
                    visited.remove(visited.size() - 1);
                    numbers.remove(numbers.size() - 1);
                    parent.remove(parent.size() - 1);
                }

                visited.set(i, false);
                visited.set(j, false);
            }
        }
    }

    static String getState(List<Pair<Float, Integer>> numbers, List<Boolean> visited) {
        List<Float> state = new ArrayList<>();
        for (int i = 0; i < numbers.size(); i++) {
            if (!visited.get(i)) {
                state.add(numbers.get(i).first);
            }
        }
        state.sort(null);
        return state.toString();
    }

    static void generatePermutations(List<Pair<Float, Integer>> nums,
                                      List<Pair<Float, Integer>> current,
                                      List<Boolean> used,
                                      int c, float t) {
        if (current.size() == nums.size()) {
            List<Tuple<Integer, Integer, Operation>> parent = new ArrayList<>(Collections.nCopies(c, new Tuple<>(0, 0, ADD)));
            List<Boolean> visited = new ArrayList<>(Collections.nCopies(c, false));
            solve(current, parent, visited, c, t);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (!used.get(i)) {
                used.set(i, true);
                current.add(nums.get(i));
                generatePermutations(nums, current, used, c, t);
                current.remove(current.size() - 1);
                used.set(i, false);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int c = scanner.nextInt();
        float t = scanner.nextFloat();

        List<Pair<Float, Integer>> nums = new ArrayList<>();
        for (int i = 0; i < c; i++) {
            float num = scanner.nextFloat();
            nums.add(new Pair<>(num, 1));
        }

        List<Pair<Float, Integer>> current = new ArrayList<>();
        List<Boolean> used = new ArrayList<>(Collections.nCopies(c, false));
        generatePermutations(nums, current, used, c, t);
    }
}

class Pair<K, V> {
    K first;
    V second;

    Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }
}

class Tuple<K, V, T> {
    K first;
    V second;
    T third;

    Tuple(K first, V second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

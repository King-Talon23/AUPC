package TK.f_AUPC;

import java.util.*;

public class ifthenelse {

    private static final int MOD = 1 << 12; // 2^12 = 4096;

    private Map<String, Integer> memory = new HashMap<>();
    private List<String> program;
    private List<Integer> expectedOutput;
    private List<Integer> actualOutput = new ArrayList<>();
    private int pc = 0; // Program counter

    public ifthenelse(List<String> program, List<Integer> expectedOutput) {
        this.program = program;
        this.expectedOutput = expectedOutput;
        // Initialize all variables to 0
        for (String line : program) {
            String[] parts = line.split(" ");
            if (parts[0].startsWith("@")) {
                memory.put(parts[0], 0);
            }
        }
    }

    // Function to perform modulo arithmetic
    private int modOp(int x) {
        return x & (MOD - 1); // Equivalent to x % MOD but faster
    }

    // Parse a value (either a variable or an integer)
    private int getValue(String val) {
        if (val.startsWith("@")) {
            return memory.getOrDefault(val, 0);
        } else {
            return Integer.parseInt(val);
        }
    }

    // Execute an assignment statement
    private void executeAssignment(String line) {
        String[] parts = line.split(" ");
        String var = parts[0];
        String val1 = parts[2];
        if (parts.length > 3) {
            String op = parts[3];
            String val2 = parts[4];
            int v1 = getValue(val1);
            int v2 = getValue(val2);
            if (op.equals("+")) {
                memory.put(var, modOp(v1 + v2));
            } else if (op.equals("*")) {
                memory.put(var, modOp(v1 * v2));
            }
        } else {
            memory.put(var, getValue(val1));
        }
    }

    // Evaluate a comparison
    private boolean evaluateCondition(String val1, String op, String val2) {
        int v1 = getValue(val1);
        int v2 = getValue(val2);
        if (op.equals("==")) return v1 == v2;
        if (op.equals("<=")) return v1 <= v2;
        if (op.equals("<")) return v1 < v2;
        return false;
    }

    // Process a print statement
    private void executePrint(String line) {
        String[] parts = line.split(" ");
        String val = parts[1];
        actualOutput.add(getValue(val));
    }

    // Run the program with a given initial value for @a
    public boolean run(int initialA) {
        // Reset state
        memory.clear();
        actualOutput.clear();
        pc = 0;
        // Initialize all variables to 0
        for (String line : program) {
            String[] parts = line.split(" ");
            if (parts[0].startsWith("@")) {
                memory.put(parts[0], 0);
            }
        }
        // Set the initial value for @a
        memory.put("@a", initialA);
        // Process each line
        while (pc < program.size()) {
            String line = program.get(pc);
            String[] parts = line.split(" ");
            String firstWord = parts[0];
            if (firstWord.startsWith("@")) {
                executeAssignment(line);
                pc++;
            } else if (firstWord.equals("if")) {
                String val1 = parts[1];
                String op = parts[2];
                String val2 = parts[3];
                boolean condition = evaluateCondition(val1, op, val2);
                pc++;
                if (!condition) {
                    // Skip the if block
                    int nesting = 1;
                    while (nesting > 0 && pc < program.size()) {
                        String[] nextParts = program.get(pc).split(" ");
                        String nextWord = nextParts[0];
                        if (nextWord.equals("if")) nesting++;
                        else if (nextWord.equals("endif")) nesting--;
                        else if (nextWord.equals("else") && nesting == 1) {
                            pc++;
                            break;
                        }
                        pc++;
                    }
                }
            } else if (firstWord.equals("else")) {
                // Skip the else block
                int nesting = 1;
                pc++;
                while (nesting > 0 && pc < program.size()) {
                    String[] nextParts = program.get(pc).split(" ");
                    String nextWord = nextParts[0];
                    if (nextWord.equals("if")) nesting++;
                    else if (nextWord.equals("endif")) nesting--;
                    pc++;
                }
            } else if (firstWord.equals("endif")) {
                pc++;
            } else if (firstWord.equals("print")) {
                executePrint(line);
                pc++;
            } else {
                // Unknown command
                pc++;
            }
        }
        // Check if output matches
        return actualOutput.equals(expectedOutput);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int I = scanner.nextInt();
        int O = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        // Read the program
        List<String> program = new ArrayList<>();
        for (int i = 0; i < I; i++) {
            program.add(scanner.nextLine());
        }
        // Read the expected output
        List<Integer> expectedOutput = new ArrayList<>();
        for (int i = 0; i < O; i++) {
            expectedOutput.add(scanner.nextInt());
        }
        // Create the interpreter
        ifthenelse interpreter = new ifthenelse(program, expectedOutput);
        // Try all possible values for @a (0 to 4095)
        for (int a = 0; a < MOD; a++) {
            if (interpreter.run(a)) {
                System.out.println(a);
                return;
            }
        }
        System.out.println("no solution");
    }
}
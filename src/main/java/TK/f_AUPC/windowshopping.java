package TK.f_AUPC;

import java.util.* ;
public class windowshopping {
    final int MAX_SIZE = 32 ;
    final int BLOCKED_CELL = 15 ;
    final int STORE_CELL = 14 ;
    final int NEW_HALL_CELL = 13 ;
    char[][] grid = new char[MAX_SIZE][MAX_SIZE] ;
    int[] rowValues = new int[MAX_SIZE] ;
    int isClosed, gridWidth, gridHeight ;
    int[] cellCounts = new int[16] ;
    long packGameState() {
        long packedState = isClosed ;
        for (int i = 0; i < 16; i++)
            cellCounts[i] = -1 ;
        int hallIndex = 0 ;
        for (int i = 1; i <= gridWidth; i++) {
            if (rowValues[i] < STORE_CELL) {
                if (cellCounts[rowValues[i]] < 0)
                    cellCounts[rowValues[i]] = hallIndex++ ;
                rowValues[i] = cellCounts[rowValues[i]] ;
            }
            packedState = (packedState << 4) + rowValues[i] ;
        }
        return packedState ;
    }
    void unpackGameState(long packedState) {
        for (int i = 0; i < 16; i++)
            cellCounts[i] = 0 ;
        for (int i = gridWidth; i > 0; i--) {
            int value = (int)(packedState & 15) ;
            rowValues[i] = value ;
            cellCounts[value]++ ;
            packedState >>= 4 ;
        }
        isClosed = (int)packedState ;
    }
    int calculateScoreIncrement(int currentColumn, int currentRow, int newValue, int leftValue, int upValue) {
        int scoreIncrement = 0 ;
        if (newValue == STORE_CELL) {
            if (leftValue < STORE_CELL && grid[currentColumn][currentRow - 1] == '.')
                scoreIncrement++ ;
            if (upValue < STORE_CELL && grid[currentColumn - 1][currentRow] == '.')
                scoreIncrement++ ;
        } else if (newValue < STORE_CELL && grid[currentColumn][currentRow] == '.') {
            if (leftValue == STORE_CELL)
                scoreIncrement++ ;
            if (upValue == STORE_CELL)
                scoreIncrement++ ;
        }
        return scoreIncrement ;
    }
    void updateRowValues(int oldValue, int newValue) {
        for (int i = 1; i <= gridWidth; i++)
            if (rowValues[i] == oldValue)
                rowValues[i] = newValue ;
    }
    void solveProblem() throws Exception {
        Scanner scanner = new Scanner(System.in) ;
        gridHeight = scanner.nextInt() ;
        gridWidth = scanner.nextInt() ;
        for (int i = 0; i < MAX_SIZE; i++)
            for (int j = 0; j < MAX_SIZE; j++)
                grid[i][j] = '#' ;
        for (int i = 0; i < MAX_SIZE; i++)
            rowValues[i] = BLOCKED_CELL ;
        for (int i = 0; i < gridHeight; i++) {
            String inputLine = scanner.next() ;
            for (int j = 0; j < gridWidth; j++)
                grid[i + 1][j + 1] = inputLine.charAt(j) ;
        }
        if (gridHeight < gridWidth) {
            for (int i = 0; i < MAX_SIZE; i++)
                for (int j = i + 1; j < MAX_SIZE; j++) {
                    char temp = grid[i][j] ;
                    grid[i][j] = grid[j][i] ;
                    grid[j][i] = temp ;
                }
            int temp = gridHeight ;
            gridHeight = gridWidth ;
            gridWidth = temp ;
        }
        HashMap<Long, Integer> gameStateScores = new HashMap<Long, Integer>() ;
        HashMap<Long, Integer> nextGameStateScores = new HashMap<Long, Integer>() ;
        long initialGameState = packGameState() ;
        gameStateScores.put(initialGameState, 0) ;
        int escapeCount = 0 ;
        for (int currentColumn = 1; currentColumn <= gridHeight + 1; currentColumn++) {
            for (int currentRow = 1; currentRow <= gridWidth; currentRow++) {
                int rowIndex = currentRow ;
                int cellValue = grid[currentColumn][currentRow] ;
                if (cellValue == 'U' || cellValue == 'D')
                    escapeCount++ ;
                nextGameStateScores.clear() ;
                for (Map.Entry<Long, Integer> entry : gameStateScores.entrySet()) {
                    for (int newValue = NEW_HALL_CELL; newValue <= BLOCKED_CELL; newValue++) {
                        if ((cellValue == '#') != (newValue == BLOCKED_CELL))
                            continue ;
                        if ((cellValue == 'U' || cellValue == 'D') && newValue != NEW_HALL_CELL)
                            continue ;
                        unpackGameState(entry.getKey()) ;
                        int closingState = 0 ;
                        if (rowValues[rowIndex] < STORE_CELL && cellCounts[rowValues[rowIndex]] == 1 && newValue != NEW_HALL_CELL)
                            closingState = 1 ;
                        if (closingState != 0 && cellCounts[1] > 0)
                            continue ;
                        if (isClosed != 0 && closingState != 0)
                            continue ;
                        if (escapeCount < 2 && closingState != 0)
                            continue ;
                        isClosed |= closingState ;
                        int score = entry.getValue() + calculateScoreIncrement(currentColumn, currentRow, newValue, rowValues[rowIndex - 1], rowValues[rowIndex]) ;
                        if (newValue == NEW_HALL_CELL) {
                            if (rowValues[rowIndex] < STORE_CELL && rowValues[rowIndex - 1] < STORE_CELL) {
                                if (rowValues[rowIndex] != rowValues[rowIndex - 1])
                                    updateRowValues(rowValues[rowIndex - 1], rowValues[rowIndex]) ;
                            } else if (rowValues[rowIndex] < STORE_CELL) {
                                // leave it be
                            } else if (rowValues[rowIndex - 1] < STORE_CELL) {
                                rowValues[rowIndex] = rowValues[rowIndex - 1] ;
                            } else {
                                rowValues[rowIndex] = NEW_HALL_CELL ;
                            }
                        } else {
                            rowValues[rowIndex] = newValue ;
                        }
                        long nextState = packGameState() ;
                        if (nextGameStateScores.containsKey(nextState))
                            nextGameStateScores.put(nextState, Math.max(score, nextGameStateScores.get(nextState))) ;
                        else
                            nextGameStateScores.put(nextState, score) ;
                    }
                }
                HashMap<Long, Integer> tempMap = gameStateScores ;
                gameStateScores = nextGameStateScores ;
                nextGameStateScores = tempMap ;
            }
        }
        int finalScore = 0 ;
        for (Map.Entry<Long, Integer> entry : gameStateScores.entrySet())
            finalScore = Math.max(finalScore, entry.getValue()) ;
        System.out.println(finalScore) ;
    }
    public static void main(String[] args) throws Exception {
        new windowshopping().solveProblem() ;
    }
}
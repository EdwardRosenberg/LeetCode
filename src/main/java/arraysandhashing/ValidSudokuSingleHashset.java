package arraysandhashing;

import java.util.HashSet;
import java.util.Set;

public class ValidSudokuSingleHashset {

    public boolean isValidSudoku(char[][] board) {

        Set<String> seen = new HashSet<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                char currentValue = board[rowIndex][colIndex];
                if (currentValue != '.') {
                    // HashSet `add` returns `true` if value did not already exist - no need to do an extra check
                    if (!seen.add(currentValue + "seen in row " + rowIndex) ||
                            !seen.add(currentValue + "seen in col " + colIndex) ||
                            // dividing row/column indexes will show box coordinates in 3 X 3 grid i.e. 0,1 or 3,3
                            // dividing ints will automatically round down
                            !seen.add(currentValue + "seen in sub box " + rowIndex / 3 + "-" + colIndex / 3)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

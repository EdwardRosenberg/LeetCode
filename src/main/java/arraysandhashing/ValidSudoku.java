package arraysandhashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {

        Map<Integer, HashSet<Character>> rows = new HashMap<>();
        Map<Integer, HashSet<Character>> cols = new HashMap<>();
        Map<String, HashSet<Character>> boxes = new HashMap<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                if (board[rowIndex][colIndex] != '.') {
                    // dividing row/column indexes will show box coordinates in 3 X 3 grid i.e. 0,1 or 3,3
                    // dividing ints will automatically round down
                    // storing coordinates as string for simplicity
                    String boxCoordinates = rowIndex / 3 + String.valueOf(colIndex / 3);

                    if (rows.getOrDefault(rowIndex, new HashSet<>()).contains(board[rowIndex][colIndex]) ||
                            cols.getOrDefault(colIndex, new HashSet<>()).contains(board[rowIndex][colIndex]) ||
                            boxes.getOrDefault(boxCoordinates, new HashSet<>()).contains(board[rowIndex][colIndex])) {
                        return false;
                    } else {
                        rows.computeIfAbsent(rowIndex, v -> new HashSet<>());
                        rows.get(rowIndex).add(board[rowIndex][colIndex]);

                        cols.computeIfAbsent(colIndex, v -> new HashSet<>());
                        cols.get(colIndex).add(board[rowIndex][colIndex]);

                        boxes.computeIfAbsent(boxCoordinates, v -> new HashSet<>());
                        boxes.get(boxCoordinates).add(board[rowIndex][colIndex]);
                    }
                }
            }
        }

        return true;
    }
}

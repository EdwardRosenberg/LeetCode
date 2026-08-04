package arraysandhashing.validsudoku;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 36 — Valid Sudoku (Medium)
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Pattern: Arrays & Hashing
 * Cue: "no duplicates within each row/column/sub-group of a grid"
 *
 * Approach: Same idea as the canonical version — track digits seen per
 * row, column, and 3x3 box — but instead of three separate maps, encode
 * "this digit in this row/col/box" as a single string key in one HashSet.
 * HashSet.add() already returns false if the key existed, so a failed add
 * doubles as the duplicate check with no separate .contains() call needed.
 *
 * Time: O(1) — the board is always 9x9, so this is 81 constant-bounded
 * operations regardless of input size.
 * Space: O(1) for the same reason — at most 3*81 encoded keys tracked.
 *
 * See ValidSudoku.java for the canonical, three-map version — fewer moving
 * parts to hold in your head than this one's single overloaded set.
 */
public class ValidSudokuSingleHashset implements SudokuValidator {

    @Override
    public boolean isValidSudoku(char[][] board) {

        Set<String> seen = new HashSet<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                char digit = board[rowIndex][colIndex];
                if (digit != '.') {
                    // HashSet `add` returns `true` if value did not already exist - no need to do an extra check
                    if (!seen.add(digit + "seen in row " + rowIndex) ||
                            !seen.add(digit + "seen in col " + colIndex) ||
                            // dividing row/column indexes will show box coordinates in 3 X 3 grid i.e. 0,1 or 3,3
                            // dividing ints will automatically round down
                            !seen.add(digit + "seen in sub box " + rowIndex / 3 + "-" + colIndex / 3)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

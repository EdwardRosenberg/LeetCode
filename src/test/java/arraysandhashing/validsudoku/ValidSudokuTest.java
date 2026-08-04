package arraysandhashing.validsudoku;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidSudokuTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps ValidSudoku (canonical, three maps) and
    // ValidSudokuSingleHashset (single encoded-key set) in sync without
    // duplicating the whole test file per variant.
    private final List<SudokuValidator> implementations = List.of(new ValidSudoku(), new ValidSudokuSingleHashset());

    @Test
    void exampleOne() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        assertAllImplementations(true, board);
    }

    @Test
    void exampleTwoDuplicateInBox() {
        // same board as exampleOne, but [0][0] changed 5 -> 8, which now
        // duplicates the 8 already at [2][2] within the same 3x3 box
        char[][] board = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        assertAllImplementations(false, board);
    }

    @Test
    void emptyBoard() {
        assertAllImplementations(true, emptyBoardOf9());
    }

    @Test
    void duplicateInRow() {
        char[][] board = emptyBoardOf9();
        board[0][0] = '5';
        board[0][5] = '5';
        assertAllImplementations(false, board);
    }

    @Test
    void duplicateInColumn() {
        char[][] board = emptyBoardOf9();
        board[0][0] = '5';
        board[5][0] = '5';
        assertAllImplementations(false, board);
    }

    @Test
    void fullyFilledValidBoard() {
        char[][] board = {
                {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                {'3', '4', '5', '2', '8', '6', '1', '7', '9'}
        };
        assertAllImplementations(true, board);
    }

    private char[][] emptyBoardOf9() {
        char[][] board = new char[9][9];
        for (char[] row : board) {
            java.util.Arrays.fill(row, '.');
        }
        return board;
    }

    private void assertAllImplementations(boolean expected, char[][] board) {
        for (SudokuValidator implementation : implementations) {
            assertEquals(expected, implementation.isValidSudoku(board),
                    implementation.getClass().getSimpleName() + " disagreed");
        }
    }
}

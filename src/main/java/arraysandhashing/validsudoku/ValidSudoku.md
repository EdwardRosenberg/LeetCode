# Valid Sudoku

- **LeetCode:** [36 — Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "no duplicates within each row/column/sub-group of a grid"

Variants: [ValidSudokuSingleHashset](ValidSudokuSingleHashset.md) (encodes row/col/box membership into single string keys in one HashSet instead of three maps — fewer lines, same idea, less immediately obvious what's being tracked)

## Approach

Track digits seen so far per row, per column, and per 3x3 box in three separate maps keyed by row index, column index, and box coordinate. A cell is invalid if its digit is already recorded in any of the three.

- **Time:** O(1) — the board is always 9x9, so this is 81 constant-bounded lookups regardless of input size.
- **Space:** O(1) for the same reason — at most 9 rows, 9 cols, 9 boxes tracked.

**Why it works:** a single left-to-right, top-to-bottom scan is enough because a duplicate is a *pair* of cells, and the scan will always visit one of the two before the other — checking-then-recording means the second cell of any duplicate pair is guaranteed to find the first one already tracked, regardless of which row/col/box the duplicate falls in. The box-key formula (`rowIndex/3` and `colIndex/3`, each landing in {0,1,2}, concatenated) also matters: integer division groups every cell into the correct 3×3 box, and the 9 possible `(0-2, 0-2)` pairs are all distinct strings, so no two different boxes ever collide onto the same key.

## Visualizing it

```
board[0][0] = '8', board[2][2] = '8'   (same 3x3 box, top-left)

boxCoordinates for (0,0):  0/3 + "" + 0/3  ->  "0" + "0"  = "00"
boxCoordinates for (2,2):  2/3 + "" + 2/3  ->  "0" + "0"  = "00"

Both cells map to the same box key "00":

  boxes["00"] after (0,0): {8}
  visiting (2,2): boxes["00"].contains('8') -> true -> return false
```

## Code

```java
package arraysandhashing.validsudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ValidSudoku implements SudokuValidator {

    @Override
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, HashSet<Character>> rows = new HashMap<>();
        Map<Integer, HashSet<Character>> cols = new HashMap<>();
        Map<String, HashSet<Character>> boxes = new HashMap<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                char digit = board[rowIndex][colIndex];
                if (digit == '.') continue;

                // Dividing row/col index by 3 (rounding down) gives the box's
                // coordinates in the 3x3 grid of boxes, e.g. (0,1) or (3,3).
                String boxCoordinates = rowIndex / 3 + String.valueOf(colIndex / 3);

                if (rows.getOrDefault(rowIndex, new HashSet<>()).contains(digit) ||
                        cols.getOrDefault(colIndex, new HashSet<>()).contains(digit) ||
                        boxes.getOrDefault(boxCoordinates, new HashSet<>()).contains(digit)) {
                    return false;
                }

                rows.computeIfAbsent(rowIndex, v -> new HashSet<>()).add(digit);
                cols.computeIfAbsent(colIndex, v -> new HashSet<>()).add(digit);
                boxes.computeIfAbsent(boxCoordinates, v -> new HashSet<>()).add(digit);
            }
        }

        return true;
    }
}
```

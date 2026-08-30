# Valid Sudoku (Single HashSet Variant)

- **LeetCode:** [36 — Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "no duplicates within each row/column/sub-group of a grid"

See [ValidSudoku.md](ValidSudoku.md) for the canonical, three-map version.

## Approach

Same idea as the canonical version — track digits seen per row, column, and 3x3 box — but instead of three separate maps, encode "this digit in this row/col/box" as a single string key in one HashSet. `HashSet.add()` already returns `false` if the key existed, so a failed add doubles as the duplicate check with no separate `.contains()` call needed.

- **Time:** O(1) — the board is always 9x9, so this is 81 constant-bounded operations regardless of input size.
- **Space:** O(1) for the same reason — at most 3×81 encoded keys tracked.

Fewer lines than the canonical, but the three-map version is arguably easier to read at a glance — the key-encoding trick here means you have to trust the string concatenation is actually collision-free rather than seeing "row 3" and "col 3" as separate maps.

**Why it works:** the same "check then record" reasoning as the canonical applies (a scan is enough because one of any duplicate pair is always visited first) — this variant's own trick is folding three separate lookups into one set by making the *key itself* encode which category it belongs to. `"5seen in row 3"`, `"5seen in col 3"`, and `"5seen in sub box 1-1"` are all textually distinct strings — the literal `"seen in row "` / `"seen in col "` / `"seen in sub box "` markers can't be confused with each other, so a digit-3-in-row-3 key never collides with an unrelated digit-3-in-col-3 key even though both keys happen to contain "3" twice. That's what makes it safe to track all three categories in a single flat set instead of three separate ones.

## Visualizing it

```
board[0][0] = '5'

seen.add("5seen in row 0")           -> true, not seen before
seen.add("5seen in col 0")           -> true, not seen before
seen.add("5seen in sub box 0-0")     -> true, not seen before

board[0][5] = '5'   (duplicate 5 in the same row)

seen.add("5seen in row 0")           -> false, already in the set!
  -> isValidSudoku returns false immediately
```

## Code

```java
package arraysandhashing.validsudoku;

import java.util.HashSet;
import java.util.Set;

public class ValidSudokuSingleHashset implements SudokuValidator {

    @Override
    public boolean isValidSudoku(char[][] board) {

        Set<String> seen = new HashSet<>();

        for (int rowIndex = 0; rowIndex < 9; rowIndex++) {
            for (int colIndex = 0; colIndex < 9; colIndex++) {
                char digit = board[rowIndex][colIndex];
                if (digit != '.') {
                    if (!seen.add(digit + "seen in row " + rowIndex) ||
                            !seen.add(digit + "seen in col " + colIndex) ||
                            !seen.add(digit + "seen in sub box " + rowIndex / 3 + "-" + colIndex / 3)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
```

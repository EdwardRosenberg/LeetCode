# Two Sum

- **LeetCode:** [1 — Two Sum](https://leetcode.com/problems/two-sum/) (Easy)
- **Pattern:** Arrays & Hashing
- **Cue:** "complement lookup → HashMap value→index"

## Approach

Walk the array once, checking whether `target - nums[i]` has already been seen. If so, its stored index plus the current index is the answer; otherwise record this value's index for later lookups.

- **Time:** O(n) — single pass, O(1) HashMap lookups.
- **Space:** O(n) for the value-to-index map.

**Why it works:** if a valid pair `(i, j)` with `i < j` exists, then by the time the loop reaches `j`, index `i` was already visited and recorded (since `i < j`). So `nums[i]` — exactly `target - nums[j]`, the complement being looked up at `j` — is guaranteed to already be in the map. The pair can never be missed by scanning left to right once; the only question is whether the *other* half has been seen yet, and the invariant guarantees it has.

## Code

```java
package arraysandhashing.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 — Two Sum (Easy)
 * https://leetcode.com/problems/two-sum/
 *
 * Pattern: Arrays & Hashing
 * Cue: "complement lookup → HashMap value→index"
 *
 * Approach: Walk the array once, checking whether target - nums[i] has
 * already been seen. If so, its stored index plus the current index is the
 * answer; otherwise record this value's index for later lookups.
 *
 * Time: O(n) — single pass, O(1) HashMap lookups.
 * Space: O(n) for the value-to-index map.
 */
public class TwoSumHashMap {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexByValue.containsKey(complement)) {
                return new int[]{indexByValue.get(complement), i};
            } else {
                indexByValue.put(nums[i], i);
            }
        }

        return new int[]{};
    }
}
```

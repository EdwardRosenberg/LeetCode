# 3Sum

- **LeetCode:** [15 — 3Sum](https://leetcode.com/problems/3sum/) (Medium)
- **Pattern:** Two Pointers
- **Cue:** "find all triples summing to a target — fix one, two-pointer the rest"

## Approach

Sort the array, then fix each index in turn as the smallest value of a candidate triple, and two-pointer the remaining subarray (`left = fixedIndex+1`, `right = end`) looking for pairs that complete the sum to zero. Skip duplicate values for the fixed index, and for `left` right after a match, so the same triple is never emitted twice.

- **Time:** O(n²) — an O(n) two-pointer scan for each of the n choices of fixed index.
- **Space:** O(n) (or O(log n)) for the sort, excluding the output.

**Why it works:** sorting turns "find a pair summing to a target" into a two-pointer search — since the array is ascending, moving `left` up only increases the pair sum and moving `right` down only decreases it, so there's always exactly one correct direction to move when the current sum is off-target, and no valid pair is ever skipped over. The `nums[fixedIndex] > 0` early exit is safe because the array is sorted: once the smallest of the three candidate values is positive, every value after it is also `>= 0`, so the triple's sum can never come back down to zero. Skipping a duplicate `fixedIndex` is safe because fixing the same value again would just re-discover exactly the same set of triples found the first time — and skipping duplicate `left` values after a match works the same way for the pair search within one fixed index.

## Visualizing it

```
nums (sorted) = [-4, -1, -1, 0, 1, 2]

fixedIndex=0 (-4): left=1(-1) right=5(2): sum=-4-1+2=-3<0 -> left++
                    left=2(-1) right=5(2): sum=-4-1+2=-3<0 -> left++
                    left=3(0)  right=5(2): sum=-4+0+2=-2<0 -> left++
                    left=4(1)  right=5(2): sum=-4+1+2=-1<0 -> left++
                    left=5, right=5 -> left<right fails, done with fixedIndex=0

fixedIndex=1 (-1): left=2(-1) right=5(2): sum=-1-1+2=0 -> MATCH [-1,-1,2]
                    left++ (skip duplicate -1s at left) -> left=3(0)
                    left=3(0) right=5(2): sum=-1+0+2=1>0 -> right--
                    left=3(0) right=4(1): sum=-1+0+1=0 -> MATCH [-1,0,1]
                    left++ -> left=4, right=4 -> done

fixedIndex=2 (-1): nums[2]==nums[1] (-1==-1) -> skip, would re-find the same triples

fixedIndex=3 (0): nums[3]=0, not >0, continue... left=4,right=5: sum=0+1+2=3>0->right--; left=4,right=4: done, no match

result: [[-1,-1,2], [-1,0,1]]
```

## Code

```java
package twopointers.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triples = new ArrayList<>();

        Arrays.sort(nums);

        for (int fixedIndex = 0; fixedIndex < nums.length - 2; fixedIndex++) {

            if (fixedIndex > 0 && nums[fixedIndex] == nums[fixedIndex - 1]) continue;
            if (nums[fixedIndex] > 0) break;

            int left = fixedIndex + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[fixedIndex] + nums[left] + nums[right];

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    triples.add(Arrays.asList(nums[fixedIndex], nums[left], nums[right]));
                    do {
                        left++;
                    } while (nums[left] == nums[left - 1] && left < right);
                }
            }
        }

        return triples;
    }
}
```

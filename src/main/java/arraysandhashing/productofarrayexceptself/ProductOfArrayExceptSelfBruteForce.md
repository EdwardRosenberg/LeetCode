# Product of Array Except Self (Brute Force Variant)

- **LeetCode:** [238 — Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "product/sum of everything except the current element, without division"

See [ProductOfArrayExceptSelfOptimal.md](ProductOfArrayExceptSelfOptimal.md) for the canonical O(n) version.

## Approach

For each index, multiply every other element together in a nested loop — no cleverness applied, just the direct reading of the problem statement.

- **Time:** O(n²) — a full inner pass per outer index.
- **Space:** O(1) extra (output array doesn't count toward space complexity).

This is the natural first solution to reach for under time pressure — it's worth writing out once to confirm correctness before reaching for the prefix/postfix trick, which is exactly what the O(n) canonical version replaces this nested loop with.

**Why it works:** this is correct by direct construction, not by any clever insight — the inner loop's `i != targetIndex` guard multiplies in literally every element except the one being excluded, which is exactly what "product except self" asks for. It's the baseline every other variant has to match, and the natural thing to hand-verify small cases against.

## Visualizing it

```
nums = [1, 2, 3, 4]

targetIndex=0: multiply everything except nums[0]
  1(skip) * 2 * 3 * 4 = 24

targetIndex=1: multiply everything except nums[1]
  1 * 2(skip) * 3 * 4 = 12

targetIndex=2: multiply everything except nums[2]
  1 * 2 * 3(skip) * 4 = 8

targetIndex=3: multiply everything except nums[3]
  1 * 2 * 3 * 4(skip) = 6

result: [24, 12, 8, 6]
```

Every targetIndex re-walks the whole array — that repeated work is exactly what the prefix/postfix approach avoids by reusing running totals instead of recomputing from scratch.

## Code

```java
package arraysandhashing.productofarrayexceptself;

public class ProductOfArrayExceptSelfBruteForce implements ProductExceptSelfCalculator {

    @Override
    public int[] productExceptSelf(int[] nums) {
        int[] productsExceptSelf = new int[nums.length];
        for (int targetIndex = 0; targetIndex < nums.length; targetIndex++) {

            int product = 1;
            for (int i = 0; i < nums.length; i++) {
                if (i != targetIndex) {
                    product = product * nums[i];
                }
            }

            productsExceptSelf[targetIndex] = product;
        }

        return productsExceptSelf;
    }
}
```

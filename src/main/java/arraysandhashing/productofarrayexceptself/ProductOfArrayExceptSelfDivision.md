# Product of Array Except Self (Division Variant)

- **LeetCode:** [238 — Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "product/sum of everything except the current element, without division"

See [ProductOfArrayExceptSelfOptimal.md](ProductOfArrayExceptSelfOptimal.md) for the canonical, division-free O(n) version.

## Approach

Compute the product of every non-zero element, then divide it back out per index — the "obvious" approach, and the reason this problem is usually posed as a follow-up challenge ("now solve it without division"). Division by zero is the entire difficulty, handled here by counting zeros up front instead of ever dividing by one:

- **two or more zeros:** every index's product excludes at most one of them, so at least one zero always remains — every answer is 0.
- **exactly one zero:** only that zero's own index "removes" it, leaving the product of every other (non-zero) element; every other index's product still includes the zero, so it's 0.
- **no zeros:** divide the total product by each element directly.

- **Time:** O(n) — one pass to multiply/count zeros, one pass to fill the answer.
- **Space:** O(1) extra (output array doesn't count toward space complexity).

**Why it works:** `answer[i] = totalProduct / nums[i]` is only valid when `nums[i]` doesn't divide out a zero it's the sole source of — the zero-count branching exists entirely to sidestep the one case where "total product" doesn't cleanly decompose: division by (or of) zero. Splitting into 0/1/2+ zero cases covers every possibility exhaustively, so each branch only ever does arithmetic it can actually justify — no case is left divided-by-zero or silently wrong.

**Watch out:** the naive version of this approach — `answer[i] = totalProduct / nums[i]` with no zero-handling — throws `ArithmeticException: / by zero` at the zero's own index whenever the input contains a zero, and LeetCode's test bank for this problem includes zero as a matter of course. It's an easy trap to miss when hand-testing only with the classic `[1,2,3,4]` example.

## Visualizing it

```
nums = [1, 2, 0, 4]   (exactly one zero, at index 2)

Pass 1: count zeros, multiply the non-zero elements
  zeroCount = 1
  productOfNonZeros = 1 * 2 * 4 = 8

Pass 2: only the zero's own index gets a nonzero answer
  i=0: nums[0]=1, not zero -> 0
  i=1: nums[1]=2, not zero -> 0
  i=2: nums[2]=0, is zero  -> productOfNonZeros = 8
  i=3: nums[3]=4, not zero -> 0

result: [0, 0, 8, 0]
```

```
nums = [0, 4, 0, 2]   (two zeros)

zeroCount = 2 -> every answer is 0 immediately, no division ever attempted

result: [0, 0, 0, 0]
```

## Code

```java
package arraysandhashing.productofarrayexceptself;

public class ProductOfArrayExceptSelfDivision implements ProductExceptSelfCalculator {

    @Override
    public int[] productExceptSelf(int[] nums) {
        int zeroCount = 0;
        int productOfNonZeros = 1;

        for (int num : nums) {
            if (num == 0) {
                zeroCount++;
            } else {
                productOfNonZeros *= num;
            }
        }

        int[] productsExceptSelf = new int[nums.length];

        if (zeroCount > 1) {
            return productsExceptSelf;
        }

        if (zeroCount == 1) {
            for (int i = 0; i < nums.length; i++) {
                productsExceptSelf[i] = (nums[i] == 0) ? productOfNonZeros : 0;
            }
            return productsExceptSelf;
        }

        for (int i = 0; i < nums.length; i++) {
            productsExceptSelf[i] = productOfNonZeros / nums[i];
        }

        return productsExceptSelf;
    }
}
```

package arraysandhashing.productofarrayexceptself;

/**
 * LeetCode 238 — Product of Array Except Self (Medium)
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Pattern: Arrays & Hashing
 * Cue: "product/sum of everything except the current element, without division"
 *
 * Approach: Compute the product of every non-zero element, then divide it
 * back out per index — the "obvious" approach, and the reason this problem
 * is usually posed as a follow-up challenge ("now solve it without
 * division"). Division by zero is the entire difficulty, handled here by
 * counting zeros up front instead of ever dividing by one:
 *   - two or more zeros: every index's product excludes at most one of
 *     them, so at least one zero always remains — every answer is 0.
 *   - exactly one zero: only that zero's own index "removes" it, leaving
 *     the product of every other (non-zero) element; every other index's
 *     product still includes the zero, so it's 0.
 *   - no zeros: divide the total product by each element directly.
 *
 * Time: O(n) — one pass to multiply/count zeros, one pass to fill the answer.
 * Space: O(1) extra (output array doesn't count toward space complexity).
 *
 * See ProductOfArrayExceptSelfOptimal.java for the canonical, division-free
 * O(n) version.
 */
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
            // Every index's product still includes at least one zero even
            // after excluding itself — every answer is 0. int[] already
            // defaults to all zeros, so there's nothing left to fill in.
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

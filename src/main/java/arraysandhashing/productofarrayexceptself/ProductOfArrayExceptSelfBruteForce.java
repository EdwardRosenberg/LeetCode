package arraysandhashing.productofarrayexceptself;

/**
 * LeetCode 238 — Product of Array Except Self (Medium)
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Pattern: Arrays & Hashing
 * Cue: "product/sum of everything except the current element, without division"
 *
 * Approach: For each index, multiply every other element together in a
 * nested loop — the brute-force approach, no cleverness applied.
 *
 * Time: O(n^2) — a full inner pass per outer index.
 * Space: O(1) extra (output array doesn't count toward space complexity).
 *
 * See ProductOfArrayExceptSelfOptimal.java for the canonical O(n) version.
 */
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

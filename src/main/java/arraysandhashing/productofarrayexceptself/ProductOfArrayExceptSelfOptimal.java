package arraysandhashing.productofarrayexceptself;

/**
 * LeetCode 238 — Product of Array Except Self (Medium)
 * https://leetcode.com/problems/product-of-array-except-self/
 *
 * Pattern: Arrays & Hashing
 * Cue: "product/sum of everything except the current element, without division"
 *
 * Approach: Two passes build the answer directly in the output array —
 * left-to-right fills each index with the running product of everything
 * before it (prefix), then right-to-left multiplies in the running product
 * of everything after it (postfix).
 *
 * Time: O(n) — two linear passes.
 * Space: O(1) extra (output array doesn't count toward space complexity).
 *
 * Variants: ProductOfArrayExceptSelfBruteForce.java (brute-force nested
 * loop, O(n^2)), ProductOfArrayExceptSelfDivision.java (divide out the
 * total product — same O(n)/O(1), but needs explicit zero-handling and is
 * usually disallowed by the problem's own follow-up constraint)
 */
public class ProductOfArrayExceptSelfOptimal implements ProductExceptSelfCalculator {

    @Override
    public int[] productExceptSelf(int[] nums) {
        int[] productsExceptSelf = new int[nums.length];
        // have to init at 1, otherwise everything will zero-out
        int prefixProduct = 1;
        int postfixProduct = 1;

        // Left-to-right: index i gets the product of everything to its left.
        for (int i = 0; i < nums.length; i++) {
            productsExceptSelf[i] = prefixProduct;
            prefixProduct *= nums[i];
        }

        // Right-to-left: multiply in the product of everything to its right.
        for (int i = nums.length - 1; i >= 0; i--) {
            productsExceptSelf[i] *= postfixProduct;
            postfixProduct *= nums[i];
        }

        return productsExceptSelf;
    }
}

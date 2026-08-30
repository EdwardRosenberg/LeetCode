# Product of Array Except Self

- **LeetCode:** [238 — Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "product/sum of everything except the current element, without division"

Variants: [ProductOfArrayExceptSelfBruteForce](ProductOfArrayExceptSelfBruteForce.md) (brute-force nested loop, O(n²)), [ProductOfArrayExceptSelfDivision](ProductOfArrayExceptSelfDivision.md) (divide out the total product — same O(n)/O(1), but needs explicit zero-handling and is usually disallowed by the problem's own follow-up constraint)

## Approach

Two passes build the answer directly in the output array — left-to-right fills each index with the running product of everything before it (prefix), then right-to-left multiplies in the running product of everything after it (postfix).

- **Time:** O(n) — two linear passes.
- **Space:** O(1) extra (output array doesn't count toward space complexity).

**Why it works:** `productsExceptSelf[i]` is, by definition, `(everything left of i) × (everything right of i)` — those two ranges partition every index except `i` itself, so their product covers exactly what "except self" asks for. Pass 1 computes the left half for every index in one running pass; pass 2 computes the right half the same way, walking backward, and multiplies it into what pass 1 already stored. The ordering inside each loop matters: `productsExceptSelf[i]` is always *read or written before* `nums[i]` gets folded into the running product for the next index — write first, then extend — which is what keeps `nums[i]` from ever being included in its own answer.

## Visualizing it

```
nums = [1, 2, 3, 4]

Pass 1 (left-to-right): productsExceptSelf[i] = product of everything left of i

  prefixProduct starts at 1
  i=0: productsExceptSelf[0] = 1        prefixProduct becomes 1*1 = 1
  i=1: productsExceptSelf[1] = 1        prefixProduct becomes 1*2 = 2
  i=2: productsExceptSelf[2] = 2        prefixProduct becomes 2*3 = 6
  i=3: productsExceptSelf[3] = 6        prefixProduct becomes 6*4 = 24

  productsExceptSelf = [1, 1, 2, 6]   (each index holds its left-product so far)

Pass 2 (right-to-left): multiply in everything right of i

  postfixProduct starts at 1
  i=3: productsExceptSelf[3] = 6*1 = 6      postfixProduct becomes 1*4 = 4
  i=2: productsExceptSelf[2] = 2*4 = 8      postfixProduct becomes 4*3 = 12
  i=1: productsExceptSelf[1] = 1*12 = 12    postfixProduct becomes 12*2 = 24
  i=0: productsExceptSelf[0] = 1*24 = 24    postfixProduct becomes 24*1 = 24

  productsExceptSelf = [24, 12, 8, 6]
```

## Code

```java
package arraysandhashing.productofarrayexceptself;

public class ProductOfArrayExceptSelfOptimal implements ProductExceptSelfCalculator {

    @Override
    public int[] productExceptSelf(int[] nums) {
        int[] productsExceptSelf = new int[nums.length];
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
```

package arraysandhashing;

public class ProductOfArrayExceptSelfOptimal {

    public int[] productExceptSelf(int[] nums) {
        int[] response = new int[nums.length];
        int right=1;
        int left=1;

        // compute prefix for every number
        for (int i = 0; i < nums.length; i++) {
            response[i] = left;
            left *= nums[i];
        }

        // compute postfix for every number
        for(int i = nums.length - 1; i >= 0; i--) {
            response[i] *= right;
            right *= nums[i];
        }

        return response;
    }

}

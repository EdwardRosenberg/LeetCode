package arraysandhashing;

public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] response = new int[nums.length];
        for (int responseIndex = 0; responseIndex < nums.length; responseIndex++) {

            int product = 1;
            for (int i = 0; i < nums.length; i++) {
                if (i != responseIndex) {
                    product = product * nums[i];
                }
            }

            response[responseIndex] = product;
        }

        return response;
    }

}

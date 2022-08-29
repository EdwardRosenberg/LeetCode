package twosum;

public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];

        int left = 0;
        int right = numbers.length - 1;

        while(left < right){

            if(numbers[right] + numbers[left] > target){
                right--;
                continue;
            }

            if(numbers[right] + numbers[left] < target){
                left++;
                continue;
            }

            res[0] = left + 1;
            res[1] = right + 1;
            break;
        }

        return res;
    }
}


/*
[0,0, 1, 2]
0

[5,25,75]
100
 */
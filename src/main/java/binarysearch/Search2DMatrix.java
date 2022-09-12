package binarysearch;

public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        int row = 0;
        int top = 0;
        int bottom = matrix.length - 1;

        int left = 0;
        int right = matrix[row].length - 1;

        while (top <= bottom) {
            int middle = (top + bottom) / 2;

            if (matrix[middle][left] > target) {
                if (middle > 0 && matrix[middle - 1][left] < target) {
                    row = middle - 1;
                    break;
                }
                bottom = middle - 1;
            } else if (matrix[middle][left] < target) {
                if ((middle + 1 < matrix.length && matrix[middle + 1][left] > target) || middle == matrix.length - 1)  {
                    row = middle;
                    break;
                }
                top = middle + 1;
            } else {
                return true;
            }
        }

        while (left <= right) {
            int middle = (left + right) / 2;

            if (matrix[row][middle] > target) {
                right = middle - 1;
            } else if (matrix[row][middle] < target) {
                left = middle + 1;
            } else {
                return true;
            }
        }

        return false;
    }

}

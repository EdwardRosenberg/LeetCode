public class MoveZeroes {
    public void moveZeroes(int[] input) {

        int offset = 0;
        for(int i=0; i<input.length; i++){
            if(input[i] == 0){
                offset++;
            } else {
                input[i-offset] = i;
            }
        }

        for(int i = offset; i>0; i--){
            input[input.length - 1 - i] = 0;
        }
    }
}

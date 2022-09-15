package containsduplicate;

import java.util.*;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] input){
        Set<Integer> vals = new HashSet<>();

        for(int i=0; i<input.length; i++){
            if(vals.contains(input[i])){
                return true;
            }else{
                vals.add(input[i]);
            }
        }

        return false;
    }

}

import java.util.*;

public class CheckPermutation{

    /*
        Check if one string is a permutation the other.
    */
    
    // Check if both strings have the same character count.
    private boolean solve(String A, String B){
        //Assuming that character format is ASCII
        int[] freq = new int[128];
        for(int i=0; i<A.length(); i++){
            freq[A.charAt(i)] += 1;
        }
        for(int j=0; j<B.length(); j++){
            freq[B.charAt(j)] -= 1;
            //Small optimization -> If character count goes below 0, return false.
            if(freq[B.charAt(j)] < 0) return false;
        }
        // Check if all counts are zero. 
        for(int k=0; k<128; k++){
            if(freq[k] != 0) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        CheckPermutation checkPermutation = new CheckPermutation();
        System.out.println(checkPermutation.solve("aabbcc", "bcaa"));
    }
}
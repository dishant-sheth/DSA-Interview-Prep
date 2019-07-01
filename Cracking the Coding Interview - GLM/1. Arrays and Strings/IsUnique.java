import java.util.*;

public class IsUnique{

    /*
        Part 1 -> An algorithm to determine if a string has all unique characters.
        Part 2 -> What if no additional DS?
    */

    //Part 1 -> O(n). Open addressing of the characters indexed by their ASCII value using an array of fixed size (128). O(n)
    private boolean solve(String input){
        //Assuming that we use ASCII. 256 if we use UTF
        int[] freq = new int[128];
        for(int i=0; i<input.length(); i++){
            char curr = input.charAt(i);
            if(freq[curr] != 0) return false;
            freq[curr] += 1;
        }
        return true;
    }

    //Part 2 -> Would take O(n^2) to compare each character with all other characters in the string.

    public static void main(String[] args) {
        String input = "Helo";
        IsUnique isUnique = new IsUnique();
        System.out.println(isUnique.solve(input));
    }
}
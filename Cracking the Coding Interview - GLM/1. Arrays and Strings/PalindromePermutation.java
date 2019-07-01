import java.util.*;

/*
    [EASY]
    Given a string, write a functions to check if it is a permutation of a palindrome. 
    The palindrome doesn't need to be limited to words in the english dictionary. 
    Example -> tact coa
    Yes. (Why? One permutation is -> taco cat)
*/

public class PalindromePermutation{

    /*
        Two solutions ->
        1. Brute force solution - Find all permutations and check if one of them is a palindrome.
        2. Check on the basis of the characteristics of a palindrome ->
            a. All characters in the string have an even count.
            b. (n-1) characters have an even count and 1 character has an odd count.

            If either of (a) or (b) holds true, a palindromic permutation exists.

    */

    private boolean isPalindrome(String input){
        int n = input.length();
        for(int i=0; i<n/2; i++){
            char a = input.charAt(i);
            char b = input.charAt(n - i - 1);
            if(a == ' ' || b == ' ') continue;
            if(a != b){
                return false;
            }
        }
        return true;
    }

    private boolean allPermutations(StringBuilder searchSpace, StringBuilder choices){
        //BASE CASE: search space is now empty
        if(searchSpace.length() == 0){
            //System.out.print(choices.toString() + "\t");
            if(isPalindrome(choices.toString())) return true;
            return false;
        }
        //RECURSIVE CASE: find permutations
        else{
            boolean result = false;
            for(int i=0; i<searchSpace.length(); i++){
                char current = searchSpace.charAt(i);
                choices.append(current);
                searchSpace.delete(i, i+1);
                result = allPermutations(searchSpace, choices);
                if(result) return true;
                choices.delete(choices.length() - 1, choices.length());
                searchSpace.insert(i, current);
            }
            return result;
        }
    }

    private boolean isPalindromicPermutation(String input){
        int even = 0, odd = 0;
        int[] count = new int[128];
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i) == ' ') continue;
            else count[input.charAt(i)] += 1;
        }
        for(int i=0; i<128; i++){
            if(count[i] == 0) continue;
            else if(count[i]%2 == 0) even += 1;
            else odd += 1;
        }
        return (odd <= 1);
    }

    public static void main(String[] args) {
        PalindromePermutation palindromePermutation = new PalindromePermutation();
        System.out.println(palindromePermutation.allPermutations(new StringBuilder("helloh"), new StringBuilder()));
        System.out.println(palindromePermutation.isPalindromicPermutation("helloh"));
    }
}
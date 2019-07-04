import java.util.*;

/*
    Implement a method to perform basic string compression using counts of repeated characters. 
    For example -> 
    aabcccccaaa becomes a2b1c5a3
    Note: If compressed string is longer than original string, return original string.
*/

public class StringCompression{

    public String compress(String input){
        String compressedString = "";
        int i = 0, n = input.length(), count = 0;
        for(i=0; i<n; i++){
            char current = input.charAt(i);
            i += 1;
            count = 1;
            while(i < n){
                if(input.charAt(i) != current){
                    break;
                }
                i += 1;
                count += 1;
            }
            i -= 1;
            compressedString += (current + String.valueOf(count));
        }

        return (input.length() < compressedString.length()) ? input : compressedString;
    }

    public static void main(String[] args) {
        StringCompression stringCompression = new StringCompression();
        System.out.println(stringCompression.compress("aabbcccdddde"));
    }
}
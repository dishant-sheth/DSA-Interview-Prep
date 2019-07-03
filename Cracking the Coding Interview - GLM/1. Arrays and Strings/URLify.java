import java.util.*;

public class URLify {

    /*
        Replace spaces with %20 - URL encoding.
    */

    //Straight-forward algorithm.
    private String solve1(String input, int true_len){
        StringBuilder result = new StringBuilder();
        for(int i=0; i<true_len; i++){
            if(input.charAt(i) == ' ') result.append("%20");
            else result.append(input.charAt(i));
        }
        return result.toString();
    }

    //Perform this in-place. In Java Strings are immutable. So, use a char array for in-place operations.
    //Note that, if there aren't enough spaces after the end of the true_len, we cannot do it in-place, a newer array will have to be created with the required size.
    private String solve2(String input, int true_len){
        char[] inputArr = input.toCharArray();
        int spaces = 0;
        for(int i=0; i<true_len; i++){
            if(inputArr[i] == ' ') spaces += 1;
        }
        //spaces*3 because "%20" has length 3.
        int new_size = (true_len-spaces) + (spaces * 3);
        //If needed size is greater than existing space in memory, cannot do it in-place
        if(new_size > inputArr.length) return "";
        //If enough space exists, we can perform it in-memory
        if(inputArr.length > new_size){
            for(int i=new_size; i<inputArr.length; i++){
                inputArr[i] = '\0';
            }
        }
        int index = new_size-1;
        for(int i=true_len-1; i>=0; i--){
            if(inputArr[i] == ' '){
                inputArr[index] = '0';
                inputArr[index - 1] = '2';
                inputArr[index - 2] = '%';
                index -= 3;
            }
            else{
                inputArr[index] = inputArr[i];
                index -= 1;
            }
        }
        return String.valueOf(inputArr);
    }

    public static void main(String[] args) {
        URLify urLify = new URLify();
        System.out.println("\"" + urLify.solve1("Mr John Smith    ", 13) + "\"");
        System.out.println("\"" + urLify.solve2("Mr John Smith      ", 13) + "\"");
    }
}
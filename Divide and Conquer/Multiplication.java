import java.util.*;

public class Multiplication{

    /*
        Optimize the way in which we would multiply two very large numbers. 
        Two appraches -> 
        1. Third Grade Multiplication Technique
        2. Karatsuba Multiplication - A divide and conquer strategy.
    */

    //Generic method taught in primary school
    //Time complexity -> 3*m*n, where m is length of first number and n is length of second number.
    public void thirdGradeApproach(String num1, String num2){
        //1. Convert inputs to int arrays.
        int num1_len = num1.length(), num2_len = num2.length();

        int[] n1 = new int[num1_len];
        int[] n2 = new int[num2_len];
        
        for(int i=0; i<num1_len; i++){
            n1[i] = num1.charAt(i) - '0';
        }
        for(int j=0; j<num2_len; j++){
            n2[j] = num2.charAt(j) - '0';
        }

        //2. Start the multiplication process. Store result of each step in a 2D matrix.
        int[][] result = new int[num2_len][2*num1_len];
        int prod = 0, carry = 0, step = 0, col_index = 0;
        int i = 0, j= 0;
        for(j=num2_len-1; j>=0; j--){
            col_index = (num1_len*2) - 1 - step;
            step += 1;
            for(i=num1_len-1; i>=0; i--){
                prod = (n2[j] * n1[i]) + carry;
                carry = prod/10;
                prod = prod%10;
                result[num2_len - j - 1][col_index--] = prod;
            }
            if(carry != 0) result[num2_len - j - 1][col_index] = prod;
        }

        //3. Add result matrix
        String answer = "";
        int sum = 0; 
        carry = 0;
        for(i=(num1_len*2)-1; i>=0; i--){
            sum = 0;
            for(j=0; j<num2_len; j++){
                sum += result[j][i];
            }
            sum += carry;
            carry = sum/10;
            sum = sum%10;
            answer = String.valueOf(sum) + answer;
        }
        if(carry != 0){
            answer = String.valueOf(carry) + answer;
        }

        //4. Removing initial zeros.
        i = 0;
        while(answer.charAt(i) == '0'){
            i += 1;
        }
        answer = answer.substring(i);

        System.out.println(answer);

    }

    //The divide and conquer approach called as karatsuba multiplication.
    public void karatsubaMultiplication(String num1, String num2){

    }

    public static void main(String[] args) {
        Multiplication KM = new Multiplication();
        KM.thirdGradeApproach("123456781234", "567812345678");
    }

}
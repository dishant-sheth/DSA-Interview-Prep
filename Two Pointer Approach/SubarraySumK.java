import java.util.*;

//Find a subarray in the given array that equals to given sum K.
//Return the first subrray found.

public class SubarraySumK{

    private int leftIndex = -1, rightIndex = -1;

    //Helper function for solution 1.
    private void helper1(int[] arr, int sum){
        int left = 0;
        int right = 0;
        int n = arr.length;
        int curr_sum = arr[left];
        while(right < n && left <= right){
            if(left == right && curr_sum > sum){
                left += 1;
                right += 1;
                if(right < n) curr_sum = arr[right];
                else break;
            }
            else if(curr_sum == sum){
                leftIndex = left + 1;
                rightIndex = right + 1;
                break;
            }
            else{
                if(curr_sum > sum){
                    curr_sum -= arr[left];
                    left += 1;
                }
                else{
                    right += 1;
                    if(right < n) curr_sum += arr[right];
                    else break;
                }
            }
        }
    }

    //Helper function for solution 2
    private void helper2(int[] arr, int sum){
        
    }

    //Solution 1 -> Input contains positive numbers only.
    public void findSubarray1(int[] arr, int sum){
        helper1(arr, sum);
        if(leftIndex == -1){
            System.out.println("No subarray found.");
        }
        else{
            System.out.println(leftIndex + " " + rightIndex);
        }
    }

    //Solution -> Input contains both positive and negative numbers.
    public void findSubarray2(int[] arr, int sum){

    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int needed_sum = 12;
        SubarraySumK subarraySumK = new SubarraySumK();
        subarraySumK.findSubarray1(arr, needed_sum);
    }
}

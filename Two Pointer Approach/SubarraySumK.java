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
        int n = arr.length;
        HashMap<Integer, Integer> contigSum = new HashMap<>();
        int curr_sum = 0;
        for(int i=0; i<n; i++){
            curr_sum += arr[i];
            int other_sum = curr_sum - sum;
            if(other_sum == 0){
                leftIndex = 1;
                rightIndex = i + 1;
                break;
            }
            if(contigSum.containsKey(other_sum)){
                leftIndex = contigSum.get(other_sum) + 2;
                rightIndex = i + 1;
                break;
            }
            contigSum.put(curr_sum, i);
        }
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
        helper2(arr, sum);
        if(leftIndex == -1){
            System.out.println("No subarray found.");
        }
        else{
            System.out.println(leftIndex + " " + rightIndex);
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int needed_sum1 = 12;
        int[] arr2 = {10, 2, -2, -20, 10};
        int needed_sum2 = -10;
        SubarraySumK subarraySumK = new SubarraySumK();
        subarraySumK.findSubarray1(arr1, needed_sum1);
        subarraySumK.findSubarray2(arr2, needed_sum2);
    }
}

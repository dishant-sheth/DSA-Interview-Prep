import java.util.*;

public class CountingInversions{

    /*
        Problem -> Given an array A containing n elements, find the count of all pairs (i,j) such that i<j and A[i]>A[j].
        Example -> 
            A = | 1 | 2 | 4 | 3 | 5 |
            Output =  (2,3) since 2 < 3 and 4 > 3.
    */

    //Approach 1 : Brute Force
    //Approach 2: Merge Sort

    private int merge(int[] arr, int left, int mid, int right){
        int n1 = mid-left+1;
        int n2 = right-mid;
        int n = n1+n2;
        int[] left_arr, right_arr, out_arr;
        left_arr = new int[n1];
        right_arr = new int[n2];
        out_arr = new int[n];
        int i=0, j=0, index=0;
        for(i=0; i<n1; i++){
            left_arr[i] = arr[left+i];
        }
        for(j=0; j<n2; j++){
            right_arr[j] = arr[mid+j+1];
        }
        int count = 0;
        i=0;
        j=0;
        while(i<n1 && j<n2){
            if(left_arr[i] <= right_arr[j]){
                out_arr[index++] = left_arr[i++];
            }
            else{
                out_arr[index++] = right_arr[j++];
                count += n1 - i;
            }
        }
        while(i<n1) out_arr[index++] = left_arr[i++];
        while(j<n2) out_arr[index++] = right_arr[j++];
        //Making changes in the original array
        for(index=0; index<n; index++){
            arr[left+index] = out_arr[index];
        }
        return count;
    }

    public int divide(int[] arr, int left, int right){
        if(left >= right) return 0;
        int mid = left + (right-left)/2;
        int leftInversions = divide(arr, left, mid);
        int rightInversions = divide(arr, mid+1, right);
        int splitInversions = merge(arr, left, mid, right);

        return leftInversions + rightInversions + splitInversions;
    }

    public static void main(String[] args) {
        CountingInversions countingInversions = new CountingInversions();
        int[] input = {5,4,3,2,1};
        System.out.println(countingInversions.divide(input, 0, input.length - 1));
    }
}
import java.util.*;

/*
    Given a rotated sorted array, sort it in O(n) time and O(1) space.
*/

public class SortRotatedArray{

    /*
        2 step solution => 
            1. Find the pivot element using Binary Search.
                7  8  1  2  3  4  5  6
                      ^
                 pivot element
            2. Rotate the array based on the pivot element index.

        Analysis -> 
        Time = O(N + logN)
        Space = O(1)
    */

    private int findPivotElement(int[] arr, int left, int right){
        if(left > right) return 0;
        int n = arr.length;
        int mid = left + (right-left)/2;
        //Check if pivot element
        if(mid == 0){
            if(arr[mid] < arr[mid+1] && arr[mid] < arr[n-1]) return mid;
        }
        else if(mid == n-1){
            if(arr[mid] < arr[mid-1] && arr[mid] < arr[0]) return mid;
        }
        else{
            if(arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) return mid;
        }
        //If not, recurse further in the unsorted side.
        if(arr[left] <= arr[mid]) return findPivotElement(arr, mid+1, right);
        else return findPivotElement(arr, left, mid-1);
    }

    private void reverseArray(int[] arr, int left, int right){
        while(left<right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    //Allows us to rotate the array in constant space.
    private void rotateArray(int[] arr, int pIndex){
        reverseArray(arr, 0, pIndex-1);
        reverseArray(arr, pIndex, arr.length - 1);
        reverseArray(arr, 0, arr.length - 1);
    }

    public void solve(int[] arr){
        int pIndex = findPivotElement(arr, 0, arr.length - 1);
        if(pIndex == 0) return;
        else rotateArray(arr, pIndex);
    }

    public static void main(String[] args) {
        int[] arr = {7,8,1,2,3,4,5,6};
        SortRotatedArray sra = new SortRotatedArray();
        sra.solve(arr);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
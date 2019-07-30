import java.util.*;

/*
    Given a sorted rotated array of N elements, find the min and max element in the array in O(logN) time.
*/

public class MinAndMax{

    public int getMin(int[] arr, int left, int right){
        //If we come to this stage, the sorted array was rotated n times to bring it to it's original indices.
        if(left > right) return arr[0];
        int mid = left + (right-left)/2;
        //Check if mid is minimum element.
        if(mid == 0){
            if(arr[mid] < arr[mid+1] && arr[mid] < arr[arr.length-1]) return arr[mid];
        }
        else if(mid == arr.length-1){
            if(arr[mid] < arr[mid-1] && arr[mid] < arr[0]) return arr[mid];
        }
        else{
            if(arr[mid] < arr[mid+1] && arr[mid] < arr[mid-1]) return arr[mid];
        }
        //If not found, check in the unsorted array because the min will next to the pivot point always.
        if(arr[left] <= arr[mid]) return getMin(arr, mid+1, right);
        else return getMin(arr, left, mid-1);
    }

    public int getMax(int[] arr, int left, int right){
        if(left > right) return arr[arr.length-1];
        int mid = left + (right-left)/2;
        if(mid == 0){
            if(arr[mid] > arr[mid+1] && arr[mid] > arr[arr.length-1]) return arr[mid];
        }
        else if(mid == arr.length - 1){
            if(arr[mid] > arr[mid-1] && arr[mid] > arr[0]) return arr[mid];
        }
        else{
            if(arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]) return arr[mid];
        }
        //The max will always be in the unsorted side.
        if(arr[left] <= arr[mid]) return getMax(arr, mid+1, right);
        else return getMax(arr, left, mid-1);
    }

    public static void main(String[] args) {
        MinAndMax minAndMax = new MinAndMax();
        int[] arr = {4,5,6,7,8,9,1,2,3};
        System.out.println("Min -> " + minAndMax.getMin(arr, 0, arr.length-1));
        System.out.println("Max -> " + minAndMax.getMax(arr, 0, arr.length-1));
    }
}
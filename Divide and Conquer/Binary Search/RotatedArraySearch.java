import java.util.*;

/*
    Given a sorted rotated array of size N. Find a number in the array in O(logN) time.
    Example input -> 4 5 1 2 3
*/

public class RotatedArraySearch{

    public int binarySearch(int[] arr, int left, int right, int target){
        if(left > right) return -1;
        int mid = left + (right-left)/2;
        //1. Divide the array into 2 halves. Sorted half and unsorted half. 
        //2. Check in sorted array, if found recurse in sorted array. 
        //3. Else, recurse in unsorted side.
        if(arr[mid] == target) return mid;
        //Sorted left side.
        else if(arr[mid] >= arr[left]){
            //Check if target is in sorted side.
            if(target >= arr[left] && target < arr[mid]){
                return binarySearch(arr, left, mid-1, target);
            }
            //Now check in unsorted side.
            else return binarySearch(arr, mid+1, right, target);
        }
        //Sorted right side.
        else{
            //Check if target is in sorted side.
            if(target > arr[mid] && target <= arr[right]){
                return binarySearch(arr, mid+1, right, target);
            }
            //Now check in unsorted side.
            else return binarySearch(arr, left, mid-1, target);
        }

    }

    public static void main(String[] args) {
        RotatedArraySearch rSearch = new RotatedArraySearch();
        int[] arr = {7,8,9,1,2,3,4,5,6};
        System.out.println(rSearch.binarySearch(arr, 0, arr.length - 1, 5));
        System.out.println(rSearch.binarySearch(arr, 0, arr.length - 1, 10));
    }
}
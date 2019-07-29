import java.util.*;

public class Search{
    
    public int binarySearch(int[] arr, int left, int right, int target){
        if(left > right) return -1;
        int mid = left + (right-left)/2;
        if(arr[mid] == target) return mid;
        else if(target > arr[mid]) return binarySearch(arr, mid+1, right, target);
        else return binarySearch(arr, left, mid-1, target);
    }
    public static void main(String[] args) {
        Search search = new Search();
        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(search.binarySearch(arr, 0, arr.length - 1, 6));
        System.out.println(search.binarySearch(arr, 0, arr.length - 1, 10));
    }
}
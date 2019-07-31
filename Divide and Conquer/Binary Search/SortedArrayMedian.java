import java.util.*;
import java.lang.Math;

/*
    Given two sorted arrays of size N, find the median of the merged arrays. Do it in O(logN)
*/

public class SortedArrayMedian{

    //Helper functions
    private double median(int[] arr, int left, int right){
        int n = right - left + 1;
        //If array length is even.
        if(n%2 == 1){
            int index = (left+right)/2;
            return arr[index]/1.0;
        }
        //If array length is odd.
        else{
            int index1 = (left + right)/2;
            int index2 = index1 + 1;
            return (arr[index1] + arr[index2])/2.0;
        }
    }

    //Get left-side median index.
    private int leftMedianIndex(int left, int right){
        return (left+right)/2;
    }

    //Get right-side median index.
    private int rightMedianIndex(int left, int right){
        int n = right-left+1;
        if(n%2 == 1){
            return (left+right)/2;
        }
        else{
            return ((left+right)/2)+1;
        }
    }

    public double solve(int[] arr1, int[] arr2, int left1, int right1, int left2, int right2){
        //Debugging purposes only -> 
        //System.out.println("(" + left1 + ", " + right1 + ", " + left2 + ", " + right2 + ")");
        //Calculate sub-array lengths
        int n1 = right1-left1+1;
        int n2 = right2-left2+1;
        //BASE CASE:
        if(n1 == 2 && n2 == 2){
            return (Math.max(arr1[left1], arr2[left2]) + Math.min(arr1[right1], arr2[right2]))/2.0;
        }
        //RECURSIVE CASES:
        double med1 = median(arr1, left1, right1);
        double med2 = median(arr2, left2, right2);
        //1. Medians are equal
        if(med1 == med2) return med1;
        //2. Median of array 1 is lesser than median of array 2
        else if(med1 < med2){
            return solve(arr1, arr2, leftMedianIndex(left1, right1), right1, left2, rightMedianIndex(left2, right2));
        }
        //3. Otherwise
        else return solve(arr1, arr2, left1, rightMedianIndex(left1, right1), leftMedianIndex(left2, right2), right2);
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {100, 200, 300, 400, 500};
        SortedArrayMedian sortedArrayMedian = new SortedArrayMedian();
        System.out.println(sortedArrayMedian.solve(arr1, arr2, 0, arr1.length - 1, 0, arr2.length - 1));
    }
}
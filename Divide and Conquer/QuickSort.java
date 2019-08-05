import java.util.*;

public class QuickSort{


    private int partition(int[] arr, int left, int right){
        int pivot = arr[right]; //Considering the last element as pivot element
        int pIndex = left;
        for(int i=left; i<right; i++){
            if(arr[i] <= pivot){
                //Swapping the elements
                int temp = arr[pIndex];
                arr[pIndex] = arr[i];
                arr[i] = temp;
                pIndex += 1;
            }
        }
        //Final swap to bring the pivot element to it's right position
        int temp = arr[pIndex];
        arr[pIndex] = arr[right];
        arr[right] = temp;
        return pIndex;
    }

    public void solve(int[] arr, int left, int right){
        if(left >= right) return;

        int pIndex = partition(arr, left, right);
        solve(arr, left, pIndex-1);
        solve(arr, pIndex+1, right);
    }

    public static void main(String[] args) {
        int[] arr = {7,2,4,5,6,1,3,9,8};
        QuickSort quickSort = new QuickSort();
        quickSort.solve(arr, 0, arr.length - 1);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

}
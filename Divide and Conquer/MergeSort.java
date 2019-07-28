import java.util.*;

public class MergeSort{

    private void merge(int[] arr, int left, int mid, int right){
        //Size of left subarray
        int n1 = mid-left+1;
        //Size of right subarray
        int n2 = right-mid;
        //Size of merged array
        int n = n1+n2;
        //Creation of arrays
        int[] merged_array, arr1, arr2;
        merged_array = new int[n];
        arr1 = new int[n1];
        arr2 = new int[n2];
        //Creating pointers
        int i = 0, j = 0, index = 0;
        for(i=0; i<n1; i++){
            arr1[i] = arr[left+i];
        }
        for(j=0; j<n2; j++){
            arr2[j] = arr[mid+j+1];
        }
        //Let the merging begin...
        i=0;
        j=0;
        while(i<n1 && j<n2){
            merged_array[index++] = (arr1[i] <= arr2[j]) ? arr1[i++] : arr2[j++];
        }
        //Handle remaining elements.
        while(i<n1){
            merged_array[index++] = arr1[i++];
        }
        while(j<n2){
            merged_array[index++] = arr2[j++];
        }
        //Making changes in the passed array
        for(index = 0; index<n; index++){
            arr[left+index] = merged_array[index];
        }

    }

    public void divide(int[] arr, int left, int right){
        if(left >= right) return ;
        //Calculating mid. Using (left+right)/2 may cause overflow in the (left+right) part.
        int mid = left + (right-left)/2;
        divide(arr, left, mid);
        divide(arr, mid+1, right);
        //Merging the two sorted arrays
        merge(arr, left, mid, right);
    }

    public static void main(String[] args) {
        int[] arr = {7,4,3,6,4,2,7,3,5,8,4,3,2,4,67,4,6,3,6,33};
        MergeSort mergeSort = new MergeSort();
        mergeSort.divide(arr, 0, arr.length - 1);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    /*      Note: Confused about how change in the array passed to the function in causing
                    a change in the original array?

            Ans: Java is pseudo-pass-by-reference i.e. only changes in the internal states 
                  of objects/arrays will be reflected back in the original object/array. 
            Example -> obj.key = "value" will be reflected back in the original object.
                        But, obj = obj2 will not be reflected back.
    */
}
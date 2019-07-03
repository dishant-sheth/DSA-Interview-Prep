import java.util.*;
import java.lang.Math;

public class OneAway{

    //Assuming that all three operations of insert, delete and edit have cost 1.
    //In some cases, edit may be said to said to have cost 2 becase it involves deleting and then inserting at same place.
    private int levenshteinDistance(String A, String B){
        int A_n = A.length();
        int B_n = B.length();
        int[][] distances = new int[A_n][B_n];
        int init_distance = (A.charAt(0) == B.charAt(0)) ? 0 : 1;
        
        //Fill the first column
        for(int i=0; i<A_n; i++){
            distances[i][0] = init_distance + i;
        }
        //Fill the first row
        for(int j=0; j<B_n; j++){
            distances[0][j] = init_distance + j;
        }
        //Fill the remaining
        for(int i=1; i<A_n; i++){
            for(int j=1; j<B_n; j++){
                if(A.charAt(i) == B.charAt(j)){
                    distances[i][j] = distances[i-1][j-1];
                }
                else{
                    //Find minimum of the three
                    int min_dist = Math.min(distances[i-1][j-1], distances[i-1][j]);
                    min_dist = Math.min(min_dist, distances[i][j-1]);
                    distances[i][j] = min_dist+1;
                }
            }
        }

        for(int i=0; i<A_n; i++){
            for(int j=0; j<B_n; j++){
                System.out.print(distances[i][j] + "\t");
            }
            System.out.println();
        }

        return distances[A_n - 1][B_n - 1];
    }

    /*
        Since we are checking only for one edit distance, there isn't a need to calculate entire levenshtein distance.
    */

    private boolean editOccured(String A, String B){
        boolean oneDiffer = false;
        for(int i=0; i<A.length(); i++){
            if(A.charAt(i) != B.charAt(i)){
                if(oneDiffer) return false;
                oneDiffer = true;
            }
        }
        return true;
    }

    private boolean insOrDelOccured(String larger, String smaller){
        boolean oneDiffer = false;
        int large_n = larger.length();
        int small_n = smaller.length();
        int index1 = 0, index2 = 0;
        while(index1 < large_n && index2 < small_n){
            if(larger.charAt(index1) != smaller.charAt(index2)){
                if(oneDiffer) return false;
                oneDiffer = true;
                index2 += 1;
            }
            else{
                index1 += 1;
                index2 += 1;
            }
        }
        return true;
    }

    private boolean isOneAway(String A, String B){
        int A_n = A.length();
        int B_n = B.length();
        //If lengths are same, only check for replacement
        if(A_n == B_n) return editOccured(A, B);
        //If lengths differ by one, check for insertion/deletion
        else if(A_n + 1 == B_n) return insOrDelOccured(A, B);
        else if(B_n + 1 == A_n) return insOrDelOccured(B, A);
        //If length differs by more than 1.
        return false;
    }

    public static void main(String[] args) {
        OneAway oneAway = new OneAway();
        //Approach 1
        int final_dist = oneAway.levenshteinDistance("pale", "bale");
        if(final_dist <= 1){
            System.out.println("True");
        }
        else{
            System.out.println("False");
        }
        //Approach 2
        System.out.println(oneAway.isOneAway("pales", "bale"));
    }
}
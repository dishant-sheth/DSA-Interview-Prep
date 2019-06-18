import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
    Initialization -> O(n)
    Union -> O(logn) -------> Depth of tree at max can be log n.
    Connected -> O(logn) --/
*/

public class WeightedQuickUnion{

    int[] id;
    int[] weights;
    public WeightedQuickUnion(int n){
        id = new int[n];
        weights = new int[n];
        for(int i=0; i<n; i++){
            id[i] = i;
            weights[i] = 1;
        }
    }

    private int weight(int i){
        return weights[i];
    }

    private int root(int i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }

    public boolean isConnected(int p, int q){
        return (root(p) == root(q));
    }

    //Lower weighted nodes get attached to higher weighted nodes.
    public void union(int p, int q){
        int pId = root(p);
        int qId = root(q);
        //DEFAULT CASE: Object p becomes child of object q
        if(weight(pId) == weight(qId)){
            weights[qId] += weights[pId];
            id[pId] = qId;
        }
        //CASE 1: Object p is heavier than object q
        else if(weight(pId) > weight(qId)){
            weights[pId] += weights[qId];
            id[qId] = pId;
        }
        //CASE 2: Object q is heavier
        else{
            weights[qId] += weights[pId];
            id[pId] = qId;
        }
    }

    private void printWeights(){
        for(int i=0; i<weights.length; i++){
            System.out.print(weight(i) + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        WeightedQuickUnion WQU = new WeightedQuickUnion(10);
        WQU.union(4, 3);
        WQU.union(3, 8);
        WQU.union(6, 5);
        WQU.printWeights();
        WQU.union(9, 4);
        WQU.union(2, 1);
        WQU.union(5, 0);
        WQU.union(7, 2);
        WQU.union(6, 1);
        WQU.printWeights();
        WQU.union(7, 3);
        WQU.printWeights();
    }
}
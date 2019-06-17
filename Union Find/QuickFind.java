import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
    Initialization -> O(n)
    Union -> O(n)
    Connected -> O(1)
*/

public class QuickFind{

    private int[] id;

    //Set initial IDs for each component. At first, each object is disjoint from each other. Hence the unique IDs.
    public QuickFind(int n){
        id = new int[n];
        for(int i=0; i<n; i++){
            id[i] = i;
        }
    }

    //Connect two components by changing ID of first component to match the ID of the second component.
    private void union(int p, int q){
        int pID = id[p];
        int qID = id[q];
        for(int i=0; i<id.length; i++){
            if(id[i] == pID){
                id[i] = qID;
            }
        }
    }

    //Check if ID of both objects is the same. If yes, connected. Else, not connected.
    private boolean isConnected(int p, int q){
        return (id[p] == id[q]);
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = sc.nextInt();
        QuickFind qf = new QuickFind(n);
        qf.union(0, 5);
        qf.union(5, 6);
        qf.union(1, 2);
        qf.union(2, 7);
        qf.union(8, 3);
        qf.union(4, 9);
        qf.union(3, 4);

        System.out.println(qf.isConnected(0, 6));
        System.out.println(qf.isConnected(1, 7));
        System.out.println(qf.isConnected(8, 9));
        sc.close();
    }
}
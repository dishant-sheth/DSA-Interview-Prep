import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class QuickUnion{

    int[] id;
    public QuickUnion(int n){
        id = new int[n];
        for(int i=0; i<n; i++){
            id[i] = i;
        }
    }

    //Get the root of a specific object.
    private int getRoot(int i){
        while(i != id[i]){
            i = id[i];
        }
        return i;
    }

    //If root of object p and q are the same, then they are connected.
    private boolean isConnected(int p, int q){
        return (getRoot(p) == getRoot(q));
    }

    //Assign root of p to 
    private void union(int p, int q){
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);
        id[pRoot] = qRoot;
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int n = sc.nextInt();
        QuickUnion QU = new QuickUnion(n);

        QU.union(0, 5);
        QU.union(5, 6);
        QU.union(1, 2);
        QU.union(2, 7);
        QU.union(8, 3);
        QU.union(4, 9);
        QU.union(3, 4);

        System.out.println(QU.isConnected(0, 6));
        System.out.println(QU.isConnected(1, 7));
        System.out.println(QU.isConnected(8, 9));
        System.out.println(QU.isConnected(0, 9));

        sc.close();
    }
}
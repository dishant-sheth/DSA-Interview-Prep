import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
    Given an N*N grid, find out if there is a possibility for water to percolate from the top to the bottom.
    The grid may contain a million cells. 
    The grid has free cells (water can flow through it) represented as 0 and obstacles/rocks (water cannot flow through it) respresented as 1.
*/

public class Percolation{

    private class WQU{

        //Graphical structure isn't of importance in Quick Union.
        private int[] ids; //(row_num * total columns) + col_num
        private int[] weights;

        WQU(int cells){
            ids = new int[cells];
            weights = new int[cells];
            for(int i=0; i<cells; i++){
                ids[i] = i;
                weights[i] = 1;
            }
        }

        private int root(int i){
            while(i != ids[i]){
                i = ids[i];
            }
            return i;
        }

        public boolean connected(int p, int q){
            return (root(p) == root(q));
        }

        public void union(int p, int q){
            int pRoot = root(p);
            int qRoot = root(q);
            // Same weight -> p becomes a child of q
            if(weights[pRoot] == weights[qRoot]){
                weights[qRoot] += weights[pRoot];
                ids[pRoot] = qRoot;
            }
            //p is heavier than q
            else if(weights[pRoot] > weights[qRoot]){
                weights[pRoot] += weights[qRoot];
                ids[qRoot] = pRoot;
            }
            //q is heavier than p
            else{
                weights[qRoot] += weights[pRoot];
                ids[pRoot] = qRoot;
            }
        }
    }

    private int n;
    private int[][] grid;
    WQU wQuickUnion = null;

    public Percolation(int n, int[][] grid){
        this.n = n;
        this.grid = grid;
        wQuickUnion = new WQU(n*n);
    }

    private int getGridID(int row, int col){
        return (row*n)+col;
    }

    private void connectFreeCells(){
        //Check right and down if any two free cells are connected. If yes, union them.
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                //If last cell, continue
                if(i == n-1 && j == n-1) continue;
                //If last row, check only right
                else if(i == n-1){
                    if(grid[i][j] == 0 && grid[i][j+1] == 0) wQuickUnion.union(getGridID(i,j), getGridID(i,j+1));
                    continue;
                }
                //If last column, check only down
                else if(j == n-1){
                    if(grid[i][j] == 0 && grid[i+1][j] == 0) wQuickUnion.union(getGridID(i,j), getGridID(i+1,j));
                    continue;
                }
                //Check both right and down
                else{
                    if(grid[i][j] == 0 && grid[i][j+1] == 0) wQuickUnion.union(getGridID(i,j), getGridID(i,j+1));
                    if(grid[i][j] == 0 && grid[i+1][j] == 0) wQuickUnion.union(getGridID(i,j), getGridID(i+1,j));
                }
            }
        }
    }

    public void doesPercolate(){
        //Check only if each cell in first row is connected with any cell in last row.
        //If yes, percolates. Else, no.
        boolean flag = false;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(wQuickUnion.connected(getGridID(0, i), getGridID(n-1, j))){
                    System.out.println("YES, percolates.");
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        if(!flag){
            System.out.println("NO, doesn't percolate.");
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 1, 0},
                        {1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 1, 0}};
        int n = grid.length;
        Percolation percolation = new Percolation(n, grid);
        percolation.connectFreeCells();
        percolation.doesPercolate();
    }
}
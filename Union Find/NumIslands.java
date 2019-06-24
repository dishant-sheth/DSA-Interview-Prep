import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

/*

    [MEDIUM]
    Leetcode Link -> https://leetcode.com/problems/number-of-islands/

    Question -> Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
    An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
    You may assume all four edges of the grid are all surrounded by water.

*/

public class NumIslands{

    //Weighted Union Find -> O(logn) running times.
    private class UnionFind {
        
        //Number of disjoint sets
        private int totalDisjointSets;
        
        private int[] id;
        private int[] weights;
        
        //Initializing the ID and weights array.
        UnionFind(int n){
            totalDisjointSets = 0;
            id = new int[n];
            weights = new int[n];
            for(int i=0; i<n; i++){
                id[i] = i;
                weights[i] = 1;
            }
        }
        
        public void setDisjointSetCount(int n){
            totalDisjointSets = n;
        }
        
        //Get weight of object
        private int weight(int i){
            return weights[i];
        }
        
        //Get root of object
        private int root(int i){
            while(i != id[i]){
                i = id[i];
            }
            return i;
        }
        
        //Union of two objects A and B.
        public void union(int A, int B){
            int rootA = root(A);
            int rootB = root(B);
            if(rootA == rootB) return;
            //Reason for handling it here is that we need to avoid repeated union of components.
            totalDisjointSets -= 1;
            //Case 1: Same weight -> A becomes child of B
            if(weight(rootA) == weight(rootB)){
                id[rootA] = rootB;
                weights[rootB] += weights[rootA];
            }
            //Case 2: A is heavier than B -> B becomes child of A
            else if(weight(rootA) > weight(rootB)){
                id[rootB] = rootA;
                weights[rootA] += weights[rootB];
            }
            //Case 3: B is heavier than A -> A becomes child of B
            else{
                id[rootA] = rootB;
                weights[rootB] += weights[rootA];
            }
        }
        
    }
    
    private char[][] grid;
    
    //Helper function to get ID of a cell in the grid
    private int id(int row, int col){
        return ((row*grid[0].length) + col);
    }
    
    //Connect components along right and down
    private void connectObjects(UnionFind UF){
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                //If last cell, return
                if(i == n-1 && j == m-1) continue;
                //If last column, check only down
                else if(j == m-1){
                    if(grid[i][j] == '1' && grid[i+1][j] == '1'){
                        UF.union(id(i, j), id(i+1, j));
                    }
                }
                //If last row, check only right
                else if(i == n-1){
                    if(grid[i][j] == '1' && grid[i][j+1] == '1'){
                        UF.union(id(i, j), id(i, j+1));
                    }
                }
                //Check down and right
                else{
                    if(grid[i][j] == '1' && grid[i+1][j] == '1'){
                        UF.union(id(i, j), id(i+1, j));
                    }
                    if(grid[i][j] == '1' && grid[i][j+1] == '1'){
                        UF.union(id(i, j), id(i, j+1));
                    }
                }
            }
        }
    }
    
    public int solve(char[][] grid) {
        this.grid = grid;
        int ones = 0;
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == '1') ones += 1;
            }
        }
        UnionFind UF = new UnionFind(n*m);
        UF.setDisjointSetCount(ones);
        connectObjects(UF);
        return UF.totalDisjointSets;
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'},
                         {'1', '1', '0', '0', '0'},
                         {'0', '0', '1', '0', '0'},
                         {'0', '0', '0', '1', '1'}
                        };
        NumIslands numIslands = new NumIslands();
        System.out.print("Number of Islands -> " + numIslands.solve(grid));
    }
}
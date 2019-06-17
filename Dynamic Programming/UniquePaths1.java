import java.util.*;

/*

    Leetcode link - https://leetcode.com/problems/unique-paths/

    Question - A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
    The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
    How many possible unique paths are there?
    Note that m -> no of columns and n -> no. of rows in m*n

*/

class UniquePaths1 {
    public int uniquePaths(int cols, int rows) {
        //Dynamic Programming solution
        //1. Fill up the right-most column and bottom-most row.
        int[][] dp = new int[rows][cols];
        //Bottom-most row
        for(int i=0; i<cols; i++){
            dp[rows-1][i] = 1;
        }
        //Right-most column
        for(int i=0; i<rows; i++){
            dp[i][cols-1] = 1;
        }
        //The mid-part
        for(int i=rows-2; i>=0; i--){
            for(int j=cols-2; j>=0; j--){
                dp[i][j] = dp[i][j+1] + dp[i+1][j];
            }
        }
        return dp[0][0];
    }


    public static void main(String[] args) {
        UniquePaths1 UP1 = new UniquePaths1();
        System.out.println(UP1.uniquePaths(7, 3));
    }

}
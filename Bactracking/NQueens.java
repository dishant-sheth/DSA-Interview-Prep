import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class NQueens{

    //The Point class to make code understanding better and OO.
    private class Point{
        int row, col;
        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    /*
        N-Queens problem driver
    */
    private int total_possibilities = 0;
    public NQueens(int n){
        solveNQueens(0, n, new ArrayList<>());
        System.out.print("Total possible arrangements -> " + total_possibilities);
    }

    /*
        Find out if this position is safe
    */
    private boolean isSafe(Point currPoint, ArrayList<Point> selected){
        //If no queens have been selected so far, the entire board is free.
        if(selected.size() == 0) return true;
        int total_cols = selected.size();
        for(int i=0; i<total_cols; i++){
            Point p = selected.get(i);
            //If they are on the same row, current queen isn't safe.
            if(p.row == currPoint.row){
                return false;
            }
            // If it is diagonally opposite, current queen isn't safe
            if(Math.abs(p.row - currPoint.row) == Math.abs(p.col - currPoint.col)){
                return false;
            }
        }
        //Else, it's safe
        return true;
    }


    /*
        The actual solver function
    */
    private void solveNQueens(int col, int n, ArrayList<Point> selected){
        //BASE CASE: places a suitable fix in all columns
        if(col == n){
            total_possibilities += 1;
            printBoard(n, new ArrayList<>(selected));
        }
        //RECURSIVE CASE: there are columns for which a queen has to be placed
        else{
            //At each column, traverse through each of the rows attempting to find a valid solution
            for(int row=0; row<n; row++){
                Point currPoint = new Point(row, col);
                // If safe, go ahead with this choice.
                if(isSafe(currPoint, new ArrayList<>(selected))){
                    //Explore next column.
                    selected.add(currPoint);
                    solveNQueens(col+1, n, selected);
                    // Unchoosing
                    selected.remove(selected.size() - 1);
                }
            }
        }
    }

    /*
        The function to print the board
    */

    private void printBoard(int n, ArrayList<Point> selected){
        String[][] board = new String[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[i][j] = "";
            }
        }
        for(int i=0; i<selected.size(); i++){
            board[selected.get(i).row][selected.get(i).col] = "X";
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i][j].isEmpty()){
                    System.out.print("_   ");
                }
                else{
                    System.out.print("Q   ");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args){
        new NQueens(8);
    }

}
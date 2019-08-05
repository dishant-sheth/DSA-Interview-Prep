import java.util.*;
import java.io.*;

//Minimax algorithm in Tic Tac Toe.
public class TicTacToe{

    private int[][] grid;

    public TicTacToe(){
        //Initialize a 3*3 tic tac toe board.
        grid = new int[3][3];
    }

    /*
        Evaluates the terminal state score of the game.
        Let 'X' be the maximizer with a score of +10.
        Let 'O' be the minimizer with a score of -10.
        A game ending in a draw has a score of 0.
    */
    private int evalScore(){
        for(int i=0; i<3; i++){
            if(grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]){
                if(grid[i][0] == 'X') return 10;
                else if(grid[i][0] == 'O') return -10;
            }
        }
        //Check the columns
        for(int j=0; j<3; j++){
            if(grid[0][j] == grid[1][j] && grid[1][j] == grid[2][j]){
                if(grid[0][j] == 'X') return 10;
                else if(grid[0][j] == 'O') return -10;
            }
        }
        //Check the diagonals
        if(grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]){
            if(grid[0][0] == 'X') return 10;
            else if(grid[0][0] == 'O') return -10;
        }
        if(grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]){
            if(grid[0][2] == 'X') return 10;
            else if(grid[0][2] == 'O') return -10;
        }
        return 0;
    }

    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        System.out.println("Choose player -> \n1. X\n2. O");
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        char choice = scanner.next();
        if(choice == 'X'){

        }
    }
}
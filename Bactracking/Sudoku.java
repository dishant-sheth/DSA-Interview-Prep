import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Sudoku {
    public static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next(){
            while(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt(){
            return Integer.parseInt(next());
        }
    }
    
    class Point{
        int row, col;
        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    
    //Check if the same row contains this value.
    private boolean isRowSafe(int[][] grid, int row, int val){
        for(int j=0; j<9; j++){
            if(grid[row][j] == val) return false;
        }
        return true;
    }
    
    //Check if same column contains the value.
    private boolean isColSafe(int[][] grid, int col, int val){
        for(int i=0; i<9; i++){
            if(grid[i][col] == val) return false;
        }
        return true;
    }
    
    //Check if the 3*3 grid the cell belongs to already contains the value.
    private boolean isMiniGridSafe(int[][] grid, int row, int col, int val){
        int startRow = row - row%3;
        int startCol = col - col%3;
        for(int i=startRow; i<startRow+3; i++){
            for(int j=startCol; j<startCol+3; j++){
                if(grid[i][j] == val) return false;
            }
        }
        return true;
    }
    
    //Check if it is safe to use this value.
    private boolean isSafe(int[][] grid, int row, int col, int val){
        if(isRowSafe(grid, row, val) && isColSafe(grid, col, val) && isMiniGridSafe(grid, row, col, val)) return true;
        return false;
    }
    
    private Point currEmpty = new Point(0, -1);
    
    //Get the next empty vacant position
    private Point getVacantCell(int[][] grid){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(grid[i][j] == 0){
                    currEmpty.row = i;
                    currEmpty.col = j;
                    return currEmpty;
                }
            }
        }
        return new Point(9, 9);
    }
    
    private boolean solved = false;
    public void solveSudoku(int[][] grid){
        if(solved) return;
        Point emptyCell = getVacantCell(grid);
        int row = emptyCell.row;
        int col = emptyCell.col;
        //BASE CASE
        if(row == 9 && col == 9){
            for(int i=0; i<9; i++){
	            for(int j=0; j<9; j++){
	                System.out.print(grid[i][j] + " ");
                }
                System.out.println();
	        }
            solved = true;
            return;
        }
        //RECURSIVE CASE
        for(int val=1; val<=9; val++){
            if(isSafe(grid, row, col, val)){
                grid[row][col] = val;
                solveSudoku(grid);
                grid[row][col] = 0;
            }
        }
    }
    
	public static void main (String[] args){
	    FastReader fr = new FastReader();
	    int t = fr.nextInt();
	    Sudoku puzzle;
	    while(t-- > 0){
	        int[][] grid = new int[9][9];
	        for(int i=0; i<9; i++){
	            for(int j=0; j<9; j++){
	                grid[i][j] = fr.nextInt();
	            }
	        }
	        puzzle = new Sudoku();
	        puzzle.solveSudoku(grid);
	        System.out.println();
	    }
	 }
}
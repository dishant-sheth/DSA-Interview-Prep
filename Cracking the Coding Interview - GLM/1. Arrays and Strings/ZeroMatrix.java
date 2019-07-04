import java.util.*;

public class ZeroMatrix{

    /*
        APPROACH 1 -> Use a HashSet to store the row & col indexes where the matrix elememt is zero.
    */
    public void solve1(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        HashSet<Integer> rows = new HashSet<>();
        HashSet<Integer> cols = new HashSet<>();
        int n = matrix.length; //Number of rows in input matrix
        int m = matrix[0].length; //Number of cols in input matrix
        // Store indexes where element in matrix is zero
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(matrix[i][j] == 0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // Set rows in HashSet to zero
        for(Integer row: rows){
            for(int j=0; j<m; j++){
                matrix[row][j] = 0;
            }
        }

        //Set cols in HashSet to zero
        for(Integer col: cols){
            for(int i=0; i<n; i++){
                matrix[i][col] = 0;
            }
        }

    }

    /*
        [DOESN'T WORK YET.]
        APPROACH 2 -> Use the first row and first col as flag for the rest of the matrix.
    */
    public void solve2(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int n = matrix.length; //Number of rows in input matrix
        int m = matrix[0].length; //Number of cols in input matrix
        //Since, the element at (0,0) is common for both first row and first col, we'll use it for the first row and use a boolean flag for the first col.
        boolean firstCol = false;
        //Check first col for zeros
        for(int i=0; i<n; i++){
            if(matrix[i][0] == 0){
                firstCol = true;
                break;
            }
        }
        //Check first row for zeros
        for(int j=0; j<m; j++){
            if(matrix[0][j] == 0){
                matrix[0][0] = 0;
                break;
            }
        }

        //Check the remaining matrix
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //Mark rows and cols as zeros
        for(int i=1; i<n; i++){
            if(matrix[i][0] == 0){
                for(int j=1; j<m; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        for(int j=1; j<m; j++){
            if(matrix[0][j] == 0){
                for(int i=1; i<n; i++){
                    matrix[i][j] = 0;
                }
            }
        }       
        
        if(firstCol){
            for(int i=0; i<n; i++){
                matrix[i][0] = 0;
            }
        }


    }

    public static void main(String[] args) {
        ZeroMatrix zeroMatrix = new ZeroMatrix();
        int[][] matrix = {{1,2,3,4}, {5,0,0,8}, {9,10,11,12}};
        zeroMatrix.solve1(matrix);
        int n = matrix.length; //Number of rows in input matrix
        int m = matrix[0].length; //Number of cols in input matrix
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        zeroMatrix.solve2(matrix);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
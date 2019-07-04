import java.util.*;

/*
    Given an image 
*/

public class RotateMatrix{

    //In-place rotation of a matrix
    public int[][] solve(int[][] matrix){
        int n = matrix.length;
        int top;
        //Divide the square matrix into layers and perform shift operations on them.
        for(int i=0; i<n/2; i++){
            //Setting column limits
            int start = i;
            int end = n-i-1;
            for(int j=start; j<end; j++){
                //Top element 
                top = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = top;
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4},
                          {5, 6, 7, 8},
                          {9, 10, 11, 12},
                          {13, 14, 15, 16}};

        RotateMatrix rotateMatrix = new RotateMatrix();
        matrix = rotateMatrix.solve(matrix);
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
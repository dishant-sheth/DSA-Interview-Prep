import java.util.*;

/*
    [HARD] [DONOT REFER THIS SOLUTION. CODE DOESN'T WORK YET.]
    Leetcode link - 

*/

public class UniquePaths3 {
    
    private class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    private int[][] grid;
    //Since we can walk over every obstacle only once, maintain a record.
    private boolean[][] path; 
    private int rows, cols, paths;

    private int id(int x, int y){
        return (x*cols)+y;
    }
    
    public int solve(int[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.path = new boolean[rows][cols];
        this.grid = grid;
        this.paths = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                //Find starting point
                if(grid[i][j] == 1){
                    findPaths(i, j, new ArrayList<>(), new HashSet<>());
                    break;
                }
            }
        }
        return paths;
    }
        
    private void findPaths(int x, int y, ArrayList<Point> choices, HashSet<Integer> visited){
        //Check boundary
        if(x < 0 || x >= rows) return;
        else if(y < 0 || y >= cols) return;
        //Check obstacles
        else if(grid[x][y] == -1) return;
        //Check if already been here to avoid infinite loop.
        else if(visited.contains(id(x, y))) return;
        //Check if path along these choices already exists
        else if(path[x][y]) return;
        //Check if exit node
        else if(grid[x][y] == 2){
            for(Point p: choices){
                path[p.x][p.y] = true;
                System.out.print("(" + p.x + ", " + p.y + ")  ->  ");
            }
            System.out.println();
            paths += 1;
        }
        else{
            visited.add(id(x, y));
            choices.add(new Point(x, y));
            findPaths(x, y+1, choices, visited);
            findPaths(x+1, y, choices, visited);
            findPaths(x, y-1, choices, visited);
            findPaths(x-1, y, choices, visited);
            choices.remove(choices.size() - 1);
            visited.remove(id(x, y));
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        UniquePaths3 uniquePaths3 = new UniquePaths3();
        System.out.println(uniquePaths3.solve(grid));
    }
    
}
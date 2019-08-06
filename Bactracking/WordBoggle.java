import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashSet;

/*
    Great Question.
    Check it out on -> https://practice.geeksforgeeks.org/problems/word-boggle
*/

class WordBoggle {
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
    
    private boolean[][] visited;
    
    private boolean findWord(char[][] board, int x, int y, int n, int m, int index, String word){
        //Boundary cases:
        boolean found = false;
        if(x<0 || x>=n || y<0 || y >=m) return false;
        else if(visited[x][y]) return false;
        else if(board[x][y] != word.charAt(index)) return false;
        //Base case:
        else if(index == word.length()-1) return true;
        //Recursive case:
        else{
            visited[x][y] = true;
            found = found || findWord(board, x-1, y, n, m, index+1, word);
            found = found || findWord(board, x+1, y, n, m, index+1, word);
            found = found || findWord(board, x, y-1, n, m, index+1, word);
            found = found || findWord(board, x, y+1, n, m, index+1, word);
            found = found || findWord(board, x-1, y-1, n, m, index+1, word);
            found = found || findWord(board, x+1, y-1, n, m, index+1, word);
            found = found || findWord(board, x-1, y+1, n, m, index+1, word);
            found = found || findWord(board, x+1, y+1, n, m, index+1, word);
            visited[x][y] = false;
        }
        return found;
    }
    
    
    private boolean isPresent(String word, char[][] board, int n, int m){
        boolean result = false;
        visited = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] == word.charAt(0)){
                    result = findWord(board, i, j, n, m, 0, word) ? true : false;
                    if(result) return result;
                }
            }
        }
        return result;
    }
    
	public static void main (String[] args){
	    FastReader fr = new FastReader();
	    int t = fr.nextInt();
	    int x, n, m;
	    WordBoggle wBoggle;
	    StringBuffer result = new StringBuffer();
	    while(t-- > 0){
	        wBoggle = new WordBoggle();
	        x = fr.nextInt();
	        HashSet<String> set = new HashSet<>();
	        int index = 0;
	        for(int i=0; i<x; i++){
	            String temp = fr.next();
	            if(set.contains(temp)) continue;
	            else{
	                set.add(temp);
	            }
	        }
	        String[] dict = new String[set.size()];
	        for(String s: set){
	            dict[index++] = s;
	        }
	        Arrays.sort(dict);
	        n = fr.nextInt();
	        m = fr.nextInt();
	        char[][] board = new char[n][m];
	        for(int i=0; i<n; i++){
	            for(int j=0; j<m; j++){
	                board[i][j] = fr.next().charAt(0);
	            }
	        }
	        boolean containsWords = false;
	        for(int i=0; i<set.size(); i++){
	            if(wBoggle.isPresent(dict[i], board, n, m)){
	                 result.append(dict[i] + " ");
	                 containsWords = true;
	            }
	        }
	        result.append((containsWords) ? "\n" : "-1\n");
	    }
	    System.out.print(result);
	}
}
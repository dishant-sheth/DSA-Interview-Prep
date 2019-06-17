import java.util.*;

/*
    Leetcode link - https://leetcode.com/problems/generate-parentheses/
    Question - Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
    Example - for n = 3, solution is    

                    [
                      "((()))",
                      "(()())",
                      "(())()",
                      "()(())",
                      "()()()"
                    ]

*/

class GenerateParantheses{
    
    List<String> result;
    
    public void solve(int n) {
        result = new ArrayList<>();
        helper(n, n, n, new StringBuilder(""));
    }
    
    private void helper(int n, int leftCount, int rightCount, StringBuilder choices){
        //BASE CASE: length of choices equals n*2
        if(choices.length() == n*2){
            result.add(choices.toString());
        }
        //RECURSIVE CASE: Add "(" or ")"
        else{
            if(leftCount != 0){
                choices = choices.insert(choices.length(), "(");
                helper(n, leftCount-1, rightCount, choices);
                choices = choices.deleteCharAt(choices.length()-1);
            }
            if(rightCount != 0){
                if(leftCount < rightCount){
                    choices = choices.insert(choices.length(), ")");
                    helper(n, leftCount, rightCount-1, choices);
                    choices = choices.deleteCharAt(choices.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        GenerateParantheses generateParantheses = new GenerateParantheses();
        generateParantheses.solve(3);
        for(String s : generateParantheses.result){
            System.out.println(s);
        }
    }
}
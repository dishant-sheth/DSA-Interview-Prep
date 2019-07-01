import java.util.*;

/*
    [MEDIUM]
    Leetcode Link -> https://leetcode.com/problems/find-largest-value-in-each-tree-row/
    (Above Leetcode problem uses BTs. For easier tree creation, I've used BSTs here. Logic remains the same.)

    You need to find the largest value in each row of a binary tree.

    Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

    Output: [1, 3, 9]

*/

public class BTRowMax{

    //Node in BT
    private class Node{

        private int data;
        private Node left, right;

        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //Root of the tree.
    private Node root = null;

    //Insert a node in the BST.
    private Node insert(Node root, int data){
        if(root == null){
            root = new Node(data);
        }
        else if(data <= root.data){
            root.left = insert(root.left, data);
        }
        else{
            root.right = insert(root.right, data);
        }
        return root;
    }

    /*
        There are two approaches to solve this problem.
        1. BFS -> Level order traversal
        2. DFS -> maintain current level while traversal.
    */

    private ArrayList<Integer> dfs(ArrayList<Integer> result, Node root, int level){
        //Check if node is valid
        if(root == null) return new ArrayList<>();
        //Check if visiting this level for the first time.
        else if(level == result.size()){
            result.add(root.data);
        }
        //Check if this is the max value at this level
        else{
            int currMax = result.get(level);
            if(root.data > currMax){
                result.set(level, root.data);
            }
        }
        dfs(result, root.left, level+1);
        dfs(result, root.right, level+1);
        return result;
    }


    public static void main(String[] args) {
        BTRowMax BT = new BTRowMax();
        BT.root = BT.insert(BT.root, 5);
        BT.root = BT.insert(BT.root, 3);
        BT.root = BT.insert(BT.root, 7);
        BT.root = BT.insert(BT.root, 2);
        BT.root = BT.insert(BT.root, 4);
        BT.root = BT.insert(BT.root, 6);
        BT.root = BT.insert(BT.root, 9);

        ArrayList<Integer> result = BT.dfs(new ArrayList<>(), BT.root, 0);
        for(Integer i : result){
            System.out.println(i + "");
        }
    }

}
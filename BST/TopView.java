import java.util.*;
import java.lang.Math;

    /*
        Print the top view of a Binary Tree. 
        Question link -> https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1

        Given below is a binary tree. The task is to print the top view of binary tree. Top view of a binary tree is the set of nodes visible when the tree is viewed from the top. For the given below tree

               1
            /     \
           2       3
          / \    /  \
        4    5  6    7

        Top view will be: 1 2 3 4 7
    */


public class TopView{

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


    public ArrayList<Integer> bfs(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> nodeQueue = new LinkedList<>();
        Queue<Integer> vRankQueue = new LinkedList<>();
        HashSet<Integer> vRankVisited = new HashSet<>();
        nodeQueue.add(root);
        vRankQueue.add(0);
        while(nodeQueue.size() > 0){
            Node curr = nodeQueue.poll();
            int currRank = vRankQueue.poll();
            if(!vRankVisited.contains(currRank)){
                vRankVisited.add(currRank);
                result.add(curr.data);
            }
            if(curr.left != null){
                nodeQueue.add(curr.left);
                vRankQueue.add(currRank - 1);
            }
            if(curr.right != null){
                nodeQueue.add(curr.right);
                vRankQueue.add(currRank + 1);
            }
        }
        return result;
    }  

    public static void main(String[] args) {
        TopView TV = new TopView();
        TV.root = TV.insert(TV.root, 5);
        TV.root = TV.insert(TV.root, 3);
        TV.root = TV.insert(TV.root, 7);
        TV.root = TV.insert(TV.root, 2);
        TV.root = TV.insert(TV.root, 4);
        TV.root = TV.insert(TV.root, 6);
        TV.root = TV.insert(TV.root, 9);

        ArrayList<Integer> result = TV.bfs(TV.root);
        for(Integer i : result){
            System.out.print(i + " ");
        }
    }
}
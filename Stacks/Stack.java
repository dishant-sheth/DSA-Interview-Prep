import java.util.Scanner;
import java.lang.Math;
import java.lang.Object;

//Basic implementation of a Stack

//Creating an interface to build a generic stack implementation

interface StackInterface<T>{
    /*
        Three essential functions - 
            1. Push
            2. Pop
            3. Peek
    */

    public boolean push(T value);
    public T pop();
    public T peek();
    public int size();
    public void clear();
}

public class Stack<T> implements StackInterface<T>{

    //We'll use the doubling mechanism to expand the Stack memory.
    private int FIXED_SIZE = 10;
    private int CURR_SIZE = 0; 
    private int stackTop = -1;

    private T[] stack;

    public Stack(){
        stack = (T[])new Object[FIXED_SIZE];
    }

    //Util functions

    private boolean isEmpty(){
        return (stackTop == -1);
    }

    private void ensureCapacity(){
        if(CURR_SIZE == FIXED_SIZE){
            FIXED_SIZE *= 2;
            T[] newStack = (T[]) new Object[FIXED_SIZE];
            for(int i=0; i<CURR_SIZE; i++){
                newStack[i] = stack[i];
            }
            stack = newStack;
        }
    }

    @Override
    public boolean push(T value) {
        ensureCapacity();
        stack[++stackTop] = value;
        CURR_SIZE += 1;
        return true;
    }

    @Override
    public T pop() {
        if(isEmpty()) return null;
        CURR_SIZE -= 1;
        return stack[stackTop--];
    }

    @Override
    public T peek() {
        if(isEmpty()) return null;
        return stack[stackTop];
    }

    @Override
    public int size() {
        return CURR_SIZE;
    }

    @Override
    public void clear(){
        FIXED_SIZE = 10;
        CURR_SIZE = 0;
        stackTop = -1;
        stack = (T[])new Object[FIXED_SIZE];
    }

    private void printStack(){
        System.out.println("----------------");
        System.out.println("STACK DETAILS: ");
        System.out.println("CAPACITY -> " + FIXED_SIZE);
        System.out.println("USED -> " + CURR_SIZE);
        System.out.println("----------------");
        System.out.println();
        for(int i=stackTop; i>=0; i--){
            System.out.println(" | " + stack[i] + " | ");
        }
    }

    public static void main(String[] args) {
        Stack<Integer> myStack = new Stack<>();
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(4);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(4);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(4);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(4);
        myStack.push(4);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(4);
        myStack.push(1);
        myStack.push(1);
        myStack.push(1);
        myStack.push(4);
        myStack.printStack();
        myStack.clear();
        myStack.printStack();
    }
}
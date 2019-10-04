import java.util.*;
import java.lang.Math;

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
}

public class Stack<T> implements StackInterface<T>{

    //We'll use the doubling mechanism to expand the Stack memory.
    private int FIXED_SIZE = 10;
    private int CURR_SIZE = 0; 
    private int stackTop = -1;

    //Util functions

    private boolean isEmpty(){
        return (stackTop == -1);
    }

    private void ensureCapacity(){
        if(CURR_SIZE == FIXED_SIZE){
                Arrays.copyOf(original, newLength);
        }
    }

    @Override
    public boolean push(T value) {

        return false;
    }

    @Override
    public T pop() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int size() {
        return CURR_SIZE;
    }

    public static void main(String[] args) {
        
    }
}
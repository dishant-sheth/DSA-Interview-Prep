import java.lang.Object;

interface QueueInterface<T>{

    /*
        Queue functions ->
        1. Enqueue
        2. Dequeue
        3. Get head element
        4. Is Empty
        5. Clear Queue
        6. Get Size
    */

    public boolean enqueue(T value);
    public T dequeue();
    public T getHead();
    public boolean isEmpty();
    public void clear();
    public int size();
}

/*
    Queue logic ->
        1. Insertion happens at the rear end.
        2. Deletion from the front.
        3. FIFO principle.
*/

public class Queue<T> implements QueueInterface<T>{

    private int front = -1, rear = -1;
    private int CAPACITY = 1, CURR_SIZE = 0;

    private T[] queue;

    public Queue(){
        queue = (T[]) new Object[CAPACITY];
    }

    private void ensureCapacity(){
        if(CURR_SIZE == CAPACITY){
            CAPACITY *= 2;
            T[] newQueue = (T[]) new Object[CAPACITY];
            for(int i=0; i<CURR_SIZE; i++){
                newQueue[i] = queue[i];
            }
            queue = newQueue;
        }
        if(CURR_SIZE < CAPACITY/2){
            CAPACITY /= 2;
            T[] newQueue = (T[]) new Object[CAPACITY];
            for(int i=0; i<CURR_SIZE; i++){
                newQueue[i] = queue[i];
            }
            queue = newQueue;
        }
    }

    @Override
    public boolean enqueue(T value){
        ensureCapacity();
        if(front == -1 && rear == -1) ++front;
        queue[++rear] = value;
        CURR_SIZE += 1;
        return true;
    }

    @Override
    public T dequeue() {
        ensureCapacity();
        if(isEmpty()) return null;
        else if(front == rear){
            T retVal = queue[front];
            front = -1;
            rear = -1;
            CURR_SIZE -= 1;
            return retVal;
        }
        else{
            CURR_SIZE -= 1;
            return queue[front++];
        }
        
    }

    @Override
    public T getHead() {
        if(isEmpty()) return null;
        return queue[front];
    }

    @Override
    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    @Override
    public void clear() {
        CAPACITY = 10;
        queue = (T[]) new Object[CAPACITY];
        CURR_SIZE = 0;
        front = -1;
        rear = -1;
    }

    @Override
    public int size() {
        return CURR_SIZE;
    }

    private void printQueue(){
        System.out.println("-----------------");
        System.out.println("CAPACITY -> " + CAPACITY);
        System.out.println("USAGE -> " + CURR_SIZE);
        System.out.println("-----------------");
        for(int i=CURR_SIZE - 1; i>=0; i--){
            System.out.print("| " + queue[i] + " |");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.printQueue();
        queue.enqueue(1);
        queue.printQueue();
        queue.enqueue(2);
        queue.printQueue();
        queue.enqueue(3);
        queue.printQueue();
        queue.enqueue(4);
        queue.printQueue();
        queue.enqueue(5);
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
        queue.dequeue();
        queue.printQueue();
    }
}
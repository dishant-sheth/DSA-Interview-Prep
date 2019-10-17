import java.lang.Object;

interface SLLInterface<T>{
    /*
        Some important functions -> 
            1. Insert
                a. At the beginning.
                b. At the end.
            2. Delete
                a. Head
                b. Index based.
                c. Element based.
            3. Clear
    */

    public void append(T value);
    public void prepend(T value);
    public void deleteHead();
    //Assuming that index starts at 0
    public void deleteByIndex(int index);
    public void deleteByElement(T value);
    public void clear();
}

public class SinglyLinkedList<T> implements SLLInterface<T>{

    private class Node<T>{
        T value;
        Node next;
        Node(T value){
            this.value = value;
            this.next = null;
        }
    }

    Node<T> head = null;

    @Override
    public void append(T value) {
        if(head == null){
            head = new Node<>(value);
            return;
        }
        Node<T> current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = new Node<>(value);
    }

    @Override
    public void prepend(T value) {
        if(head == null){
            head = new Node<>(value);
            return;
        }
        Node<T> current = new Node<>(value);
        current.next = head;
        head = current;
    }

    @Override
    public void deleteHead() {
        if(head == null) return;
        head = head.next;
    }

    @Override
    public void deleteByElement(T value) {
        if(head == null) return;
        if(head.value == value){
            head = head.next;
            return;
        }
        Node<T> current = head;
        while(current.next != null){
            if(current.next.value == value){
                current.next = current.next.next;
                break;
            }
            current = current.next;
        }
    }

    @Override
    public void deleteByIndex(int index) {
        if(head == null) return;
        if(index == 0){
            head = head.next;
            return;
        }
        int i = 0;
        Node<T> current = head;
        while(current.next != null){
            if(i+1 == index){
                current.next = current.next.next;
                break;
            }
            current = current.next;
            i+=1;
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    private void printSLL(){
        Node<T> current = head;
        while(current.next != null){
            System.out.print(current.value + " -> ");
            current = current.next;
        }
        System.out.print(current.value + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> sll = new SinglyLinkedList<>();
        sll.append(1);
        sll.append(2);
        sll.append(3);
        sll.append(4);
        sll.append(5);
        sll.printSLL();
        sll.deleteHead();
        sll.printSLL();
        sll.deleteByElement(3);
        sll.printSLL();
        sll.deleteByIndex(2);
        sll.printSLL();
    }

}
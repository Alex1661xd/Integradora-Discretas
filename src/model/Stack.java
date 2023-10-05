package model;

public class Stack<T extends Comparable<T>> {
    private Node<T> top;

    public Stack() {
        top = null;
    }

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        if (top == null) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top = newNode;
        }
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return top.getData();
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public Node<T> getTop() {
        return top;
    }

    public void setTop(Node<T> top) {
        this.top = top;
    }

    public String printStack() {
        Node<T> current = top;
        StringBuilder msg = new StringBuilder("Stack:\n");
        while (current != null) {

            msg+=((Task)current.data).getDescriptionUseRealized() + "\n";
            current = current.next;
 
        }
        return msg.toString();
    }
}

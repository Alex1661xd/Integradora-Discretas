package model;

public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;

    public Queue() {
        front = null;
        rear = null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null; // Si el último elemento se ha eliminado, actualiza 'rear'
        }
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return front.data;
    }

    public T rear() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return rear.data;
    }
    
    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public String printQueue() {
        Node<T> current = front;
        String msg="";
        while (current != null) {
            msg+=current.data + "\n";
            current = current.next;
        }
        msg+="\n"; //Imprime una línea en blanco al final para una mejor presentación.
        return msg; 
    }

    public Node<T> getFront() {
        return front;
    }

    public void setFront(Node<T> front) {
        this.front = front;
    }

    public Node<T> getRear() {
        return rear;
    }

    public void setRear(Node<T> rear) {
        this.rear = rear;
    }

    
    
}

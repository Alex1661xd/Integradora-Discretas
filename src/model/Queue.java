package model;

public class Queue<T extends Comparable<T>> {
    private Node<T> front;
    private Node<T> rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        T data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null; // Si el último elemento se ha eliminado, actualiza 'rear'
        }
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return front.getData();
    }

    public T rear() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return rear.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = front;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public String printQueue() {
        Node<T> current = front;
        StringBuilder msg = new StringBuilder();
        while (current != null) {
            msg.append(current.getData()).append("\n");
            current = current.getNext();
        }
        msg.append("\n"); // Imprime una línea en blanco al final para una mejor presentación.
        return msg.toString();
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

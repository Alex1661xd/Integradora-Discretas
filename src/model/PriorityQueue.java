package model;

public class PriorityQueue<T extends Comparable<T>> {
    private Node<T> head;

    public PriorityQueue() {
        head = null;
    }

    public void enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty() || item.compareTo(head.getData()) <= 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null && item.compareTo(current.getNext().getData()) > 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía.");
        }
        T data = head.getData();
        head = head.getNext();
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía.");
        }
        return head.getData();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}




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

    /**
    * Adds an element to the end of the queue.
    *
    * @param item The item to add to the queue.
    */
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

    /**
    * Delete and return the element to the front of the queue.
    *
    * @return The element at the front of the queue.
    * @throws IllegalStateException If the queue is empty.
    */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null; // If the last element has been removed, update 'rear'
        }
        return data;
    }

    /**
    * Gets the element at the front of the queue without deleting it.
    *
    * @return The element at the front of the queue.
    * @throws IllegalStateException If the queue is empty.
    */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return front.data;
    }

    /**
    * Gets the element at the back of the queue without deleting it.
    *
    * @return The element at the back of the queue.
    * @throws IllegalStateException If the queue is empty.
    */
    public T rear() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return rear.data;
    }
    
    /**
    * Check if the queue is empty.
    *
    * @return true if the queue is empty, false otherwise.
    */
    public boolean isEmpty() {
        return front == null;
    }

    /**
    * Gets the number of elements in the queue.
    *
    * @return The number of elements in the queue.
    */
    public int size() {
        int count = 0;
        Node<T> current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    /**
    * Gets a string representation of the queue and its elements.
    *
    * @return A string representing the information of the queue and its elements.
    */
    public String printQueue() {
        Node<T> current = front;
        String msg="";
        while (current != null) {
            msg+=current.data + "\n";
            current = current.next;
        }
        msg+="\n"; //Print a blank line at the end for better presentation.
        return msg; 
    }  
    
}

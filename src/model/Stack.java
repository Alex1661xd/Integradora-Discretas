package model;

public class Stack<T> {
    private Node<T> top;

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node<T> getTop() {
        return top;
    }
    public void setTop(Node<T> top) {
        this.top = top;
    }
    
    /**
    * Adds an element to the top of the stack.
    *
    * @param item The item to add to the stack.
    */
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    /**
    * Remove and return the element at the top of the stack.
    *
    * @return The element at the top of the stack.
    * @throws IllegalStateException If the stack is empty.
    */
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("El usuario no ha realizado acciones");
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    /**
    * Gets the element on top of the stack without removing it.
    *
    * @return The element at the top of the stack.
    * @throws IllegalStateException If the stack is empty.
    */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return top.data;
    }

    /**
    * Check if the stack is empty.
    *
    * @return true if the stack is empty, false otherwise.
    */
    public boolean isEmpty() {
        return top == null;
    }

    /**
    * Gets the number of elements in the stack.
    *
    * @return The number of elements on the stack.
    */
    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
 

    /**
    * Gets a string representation of the stack and its elements.
    *
    * @return A string representing the stack information and its elements.
    */
    public String printStack() {
        Node<T> current = top;
        String msg="    Stack: ";
        while (current != null) {
            msg+=((Task)current.data).getDescriptionUseRealized() + "\n";
            current = current.next;
        }
        return msg;// Imprime una línea en blanco al final para una mejor presentación.
    }
    

    
}
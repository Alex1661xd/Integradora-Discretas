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
    //Añade un elemento a la cima

    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }
    //Elimina el elemento de la cima y el valor del elemto
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("El usuario no ha realizado acciones");
        }
        T data = top.data;
        top = top.next;
        return data;
    }
    //Muestra el elemento de la cima

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    public Node<T> getTop() {
        return top;
    }
    public void setTop(Node<T> top) {
        this.top = top;
    }

    
}

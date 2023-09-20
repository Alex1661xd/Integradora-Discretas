package model;

public class DoubleLinkedList {

    private Node head; // Primer nodo de la lista
    private Node tail; // Último nodo de la lista

    // Constructor para inicializar la lista vacía
    public DoubleLinkedList() {
        head = null;
        tail = null;
    }

    // Método para verificar si la lista está vacía
    public boolean isEmpty() {
        return head == null;
    }

    // Método para agregar un nodo al final de la lista
    public void addAppend(Task task) {
        Node newNode = new Node(task);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrevious(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    // Método para agregar un nodo al principio de la lista
    public void AddPrepend(Task task) {
        Node newNode = new Node(task);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
    }

    // Método para eliminar un nodo de la lista
    public void delete(Task task) {
        Node current = head;
        while (current != null) {
            if (current.getTask().equals(task)) {
                if (current == head) {
                    head = current.getNext();
                    if (head != null) {
                        head.setPrevious(null);
                    }
                } else if (current == tail) {
                    tail = current.getPrevious();
                    tail.setNext(null);
                } else {
                    Node previousNode = current.getPrevious();
                    Node nextNode = current.getNext();
                    previousNode.setNext(nextNode);
                    nextNode.setPrevious(previousNode);
                }
                return;
            }
            current = current.getNext();
        }
    }

    // Método para imprimir la lista en orden desde el primer nodo hasta el último
    public String printForward() {
        Node current = head;
        String msg=null;
        while (current != null) {
            msg+=current.getTask() + " ";
            current = current.getNext();
        }
        return msg;
    }

    // Método para imprimir la lista en orden inverso, desde el último nodo hasta el primero
    public String printBackward() {
        Node current = tail;
        String msg=null;
        while (current != null) {
            msg+=current.getTask() + " ";
            current = current.getPrevious();
        }
        return msg;
    }
}

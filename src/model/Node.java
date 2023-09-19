package model;

public class Node {

    private Task task;

    private Node next;

    private Node previous;

    public Node(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setPerson(Task task) {
        this.task = task;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}

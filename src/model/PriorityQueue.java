package model;
import java.util.Arrays;

public class PriorityQueue{
    private Task[] heap;
    private int size;

    public PriorityQueue(int capacity) {
        heap = new Task[capacity];
        size = 0;
    }

    /**
    * Insert a new task into the priority queue.
    *
    * @param value The task to add to the priority queue.
    */
    public void insert(Task value) {
        if (size == heap.length) {
            // If the array is full, we enlarge the array
            ensureCapacity();
        }

        // Add the new element to the end of the array
        heap[size] = value;
        size++;

        // Reorganize the array to maintain priority queue ownership
        bubbleUp();
    }

    /**
    * Delete and return the task with the highest priority in the priority queue.
    *
    * @return The task with the highest priority in the priority queue.
    * @throws IllegalStateException If the priority queue is empty.
    */
    public Task remove() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }

        Task root = heap[0];

        // Move the last element to the beginning of the array
        heap[0] = heap[size - 1];
        size--;

        // Reorganize the array to maintain priority queue ownership
        bubbleDown();

        return root;
    }

    /**
    * Checks if the priority queue is empty.
    *
    * @return true if the priority queue is empty, false otherwise.
    */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
    * Increases the capacity of the array if it is full.
    */
    private void ensureCapacity() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    /**
    * Scroll up the array to restore the priority queue property.
    */
    private void bubbleUp() {
        int index = size - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].compareTo(heap[parentIndex].getDate())==-1) {
                // Swap the element with its parent if it is smaller
                Task temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                index = parentIndex;
            } else {
                break; // Priority queue ownership is maintained
            }
        }
    }

    /**
    * Scroll down the array to restore the priority queue property.
    */
    private void bubbleDown() {
        int index = 0;
        while (true) {
            int leftChildIdx = 2 * index + 1;
            int rightChildIdx = 2 * index + 2;
            int smallest = index;

            if (leftChildIdx < size && heap[leftChildIdx].compareTo(heap[smallest].getDate())==-1) {
                smallest = leftChildIdx;
            }

            if (rightChildIdx < size && heap[rightChildIdx].compareTo(heap[smallest].getDate())==-1) {
                smallest = rightChildIdx;
            }

            if (smallest != index) {
                // Exchange with the youngest child
                Task temp = heap[index];
                heap[index] = heap[smallest];
                heap[smallest] = temp;
                index = smallest;
            } else {
                break; // Priority queue ownership is maintained
            }
        }
    }

    /**
    * Gets a string representation of the priority queue and its elements.
    *
    * @return A string representing the priority queue information and its elements.
    */
    public String showPriorityQueue(){
        String msg="";
        while (!isEmpty()) {
            Task tarea = remove();
            msg+="\nTarea: " + tarea.getTitle() + "\nFecha: " + tarea.getDate()+"\n";
        }
        return msg;
    }

    
}

package model;
import java.util.Arrays;

public class PriorityQueue{
    private Task[] heap;
    private int size;

    public PriorityQueue(int capacity) {
        heap = new Task[capacity];
        size = 0;
    }

    public void insert(Task value) {
        if (size == heap.length) {
            // Si el arreglo está lleno, agrandamos el arreglo
            ensureCapacity();
        }

        // Agregar el nuevo elemento al final del arreglo
        heap[size] = value;
        size++;

        // Reorganizar el arreglo para mantener la propiedad de la cola de prioridad
        bubbleUp();
    }

    public Task remove() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }

        Task root = heap[0];

        // Mover el último elemento al inicio del arreglo
        heap[0] = heap[size - 1];
        size--;

        // Reorganizar el arreglo para mantener la propiedad de la cola de prioridad
        bubbleDown();

        return root;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        heap = Arrays.copyOf(heap, heap.length * 2);
    }

    private void bubbleUp() {
        int index = size - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[index].compareTo(heap[parentIndex].getDate())==-1) {
                // Intercambiar el elemento con su padre si es menor
                Task temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                index = parentIndex;
            } else {
                break; // La propiedad de la cola de prioridad se mantiene
            }
        }
    }

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
                // Intercambiar con el hijo más pequeño
                Task temp = heap[index];
                heap[index] = heap[smallest];
                heap[smallest] = temp;
                index = smallest;
            } else {
                break; // La propiedad de la cola de prioridad se mantiene
            }
        }
    }

    public String showPriorityQueue(){
        String msg="";
        while (!isEmpty()) {
            Task tarea = remove();
            msg+="\nTarea: " + tarea.getTitle() + "\nFecha: " + tarea.getDate()+"\n";
        }
        return msg;
    }

    
}

package UnitTesting;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import model.PriorityQueue;
import model.Task;
import model.TypePriority;
import java.time.LocalDate;


public class PriorityTest extends TestCase {

    private PriorityQueue priorityQueue;

    @Before
    public void setUp() {
        priorityQueue = new PriorityQueue(10); 
    }

    @Test
    public void testInsertAndRemove() {
        LocalDate date1 = LocalDate.of(2023, 10, 15); 
        LocalDate date2 = LocalDate.of(2023, 10, 16);

        Task task1 = new Task("Pastillas", "Tomar", date1, TypePriority.PRIORITY, "A123F");
        Task task2 = new Task("Despertar", "Reunión", date2, TypePriority.PRIORITY, "A123F");

        priorityQueue.insert(task1);
        assertFalse(priorityQueue.isEmpty());

        priorityQueue.insert(task2);
        assertFalse(priorityQueue.isEmpty());

        Task removedTask = priorityQueue.remove();
        assertEquals(task1, removedTask);

        removedTask = priorityQueue.remove();
        assertEquals(task2, removedTask);

        assertTrue(priorityQueue.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveOnEmptyPriorityQueue() {
        assertTrue(priorityQueue.isEmpty()); 
        try {
            priorityQueue.remove();
            fail("No fallo, ocurrio un error con la prueba"); 
        } catch (Exception e) {
            System.out.println("No se puede remover, esta vacia");
        }
        
    }
    

    @Test
    public void testShowPriorityQueue() {
        LocalDate date1 = LocalDate.of(2023, 10, 15); 
        LocalDate date2 = LocalDate.of(2023, 10, 16); 

        Task task1 = new Task("Pastillas", "Tomar", date1, TypePriority.PRIORITY, "A123F");
        Task task2 = new Task("Despertar", "Reunión", date2, TypePriority.PRIORITY, "A123F");

        priorityQueue.insert(task1);
        priorityQueue.insert(task2);

        String result = priorityQueue.showPriorityQueue();
        assertTrue(result.contains("Pastillas"));
        assertTrue(result.contains("Despertar"));
    }

    @Test
    public void testInsertAndRemoveWithDifferentPriorities() {
        LocalDate date1 = LocalDate.of(2023, 10, 15); 
        LocalDate date2 = LocalDate.of(2023, 10, 16); 

        Task highPriorityTask = new Task("Urgente", "Tarea urgente", date1, TypePriority.PRIORITY, "A123F");
        Task lowPriorityTask = new Task("Normal", "Tarea normal", date2, TypePriority.PRIORITY, "A123F");

        priorityQueue.insert(lowPriorityTask);
        priorityQueue.insert(highPriorityTask);

        Task removedTask = priorityQueue.remove();
        assertEquals(highPriorityTask, removedTask);

        removedTask = priorityQueue.remove();
        assertEquals(lowPriorityTask, removedTask);
    }

    @Test
    public void testInsertAndRemoveWithSamePriority() {
        LocalDate date1 = LocalDate.of(2023, 10, 15); 
        LocalDate date2 = LocalDate.of(2023, 10, 15); 

        Task task1 = new Task("Tarea 1", "Descripción 1", date1, TypePriority.PRIORITY, "A123F");
        Task task2 = new Task("Tarea 2", "Descripción 2", date2, TypePriority.PRIORITY, "A123F");

        priorityQueue.insert(task1);
        priorityQueue.insert(task2);

        Task removedTask = priorityQueue.remove();
        assertEquals(task1, removedTask);

        removedTask = priorityQueue.remove();
        assertEquals(task2, removedTask);
    }

    @Test
    public void testShowPriorityQueueWithMultipleTasks() {
        LocalDate date1 = LocalDate.of(2023, 10, 15); 
        LocalDate date2 = LocalDate.of(2023, 10, 16); 

        Task task1 = new Task("Tarea 1", "Descripción 1", date1, TypePriority.PRIORITY, "A123F");
        Task task2 = new Task("Tarea 2", "Descripción 2", date2, TypePriority.PRIORITY, "A123F");

        priorityQueue.insert(task1);
        priorityQueue.insert(task2);

        String result = priorityQueue.showPriorityQueue();
        assertTrue(result.contains("Tarea 1"));
        assertTrue(result.contains("Tarea 2"));
    }

    
}

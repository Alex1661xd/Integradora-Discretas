package UnitTesting;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Queue;

public class QueueTest {

    private Queue<Integer> queue;

    @Before
    public void setUp() {
        queue = new Queue<>();
    }

    @Test
    public void testEnqueueAndPeek() {
        queue.enqueue(1);
        assertEquals(Integer.valueOf(1), queue.peek());

        queue.enqueue(2);
        assertEquals(Integer.valueOf(1), queue.peek());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(Integer.valueOf(1), queue.dequeue());
        assertEquals(Integer.valueOf(2), queue.dequeue());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());

        queue.enqueue(1);

        assertFalse(queue.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, queue.size());

        queue.enqueue(1);
        assertEquals(1, queue.size());

        queue.enqueue(2);
        assertEquals(2, queue.size());

        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @Test
    public void testRear() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(Integer.valueOf(2), queue.rear());
    }

    @Test(expected = IllegalStateException.class)
    public void testDequeueOnEmptyQueue() {
        assertTrue(queue.isEmpty());
        queue.dequeue();
    }


}

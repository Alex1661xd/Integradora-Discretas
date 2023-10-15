package UnitTesting;
import model.Stack;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class StackTest extends TestCase {

    private Stack<Integer> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }

    @Test
    public void testPushAndPeek() {
        stack.push(1);
        assertEquals(Integer.valueOf(1), stack.peek());

        stack.push(2);
        assertEquals(Integer.valueOf(2), stack.peek());
    }

    @Test
    public void testPop() {
        stack.push(1);
        stack.push(2);

        assertEquals(Integer.valueOf(2), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(stack.isEmpty());

        stack.push(1);

        assertFalse(stack.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, stack.size());

        stack.push(1);
        assertEquals(1, stack.size());

        stack.push(2);
        assertEquals(2, stack.size());

        stack.pop();
        assertEquals(1, stack.size());
    }
}

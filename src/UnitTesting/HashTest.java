package UnitTesting;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import model.HashTable;

public class HashTest extends TestCase {

    private HashTable<String, Integer> hashTable;

    @Before
    public void setUp() {
        hashTable = new HashTable<>(10); 
    }

    @Test
    public void testInsert() {
        hashTable.insert("clave1", 1);
        hashTable.insert("clave2", 2);
        assertEquals(Integer.valueOf(1), hashTable.search("clave1"));
        assertEquals(Integer.valueOf(2), hashTable.search("clave2"));
    }

    @Test
    public void testDelete() {
        hashTable.insert("clave1", 1);
        assertTrue(hashTable.delete("clave1"));
        assertFalse(hashTable.delete("clave2"));
    }

    @Test
    public void testFunctionHash() {
        int hash1 = hashTable.FunctionHash("clave1");
        int hash2 = hashTable.FunctionHash("clave2");
        
        
        assertTrue(hash1 >= 0 && hash1 < 10);
        assertTrue(hash2 >= 0 && hash2 < 10);
    }

    @Test
    public void testInsertAndSearch() {
        hashTable.insert("clave1", 1);
        hashTable.insert("clave2", 2);
        
        assertEquals(Integer.valueOf(1), hashTable.search("clave1"));
        assertEquals(Integer.valueOf(2), hashTable.search("clave2"));
        

        assertNull(hashTable.search("clave3"));
    }

    @Test
    public void testColisiones() {
        
        hashTable.insert("cola", 3);
        hashTable.insert("cola", 4);
        
        
        assertEquals(Integer.valueOf(4), hashTable.search("cola"));
        assertEquals(Integer.valueOf(4), hashTable.search("cola"));
    }
}

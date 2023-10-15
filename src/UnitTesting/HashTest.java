package UnitTesting;
import junit.framework.TestCase;
import model.HashTable;
import model.Task;
import java.time.LocalDate;
import model.TypePriority;

public class HashTest<K, V> extends TestCase{
    private HashTable<String, String> hashTable;
    private Task tarea;
    private void setUpStage1(){
        hashTable= new HashTable<>(25);
        // tarea= new Task("Español", "Lindo", LocalDate.now() , TypePriority.NO_PRIORITY ,"123A");
    }

    private void testAddNode(){
        setUpStage1();
        try {
            hashTable.insert("123A", "Pastillas" );
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
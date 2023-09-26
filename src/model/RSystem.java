package model;


public class RSystem {

    private HashTable<String, Task> hashTable;
    private Queue<Task> cola=new Queue<>();

    public RSystem(){
        this.hashTable= new HashTable<>(25);

        //addTask("123A", "Español","Esta tarea es de poemas", "10/10/23",2);
    }
    
    public boolean addTask(String key, String title, String description, String fechaLimit, int priority){
        
        return false;

    }

    public boolean editTask(String identifier, String nuevoValor, int option){

        return false;
        
    }

    public String taskValue(String id) {
        Task tareaEncontrada = hashTable.search(id);

        return tareaEncontrada.toString();
    }

    public String taskCreated(){
        return hashTable.print();
    }

    public boolean deleteTask(String id){
        return hashTable.delete(id);
    }

    public void addTaskAtQueue(Task tarea){
        cola.enqueue(tarea);
    }

    public String printQueue(){
        return cola.printQueue();
    }

}

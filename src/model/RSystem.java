package model;


public class RSystem {

    private HashTable<String, Task> hashTable;

    public RSystem(){
        this.hashTable= new HashTable<>(25);
        addTask("123A", "Español","Esta tarea es de poemas", "10/10/23",2);
    }
    
    public void addTask(String key, String title, String description, String fechaLimit, int priority){
        
        TypePriority tipoPrioridad=null;
        if(priority==1){
            tipoPrioridad=TypePriority.PRIORITY;
        }else{
            tipoPrioridad=TypePriority.NO_PRIORITY;
        }
        //Crea una nueva tarea o recordatorio con los datos ingresados por el usuario
        Task nuevaTarea = new Task(title, description, fechaLimit, tipoPrioridad, key);
        //Inserta en la tabla hash  el nuevo nodo
        hashTable.insert(key, nuevaTarea);

    }

    public void editTask(){
        String identifier;
        Task tareaModificada;

    }

    public String taskValue(String id) {
        Task tareaEncontrada = hashTable.search(id);

        return tareaEncontrada.toString();
    }

    public String taskCreated(){
        return hashTable.print();
    }

}

package model;


public class RSystem {

    private HashTable<String, Task> hashTable;

    public RSystem(){
        this.hashTable= new HashTable<>(25);
    }
    
    public void addTask(String Key, String title, String description, String fechaLimit, int priority, String identifier){
        
        TypePriority tipoPrioridad=null;
        if(priority==1){
            tipoPrioridad=TypePriority.PRIORITY;
        }else{
            tipoPrioridad=TypePriority.NO_PRIORITY;
        }
        //Crea una nueva tarea o recordatorio con los datos ingresados por el usuario
        Task nuevaTarea=new Task(title, description, fechaLimit, tipoPrioridad, identifier);
        //Inserta en la tabla hash  el nuevo nodo
        hashTable.insert(identifier, nuevaTarea);

    }

    public void editTask(){
        
    }
}

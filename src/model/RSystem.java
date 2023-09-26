package model;


public class RSystem {

    private HashTable<String, Task> hashTable;
    private Queue<Task> cola=new Queue<>();

    public RSystem(){
        this.hashTable= new HashTable<>(25);

        //addTask("123A", "Español","Esta tarea es de poemas", "10/10/23",2);
    }

    public boolean addTask(String key, String title, String description, String fechaLimit, int priority){

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

        if(priority==2){
            addTaskAtQueue(nuevaTarea);
        }

        return true;

    }

    public boolean editTask(String identifier, String nuevoValor, int option){
        boolean flag=false;

        Task tareaEncontrado=hashTable.search(identifier);

        if(option==2){
            tareaEncontrado.setTitle(nuevoValor);
            flag=true;
        }else if(option==3){
            tareaEncontrado.setDescription(nuevoValor);
            flag=true;
        }else if(option==4){
            tareaEncontrado.setDate(nuevoValor);
            flag=true;
        }else if(option==1){
            hashTable.delete(identifier);
            tareaEncontrado.setIdentifier(nuevoValor);
            hashTable.insert(nuevoValor, tareaEncontrado);

            flag=true;
        }
        return flag;
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

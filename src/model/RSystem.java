package model;


public class RSystem {

    private HashTable<String, Task> hashTable;
    private Queue<Task> cola=new Queue<>();
    private Stack<String> historialAcciones=new Stack<>();

    public RSystem(){
        this.hashTable= new HashTable<>(25);

        addTask("123A", "Español","Esta tarea es de poemas", "10/10/23",2);
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

    public void addActionStack(String actionR, int optionEditTask, String identifier, int optionRegist){
        String mensajeHistorial="";
        //1 significa que desea registrar la creacion de una tarea
        if(optionRegist==1){
            mensajeHistorial=actionR+"\nEl identificador de la nueva tareas es: ["+identifier+"]";
        }else if(optionRegist==2){
            mensajeHistorial=actionR;
                if(optionEditTask==1){
                    mensajeHistorial+="\nEn la tarea se edito el Identificador";
                }else if(optionEditTask==2){
                    mensajeHistorial+="\nEn la tarea se edito el Titulo";
                }else if(optionEditTask==3){
                    mensajeHistorial+="\nEn la tarea se edito la descripcion";
                }else{
                    mensajeHistorial+="\nEn la tarea se edito la fecha";
                }
        }else if(optionEditTask==3){
            mensajeHistorial+="\nEl usuario elimino una tarea con el identificador: "+identifier;
        }

        
    }

}

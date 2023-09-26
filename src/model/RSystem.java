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
        addActionStack("\nSe creo una tarea", 1, key, 1);
        if(priority==2){
            addTaskAtQueue(nuevaTarea);
        }

        return true;

    }

    public boolean editTask(String identifier, String nuevoValor, int option){

        addActionStack("\nSe edito una tarea creada", option, identifier, 2);
        return false;

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
        addActionStack("\nSe elimino una tarea", 1, id, 3);
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
            mensajeHistorial=actionR+"\nEl identificador de la nueva tarea es: ["+identifier+"]";
        }else if(optionRegist==2){
            mensajeHistorial=actionR+" \nSu identificador es: ["+identifier+"]";
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

        historialAcciones.push(mensajeHistorial);

        
    }

    public String printHistorial(){
        return historialAcciones.printStack();
    }

}

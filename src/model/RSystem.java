package model;


public class RSystem {

    private HashTable<String, Task> hashTable;
    private Queue<Task> cola=new Queue<>();
    private Stack<Task> historialAcciones=new Stack<>();

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
        Task nuevaTarea = new Task(title, description, fechaLimit, tipoPrioridad, key, "");
        //Inserta en la tabla hash  el nuevo nodo

        hashTable.insert(key, nuevaTarea);
        addActionStack("\n-[Se creo una tarea]-", 0, hashTable.search(key), 1);
        if(priority==2){
            addTaskAtQueue(nuevaTarea);
        }

        return true;

    }

    public boolean addTastWithTask(Task tarea){
        hashTable.insert(tarea.getIdentifier(), tarea);
        addActionStack("\n-[Se creo una tarea]-", 1, hashTable.search(tarea.getIdentifier()), 1);
        if(tarea.getTipoPrioridad()==TypePriority.NO_PRIORITY){
            addTaskAtQueue(tarea);
            return true;
        }
        return true;
    }

    public boolean editTask(String identifier, String nuevoValor, int option){


        boolean flag=false;

        Task tareaEncontrado=hashTable.search(identifier);
        tareaEncontrado.setPosicionEditAtributo(option);
        if(option==2){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getTitle());
            tareaEncontrado.setTitle(nuevoValor);
            addActionStack("\n-[Se edito una tarea creada]-", option, tareaEncontrado, 2);
            flag=true;
        }else if(option==3){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getDescription());
            tareaEncontrado.setDescription(nuevoValor);
            addActionStack("\n-[Se edito una tarea creada]-", option, tareaEncontrado, 2);
            flag=true;
        }else if(option==4){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getDate());
            tareaEncontrado.setDate(nuevoValor);
            addActionStack("\n-[Se edito una tarea creada]-", option, tareaEncontrado, 2);
            flag=true;
        }else if(option==1){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getIdentifier());
            hashTable.delete(identifier);
            tareaEncontrado.setIdentifier(nuevoValor);
            hashTable.insert(nuevoValor, tareaEncontrado);
            addActionStack("\n-[Se edito una tarea creada]-", option, tareaEncontrado, 2);

            flag=true;
        }
        return flag;

    }

    public boolean editTaskWithTask(Task tareaEncontrado, String valorAnterior, int optionEdit){
        boolean flag=false;
        if(optionEdit==2){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getTitle());
            tareaEncontrado.setTitle(valorAnterior);
            addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);
            flag=true;
        }else if(optionEdit==3){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getDescription());
            tareaEncontrado.setDescription(valorAnterior);
            addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);
            flag=true;
        }else if(optionEdit==4){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getDate());
            tareaEncontrado.setDate(valorAnterior);
            addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);
            flag=true;
        }else if(optionEdit==1){
            tareaEncontrado.setValorAnterior(tareaEncontrado.getIdentifier());
            hashTable.delete(tareaEncontrado.getIdentifier());
            tareaEncontrado.setIdentifier(valorAnterior);
            hashTable.insert(valorAnterior, tareaEncontrado);
            addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);

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
        addActionStack("\n-[Se elimino una tarea]-", 0, hashTable.search(id), 3);
        return hashTable.delete(id);
    }

    public void addTaskAtQueue(Task tarea){
        cola.enqueue(tarea);
    }

    public String printQueue(){
        return cola.printQueue();
    }

    public void addActionStack(String actionR, int optionEditTask, Task tarea, int optionRegist){
        String actionString="\nEl identificador de la tarea es ["+tarea.getIdentifier()+"]";
        tarea.setTypeModification(optionRegist);
        tarea.setPosicionEditAtributo(optionEditTask);
        if(optionRegist==2){
                
                if(optionEditTask==1){
                    actionR+="\nEn la tarea se edito el Identificador";
                }else if(optionEditTask==2){
                    actionR+="\nEn la tarea se edito el Titulo";
                }else if(optionEditTask==3){
                    actionR+="\nEn la tarea se edito la descripcion";
                }else{
                    actionR+="\nEn la tarea se edito la fecha";
                }
        }
        actionR+=actionString;
        tarea.setDescriptionUseRealized(actionR);
        historialAcciones.push(tarea);
    }

    public String printHistorial(){
        return historialAcciones.printStack();
    }

    public String printLastAction(){
        return historialAcciones.peek().getDescriptionUseRealized();
    }

    public int obtenerMoficiacion(){
        return historialAcciones.peek().getTypeModification();
    }

    public Task obtenerUltimaTarea(){
        return historialAcciones.peek();
    }

    public boolean deshacerAccion(Task tarea){
        if(tarea.getTypeModification()==1){
            deleteTask(tarea.getIdentifier());
            historialAcciones.pop();
            return true;
        }else if(tarea.getTypeModification()==3){
            addTastWithTask(tarea);
            historialAcciones.pop();
            return true;
        }else if(tarea.getTypeModification()==2){
            editTaskWithTask(tarea, tarea.getValorAnterior(), tarea.getPosicionEditAtributo());
            return true;
        }
        
        
        return false;
    }
}   

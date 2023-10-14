package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RSystem {

    private HashTable<String, Task> hashTable;
    private Queue<Task> cola=new Queue<>();
    private Stack<Task> historialAcciones=new Stack<>();
    private PriorityQueue colaPrioridad= new PriorityQueue(25);

    public RSystem(){
        this.hashTable= new HashTable<>(25);
        addTask("123A", "Español","Esta tarea es de poemas", LocalDate.of(2023, 10, 5),2);
        addTask("123B", "Rojo","Esta tarea es de poemas", LocalDate.of(2023, 02, 5),1);
        addTask("148C", "Rosado","Esta tarea es de poemas", LocalDate.of(2023, 04, 5),1);
        addTask("985G", "Lila","Esta tarea es de poemas", LocalDate.of(2023, 07, 5),1);
    }

    /**
     * Add a new task or reminder to the system.
     *
     * @param key The unique key to identify the task.
     * @param title The title of the task.
     * @param description The description of the task.
     * @param fechaLimit The deadline to complete the task.
     * @param priority The priority of the task (1 for priority, 2 for non-priority).
     * @return true if the task was added successfully, false otherwise.
    */
    public boolean addTask(String key, String title, String description, LocalDate fechaLimit, int priority){
        
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
            cola.enqueue(nuevaTarea);
        }else if(priority==1){
            colaPrioridad.insert(nuevaTarea);
        }

        return true;

    }

    /**
    * Converts a text string in the format "dd/MM/yyyy" to a LocalDate object.
    *
    * @param fecha The text string representing a date in the format "dd/MM/yyyy".
    * @return The LocalDate object corresponding to the given date.
    * @throws Exception If there is an error parsing the date or the format is incorrect.
    */
    public LocalDate convertStringToLocalDate(String fecha)throws Exception{
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaLocalDate=LocalDate.parse(fecha, formato);;
        
        return fechaLocalDate;
    }

    /**
    * Adds an existing task to the system.
    *
    * @param tarea The task to add.
    * @return true if the task was added successfully, false otherwise.
    */
    public boolean addTastWithTask(Task tarea){
        hashTable.insert(tarea.getIdentifier(), tarea);
        addActionStack("\n-[Se creo una tarea]-", 1, hashTable.search(tarea.getIdentifier()), 1);
        if(tarea.getTipoPrioridad()==TypePriority.NO_PRIORITY){
            cola.enqueue(tarea);
            return true;
        }
        return true;
    }

    /**
    * Edit an attribute of a task identified by its unique key.
    *
    * @param identifier The unique key of the task you want to edit.
    * @param nuevoValor The new value to assign to the attribute.
    * @param option The option indicating which attribute to edit (1 identifier, 2 title, 3 description).
    * @return true if the task was edited successfully, false otherwise.
    */
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

    /**
    * Edit a task attribute using an existing Task object.
    *
    * @param tareaEncontrado The Task object to edit.
    * @param valorAnterior The previous value of the attribute being edited.
    * @param optionEdit The option indicating which attribute to edit (1 identifier, 2 title, 3 description).
    * @return true if the task was edited successfully, false otherwise.
    */
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

    /**
    * Gets a string representation of a task identified by its unique key.
    *
    * @param id The key of the task you want to obtain.
    * @return A string representing the task information.
    */
    public String taskValue(String id) {
        Task tareaEncontrada = hashTable.search(id);

        return tareaEncontrada.toString();
    }

    /**
    * Gets a string representation of the tasks created so far.
    *
    * @return A string representing the information of the created tasks.
    */
    public String taskCreated(){
        return hashTable.print();
    }

    /**
    * Delete a task identified by its unique key.
    *
    * @param id The unique key of the task to delete.
    * @return true if the task was successfully deleted, false otherwise.
    */
    public boolean deleteTask(String id){
        addActionStack("\n-[Se elimino una tarea]-", 0, hashTable.search(id), 3);
        return hashTable.delete(id);
    }

    /**
    * Gets a string representation of the priority queue.
    *
    * @return A string representing priority queue information.
    */
    public String printPriorityQueue(){
        return colaPrioridad.showPriorityQueue();
    }

    /**
    * Gets a string representation of the queue.
    *
    * @return A string representing the queue information.
    */
    public String printQueue(){
        return cola.printQueue();
    }

    /**
    * Adds an action to the action history, along with the task and relevant information.
    *
    * @param actionR A string describing the action performed.
    * @param optionEditTask The option indicating which attribute was edited (1 identifier, 2 title, 3 description, 4 date).
    * @param tarea The task associated with the action.
    * @param optionRegist The option indicating the type of registry (1 for creation, 2 for editing, 3 for deletion).
    */
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

    /**
    * Gets a string representation of the action history.
    *
    * @return A string representing action history information.
    */
    public String printHistorial(){
        return historialAcciones.printStack();
    }

    /**
    * Gets a string representation of the last action performed.
    *
    * @return A string representing information about the last action performed.
    */
    public String printLastAction(){
        return historialAcciones.peek().getDescriptionUseRealized();
    }

    /**
    * Gets the modification type of the last action performed.
    *
    * @return The type of modification (1 for creation, 2 for editing, 3 for deletion).
    */
    public int obtenerMoficiacion(){
        return historialAcciones.peek().getTypeModification();
    }

    /**
    * Gets the last task recorded in the action history.
    *
    * @return The last task recorded in the action history.
    */
    public Task obtenerUltimaTarea(){
        return historialAcciones.peek();
    }

    /**
    * Undoes the last action performed.
    *
    * @param tarea The task associated with the action to be undone.
    * @return true if the action was undone successfully, false otherwise.
    */
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
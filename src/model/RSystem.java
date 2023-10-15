package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RSystem {

    private HashTable<String, Task> hashTable;
    private Queue<Task> cola=new Queue<>();
    private Stack<Action> historialAcciones=new Stack<>();
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
        //Create the type of priority according to the user's option
        if(priority==1){
            tipoPrioridad=TypePriority.PRIORITY;
        }else{
            tipoPrioridad=TypePriority.NO_PRIORITY;
        }
        //Create a new task or reminder with the data entered by the user
        Task nuevaTarea = new Task(title, description, fechaLimit, tipoPrioridad, key);
        //Se crea la accion
        addAction(1, -1,null, null, -1, key);
        //Insert the new node into the hash table
        hashTable.insert(key, nuevaTarea);

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
            addAction(1, -1, null, null,-1, tarea.getIdentifier());
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
        if(option==2){
            addAction(2, 2, tareaEncontrado.getTitle(), nuevoValor, 2, identifier);
            tareaEncontrado.setTitle(nuevoValor);
            flag=true;
        }else if(option==3){
            addAction(2, 3, tareaEncontrado.getDescription(), nuevoValor, 3, identifier);
            tareaEncontrado.setDescription(nuevoValor);
            flag=true;
        }else if(option==1){
            addAction(2, 1, tareaEncontrado.getIdentifier(), nuevoValor, 1, nuevoValor);
            hashTable.delete(identifier);
            tareaEncontrado.setIdentifier(nuevoValor);
            hashTable.insert(nuevoValor, tareaEncontrado);
            flag=true;
        }
        return flag;

    }

    // public boolean editTaskWithTask(Task tareaEncontrado, String valorAnterior, int optionEdit){
    //     boolean flag=false;
    //     if(optionEdit==2){
    //         tareaEncontrado.setValorAnterior(tareaEncontrado.getTitle());
    //         tareaEncontrado.setTitle(valorAnterior);
    //         addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);
    //         flag=true;
    //     }else if(optionEdit==3){
    //         tareaEncontrado.setValorAnterior(tareaEncontrado.getDescription());
    //         tareaEncontrado.setDescription(valorAnterior);
    //         addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);
    //         flag=true;
    //     }else if(optionEdit==1){
    //         tareaEncontrado.setValorAnterior(tareaEncontrado.getIdentifier());
    //         hashTable.delete(tareaEncontrado.getIdentifier());
    //         tareaEncontrado.setIdentifier(valorAnterior);
    //         hashTable.insert(valorAnterior, tareaEncontrado);
    //         addActionStack("\n-[Se edito una tarea creada]-", optionEdit, tareaEncontrado, 2);

    //         flag=true;
    //     }
    //     return flag;
    // }

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
        addAction(3, -1, null, null, -1, id);
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
    * Gets a string representation of the action history.
    *
    * @return A string representing action history information.
    */
    public String printHistorial(){
        return historialAcciones.printStack();
    }


    /**
    * Adds a new action to the action history.
    *
    * @param typeActionn The type of action (1 for add, 2 for edit, 3 for delete).
    * @param attributeAf The affected attribute (1 for identifier, 2 for title, 3 for description, -1 if not applicable).
    * @param dataBefore The data before the action (can be null if not applicable).
    * @param dataAfter The data after the action (can be null if not applicable).
    * @param positionAttribute The position of the attribute (only applicable for editing, -1 if not applicable).
    * @param id The identifier of the affected task (can be null if not applicable).
    */
    public void addAction(int typeActionn, int atributeAf,String dataBefore, String dataAfter, int posicionAtribute, String id){

        Action accion=null;
        TypeAction typeAction=null;
        String actionR=null;
        String atributeAff=null;

        if(typeActionn==1){
            typeAction=TypeAction.ADD;
            actionR="(SE CREO UNA TAREA)";
        }else if(typeActionn==2){
            typeAction=TypeAction.EDIT;
            actionR="(SE EDITO UNA TAREA)";
                if(atributeAf==1){
                    atributeAff="-identifier-";
                }else if(atributeAf==2){
                    atributeAff="-title-";
                }else if(atributeAf==3){
                    atributeAff="-description-";
                }
        }else if(typeActionn==3){
            typeAction=TypeAction.ELIMINATE;
            actionR="(SE ELIMINO UNA TAREA)";
        }

        accion=new Action(typeAction, actionR, atributeAff, dataBefore, dataAfter, posicionAtribute, id);
        if(typeActionn==3){
            accion.setTareaEliminate(hashTable.search(id));
        }
        historialAcciones.push(accion);
    }

    /**
    * Undoes the last action performed in the action history.
    *
    * @return true if the action was successfully undone, false otherwise.
    */
    public boolean deshacerAccion(){
        String id=historialAcciones.peek().getId();
        if(historialAcciones.peek().getTypeAction()==TypeAction.ADD){
            deleteTask(id);
            historialAcciones.pop();
            return true;
        }else if(historialAcciones.peek().getTypeAction()==TypeAction.ELIMINATE){
            addTastWithTask(historialAcciones.peek().getTareaEliminate());
            historialAcciones.pop();
            return true;
        }else if(historialAcciones.peek().getTypeAction()==TypeAction.EDIT){
            String datab=historialAcciones.peek().getDataBefore();
            int pa=historialAcciones.peek().getPosicionEditAtributo();
            editTask(historialAcciones.peek().getId(), datab, pa);
            historialAcciones.pop();
            return true;
        }
        return false;
        
    }

    /**
    * Gets a string representation of the last action performed.
    *
    * @return A string representing the last action performed.
    */
    public String printLastAction(){
        if(historialAcciones.peek().getTypeAction()==TypeAction.EDIT){
            return historialAcciones.peek().ToStringEdit();
        }else{
            return historialAcciones.peek().ToStringN();
        }
        
    } 

    /**
    * Gets the modification type of the last action performed in the action history.
    *
    * @return An integer representing the type of modification (1 for add, 2 for edit, 3 for delete).
    */
    public int obtenerMoficiacion(){
         if(historialAcciones.peek().getTypeAction()==TypeAction.EDIT){
            return 2;
        }else if(historialAcciones.peek().getTypeAction()==TypeAction.ADD){
            return 1;
        }else{
            return 3;
        }
    }
}

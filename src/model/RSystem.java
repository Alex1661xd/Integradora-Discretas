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

    public boolean addTask(String key, String title, String description, LocalDate fechaLimit, int priority){

        TypePriority tipoPrioridad=null;
        //Crea el tipo de prioridad segun la opcion del usuario
        if(priority==1){
            tipoPrioridad=TypePriority.PRIORITY;
        }else{
            tipoPrioridad=TypePriority.NO_PRIORITY;
        }
        //Crea una nueva tarea o recordatorio con los datos ingresados por el usuario
        Task nuevaTarea = new Task(title, description, fechaLimit, tipoPrioridad, key);
        //Se crea la accion
        addAction(1, -1,null, null, -1, key);
        //Inserta en la tabla hash  el nuevo nodo
        hashTable.insert(key, nuevaTarea);

        if(priority==2){
            cola.enqueue(nuevaTarea);
        }else if(priority==1){
            colaPrioridad.insert(nuevaTarea);
        }

        return true;

    }

    public LocalDate convertStringToLocalDate(String fecha)throws Exception{
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaLocalDate=LocalDate.parse(fecha, formato);;

        return fechaLocalDate;
    }

      public boolean addTastWithTask(Task tarea){
            hashTable.insert(tarea.getIdentifier(), tarea);
            addAction(1, -1, null, null,-1, tarea.getIdentifier());
         if(tarea.getTipoPrioridad()==TypePriority.NO_PRIORITY){
             cola.enqueue(tarea);
            return true;
         }
         return true;
     }

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

    public String taskValue(String id) {
        Task tareaEncontrada = hashTable.search(id);

        return tareaEncontrada.toString();
    }

    public String taskCreated(){
        return hashTable.print();
    }

    public boolean deleteTask(String id){
        addAction(3, -1, null, null, -1, id);
        return hashTable.delete(id);
    }

    public String printPriorityQueue(){
        return colaPrioridad.showPriorityQueue();
    }

    public String printQueue(){
        return cola.printQueue();
    }

    public String printHistorial(){
        return historialAcciones.printStack();
    }



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

    public String printLastAction(){
        if(historialAcciones.peek().getTypeAction()==TypeAction.EDIT){
            return historialAcciones.peek().ToStringEdit();
        }else{
            return historialAcciones.peek().ToStringN();
        }
        
    } 

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

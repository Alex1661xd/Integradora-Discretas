package ui;
import java.time.LocalDate;
import java.util.Scanner;
import model.RSystem;

public class Main {

    static Scanner reader = new Scanner(System.in);
    static RSystem controller = new RSystem();
    static String colorMorado = "\u001B[35m";
    static String resetColor = "\u001B[0m";
    static String colorAmarillo = "\u001B[33m";
    static String colorAzul = "\u001B[38;5;12m";
    static String colorRojo = "\u001B[31m";
    static String colorVerde = "\u001B[32m";
    static String colorNaranja = "\u001B[38;5;208m";


    public static void main(String[] args) throws Exception {

        boolean flag = true;

        System.out.println(colorMorado + "╔══════════════════════╗");
        System.out.println("║" + resetColor + "       ¡Welcome!      " + colorMorado + "║");
        System.out.println("╚══════════════════════╝" + resetColor);

        while(flag) {

            System.out.println(colorAmarillo + "\n-" + resetColor + " What do you want to do? " + colorAmarillo + "-" + resetColor);
            System.out.println("\n1. Add task/Reminder");
            System.out.println("2. Other options");
            System.out.println("-------------------------");
            System.out.println("3. Exit");
            System.out.print(colorAzul + "\n>> " + resetColor);
            int optionMenu = reader.nextInt();

            switch (optionMenu) {

                case 1:
                    addReminderTask();
                    break;

                case 2:
                    System.out.println(colorMorado + "\n╔══════════════════════╗");
                    System.out.println("║" + resetColor + "      ¡Options!      " + colorMorado + " ║");
                    System.out.println("╚══════════════════════╝" + resetColor);

                    System.out.println("\n1. Edit task ");
                    System.out.println("2. Delete task");
                    System.out.println("3. Show tasks in order of arrival");
                    System.out.println("4. Show action history");
                    System.out.println("5. Deshacer accion");
                    System.out.println("6. Show priority Queue");
                    System.out.println("-------------------------");
                    System.out.println("7. Back");
                    System.out.print(colorAzul + "\n>> " + resetColor);

                    int optionMenu2 = reader.nextInt();

                    switch (optionMenu2) {
                        case 1:
                            editTaskReminder();
                            break;
                        case 2:
                            deleteTaskReminder();
                            break;
                        case 7:
                            System.out.println(colorMorado + "\n╔══════════════════════╗");
                            System.out.println("║" + resetColor + "        ¡MENU!     " + colorMorado + "   ║");
                            System.out.println("╚══════════════════════╝" + resetColor);
                            flag = false;
                            break;
                        case 3:
                            printQueue();
                            break;
                        case 4:
                            System.out.println("Estas son las acciones que has hecho: ");
                            printHistorial();
                            break;
                        case 5:
                            System.out.println("Esta es la ultima accion realizada");
                            printLastAction();

                            break;

                        case 6:
                            System.out.println("Estas son las acciones de prioridad");
                            printPriorityQueue();
                            break;

                    }

                    break;
                case 3:
                    System.out.println(colorAmarillo + "Good bye!!!"+resetColor);
                    flag = false;
                    break;
            }
        }
    }

    private static void addReminderTask() throws Exception {
        System.out.println("\nEnter the identifier");
        reader.nextLine();
        String identifier=reader.nextLine();
        System.out.println("\nEnter the title");
        String title = reader.nextLine();
        System.out.println("\nEnter the description");
        String descripcion = reader.nextLine();
        LocalDate fechaL=null;
        while(true){
            System.out.println("\nEnter the deadline (dd/MM/yyyy)");
            String fecha = reader.nextLine();
            
                try {
                    fechaL=controller.convertStringToLocalDate(fecha);
                    break;
                } catch (java.time.format.DateTimeParseException e) {
                System.err.println("Error al parsear la fecha: (Ingrese una fecha valida, verifique el formato)");
            }
        }
        

        System.out.println("\nIs it a priority??");
        System.out.println("1. SI\n2. NO");
        int priority = reader.nextInt();

        if(controller.addTask(identifier, title, descripcion, fechaL, priority)){
                    System.out.println(colorVerde + "\nAdded successfully");
        }else{
                System.out.println(colorRojo + "\nAn error occurred while adding");
        }
        
        
        
    }

    private static void editTaskReminder() {
        System.out.println("These are the tasks you have created: \n");
        if(controller.taskCreated()==""){
            System.out.println(colorRojo + "\n-There is nothing created-");
        }else{
        System.out.println(controller.taskCreated());
        System.out.println("Enter the identifier of the task to edit: ");
        reader.nextLine();
        String id = reader.nextLine();
        System.out.println(controller.taskValue(id));

        System.out.println("\nWhat elements do you want to change?\n\n1.Identifier\n2.Title\n3.Description");
        int option=reader.nextInt();
        String nuevoValor=null;
        if(option==1){
            System.out.println("\nIf you change the identifier, its location can be changed");
            System.out.println("Enter the new value");
            reader.nextLine();
            nuevoValor=reader.nextLine();
        }else{
            System.out.println("Enter the new value");
            reader.nextLine();
            nuevoValor=reader.nextLine();
        }

        if(controller.editTask(id, nuevoValor, option)){
            System.out.println(colorVerde + "Edited successfully");
            System.out.println("This is how it was edited: ");
                if(option==1){
                    System.out.println("\n"+controller.taskValue(nuevoValor));
                }else{
                    System.out.println("\n"+controller.taskValue(id));
                }
        }else{
            System.out.println(colorNaranja + "An unexpected error occurred");
        }
        }
    }

    private static void deleteTaskReminder() {
        
        if(controller.taskCreated()==""){
            System.out.println("\n-There is nothing created-");
        }else{
            System.out.println(controller.taskCreated());
            System.out.println("Enter the identifier of the task to delete: ");
            reader.nextLine();
            String id = reader.nextLine();
            if(controller.deleteTask(id)){
                System.out.println(colorVerde + "It was deleted correctly");
            }else{
                System.out.println(colorNaranja + "An unexpected error occurred");
            }
        }
    }

    private static void printQueue(){
        System.out.println(controller.printQueue());
    }

    private static void printHistorial(){
        System.out.println(controller.printHistorial());
    }

    private static void printPriorityQueue(){
        System.out.println(controller.printPriorityQueue());
    }

     private static void printLastAction(){
         System.out.println(controller.printLastAction());
         System.out.println("Estas seguro que deseas deshacer esta accion?");
         if(controller.obtenerMoficiacion()==1){
             System.out.println("Se va a eliminar esta tarea recientemente creada");
         }else if(controller.obtenerMoficiacion()==2){
             System.out.println("Se va a restaurar el estado anterior de la tarea (Antes de editar)");
        }else if(controller.obtenerMoficiacion()==3){
             System.out.println("Se va a restaurar la tarea eliminada");
         }
         while(true){
             System.out.println("1. SI\n2. NO");
             int confirmar=reader.nextInt();
             if(confirmar==1){
                 if(controller.deshacerAccion()){
                     System.out.println("Se deshizo la ultima accion con exito");
                     break;
                 }
             }else if(confirmar>2||confirmar<1){
                 System.out.println("No es una opcion valida");
             }else{
                 break;
             }
         }
        
     }
}

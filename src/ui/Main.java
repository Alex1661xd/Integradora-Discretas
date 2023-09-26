package ui;
import java.util.Scanner;
import model.RSystem;

public class Main {

    static Scanner reader = new Scanner(System.in);
    static RSystem controller = new RSystem();
    static String colorMorado = "\u001B[35m";
    static String resetColor = "\u001B[0m";
    static String colorAmarillo = "\u001B[33m";
    static String colorRGB = "\u001B[38;5;12m";
    static String colorVerde = "\u001B[32m";

    public static void main(String[] args) throws Exception {

        boolean flag = true;

        System.out.println(colorMorado + "╔══════════════════════╗");
        System.out.println("║" + resetColor + "     ¡Bienvenido!     " + colorMorado + "║");
        System.out.println("╚══════════════════════╝" + resetColor);

        while(flag) {

            System.out.println(colorAmarillo + "\n-" + resetColor + "  ¿Que deseas hacer?  " + colorAmarillo + "-" + resetColor);
            System.out.println("\n1. Agregar Tarea/Recordatorio");
            System.out.println("2. Agregar tarea");
            System.out.println("3. Otras opciones");
            System.out.println("-------------------------");
            System.out.println("4. Salir");
            System.out.print(colorVerde + "\n>> " + resetColor);
            int optionMenu = reader.nextInt();

            switch (optionMenu) {

                case 1:
                    addReminderTask();
                    break;

                case 2:
                    addReminderTask();
                    break;

                case 3:
                    System.out.println(colorMorado + "\n╔══════════════════════╗");
                    System.out.println("║" + resetColor + "      ¡Opciones!     " + colorMorado + " ║");
                    System.out.println("╚══════════════════════╝" + resetColor);
                    System.out.println("\n1. Editar ");
                    System.out.println("2. Eliminar");
                    System.out.println("3. Mostrar tareas por orden de llegada");
                    System.out.println("4. Mostrar historial de acciones");
                    System.out.println("-------------------------");
                    System.out.println("5. Volver");
                    System.out.print(colorVerde + "\n>> " + resetColor);
                    int optionMenu2 = reader.nextInt();

                    switch (optionMenu2) {
                        case 1:
                            editTaskReminder();
                            break;
                        case 2:
                            deleteTaskReminder();
                            break;
                        case 5:
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

                    }

                    break;
                case 4:
                    System.out.println("Hasta luego");
                    flag = false;
                    break;
            }
        }
    }

    private static void addReminderTask() {
        System.out.println("\nIngrese el identificador");
        reader.nextLine();
        String identifier=reader.nextLine();
        System.out.println("\nIngrese el Titulo");
        String title = reader.nextLine();
        System.out.println("\nIngrese la descripcion");
        String descripcion = reader.nextLine();
        System.out.println("\nIngrese la fecha limite (dd/MM/yyyy)");
        String fecha = reader.nextLine();
        System.out.println("\n¿Es prioritaria?");
        System.out.println("1. SI\n2. NO");
        int priority = reader.nextInt();

        if(controller.addTask(identifier, title, descripcion, fecha, priority)){
            System.out.println("\nSe agrego correctamente");
        }else{
            System.out.println("\nOcurrio un error al agregar");
        }
        
    }

    private static void editTaskReminder() {
        System.out.println("Estas son las tareas que tienes creadas: \n");
        if(controller.taskCreated()==""){
            System.out.println("\n-No hay nada creado-");
        }else{
        System.out.println(controller.taskCreated());
        System.out.println("Ingrese el identificador de la tarea a editar: ");
        reader.nextLine();
        String id = reader.nextLine();
        System.out.println(controller.taskValue(id));

        System.out.println("\n¿Que elementos deseas cambiar?\n\n1.Identifier\n2.Titulo\n3.Descripcion\n4.Fecha prioridad");
        int option=reader.nextInt();
        String nuevoValor=null;
        if(option==1){
            System.out.println("\nSi cambias el identificador, se puede cambiar su ubicacion");
            System.out.println("Ingrese el nuevo valor");
            reader.nextLine();
            nuevoValor=reader.nextLine();
        }else{
            System.out.println("Ingrese el nuevo valor");
            reader.nextLine();
            nuevoValor=reader.nextLine();
        }

        if(controller.editTask(id, nuevoValor, option)){
            System.out.println("Editado con exito");
            System.out.println("Asi quedo editada: ");
                if(option==1){
                    System.out.println("\n"+controller.taskValue(nuevoValor));
                }else{
                    System.out.println("\n"+controller.taskValue(id));
                }
        }else{
            System.out.println("Ocurrio un error inesperado");
        }
        }
    }

    private static void deleteTaskReminder() {
        
        if(controller.taskCreated()==""){
            System.out.println("\n-No hay nada creado-");
        }else{
            System.out.println(controller.taskCreated());
            System.out.println("Ingrese el identificador de la tarea a eliminar: ");
            reader.nextLine();
            String id = reader.nextLine();
            if(controller.deleteTask(id)){
                System.out.println("Se elimino correctamente");
            }else{
                System.out.println("Ocurrio un error inesperado");
            }
        }
    }

    private static void printQueue(){
        System.out.println(controller.printQueue());
    }

    private static void printHistorial(){
        System.out.println(controller.printHistorial());
    }
}

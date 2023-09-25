package ui;
import java.util.Scanner;
import model.RSystem;

public class Main {

    static Scanner reader=new Scanner(System.in);
    static RSystem controller=new RSystem();
    static String colorMorado= "\u001B[35m";
    static String resetColor = "\u001B[0m";
    static String colorAmarillo="\u001B[33m";
    static String colorRGB="\u001B[38;5;12m";
    static String colorVerde="\u001B[32m";
    public static void main(String[] args) throws Exception {
<<<<<<< HEAD
        boolean flag=true;

        System.out.println("\n* * * * * * * * *\n"+
                "* ¡Bienvenido! *\n" +
                "* * * * * * * * *\n"
        );
        while(flag){
        System.out.println("\nQue deseas hacer");
        System.out.println("\n1. Agregar tarea\n2. Modificar\n3. Eliminar\n4. Salir");
        int optionMenu=reader.nextInt();
            switch(optionMenu){
=======
        System.out.println(colorMorado+"╔══════════════════════╗");
        System.out.println("║"+resetColor+"     ¡Bienvenido!     "+colorMorado+"║");
        System.out.println("╚══════════════════════╝"+resetColor);
        boolean flag1=true;
        boolean flag2=true;
        
        while(flag1){
        System.out.println(colorAmarillo+"\n-"+resetColor+"  ¿Que deseas hacer?  "+colorAmarillo+"-"+resetColor);
        System.out.println("\n1. Agregar Tarea/Recordatorio");
        System.out.println("2. Agregar tarea");
        System.out.println("3. Otras opciones");
        System.out.println("-------------------------");
        System.out.println("4. Salir");
        System.out.print(colorVerde+"\n>> "+resetColor);
        int optionMenu1=reader.nextInt();
            switch(optionMenu1){
                
>>>>>>> 2f6e88e86e02ad3378f22af6f48dabf3a7cc189e
            case 1:
                addReminderTask();
            break;

            case 2:
                addReminderTask();
            break;

            case 3:
                while(flag2){
                    System.out.println(colorMorado+"\n╔══════════════════════╗");
                    System.out.println("║"+resetColor+"      ¡Opciones!     "+colorMorado+" ║");
                    System.out.println("╚══════════════════════╝"+resetColor);
                    System.out.println("\n1. Editar ");
                    System.out.println("2. Eliminar");
                    System.out.println("-------------------------");
                    System.out.println("3. Volver");
                    int optionMenu2=reader.nextInt();

                    switch(optionMenu2){
                        case 1:
                            editTaskReminder();
                            break;
                        case 2:
                            deleteTaskReminder();
                            break;
                        case 3:
                        System.out.println(colorMorado+"\n╔══════════════════════╗");
                        System.out.println("║"+resetColor+"        ¡MENU!     "+colorMorado+"   ║");
                        System.out.println("╚══════════════════════╝"+resetColor);
                            flag2=false;
                            break;
                    }
                }
                break;
            case 4:
                System.out.println("Hasta luego");
                flag1=false;
            break;
        }
        }
        
    }

    private static boolean addReminderTask(){
        System.out.println("\nIngrese el Titulo");
        reader.nextLine();
        String title=reader.nextLine();
        System.out.println("\nIngrese la descripcion");
        String descripcion=reader.nextLine();
        System.out.println("\nIngrese la fecha limite (dd/MM/yyyy)");
        String fecha=reader.nextLine();
        System.out.println("\n¿Es prioritaria?");
        System.out.println("1. SI\n2. NO");
        int priority=reader.nextInt();
        return false;
    }

    private static void editTaskReminder(){

    }

    private static void deleteTaskReminder(){

    }

 
}

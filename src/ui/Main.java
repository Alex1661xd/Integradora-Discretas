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
        System.out.println(colorMorado+"╔══════════════════════╗");
        System.out.println("║"+resetColor+"     ¡Bienvenido!     "+colorMorado+"║");
        System.out.println("╚══════════════════════╝"+resetColor);
        boolean flag1=true;
        boolean flag2=true;
        
        while(flag1){
        System.out.println(colorAmarillo+"\n-"+resetColor+"  ¿Que deseas hacer?  "+colorAmarillo+"-"+resetColor);
        System.out.println("\n1. Agregar recordatorio");
        System.out.println("2. Agregar tarea");
        System.out.println("3. Otras opciones");
        System.out.println("4. Salir");
        System.out.print(colorVerde+"\n>> "+resetColor);
        int optionMenu1=reader.nextInt();
            switch(optionMenu1){
                
            case 1:
                addReminder();
            break;

            case 2:
               
                addTask();
            break;

            case 3:
                while(flag2){
                    System.out.println(colorAmarillo+"\n-"+resetColor+"    OPCIONES  "+colorAmarillo+"-"+resetColor);
                    System.out.println("\n1. Editar tarea/recordatorio");
                    System.out.println("2. Eliminar tarea/recordatorio");
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

    private static boolean addReminder(){
        System.out.println();
        return false;
    }

    private static void addTask(){


    }

    private static void editTaskReminder(){

    }

    private static void deleteTaskReminder(){

    }

 
}

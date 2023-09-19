package ui;
import java.util.Scanner;

public class Main {
    static Scanner reader=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        boolean flag=true;
        System.out.println("\n¡Bienvenido!");
        while(flag){
        System.out.println("\nQue deseas hacer");
        System.out.println("\n1. Agregar tarea\n2. Modificar\n3. Eliminar\n4. Salir");
        int optionMenu=reader.nextInt();
            switch(optionMenu){
            case 1:
                addTask();
            break;

            case 2:
                editTask();
            break;

            case 3:
                deleteTask();
            break;

            case 4:
                System.out.println("Hasta luego");
                flag=false;
            break;
        }
        }
        
    }

    private static void addTask(){
        searchUser();

    }

    private static void editTask(){

    }

    private static void deleteTask(){

    }

    private static int searchUser(){
        
        
        
        return -1;
    }
}

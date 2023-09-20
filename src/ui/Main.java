package ui;
import java.util.Scanner;
import model.RSystem;

public class Main {

    static Scanner reader=new Scanner(System.in);
    static RSystem controller=new RSystem();
    public static void main(String[] args) throws Exception {
        boolean flag=true;
        System.out.println("\n¡Bienvenido!");
        while(flag){
        System.out.println("\n¿Que deseas hacer?");
        System.out.println("\n1. Nuevo Usuario");
        System.out.println("2. Usuario Existente");

        int optionMenu1=reader.nextInt();
            switch(optionMenu1){
            case 1:
                reader.nextLine();
                System.out.print(">> ");
                System.out.println("Ingrese su nombre");
                String nameUser=reader.nextLine();
                System.out.print(">> ");
                System.out.println("Ingrese su id (Recuerde. Ex. ABC-123)");
                String idUser=reader.nextLine();
                if(addUserArray(nameUser, idUser)){
                    System.out.println("\n-Usuario añadido con exito-");
                }else{
                    System.out.println("\n-Ocurrio un error inesperado-");
                }
            break;

            case 2:
                System.out.println("\n¿Que usuario desea ver?");
                if(controller.printUsers().equals("")){
                    System.out.println("\n-No hay usuarios creados-");
                }else{
                    System.out.println(controller.printUsers());
                }
                
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

    private static boolean addUserArray(String name, String idUser){
        return controller.addUser(name, idUser);
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

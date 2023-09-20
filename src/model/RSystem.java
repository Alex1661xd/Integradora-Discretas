package model;
import java.util.ArrayList;

public class RSystem {
 
    
    static ArrayList<User> usuarios= new ArrayList<>();

    
    public boolean addUser(String name, String idUser){
        boolean flag=false;
        
        if(flag==false){
            usuarios.add(new User(name, idUser));
            return true;
        }

        return false;
    }

    public String printUsers(){
        String msg="";
        for(int i=0; i<usuarios.size();i++){
            msg+="\n"+(i+1)+". "+usuarios.get(i).getName()+" ("+usuarios.get(i).getIdUser()+")";
        }
        return msg;
    }
}

package model;

public class User {
    
    private String name;

    private String idUser;

    public User(String name, String idUser) {
        this.name = name;
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    
}

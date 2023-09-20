package model;

public class User {
    
    private String name;

    private String idUser;
    
    private HashTable hashTable;

    public User(String name, String idUser) {
        this.name = name;
        this.idUser = idUser;
        this.hashTable=new HashTable();
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

    public HashTable getHashTable() {
        return hashTable;
    }

    public void setHashTable(HashTable hashTable) {
        this.hashTable = hashTable;
    }

    
}

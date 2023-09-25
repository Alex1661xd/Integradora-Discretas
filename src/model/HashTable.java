package model;

public class HashTable {
     
    private DoubleLinkedList espacios [];

    public HashTable() {
        this.espacios= new DoubleLinkedList[10];
        for (int i = 0; i < espacios.length; i++) {
            espacios[i] = new DoubleLinkedList();
        }
    }

    public DoubleLinkedList[] getEspacios() {
        return this.espacios;
    }

    public void setEspacios(DoubleLinkedList[] espacios) {
        this.espacios = espacios;
    }

    

}

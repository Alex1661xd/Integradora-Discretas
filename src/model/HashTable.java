package model;

public class HashTable<K, V> implements IHashTable<K,V> {

    private Node<K, V>[] table;
    private int size;


    public HashTable(int size) {
        this.size=size;
        this.table = new Node[size];
    }

    public int FunctionHash(K key) {
        return key.hashCode()%size;
    }

    
    @Override
    public void insert(K key, V value) {
        int index = FunctionHash(key); // Calcula el índice utilizando la función hash
    
        Node<K, V> newNode = new Node<>(key, value); // Crea un nuevo nodo con la clave y el valor
    
        if (table[index] == null) {
            // Si no hay ningún nodo en ese índice, simplemente establece el nuevo nodo en ese índice
            table[index] = newNode;
        } else {
            // Si ya hay un nodo en ese índice, coloca el nuevo nodo en la cabeza de la lista enlazada
            newNode.setNext(table[index]);
            table[index].setPrevious(newNode);
            table[index] = newNode;
        }
    }
    

    @Override
    public V search(K key) {
        int index = FunctionHash(key); // Calcula el índice utilizando la función hash
    
        if (table[index] == null) {
            // No se encontró un nodo en ese índice, devuelve null o realiza el manejo adecuado
            return null;
        } else {
            // Recorre la lista enlazada en ese índice para buscar la clave y devolver el valor asociado
            Node<K, V> currentNode = table[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    return currentNode.getValue(); // Se encontró la clave, devuelve el valor asociado
                }
                currentNode = currentNode.getNext();
            }
            // La clave no se encontró en la lista enlazada, devuelve null o realiza el manejo adecuado
            return null;
        }
    }
    

    @Override
    public boolean delete(K key) {
        int index = FunctionHash(key); // Calcula el índice utilizando la función hash
    
        if (table[index] == null) {
            // No se encontró un nodo en ese índice, no hay nada que eliminar
            return false;
        } else {
            Node<K, V> currentNode = table[index];
            Node<K, V> previousNode = null;
    
            // Busca el nodo con la clave para eliminarlo
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    Node<K, V> nextNode = currentNode.getNext();
    
                    // Actualiza las referencias para eliminar el nodo
                    if (previousNode != null) {
                        previousNode.setNext(nextNode);
                    } else {
                        table[index] = nextNode; // Si es el primer nodo, actualiza la referencia de la tabla
                    }
    
                    if (nextNode != null) {
                        nextNode.setPrevious(previousNode);
                    }
    
                    return true; // Nodo eliminado exitosamente
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }
        return false; // No se encontró el nodo con la clave
    }
    

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        for (int i = 0; i < table.length; i++) {
            if(table[i]==null){
                continue;
            }else{
                stringBuilder.append("Index [").append(i).append("]: ");

                Node<K, V> currentNode = table[i];

                while (currentNode != null) {
                    stringBuilder.append("\n- (").append(currentNode.getKey()).append(", ").append(((Task)currentNode.getValue()).getTitle()).append(") ");
                    currentNode = currentNode.getNext();
                }
                stringBuilder.append("\n");
            }
 
        }
        
        return stringBuilder.toString();
    }


    

}


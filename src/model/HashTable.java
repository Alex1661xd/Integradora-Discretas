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
    public void delete(K key) {
        int index = FunctionHash(key); // Calcula el índice utilizando la función hash
    
        if (table[index] == null) {
            // No se encontró un nodo en ese índice, no hay nada que eliminar
            return;
        } else {
            Node<K, V> currentNode = table[index];
    
            // Caso especial: si el nodo a eliminar es el primer nodo en el índice
            if (currentNode.getKey().equals(key)) {
                table[index] = currentNode.getNext();
                if (table[index] != null) {
                    table[index].setPrevious(null);
                }
                return;
            }
    
            // Busca el nodo con la clave para eliminarlo
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    Node<K, V> previousNode = currentNode.getPrevious();
                    Node<K, V> nextNode = currentNode.getNext();
                    
                    // Actualiza las referencias para eliminar el nodo
                    if (previousNode != null) {
                        previousNode.setNext(nextNode);
                    }
                    if (nextNode != null) {
                        nextNode.setPrevious(previousNode);
                    }
                    return;
                }
                currentNode = currentNode.getNext();
            }
        }
    }
    

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < table.length; i++) {
            if(table[i]==null){
                stringBuilder.append("Posicion "+"["+i+"]"+": Vacia");
            }else{
                stringBuilder.append("Index ").append(i).append(": ");

                Node<K, V> currentNode = table[i];

                while (currentNode != null) {
                    stringBuilder.append("(").append(currentNode.getKey()).append(", ").append(currentNode.getValue()).append(") ");
                    currentNode = currentNode.getNext();
                }
            }

            
            stringBuilder.append("\n");
        }
        
        return stringBuilder.toString();
    }


    

}


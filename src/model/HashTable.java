package model;

public class HashTable<K, V> implements IHashTable<K,V> {

    private NodeHT<K, V>[] table;
    private int size;


    public HashTable(int size) {
        this.size=size;
        this.table = new NodeHT[size];
    }

    /**
    * Calculates the corresponding index for the given key using a hash function.
    *
    * @param key The key for which the index will be calculated.
    * @return The calculated index for the key.
    */
    public int FunctionHash(K key) {
        return key.hashCode()%size;
    }


    /**
     * Inserts a new key-value pair into the hash table.
     *
     * @param key The key to use to index the value.
     * @param value The value associated with the key.
    */
    @Override
    public void insert(K key, V value) {
        int index = FunctionHash(key); // Calculate the index using the hash function
    
        NodeHT<K, V> newNode = new NodeHT<>(key, value); // Create a new node with the key and value
    
        if (table[index] == null) {
            // If there is no node at that index, set the new node at that index
            table[index] = newNode;
        } else {
            // If there is already a node at that index, place the new node at the head of the linked list
            newNode.setNext(table[index]);
            table[index].setPrevious(newNode);
            table[index] = newNode;
        }
    }


    /**
    * Looks up the value associated with a key in the hash table.
    *
    * @param key The key to search for.
    * @return The value associated with the key or null if the key is not found in the table.
    */
    @Override
    public V search(K key) {
        int index = FunctionHash(key); // Calculate the index using the hash function
    
        if (table[index] == null) {
            // No node was found at that index, return null or do the appropriate handling
            return null;
        } else {
            // Loops through the linked list at that index to find the key and return the associated value
            NodeHT<K, V> currentNode = table[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    return currentNode.getValue(); // Key found, returns associated value
                }
                currentNode = currentNode.getNext();
            }
            // Key not found in linked list, return null or do appropriate handling
            return null;
        }
    }

    /**
    * Removes a key-value pair from the hash table.
    *
    * @param key The key of the pair to delete.
    * @return true if the key-value pair was deleted successfully, false if the key was not found in the table.
    */
    @Override
    public boolean delete(K key) {
        int index = FunctionHash(key); // Calculate the index using the hash function
    
        if (table[index] == null) {
            // No node was found in that index, there is nothing to delete
            return false;
        } else {
            NodeHT<K, V> currentNode = table[index];
            NodeHT<K, V> previousNode = null;
    
            // Find the node with the key to delete it
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    NodeHT<K, V> nextNode = currentNode.getNext();
    
                    // Update references to remove the node
                    if (previousNode != null) {
                        previousNode.setNext(nextNode);
                    } else {
                        table[index] = nextNode; // If it is the first node, update the table reference
                    }
    
                    if (nextNode != null) {
                        nextNode.setPrevious(previousNode);
                    }
    
                    return true; // Node removed successfully
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
        }
        return false; // The node with the key was not found
    }
    

    /**
    * Gets a string representation of the hash table and its elements.
    *
    * @return A string representing the hash table information and its elements.
    */
    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        for (int i = 0; i < table.length; i++) {
            if(table[i]==null){
                continue;
            }else{
                stringBuilder.append("Index [").append(i).append("]: ");

                NodeHT<K, V> currentNode = table[i];

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


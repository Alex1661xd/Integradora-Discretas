package model;

public class NodeHT<K, V> {

    private K key;
    private V value;
    private NodeHT<K, V> next;
    private NodeHT<K, V> previous;

    public NodeHT(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public NodeHT<K, V> getNext() {
        return next;
    }

    public void setNext(NodeHT<K, V> next) {
        this.next = next;
    }

    public NodeHT<K, V> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeHT<K, V> previous) {
        this.previous = previous;
    }
}

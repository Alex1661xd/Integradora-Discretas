package model;

public interface IHashTable<K,V> {
    public void insert(K key, V value);
    public V search(K key);
    public boolean delete(K Key);
    public String print();
}

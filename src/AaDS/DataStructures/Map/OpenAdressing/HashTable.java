/**
 * Linear probing, Open Addressing
 */

package AaDS.DataStructures.Map.OpenAdressing;

import AaDS.DataStructures.Map.MapX;

import java.lang.reflect.Array;
import java.util.Objects;

class HashItem<K, V>{
    private final K key;
    private V value;

    public HashItem(K key, V value) {
        if(key == null)throw new NullPointerException("Key is null");
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashItem<?, ?> hashItem = (HashItem<?, ?>) o;

        if (!key.equals(hashItem.key)) return false;
        return Objects.equals(value, hashItem.value);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HashItem{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
class HashTable<K, V> implements MapX<K, V> {
    public HashItem<K, V>[] getHashTable() {
        return hashTable;
    }

    private static final int MAX_MAP_LENGTH = 5;

    private HashItem<K, V>[] hashTable;
    private int size;
    private int capacity;

    public HashTable(){
        hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, MAX_MAP_LENGTH);
        capacity = MAX_MAP_LENGTH;
    }
    private HashTable(int capacity){ //used in resizing
        hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, MAX_MAP_LENGTH);
        this.capacity = capacity;
    }
    private int hash(K key){
        return key.hashCode() % capacity;
    }
    public void put(K key, V value){
        if(size >= capacity * 0.75f){
            resize(2 * capacity);
        }
        int index = hash(key);
        while(hashTable[index] != null){
            if(hashTable[index].getKey().equals(key)){
                hashTable[index].setValue(value);
                return;
            }
            index = (index + 1) % capacity;
        }
        hashTable[index] = new HashItem<>(key, value);
        size++;
    }
    private void resize(int newCap){
        HashTable<K, V> tempMap = new HashTable<>(newCap);
        for (int i = 0; i < capacity; i++){
            if(hashTable[i] != null){
                tempMap.put(hashTable[i].getKey(), hashTable[i].getValue());
            }
        }
        hashTable = tempMap.getHashTable();
        capacity = newCap;
    }
    public V get(K key){
        int index = hash(key);
        while(hashTable[index] != null){
            if(hashTable[index].getKey().equals(key)){
                return hashTable[index].getValue();
            }
            index = (index + 1) % capacity;
        }
        return null;
    }
    public V remove (K key){
        int index = hash(key);
        while(!hashTable[index].getKey().equals(key)){
            index = (index + 1) % capacity;
        }
        V toReturn = hashTable[index].getValue();
        hashTable[index] = null;
        size--;
        while(hashTable[index = (index + 1) % capacity] != null){
            HashItem<K, V> nextItem = hashTable[index];
            hashTable[index] = null;
            put(nextItem.getKey(), nextItem.getValue());
        }
        return toReturn;
    }
}

class Realization{
    public static void main(String[] args) {
        MapX<Integer, Integer> mapX = new HashTable<>();
        mapX.put(1, 10);
    }
}

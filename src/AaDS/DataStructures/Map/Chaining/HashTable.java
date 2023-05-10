/**
 * Chaining implementation
 */

package AaDS.DataStructures.Map.Chaining;

import AaDS.DataStructures.Map.MapX;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

class HashItem<K, V>{
    private final K key;
    private V value;
    private HashItem<K, V> nextHashItem;

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

    public HashItem<K, V> getNextHashItem() {
        return nextHashItem;
    }

    public void setNextHashItem(HashItem<K, V> nextHashItem) {
        this.nextHashItem = nextHashItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HashItem<?, ?> hashItem = (HashItem<?, ?>) o;

        if (!key.equals(hashItem.key)) return false;
        if (!Objects.equals(value, hashItem.value)) return false;
        return Objects.equals(nextHashItem, hashItem.nextHashItem);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (nextHashItem != null ? nextHashItem.hashCode() : 0);
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
    private static final int MAX_MAP_SIZE = 5;
    private final HashItem<K, V>[] hashTable;
    private int size;

    /*
    Данная запись на языке Java означает создание нового массива hashTable типа HashItem<K, V>[], который является массивом объектов типа HashItem.
    Размер массива равен константе MAX_MAP_SIZE, определенной где-то в коде.
    Array.newInstance - это статический метод класса java.lang.reflect.Array, который позволяет создать новый массив заданного типа и размера.
    Он принимает два параметра: тип массива (в данном случае HashItem.class) и размер массива (MAX_MAP_SIZE).
    HashItem - это, вероятно, пользовательский класс, представляющий элемент хеш-таблицы с ключом типа K и значением типа V. Создание массива HashItem<K, V>[]
    позволяет хранить множество таких элементов в хеш-таблице.
    */
    HashTable(){
        hashTable = (HashItem<K, V>[]) Array.newInstance(HashItem.class, MAX_MAP_SIZE);//this is how generic array initialized in JAVA
    }
    private int hash(K key){
        return key.hashCode() % MAX_MAP_SIZE;
    }

    public void put(K key, V value){
        int index = hash(key);//hashing our key
        HashItem<K, V> item = hashTable[index];//make hashItem (pair)
        while(item != null){
            if(item.getKey().equals(key)){
                item.setValue(value);//update an existing key
                return;
            }
            item = item.getNextHashItem();
        }
        HashItem<K, V> hashItem = new HashItem<>(key, value);
        hashItem.setNextHashItem(hashTable[index]);
        hashTable[index] = hashItem;
        size++;
    }

    public V get(K key){
        int index = hash(key);
        HashItem<K, V> item = hashTable[index];
        while(item != null){
            if(item.getKey().equals(key)){
                return item.getValue();
            }
            item = item.getNextHashItem();
        }
        return null;
    }
    public V remove(K key){
        int index = hash(key);
        HashItem<K, V> item = hashTable[index];
        HashItem<K, V> prev = null;//we need to keep track of the prev node

        while(item != null){
            if(item.getKey().equals(key)) break;
            prev = item;
            item = item.getNextHashItem();
        }
        if(item == null) return null;//key don't exist
        if(prev == null) hashTable[index] = item.getNextHashItem(); //remove the root hash item
        else prev.setNextHashItem(item.getNextHashItem());//the hash item we are remove not the root
        size--;
        return item.getValue();
    }
    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                 Arrays.toString(hashTable) +
                ", size=" + size +
                '}';
    }
}
class Implements {
    public static void main(String[] args) {
        HashTable<Integer, Integer> table = new HashTable<>();
        table.put(1, 10);
        table.put(2, 20);
        table.put(3, 30);
        table.put(4, 30);
        System.out.println(table);
        System.out.println(table.get(1));
    }
}


/**
 * General interface for my map
 */

package AaDS.DataStructures.Map;

public interface MapX<K, V> {
    V get(K key);
    void put(K key, V value);
    V remove(K key);
}

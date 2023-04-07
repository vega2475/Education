package AaDS.DataStructures.LinkedList;

public interface ListVEGA<T> {
    void add(T item);
    void remove(int index);
    T get(int index);
    long length();
    String toString();
    boolean isEmpty();
}

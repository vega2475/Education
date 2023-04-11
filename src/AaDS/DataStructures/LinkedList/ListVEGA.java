package AaDS.DataStructures.LinkedList;

public interface ListVEGA<T> {
    public void add(T item);
    public void remove(int index);
    public T get(int index);
    public long length();
    public String toString();
    public boolean isEmpty();
}

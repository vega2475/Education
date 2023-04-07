package AaDS.DataStructures.Stack;

public interface Stack<T> {
    void push(T item);
    T pop();

    T peek();

    boolean isEmpty();
}

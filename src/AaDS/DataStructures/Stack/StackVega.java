package AaDS.DataStructures.Stack;

import java.util.ArrayList;

public class StackVega<T> implements Stack<T> {

    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void push(T item) {
        list.add(0, item);
    }

    @Override
    public T pop() {
        return list.remove(0);
    }

    public T peek(){
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

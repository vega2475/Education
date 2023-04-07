package AaDS.DataStructures.Queue;

import java.util.ArrayList;

public class QueueVega<T> implements Queue<T> {

    private ArrayList<T> list = new ArrayList<>();

    @Override
    public void add(T item) {
        list.add(item);
    }

    @Override
    public T remove() {
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

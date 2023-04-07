package AaDS.DataStructures.LinkedList;

public class LinkedListVEGA<T> implements ListVEGA<T> {
    private Node head;
    private long size;
    class Node{
        T item;
        Node next;

        public Node(T item){
            this.item = item;
            next = null;
        }
    }


    @Override
    public void add(T item) {
        if(head == null){
            this.head = new Node(item);
        }else{
            Node tempNode = head;
            while(tempNode.next != null){
                tempNode = tempNode.next;
            }
            tempNode.next = new Node(item);
        }
        size++;
    }

    @Override
    public void remove(int index) {
        if(index == 0){
            head = head.next;
            size--;
            return;
        }
        int currentIndex = 0;
        Node tempNode = head;
        while(currentIndex < index){
            if(currentIndex + 1 == index){
                tempNode.next = tempNode.next.next;
            }
            tempNode = tempNode.next;
            currentIndex++;
        }
        size--;
    }

    @Override
    public T get(int index) {
        Node tempNode = head;
        int currentIndex = 0;
        while(currentIndex <= index){
            if(currentIndex == index) return tempNode.item;
            tempNode = tempNode.next;
            currentIndex++;
        }
        return null;
    }

    @Override
    public long length() {
        return size;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node tempNode = head;
        while(tempNode != null){
            sb.append(tempNode.item);
            if(tempNode.next != null) sb.append(" -> ");
            tempNode = tempNode.next;
        }
        sb.append(']');
        return sb.toString();
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}

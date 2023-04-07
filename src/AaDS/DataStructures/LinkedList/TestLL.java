package AaDS.DataStructures.LinkedList;

public class TestLL {
    public static void main(String[] args) {
        ListVEGA<Object> list = new LinkedListVEGA<>();
        System.out.println(list.isEmpty());
        list.add("Hi");
        list.add(11);
        list.add("Vega");
        list.add(22);
        list.remove(3);

        System.out.println(list.toString());
        System.out.println(list.get(2));
        System.out.println(list.length());
        System.out.println(list.isEmpty());
    }

}

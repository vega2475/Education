package AaDS.DataStructures.Queue;

public class QueueTest {
    public static void main(String[] args) {
        testQueue();
    }

    private static void testQueue(){
        QueueVega<Integer> queue = new QueueVega<>();

        for(int i = 0; i < 15; i++){
            System.out.print(i + " ");
            queue.add(i);
        }

        System.out.println();

        while (!queue.isEmpty()){
            System.out.print(queue.remove() + " ");
        }
    }
}

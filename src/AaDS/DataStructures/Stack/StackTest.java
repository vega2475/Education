package AaDS.DataStructures.Stack;



public class StackTest {
    public static void main(String[] args) {
        testStack();
    }

    private static void testStack(){
        StackVega<Integer> stack = new StackVega<>();
        for(int i = 0; i < 15; i++){
            System.out.print(i + " ");
            stack.push(i);
        }

        System.out.println();

        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}

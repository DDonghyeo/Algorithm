import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class StackS {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        //삭제하지 않고 top에 있는 값을 가져옴
        if (stack.peek() == 1) {
            stack.pop();
        }

        stack.push(1);
        stack.push(2);
        stack.push(3);

        if (stack.search(4) ==  -1 ) {
            System.out.println("4 is not in stack");
        }


        HashMap<String, Integer> hashMap = new HashMap<>();

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        priorityQueue.






    }
}

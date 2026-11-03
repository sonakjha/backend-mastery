import java.util.*;
public class LinkedListAsQueue {

    public static void main(String[] args){

    Queue<String> queue = new LinkedList<>();

    queue.offer("A");
    queue.offer("B");
    queue.offer("C");

    System.out.println(queue);

    System.out.println(queue.peek());
    System.out.println(queue);
    System.out.println(queue.poll());
    System.out.println(queue);
    }
}

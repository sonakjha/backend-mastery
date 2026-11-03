import java.util.*;
public class PriorityQueue01 {

    public static void main(String[] args) {
        //PriorityQueue<Integer> queue = new PriorityQueue<>();
        PriorityQueue<Integer> queue =new PriorityQueue<>(Comparator.reverseOrder());
        queue.offer(50);
        queue.offer(10);
        queue.offer(30);
        queue.offer(20);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
    
}

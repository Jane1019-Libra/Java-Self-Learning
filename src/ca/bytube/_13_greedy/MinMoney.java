package ca.bytube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinMoney {
    public static int minMoney(int[] arr) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for (int data:arr) queue.add(data);
        int cost = 0;
        while(queue.size() > 1) {
            int a = queue.poll();
            int b = queue.poll();
            cost += a;
            cost += b;
            queue.add(a+b);

        }
        return cost;
    }

}

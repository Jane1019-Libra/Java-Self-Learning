package ca.bytube._08_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void topk(Integer[] data, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < data.length; i++) {
            if (minHeap.size() < k) minHeap.add(data[i]);
            else {
                if (minHeap.peek() >= data[i]) continue;
                else {
                    minHeap.poll();
                    minHeap.add(data[i]);
                }
            }
        }

    }
    public static void topK(Integer[] data, int k) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        for (int i = 0; i < data.length; i++) {
            if (heap.size < k) heap.add(data[i]);
            else {
                if (heap.get() >= data[i]) continue;
                else {
                    heap.replace(data[i]);
                }
            }
        }
    }
}

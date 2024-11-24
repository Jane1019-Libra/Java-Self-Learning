package ca.bytube._00_leetcode.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow1(int[] nums, int k) {
        int[] maxes = new int[nums.length - k + 1];
        int maxIndex = 0;
        for (int i = 0; i < k; ++i) {
            if (nums[i] > nums[maxIndex]) maxIndex = i;
        }

        for (int li = 0; li < maxes.length; li++) {
            int ri = li + k - 1;
            if (maxIndex < li)  {
                maxIndex = li;
                for (int i = li; i < ri; ++i) {
                    if (nums[i] > nums[maxIndex]) maxIndex = ri;
                }
            }
            else if (nums[ri] >= nums[maxIndex]) maxIndex = ri;

            maxes[li] = nums[maxIndex];
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] maxes = new int[nums.length - k + 1];
        Deque<Integer> deque= new LinkedList<>();
        deque.size();
        for (int i = 0; i < nums.length; ++i) {
            while(! deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) deque.pollLast();
            deque.addLast(i);
            int w = i-k + 1;
            if (w < 0) continue;
            if (w > deque.peekFirst()) deque.removeFirst();
            maxes[w] = nums[deque.peekFirst()];
        }
        return maxes;
    }
}

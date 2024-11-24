package ca.bytube._00_leetcode.stack;

import java.util.LinkedList;
import java.util.Queue;

public class MyStack {
    Queue<Integer> data;
    Queue<Integer> help;

    public MyStack() {
        data = new LinkedList<>();
        help = new LinkedList<>();
    }

    public void push(int x) {
        data.offer(x);
    }

    private void swap(){
        Queue<Integer> temp = help;
        help = data;
        data = temp;
    }
    public int pop() {
        while (data.size() > 1) help.offer(data.poll());
        Integer poll = data.poll();
        swap();
        return poll;
    }

    public int top() {
        while (data.size() > 1) help.offer(data.poll());
        Integer poll = data.poll();
        help.offer(poll);
        swap();
        return poll;
    }

    public boolean empty() {
        return data.isEmpty() && help.isEmpty();
    }
}

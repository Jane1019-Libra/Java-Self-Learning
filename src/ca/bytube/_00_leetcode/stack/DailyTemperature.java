package ca.bytube._00_leetcode.stack;

import java.util.Stack;

public class DailyTemperature {
    public int[] dailyTemperature(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; ++i) {
            while (!stack.empty() && temperatures[i] > temperatures[stack.peek()]) {

                result[stack.peek()] = i-stack.pop();

            }
            stack.push(i);

        }
        return result;
    }
}

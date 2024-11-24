package ca.bytube._11_recursion;

import java.util.Stack;

public class RecursivetoNonRecursive {
    public void log(int n) {
        if (n < 1) return;
        log(n-1);
        int v = n + 10;
        System.out.println(v);
    }

    public static void log1(int n) {
        if (n < 1) return;
        Stack<Frame> stack = new Stack<>();
        while (n > 0) {
            stack.push(new Frame(n,n+10));
            n--;
        }
        while(!stack.isEmpty()) {
            Frame frame = stack.pop();
            System.out.println(frame.v);
        }
    }

    public static void log2(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.println(i+10);
        }
    }
    private static class Frame {
        int n;
        int v;

        public Frame(int n, int v) {
            this.n = n;
            this.v = v;
        }
    }
}

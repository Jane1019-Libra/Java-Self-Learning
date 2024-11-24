package ca.bytube._00_leetcode.stack;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') stack.push(ch);
            else {
                if (stack.isEmpty()) return false;
                Character left = stack.pop();
                if (left == '(' && ch != ')') return false;
                if (left == '[' && ch != ']') return false;
                if (left == '{' && ch != '}') return false;
            }
        }
        return stack.isEmpty();
    }
}

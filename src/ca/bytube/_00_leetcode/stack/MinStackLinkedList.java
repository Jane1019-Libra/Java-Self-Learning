package ca.bytube._00_leetcode.stack;

public class MinStackLinkedList {
    Node head;
    private class Node{
        int val;
        Node next;
        int min;

        public Node(int val, Node next, int min) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

    public MinStackLinkedList() {
        head = new Node(0, null, Integer.MAX_VALUE);
    }

    public void push(int val) {
        head = new Node(val, head, Math.min(head.min, val));
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int min() {
        return head.min;
    }
}

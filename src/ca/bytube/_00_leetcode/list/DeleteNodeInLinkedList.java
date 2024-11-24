package ca.bytube._00_leetcode.list;

public class DeleteNodeInLinkedList {
    public void DeleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

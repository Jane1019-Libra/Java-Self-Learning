package ca.bytube._00_leetcode.list;

public class RemoveDuplicatesFromSortedList{
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode root = head;
        while (root.next != null) {
            if (root.val == root.next.val) root.next = root.next.next;
            else root = root.next;
        }
        return head;
    }
}

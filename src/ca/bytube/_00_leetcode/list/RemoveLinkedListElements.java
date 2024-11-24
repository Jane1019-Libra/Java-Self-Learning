package ca.bytube._00_leetcode.list;

public class RemoveLinkedListElements {
    //构建新链表，和构建新数组不一样，空间复杂度不会提升
    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = null;
        ListNode newTail = null;
        while (head != null) {
            if (head.val != val) {
                if (newHead == null) {
                    newHead = head;
                    newTail = head;
                } else {
                    newTail.next = head;
                    newTail = head;
                }
            }
            head = head.next;
        }
        if (newTail != null) newTail.next = null;
        return newHead;
    }
}

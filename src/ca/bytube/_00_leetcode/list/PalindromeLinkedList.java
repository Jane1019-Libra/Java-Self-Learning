package ca.bytube._00_leetcode.list;

public class PalindromeLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
    public boolean isPalindrome(ListNode head) {
        if(head.next == null) return true;

        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode dummy = slow;
        ListNode secondHalf = reverseList(slow);
        while(secondHalf != null)
        {
            if(head.val != secondHalf.val) return false;
            head = head.next;
            secondHalf = secondHalf.next;
        }
        reverseList(secondHalf);
        return true;
    }
}

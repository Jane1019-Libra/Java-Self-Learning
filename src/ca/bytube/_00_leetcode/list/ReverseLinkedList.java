package ca.bytube._00_leetcode.list;

public class ReverseLinkedList {
    /*
    recursion 的思路： 假设这个方法已经实现了
    主人公心态：觉得自己的已经写好了
    因为recursion的每一个逻辑都是相同的
    比如reverseList 功能就是反转链表
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 头插法做
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }
}
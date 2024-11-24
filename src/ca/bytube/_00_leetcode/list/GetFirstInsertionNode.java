package ca.bytube._00_leetcode.list;



public class GetFirstInsertionNode {
    public static void main(String[] args) {

    }

    public static Node getFirstInsertionNode(Node head1, Node head2) {
        Node cycleNode1 = getCycleNode(head1);
        Node cycleNode2 = getCycleNode(head2);
        if (cycleNode1 == null && cycleNode2 == null)
            return getNoCycleInsertionNode(head1, head2);
        else if (cycleNode1 != null && cycleNode2 != null)
            return getCycleInsertionNode(head1, cycleNode1, head2, cycleNode2);
        else return null;
    }

    public static class Node {
        int val;
        Node next;
        public Node(int x) { val = x; }
    }

    public static Node getCycleInsertionNode(Node head1, Node cycleNode1, Node head2, Node cycleNode2) {
        Node cur1 = null;
        Node cur2 = null;
        if (cycleNode1 == cycleNode2) {
            cur1 = head1;
            cur2 = head2;
            int difference = 0;
            while (cur1 != cycleNode1) {
                cur1 = cur1.next;
                difference++;
            }
            while (cur2 != cycleNode2) {
                cur2 = cur2.next;
                difference--;
            }
            cur1 = difference > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            difference = Math.abs(difference);
            while (difference != 0) {
                cur1 = cur1.next;
                difference--;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = cycleNode1.next;
            while(cur1 != cycleNode1) {
                if (cur1 == cycleNode2) {
                    return cycleNode1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
    public static Node getNoCycleInsertionNode(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int difference = 0;
        while (cur1 != null) {
            cur1 = cur1.next;
            difference++;
        }
        while (cur2 != null) {
            cur2 = cur2.next;
            difference--;
        }
        cur1 = difference > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;
        difference = Math.abs(difference);
        while (difference != 0) {
            cur1 = cur1.next;
            difference--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
    public static Node getCycleNode(Node head) {
        Node slow = head.next;
        Node fast = head.next.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

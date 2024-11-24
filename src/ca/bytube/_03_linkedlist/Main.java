package ca.bytube._03_linkedlist;

import ca.bytube._03_linkedlist.doubly.DoubleLinkedList;
import ca.bytube._03_linkedlist.singly.SingleCircularLinkedList;

public class Main {
    public static void main(String[] args) {
        SingleCircularLinkedList<Integer> a = new SingleCircularLinkedList<>();
        for (int i = 0; i < 4; i++) a.add(i + 10);
        System.out.println();
        while(!a.isEmpty()) {
            System.out.println(a.remove(0));
            System.out.println();
        }
    }
}
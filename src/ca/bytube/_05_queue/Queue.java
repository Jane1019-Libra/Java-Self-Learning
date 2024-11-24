package ca.bytube._05_queue;

import ca.bytube._05_queue.doubly.DoubleLinkedList;

public class Queue<E> {
    private DoubleLinkedList<E> list = new DoubleLinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public void enQueue(E element) {
        list.add(element);
    }

    public E deQueue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }


}

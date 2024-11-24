package ca.bytube._03_linkedlist.doubly;

import ca.bytube._03_linkedlist.AbstractList;

public class DoubleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> next, Node<E> prev){
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }
    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node;
        if (index < (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) node = node.next;
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) node = node.prev;
        }
        return node;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            Node<E> oldLast = last;
            Node<E> newLast = new Node<> (element,null, oldLast);
            last = newLast;
            if (size == 0) first = newLast;
            else oldLast.next = newLast;
        } else {
            Node<E> nextNode = node(index);
            Node<E> prevNode = nextNode.prev;
            Node<E> newNode = new Node<>(element, nextNode, prevNode);
            nextNode.prev = newNode;
            if (index == 0) first = newNode;
            else prevNode.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        E oldElement = oldNode.element;
        oldNode.element = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        if (index == 0) first = nextNode;
        else prevNode.next = nextNode;

        if (index == size - 1) last = prevNode;
        else nextNode.prev = prevNode;

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }
}

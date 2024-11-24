package ca.bytube._03_linkedlist.singly;

// Linked List 存在的目的：dynamic array waster memory space
//              apply as much memory as we use
//              linear list of chain storage, the memory address of
//              all elements are not necessary continuous

// how to design a node
// class Node {
//       int element;
//       Node next; // 设计成Node 的类型， 语法上是个variable，用reference解决
//    }

// first 指向的node 是 head node
// next 如果是null，这样的node 就是last node or tail node

// 为什么还需要加一个接口？ 因为面向接口编程，这样的好处就是抽象类不能实力化

import ca.bytube._03_linkedlist.AbstractList;

public class SingleCircularLinkedList<E> extends AbstractList<E> {
    Node<E> first;

    private static class Node <E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next){
            this.element = element;
            this.next = next;
        }
    }

    public void clear() {
        size = 0;
        first = null;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) node = node.next;
        return node;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        // head （头部添加）
        size++;
        if (index == 0) {
            /*
            如果不是cycle 的情况，直接一行就够了
            first = new Node<>(element, first);
             */
            Node<E> newFirst = new Node<>(element, first);
            first = newFirst;
            Node<E> last = node(size - 1);
            last.next = newFirst;
        }
        // current + head （现在为止添加）
        else {
            Node<E> prevNode = node(index - 1);
            prevNode.next = new Node<>(element, prevNode.next);
        }
        //tail （尾部添加）

        //first node （第一个item 添加）
    }

    public E get(int index) {
        rangeCheck(index);
        return node(index).element;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        Node<E> oldNode = node(index);
        E oldElement = oldNode.element;
        oldNode.element = element;
        return oldElement;
    }

    public E remove(int index) {
        rangeCheck(index);
        // java的东西需要初始
        Node<E> removed = first;
        /*
        只针对first element的时候
        if (index == 0) first = first.next;
         */
        //head
        if (index == 0) {
            if (size == 1) first = null;
            else {
                first = removed.next;
                Node<E> last = node(size - 1);
                System.out.println("last element here is"+last.element);
                last.next = first;
            }
        } else {
            //current + tail 都可以用这一块
            Node<E> prevNode = node(index - 1);
            removed = prevNode.next;
            prevNode.next = removed.next;
        }
        //tail
        //only one node
        size--;
        return removed.element;
    }


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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(", elements=[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            sb.append(node.element);
            node = node.next;
            if (i != size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

package ca.bytube._08_heap;

import java.util.Comparator;

public class BinaryHeap<E> extends AbstractHeap<E> {

    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(Comparator<E> comparator, E[] elements) {
        super(comparator);
        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            int length = elements.length;
            size = length;
            length = Math.max(length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[length];
            for (int i = 0; i < length; i++) {
                this.elements[i] = elements[i];
            }
        }
        heapify();
    }

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(E[] elements) {
        this(null, elements);
    }

    public BinaryHeap() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public BinaryHeap(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        this.elements = (E[]) new Object[capacity];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size++] = element;

        siftUp(size - 1);
    }

    private void siftUp(int index) {
        E e = elements[index];
        while(index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(e, parent) <= 0) break;
            elements[index] = parent;
            index = parentIndex;
        }
        elements[index] = e;
    }

    @Override
    public E get() {
        EmptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        E removeVal = elements[0];
        int lastIndex = --size;
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        siftDown(0);
        return removeVal;
    }

    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rightIndex = childIndex + 1;
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }
            if (compare(element, child) >= 0) break;
            elements[index] = child;
            index = childIndex;
        }

        elements[index] = element;
    }
    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void EmptyCheck() {
        if (elements.length == 0 || elements == null) throw new RuntimeException("heap is empty");
    }

    private void heapify() {
        for (int i = (size >> 1) - 1; i >= 0; --i) siftDown(i);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) throw new IllegalArgumentException("Element cannot be null");
    }
    private void ensureCapacity(int capacity) {
        //1 获取老容量
        int oldCapacity = elements.length;
        //2 如果老容量 >= capacity 不用扩容
        if (oldCapacity >= capacity) return;
        //3 else 扩容1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        //4 值的迁移
        for (int i = 0; i < size; i++) newElements[i] = elements[i];
        //5 返回扩容后的容器
        elements = newElements;
    }
}

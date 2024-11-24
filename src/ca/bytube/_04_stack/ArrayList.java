package ca.bytube._04_stack;

public class ArrayList <E> extends AbstractList<E> {
    // 盛放数据
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    //Clear all elements
    public void clear() {
        //可以理解size 为指针 aka 可以接触到array的范围
        //用来控制可以操作arraylist的范围
        //就是size 至0
        //这样不用把每个element remove
        //我理解的是下一个insert 我可以加到的位置
        size = 0;
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        //return -1; //hardcode
        return ELEMENT_NOT_FOUND;
    }

    //Add elements to the index position
    public void add(int index, E element) {
        //需要考虑范围问题 index是不是超额 或者是一个负数
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) elements[i] = elements[i-1];
        elements[index] = element;
        size++;
    }

    //Return the element corresponding to the index position
    public E get(int index) {
        rangeCheck(index);
        return elements[index];
    }

    //Set the element at the index position
    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    //Remove the index of the element
    public E remove(int index) {
        rangeCheck(index);
        E oldElement = elements[index];
        for (int i = index + 1; i < size; i++) elements[i-1] = elements[i];
        size--;
        return oldElement;
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

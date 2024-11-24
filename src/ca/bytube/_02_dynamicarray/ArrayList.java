package ca.bytube._02_dynamicarray;

/*
ArrayList:
前生：array ---> address of all elements are continuous
           ---> disadvantages: cannot be modified dynamically
           --->                but we want the array be
           --->                changed dynamically.
现在：ArrayList: 可以用下标快速找到想要的，如果有查询就用这个
 */

public class ArrayList <E>{
    // 盛放数据
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    private int size;

    public ArrayList(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    //Number of elements
    //如果这边直接返回elements.length, 有些地方写起来会有些麻烦
    //比如 如果容器内什么东西都没装的话
    //or 当前数据有没有可能出现其他问题，导致这边的length不能反应出来真实的长度
    //java 源码也涉及到了size， private int size （which is the size of the array list）
    public int size() {
        //return elements.length;
        return size;
    }

    //Is it empty
    public boolean isEmpty() {
        return size == 0;
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

    //Add elements to the end
    public void add(E element) {
        add(size, element);
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

    //Contains a certain element
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + " size : " + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) outOfBounds(index);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) outOfBounds(index);
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
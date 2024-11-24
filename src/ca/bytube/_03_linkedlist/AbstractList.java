package ca.bytube._03_linkedlist;


/*
   为什么抽象父类：包含属性 + 责任划分
   1. 有父类本身的功能：能够在类种定义属性
   2. 抽象父类 可以写抽象方法 也可以写非抽象方法
   为什么多加一层interface 而不是直接设计成一整盒的抽象父类？
   为什么要多加一层
   面向接口编程，这样做的好处就是在一般情况下 抽象父类不能实例化
   list 里面只有抽象方法和常量 但是安全性比如size 这个属性不相对外界公布
   所以abstract 不向外公布
 */
public abstract class AbstractList <E> implements List<E>{
    protected int size;

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void add (E element) { add(size, element); }

    public boolean contains(E element) { return indexOf(element) != ELEMENT_NOT_FOUND;}

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("index: " + index + " size : " + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) outOfBounds(index);
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) outOfBounds(index);
    }

}

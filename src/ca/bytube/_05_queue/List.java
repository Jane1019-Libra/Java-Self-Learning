package ca.bytube._05_queue;

/*
 为什么定义成接口：因为有常量 和需要不同implement的方法 但是接口不能有属性和共有方法
 虽然可用default 但是。。。。。属性还是不能被解决，希望属性还是能够向上抽取
 */
public interface List<E> {
    public static final int ELEMENT_NOT_FOUND = -1;
    public int size();
    public boolean isEmpty();
    public void clear();
    public void add(E element);
    public void add(int index, E element);
    public E get(int index);
    public E set(int index, E element);
    public E remove(int index);
    public boolean contains(E element);
    public int indexOf(E element);
}

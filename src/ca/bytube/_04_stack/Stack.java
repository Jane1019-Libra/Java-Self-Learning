package ca.bytube._04_stack;

public class Stack<E>{
    private ArrayList<E> list = new ArrayList<>();

    public void push(E element) {
        list.add(element);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }

    public void clear(){
        list.clear();
    }
}

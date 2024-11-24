package ca.bytube._06_Tree;

public class Person implements Comparable<Person>{
    int age;

    String name;

    @Override
    public int compareTo(Person o) {
        return this.age - o.age;
    }
}

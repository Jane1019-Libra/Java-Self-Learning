package ca.bytube._03_linkedlist;

public class Person {
    public int age;
    public String name;

    public Person(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "age="+age + " name=" + name;
    }
}

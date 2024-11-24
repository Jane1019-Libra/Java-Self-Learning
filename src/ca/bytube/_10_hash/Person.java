package ca.bytube._10_hash;

import java.util.HashMap;

public class Person {
    public int age;
    public String name;
    public float height;

    public Person() {}

    public Person(int age,float height,String name) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.age);
        hashCode = hashCode * 31 + Float.hashCode(this.height);
        hashCode = hashCode * 31 + (this.name != null ? this.name.hashCode() : 0);
        return hashCode;
    }

    public static void main(String[] args) {
        Person p1 = new Person(54, 17.7f, "jack");
        Person p2 = new Person(45, 18.2f, "pony");
        Person p3 = new Person(54, 17.7f, "jack");

        HashMap<Person, Integer> map = new HashMap<>();
        map.put(p1,999);
        map.put(p2,222);
        map.put(p3,555);


    }
}

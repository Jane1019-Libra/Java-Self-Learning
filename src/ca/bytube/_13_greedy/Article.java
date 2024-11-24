package ca.bytube._13_greedy;

public class Article {
    public int weight;
    public int value;

    public double density;

    public Article(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.density = value * 1.0 /weight;
    }
}

package ca.bytube._13_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Knapsack {

    public static void main(String[] args) {
    }

    public static int select(int W, Article[] articles, Comparator<Article> comparator) {
        int value = 0;
        Arrays.sort(articles, comparator);
        int weight = 0;
        List<Article> list = new ArrayList<>();
        for (int i = 0; i < articles.length && weight <= W; ++i) {
            int newweight = articles[i].weight + weight;
            if (newweight <= W) {
                weight = newweight;
                value += articles[i].value;
                list.add(articles[i]);
            }
        }


        return value;
    }
}

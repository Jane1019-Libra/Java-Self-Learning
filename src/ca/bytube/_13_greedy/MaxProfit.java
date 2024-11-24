package ca.bytube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxProfit {
    private static class Project {
        int cost;
        int profit;

        public Project(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }
    }

    public static int maxProfit(int[] costs, int[] profits, int k, int m) {
        Project[] project = new Project[costs.length];
        for (int i = 0; i < costs.length; ++i) {
            project[i] = new Project(costs[i], profits[i]);
        }

        // cost 划分项目，装进小根堆
        PriorityQueue<Project> minCostQ = new PriorityQueue<>(new Comparator<Project>() {
            public int compare(Project p1, Project p2) {
                return p1.cost - p2.cost;
            }
        });
        minCostQ.addAll(Arrays.asList(project));

        PriorityQueue<Project> maxProfitQ = new PriorityQueue<>(new Comparator<Project>() {
            public int compare(Project p1, Project p2) {
                return p2.profit - p1.profit;
            }
        });
        while (k > 0) {
            while (!minCostQ.isEmpty() && minCostQ.peek().cost <= m) {
                maxProfitQ.add(minCostQ.poll());
            }

            Project project_tmp = maxProfitQ.poll();
            if (project_tmp == null) break;
            System.out.println(project_tmp);
            m += project_tmp.profit;
            k--;
        }
        return m;
    }
}

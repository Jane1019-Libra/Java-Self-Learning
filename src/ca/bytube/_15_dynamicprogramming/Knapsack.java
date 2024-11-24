package ca.bytube._15_dynamicprogramming;

public class Knapsack {
    public static int knapsack(int[] values, int[] weights, int W) {
        int[][] dp = new int[values.length + 1][W+1];
        for (int i = 1; i <= values.length; ++i) {
            for (int j = 1; j <= W; ++j) {
                if (j < weights[i-1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
            }
        }
        return dp[values.length][W];
    }

    public static int knapsack2(int[] values, int[] weights, int W) {
        int[] dp = new int[W+1];
        for (int i = 1; i <= values.length; ++i) {
            for (int j = W; j >= 1; --j) {
                if (j < weights[i-1]) continue;
                else dp[j] = Math.max(dp[j], dp[j-weights[i-1]] + values[i-1]);
            }
        }
        return dp[W];
    }

    public static int knapsack3(int[] values, int[] weights, int W) {
        int[] dp = new int[W+1];
        for (int i = 1; i <= values.length; ++i) {
            for (int j = W; j >= weights[i-1]; --j) {
                dp[j] = Math.max(dp[j], dp[j-weights[i-1]] + values[i-1]);
            }
        }
        return dp[W];
    }
}

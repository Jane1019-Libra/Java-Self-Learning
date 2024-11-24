package ca.bytube._15_dynamicprogramming;

import java.util.Arrays;

public class CoinChange {
    public static int coinChange(int n) {
        if (n < 1) return Integer.MAX_VALUE;
        if (n == 25 || n == 20 || n == 5 || n == 1) return 0;
        int min1 = Math.min(coinChange(n-25), coinChange(n-20));
        int min2 = Math.min(coinChange(n-5), coinChange(n-1));

        return Math.min(min1, min2) + 1;
    }

    public static int coinChange2(int[] coins, int n) {
        if (n < 1) return Integer.MAX_VALUE;
        int[] dp = new int[n+1];
        for (int each : coins) {
            if (n < each) continue;
            dp[each] = 1;
        }
        return coinChange2(n, dp);
    }

    private static int coinChange2(int n, int []dp) {
        if (n < 1) return Integer.MAX_VALUE;
        if (dp[n] == 0) {
            int min1 = Math.min(coinChange2(n-25, dp), coinChange2(n-20,dp));
            int min2 = Math.min(coinChange2(n-5, dp), coinChange2(n-1,dp));
            dp[n] = Math.min(min1, min2) + 1;
        }
        return dp[n];
    }

    public static int coinChange3(int n, int[] coins) {
        int [] dp = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            if (i >= 1) min = Math.min(dp[i-1], min);
            if (i >= 5) min = Math.min(dp[i-5], min);
            if (i >= 20) min = Math.min(dp[i-20], min);
            if (i >= 25) min = Math.min(dp[i-25], min);
            dp[i] = min + 1;
        }
        return dp[n];
    }

    // Leetcode
    public static int coinChange4(int n, int[] coins) {
        Arrays.sort(coins);
        int [] dp = new int[n+1];
        for (int i = 1; i <= n; ++i) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin || dp[i-coin] == -1) continue;
                min = Math.min(dp[i-coin], min);
            }
            if (min == Integer.MAX_VALUE) dp[i] = -1;
            else dp[i] = min + 1;
        }
        return dp[n];
    }

}

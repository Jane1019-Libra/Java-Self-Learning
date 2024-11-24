package ca.bytube._15_dynamicprogramming;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int [] nums) {
        int max = 1;
        int [] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] < nums[j]) continue;
                else dp[i]  = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

package ca.bytube._15_dynamicprogramming;

public class LongestCommonSubsequence {

    public static int LCS(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <=nums1.length; ++i) {
            for (int j = 1; j <= nums2.length; ++j) {
                if (nums1[i-1] == nums2[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[nums1.length][nums2.length];
    }

    public static int LCS3(int[] nums1, int[] nums2) {
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <=nums1.length; ++i) {
            for (int j = 1; j <= nums2.length; ++j) {
                int row = i & 1;
                int preRow = (i-1) & 1;
                if (nums1[i-1] == nums2[j-1]) dp[row][j] = dp[preRow][j-1] + 1;
                else dp[row][j] = Math.max(dp[preRow][j], dp[row][j-1]);
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }

    public static int LCS4(int[] nums1, int[] nums2) {
        int[]dp = new int[nums2.length + 1];
        for (int i = 1; i <=nums1.length; ++i) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; ++j) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i-1] == nums2[j-1]) dp[j] = leftTop + 1;
                else dp[j] = Math.max(dp[j], dp[j-1]);
            }
        }
        return dp[nums2.length];
    }

    public static int LCS5(int[] nums1, int[] nums2) {
        int[]dp = new int[nums2.length + 1];
        for (int i = 1; i <=nums1.length; ++i) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; ++j) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i-1] == nums2[j-1]) dp[j] = leftTop + 1;
                else dp[j] = Math.max(dp[j], dp[j-1]);
            }
        }
        return dp[nums2.length];
    }

    public int LCS1(int[] nums1, int[] nums2) {
        return LCS(nums1, nums1.length, nums2, nums2.length);
    }

    public int LCS(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i-1] == nums2[j-1]) return LCS(nums1, i-1, nums2, j-1) + 1;
        else return Math.max(LCS(nums1, i, nums2, j-1), LCS(nums1, i-1, nums2, j));
    }
}

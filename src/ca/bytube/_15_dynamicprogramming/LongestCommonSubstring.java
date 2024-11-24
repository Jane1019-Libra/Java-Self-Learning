package ca.bytube._15_dynamicprogramming;

public class LongestCommonSubstring {
    public static int longestCommonSubstring(String str1, String str2) {
        char [] chars1 = str1.toCharArray();
        if (chars1.length == 0) return 0;
        char [] chars2 = str2.toCharArray();
        if (chars2.length == 0) return 0;
        int max = 0;
        int [][] dp = new int[chars1.length + 1][chars2.length + 1];
        for (int i = 1; i <= chars1.length; ++i) {
            for (int j = 1; j <= chars2.length; ++j) {
                if (chars1[i-1] != chars2[j-1]) continue;
                dp[i][j] = dp[i-1][j-1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static int longestCommonSubstring1(int[] nums1, int[] nums2) {
        int[]dp = new int[nums2.length + 1];
        int max = 0;
        for (int i = 1; i <=nums1.length; ++i) {
            int cur = 0;
            for (int j = 1; j <= nums2.length; ++j) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i-1] == nums2[j-1]) dp[j] = leftTop + 1;
                else continue;
                max = Math.max(dp[j], max);
            }
        }
        return max;
    }

    public static int longestCommonSubstring2(int[] nums1, int[] nums2) {
        int[]dp = new int[nums2.length + 1];
        int max = 0;
        for (int i = 1; i <=nums1.length; ++i) {
            for (int j = nums2.length; j >= 1; --j) {
                if (nums1[i-1] == nums2[j-1]) dp[j] = dp[j-1] + 1;
                else continue;
                max = Math.max(dp[j], max);
            }
        }
        return max;
    }
}

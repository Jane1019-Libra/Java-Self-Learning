package ca.bytube._14_divideandconquer;

import java.util.ArrayList;
import java.util.List;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length);
    }

    public int maxSubArray(int[] nums, int start, int end) {
        int mid = (start + end) >> 1;
        int leftSum = 0;
        int leftMax = Integer.MIN_VALUE;
        for (int i = mid - 1; i >= 0; --i) {
            leftSum += nums[i];
            if (leftSum > leftMax) leftMax = leftSum;
        }
        int rightSum = 0;
        int rightMax = Integer.MIN_VALUE;
        for (int i = mid; i < end; ++i) {
            rightSum += nums[i];
            if (rightSum > rightMax) rightMax = rightSum;
        }
        return Math.max(maxSubArray(nums, start, mid), Math.max(maxSubArray(nums, mid, end), leftMax + rightMax));
    }
}
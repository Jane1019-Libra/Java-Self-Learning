package ca.bytube._00_leetcode.tree;

import java.util.Stack;

public class MaximumBinaryTree {
    private TreeNode findRoot(int[] nums, int l, int r) {
        if (l == r) return null;
        int maxIndex = l;
        for (int i = l+1; i < r; ++i) {
            if (nums[i] > nums[maxIndex]) nums[maxIndex] = i;
        }
        TreeNode root = new TreeNode(nums[maxIndex]);
        root.left = findRoot(nums, l, maxIndex);
        root.right = findRoot(nums,maxIndex + 1, r);
        return root;
    }

    public static int[] parentIndex(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];

        for (int i = 0; i < nums.length; ++i) {
            while (!stack.empty() && nums[i] > nums[stack.peek()]) {
                ris[stack.pop()] = i;

            }
            if (!stack.empty() && nums[i] <= nums[stack.peek()]) {
                lis[i] = stack.peek();
            }
            stack.push(i);

        }
        int [] pis = new int[nums.length];
        for (int i = 0; i < pis.length; ++i) {
            if (lis[i] == -1) {
                pis[i] = ris[i];
            } else if (ris[i] == -1) {
                pis[i] = lis[i];
            } else {
                pis[i] = Math.min(ris[i], lis[i]);
            }
        }
    }
}

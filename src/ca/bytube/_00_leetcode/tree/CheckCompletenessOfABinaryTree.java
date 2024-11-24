package ca.bytube._00_leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isLeaf = false;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isLeaf && !isLeafNode(node)) return false;
            if (hasTwoChildren(node)) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
            else if (node.left == null && node.right != null) return false;
            else if (node.left != null) {
                isLeaf = true;
                queue.offer(node.left);
            } else {
                isLeaf = true;
            }
        }
        return true;
    }

    private boolean hasTwoChildren(TreeNode node) {
        return node.left != null && node.right != null;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }
}

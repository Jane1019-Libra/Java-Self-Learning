package ca.bytube._00_leetcode.tree;

import ca.bytube._06_Tree.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return null;
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree1(root.left);
        invertTree1(root.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        invertTree2(root.left);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree2(root.left);
        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        if (root == null) return null;
        invertTree3(root.left);
        invertTree3(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        return root;
    }

    public TreeNode invertTree4(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            TreeNode tmp = poll.left;
            poll.left = poll.right;
            poll.right = tmp;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        return root;
    }
}

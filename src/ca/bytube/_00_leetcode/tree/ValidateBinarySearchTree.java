package ca.bytube._00_leetcode.tree;

import ca.bytube._06_Tree.BinaryTree;

import java.util.Stack;

public class ValidateBinarySearchTree {
    public boolean isValidBst(TreeNode node) {
        TreeNode visited = null;
        if (node != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    TreeNode pop = stack.pop();
                    if (visited != null && visited.val > pop.val) return false;
                    visited = pop;
                    node = pop.right;
                }
            }
        }
        return true;
    }
}
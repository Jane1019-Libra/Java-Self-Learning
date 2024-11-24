package ca.bytube._00_leetcode.tree;

public class IsBalanced {
    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(height(root.left) - height(root.right)) > 1) return false;
        return isBalanced1(root.left) && isBalanced1(root.right);
    }

    private static class ReturnData{
        public boolean isB;
        public int height;

        public ReturnData(boolean isB, int height){
            this.isB = isB;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return isB(root).isB;
    }

    private ReturnData isB(TreeNode root) {
        if (root == null) return new ReturnData(true, 0);
        ReturnData leftData = isB(root.left);
        ReturnData rightData = isB(root.right);

        if (!leftData.isB || !rightData.isB) return new ReturnData(false, 0);
        if (Math.abs(leftData.height - rightData.height) > 1) return new ReturnData(false, 0);
        return new ReturnData(true, Math.max(leftData.height, rightData.height) + 1);
    }
    private int height(TreeNode node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }


}

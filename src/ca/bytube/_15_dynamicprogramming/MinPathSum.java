package ca.bytube._15_dynamicprogramming;

public class MinPathSum {
    public static int minPathSum(int [][] matrix) {
        return minPathSum(matrix, 0,0);
    }

    public static int minPathSum(int [][] matrix, int i, int j) {
        if (i == matrix.length - 1 && j == matrix[0].length - 1) return 0;
        if (i == matrix.length - 1) return matrix[i][j] + minPathSum(matrix, i, j + 1);
        else if (j == matrix[0].length - 1) return matrix[i][j] + minPathSum(matrix, i + 1, j);
        else return matrix[i][j] + Math.min(minPathSum(matrix, i + 1, j), minPathSum(matrix, i, j + 1));
    }

    public static int minPathSum2(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; ++i) dp[i][0] = dp[i-1][0] + matrix[i][0];
        for (int i = 1; i < matrix[0].length; ++i) dp[0][i] = dp[0][i-1] + matrix[0][i];
        for (int i = 1; i < matrix.length; ++i) {
            for (int j = 1; j < matrix[0].length; ++i) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[matrix.length - 1][matrix[0].length - 1];
    }
}

package ca.bytube._12_backtracking;

public class NQueens {

    private int[] cols;

    private int ways;
    public int totalNQueens(int n) {
        cols = new int[n];
        place(0);
        return ways;
    }

    private void place(int row) {
        if (row == cols.length) {
            ways++;
            return;
        }
        for (int col = 0; col < cols.length; ++col) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
            }
        }
    }

    private boolean isValid(int row, int col) {
        for (int i = 0; i < row; ++i) {
            if (cols[row] == col) return false;
            if (Math.abs(col - cols[i]) == Math.abs(row - i)) return false;
        }
        return true;
    }
}

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int length;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.length = N;
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = (int) Math.round(Math.random());
            }
        }
    }
    public void open(int row, int col) {
        if (row >= this.length || col > this.length) {
            throw new IndexOutOfBoundsException();
        }
        grid[row][col] = 1;
    }
    public boolean isOpen(int row, int col) {
        if (row >= this.length || col > this.length) {
            throw new IndexOutOfBoundsException();
        } else if (grid[row][col] == 1) {
            return true;
        } else {
            return false;
        }

    }
    public boolean isFull(int row, int col) {
        if (row >= this.length || col > this.length) {
            throw new IndexOutOfBoundsException();
        } else if (grid[row][col] == 0) {
            return true;
        } else {
            return false;
        }
    }
    public int numberOfOpenSites() {

    }
    public boolean percolates()
    public static void main(String[] args)
}

package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size; // the length of row and column
    private int numberOfOpenSites = 0; // except top and bottom
    private WeightedQuickUnionUF uf;
    private int top; // vitual top, which means to union with the first empty site
    private int bottom;
    private int[][] surroundings = new int[][]{{0,1},{0,-1},{1,0},{-1,0}}; // to implement surrounded site
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.size = N;
        grid = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2); // an array to put every site and vitual top/bottom
        top = 0;
        bottom = N * N + 1;
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                grid[i][j] = false;
//            }
//        }
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            throw new IllegalArgumentException();
        }
    }

    private int xyTo1D(int row, int col) {
        return (row * size) + (col + 1);
    }

    public void open(int row, int col) {
        validate(row, col);
        if (isFull(row, col)) {
            grid[row][col] = true;
            numberOfOpenSites += 1;
        }

        if (row == 0) {
            uf.union(top, xyTo1D(row, col));
        }
        if (row == size - 1) {
            uf.union(bottom, xyTo1D(row, col));
        }
        for(int[] surround: surroundings) {
            int adjecrow = row + surround[0];
            int adjecol = col + surround[1];
            if (adjecrow >= 0 && adjecrow < size) {
                if (adjecol >= 0 && adjecol < size) {
                    if (isOpen(adjecrow, adjecol)) {
                        uf.union(xyTo1D(row,col), xyTo1D(adjecrow, adjecol));
                    }
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return !grid[row][col];
    }
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }



    public boolean percolates() {
        return uf.connected(top, bottom);
    }
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        System.out.println(percolation.percolates());
        percolation.open(0, 1);
        percolation.open(1, 1);
        System.out.println(percolation.percolates());
        percolation.open(1, 2);
        percolation.open(2, 2);
        System.out.println(percolation.isFull(3, 2));
        percolation.open(3,2);
        System.out.println(percolation.percolates());
    }
}

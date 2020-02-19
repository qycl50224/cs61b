package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] fraction;
    private int Trials;
    public PercolationStats(int N, int T, PercolationFactory pf) { //T = numOfTrials
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        fraction = new double[T];
        Trials = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int numOfOpened = 0;
            while(!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
                numOfOpened += 1;
            }
            fraction[i] = numOfOpened / (N * N);
        }
    }
    public double mean() {
        return StdStats.mean(fraction);
    }
    public double stddev() {
        return StdStats.stddev(fraction);
    }
    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return mu - 1.96 * sigma / (Math.pow(Trials, 0.5));
    }
    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return mu + 1.96 * sigma / (Math.pow(Trials, 0.5));
    }
}

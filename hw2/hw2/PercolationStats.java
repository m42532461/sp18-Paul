package hw2;
import edu.princeton.cs.introcs.*;

public class PercolationStats {
    private int T;
    private double[] openSiteFractions;
    private double sum;
    private double mean;
    private double stddev;


    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        openSiteFractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (percolation.isOpen(x, y));
                percolation.open(x, y);
            }
            openSiteFractions[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }
    public double calSum() {
        for (double i : openSiteFractions) {
            this.sum += i;
        }
        return this.sum;
    }

    /*
    sample mean of percolation threshold
     */
    public double mean() {
        this.mean = this.sum / T;
        return  calSum() / T;
    }
    /*
    sample standard deviation of percolation threshold
     */
    public double stddev(){
        double cal = 0;
        for (double i : openSiteFractions) {
            cal += (i - this.mean) * (i - this.mean);
        }
        this.stddev = Math.sqrt(cal / (this.T -1));
        return this.stddev;
    }
    /*
    low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return (this.mean - 1.96 * this.stddev / Math.sqrt(this.T));
    }
    /*
    high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return (this.mean + 1.96 * this.stddev / Math.sqrt(this.T));
    }
}

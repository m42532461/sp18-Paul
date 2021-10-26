package hw2;
import edu.princeton.cs.introcs.*;

public class PercolationStats {

    private int row;
    private int expTime;
    private PercolationFactory pf;
    private double sumOfValue;
    /*
    Constructor
    perform T independent experiments on an N-by-N grid
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.row = N;
        this.expTime = T;
        this.pf = pf;
    }

    public double calSum() {
        Percolation a = pf.make(row);
        while (!a.percolates()) {
            a.open(0,1);
            StdRandom
        }
        this.sumOfValue += a.size;
        return 0;
    }

    /*
    sample mean of percolation threshold
     */
    public double mean() {
        return 0;
    }
    /*
    sample standard deviation of percolation threshold
     */
    public double stddev(){
        return 0;
    }
    /*
    low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return 0;
    }
    /*
    high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return 0;
    }
}

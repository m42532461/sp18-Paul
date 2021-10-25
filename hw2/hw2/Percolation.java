package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public WeightedQuickUnionUF union;
    public int size;
    public int row;
    private boolean[] open;

    public Percolation(int r) {                  // create N-by-N grid, with all sites initially blocked
        union = new WeightedQuickUnionUF(r*r);
        open = new boolean[r*r];
        for (int i = 0; i < open.length; i++) {
            open[i] = false;
        }
        this.row = r;
        this.size = 0;
    }
    
    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        int position = xyTo1D(row, col);
        open[position] = true;
        size++;
        checkNear(row, col);
        System.out.println("Open (" + row + ", " + col +")");
    }

    public void checkNear (int row, int col) {
        if ((xyTo1D(row, col) + this.row) < (row * row - 1) && (isOpen(row+1, col))) {
            union.union(xyTo1D(row, col), xyTo1D(row+1, col));
        }
    }

    public int xyTo1D(int row, int col) {
        return row * this.row + col;
    }
    /*
    is the site (row, col) open?
    **/
    public boolean isOpen(int row, int col) {
        System.out.println("(" + row + ", " + col +") is open");
        return open[xyTo1D(row, col)];
    }
    /*
    is the site (row, col) full?
    **/
    public boolean isFull(int row, int col) {
        for (int i = 0; i < this.row; i++) {
            if (union.connected(xyTo1D(0, i), xyTo1D(row, col))) {
                System.out.println("(" + row + ", " + col +") is full");
                return true;
            }
        }
        return false;
    }
    /*
    number of open sites
    */
    public int numberOfOpenSites() {
       return this.size;
    }
    /*
    does the system percolate?
     */
    public boolean percolates() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.row; j++) {
                if (union.connected(xyTo1D(0, i), xyTo1D(this.row - 1, j))) {
                    System.out.println("Percolated!!");
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Percolation a = new Percolation(5);
        a.open(0,0);
        a.open(1,0);
        a.open(2,0);
        a.open(3,0);
        a.open(4,0);
        a.isOpen(0,0);
        a.isOpen(1,0);
        a.isOpen(2,0);
        a.isOpen(3,0);
        a.isOpen(4,0);
        a.isFull(0,0);
        a.isFull(1,0);
        a.isFull(2,0);
        a.isFull(3,0);
        a.isFull(4,0);
        System.out.println(a.union.connected(a.xyTo1D(0,0), a.xyTo1D(1,0)));
    }
}

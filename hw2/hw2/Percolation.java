package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public WeightedQuickUnionUF union1; // Used to check percolate or not
    public WeightedQuickUnionUF union2; // Used to check full or not
    public int size;
    public int row;
    public boolean[] open;
    /*
    Constructor
     */
    public Percolation(int r) {                  // create N-by-N grid, with all sites initially blocked
        if (r <= 0) throw new IllegalArgumentException("Row and Column can not less or equal to zero");
        this.row = r;
        this.size = 0;
        union1 = new WeightedQuickUnionUF(r*r);
        union2 = new WeightedQuickUnionUF(r*r);
        for (int i = 1; i < r; i++) {
            // union every node in first row and last row,
            // so that all I need to do is check if any node of them is connected to verify if it is percolated.
            union1.union(0, i);
            union2.union(0, i);
            union1.union(xyTo1D(r-1, 0), xyTo1D(r-1, i));
        }
        open = new boolean[r*r];
        for (int i = 0; i < open.length; i++) {
            open[i] = false;
        }
    }
    /*
    Open the space and the size of open site plus 1.
    */
    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        if (!validate(row, col)) throw new IndexOutOfBoundsException("Out of bounds !");
        int position = xyTo1D(row, col);
        open[position] = true;
        size++;
        checkNear(row, col);
    }
    /*
    checkNear used to check if other spaces nearby are opened.
    And if true then connect them.
    */
    public void checkNear (int row, int col) {
        int center = xyTo1D(row, col);
        int top = xyTo1D(row - 1, col);
        int right = xyTo1D(row, col + 1);
        int bottom = xyTo1D(row + 1, col);
        int left = xyTo1D(row, col - 1);
        // Check top
        if (row >= 1) {
            if(isOpen(row - 1, col)){
                union1.union(top, center);
                union2.union(top, center);
            }
        }
        // Check right
        if (col < this.row - 1) {
            if(isOpen(row, col + 1)){
                union1.union(right, center);
                union2.union(right, center);
            }
        }
        // Check bottom
        if (row < this.row -1) {
            if(isOpen(row + 1, col)){
                union1.union(bottom, center);
                union2.union(bottom, center);
            }
        }
        // Check left
        if (col >= 1) {
            if(isOpen(row, col - 1)){
                union1.union(left, center);
                union2.union(left, center);
            }
        }
    }
    /*
    xyTo1D transfer the 2D coordinate to 1D and return the number.
    */
    public int xyTo1D(int row, int col) {
        return row * this.row + col;
    }
    /*
    is the site (row, col) open?
    **/
    public boolean isOpen(int row, int col) {
        if (!validate(row, col)) throw new IndexOutOfBoundsException("Out of bounds !");
        return open[xyTo1D(row, col)];
    }
    /*
    is the site (row, col) full?
    **/
    public boolean isFull(int row, int col) {
        if (!validate(row, col)) throw new IndexOutOfBoundsException("Out of bounds !");
        if (union2.connected(0, xyTo1D(row, col)) && isOpen(row, col)) {
            return true;
        }

        return false;
    }

    public boolean validate(int row, int col) {
        if ((row < 0) || (row >= this.row) || (col < 0) || (col >= this.row)) return false;
        return true;
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
        return (union1.connected(0, xyTo1D(this.row - 1,this.row - 1)));
    }
    public static void main(String[] args) {
        Percolation a = new Percolation(5);
        a.open(1,1);
        a.open(2,1);
        a.open(3,1);
        a.open(4,1);
        a.open(1,2);
        a.open(1,3);
        a.open(1,4);
        a.open(0,4);
        a.isOpen(0,1);
        a.isOpen(1,1);
        a.isOpen(2,1);
        a.isOpen(3,1);
        a.isOpen(4,1);
        a.isFull(0,1);
        a.isFull(1,1);
        a.isFull(2,1);
        a.isFull(3,1);
        a.isFull(4,1);
        System.out.println(a.union1.connected(a.xyTo1D(0,0), a.xyTo1D(1,0)));
        System.out.println(a.percolates());
        System.out.println(a.numberOfOpenSites());
    }
}

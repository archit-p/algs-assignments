import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] opened;
    private final int top = 0;
    private final int bottom;
    private final int size;
    private WeightedQuickUnionUF qf;
    private int count = 0;
    /**
     * Creates N-by-N grid, with all sites blocked.
     */
    public Percolation(int N) {
        if (N <= 0)
            throw new java.lang.IllegalArgumentException("ERROR!");
        size = N;
        bottom = size * size + 1;
        qf = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    /**
     * Opens site (row i, column j) if it is not already.
     */
    public void open(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new java.lang.IllegalArgumentException("ERROR!");
        if (opened[i-1][j-1] != true)
            count++;
        opened[i - 1][j - 1] = true;
        if (i == 1) {
            qf.union(getQFIndex(i, j), top);
        }
        if (i == size) {
            qf.union(getQFIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
    }

    /**
     * Is site (row i, column j) open?
     */
    public boolean isOpen(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new java.lang.IllegalArgumentException("ERROR!");
        return opened[i - 1][j - 1];
    }

    /**
     * Is site (row i, column j) full?
     */
    public boolean isFull(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new java.lang.IllegalArgumentException("ERROR!");
        else
            return qf.connected(top, getQFIndex(i , j));
    }

    public int numberOfOpenSites() {
        return count;
    }
    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return qf.connected(top, bottom);
    }

    private int getQFIndex(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size)
            throw new java.lang.IllegalArgumentException("ERROR!");
        return size * (i - 1) + j;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N;
        StdOut.print("Enter value for N: ");
        N = StdIn.readInt();
        Percolation test = new Percolation(N);
		int keyi, keyj;
		do {
			keyi = StdRandom.uniform(1, N+1);
			keyj = StdRandom.uniform(1, N+1);
			test.open(keyi, keyj);
		} while (!test.percolates());
        StdOut.println(test.numberOfOpenSites());
	}
}
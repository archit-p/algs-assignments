import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private Percolation test;
	private int t;
	private double[] p;
	private int size;

	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0)
            throw new java.lang.IllegalArgumentException("ERROR!");
		size = n;
		t = trials;
		int keyi, keyj;
		p = new double[t];
		test = new Percolation(n);
		for(int i = 0; i < t; i++) {
			do {
				keyi = StdRandom.uniform(1, n+1);
				keyj = StdRandom.uniform(1, n+1);
				test.open(keyi, keyj);
			} while(test.percolates() != true);
			p[i] = (double)test.numberOfOpenSites()/(n*n);
		}
	}

	public double mean() {
		return StdStats.mean(p);
	}

	public double stddev() {
		return StdStats.stddev(p);
	}

	public double confidenceLo() {
		double z = 1.96;
		return mean()-z*stddev()/Math.sqrt(size);
	}

	public double confidenceHi() {
		double z = 1.96;
		return mean()+z*stddev()/Math.sqrt(size);
	}

	public static void main(String[] args) {
		int N, T;
		N = Integer.parseInt(args[0]);
		T = Integer.parseInt(args[1]);
		PercolationStats trials = new PercolationStats(N, T);
		StdOut.println("Mean                      = " + trials.mean());
		StdOut.println("stddev                    = " + trials.stddev());
		StdOut.println("95% cofidence interval    = [" +  trials.confidenceLo() + ", " + trials.confidenceHi() + "]");
	}
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

/**
 * @author archit
 */
public class FastCollinearPoints {
    private ArrayList<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        int N = points.length;
        Point[] sorted = new Point[N];
        for (int i = 0; i < N; i++) {
            Point p = points[i];
            if (p == null) {
                throw new NullPointerException();
            }
            sorted[i] = p;
        }
        Arrays.sort(sorted);

        int M = N - 1;
        Point[] ends = new Point[M];
        segments = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Point p = sorted[i];


            for (int j = 0, k = 0; j < N; j++) {
                if (j != i) {
                    ends[k++] = sorted[j];
                }
            }
            Arrays.sort(ends, p.slopeOrder());

            for (int j = 0, lo = 0; j < M; j++) {
                double slopeP = p.slopeTo(ends[lo]);
                double slopeQ = p.slopeTo(ends[j]);
                if (slopeQ == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }

                if (slopeP == slopeQ) {
                    if (j == M - 1) {
                        if (j - lo >= 2) {
                            addSegments(p, ends, lo, j);
                        }
                    }
                } else {
                    if (j - lo >= 3) {
                        addSegments(p, ends, lo, j - 1);
                    }
                    lo = j;
                }
            }
        }
    }

    private void addSegments(Point origin, Point[] points, int i, int j) {
        if (origin.compareTo(points[i]) < 0) {
            segments.add(new LineSegment(origin, points[j]));
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] array = new LineSegment[numberOfSegments()];
        segments.toArray(array);
        return array;
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // read the n points from a file
        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println(collinear.numberOfSegments());
        StdDraw.show();
    }
}

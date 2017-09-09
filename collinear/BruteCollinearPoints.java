import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;
import java.util.ArrayList;

public class BruteCollinearPoints {
	
	private	ArrayList<LineSegment> save = new ArrayList<LineSegment>();
	private int num;

	public BruteCollinearPoints(Point[] points) {
		int n = points.length;
		Point a[] = new Point[n];
		for(int i = 0; i < n; i++) {
			a[i] = points[i];
		}
		Point high, low;
		for (int p = 0; p < n - 3; p++) {
            for (int q = p + 1; q < n - 2; q++) {
                for (int r = q + 1; r < n - 1; r++) {
                    for (int s = r + 1; s < a.length; s++) {
                    	low = smallestPoint(a[p], a[q], a[r], a[s]);
                    	high = largestPoint(a[p], a[q], a[r], a[s]);
                        if (a[p].slopeTo(a[q]) == a[p].slopeTo(a[r]) && a[p].slopeTo(a[q]) == a[p].slopeTo(a[s])) {
                        	LineSegment l = new LineSegment(low, high);
                            save.add(l);
                            break;
                        }
                    }
                }
            }
        }
	}

	private Point smallestPoint(Point a, Point b, Point c, Point d) {
        Point s2, s3;
        if(a.compareTo(b) < 0)
            s2 = a;
        else
            s2 = b;
        if(s2.compareTo(c) < 0)
            s3 = s2;
        else
            s3 = c;
        if(s3.compareTo(d) < 0)
            return s3;
        else
            return d;
    }
    
    private Point largestPoint(Point a, Point b, Point c, Point d) {
        Point l2, l3;
        if(a.compareTo(b) > 0)
            l2 = a;
        else
            l2 = b;
        if(l2.compareTo(c) > 0)
            l3 = l2;
        else
            l3 = c;
        if(l3.compareTo(d) > 0)
            return l3;
        else
            return d;
    }
	public int numberOfSegments() {
		return num;
	}

	public LineSegment[] segments() {
		LineSegment[] store = new LineSegment[save.size()];
		save.toArray(store);
		return store;
	}
	    
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdOut.println(collinear.numberOfSegments());
        StdDraw.show();
    }
} 

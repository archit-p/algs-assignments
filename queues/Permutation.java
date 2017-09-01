import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class Permutation	{

	public static void main(String[] args) {
		RandomizedQueue<String> r = new RandomizedQueue<String>(); 
		int k = Integer.parseInt(args[0]);
		while(!StdIn.isEmpty())
		{
			String s = StdIn.readString();
			r.enqueue(s);
		}
		for(int i = 0; i < k; i++) {
			StdOut.println(r.dequeue());
		}
	}
}
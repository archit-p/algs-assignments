import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int n;
	private int first;
	private int last;

  	public RandomizedQueue()	{
  		a = (Item[])new Object[4];
  		n = 0;
  		first = 0;
  		last = 0;
   	}              // construct an empty randomized queue

   	public boolean isEmpty()	{
   		return n == 0;
   	}                 // is the queue empty?

   	public int size()	{
   		return n;
   	}                        // return the number of items on the queue

   	private void resize(int capacity) {
   		Item[] temp = (Item[])new Object[capacity];
   		for(int i = 0; i < n; i++) {
   			temp[i] = a[(first + i) % a.length];
   		}  
   		a = temp;
   		first = 0;
   		last = n;
   	}			//resizes the array to given size and copies elements

   	public void enqueue(Item item)	{
   		if(item == null)
   			throw new IllegalArgumentException("Item is null!");
   		if(n == a.length) resize(2*a.length);
   		a[last++] = item;
   		if(last == a.length) last = 0;	//wrap round
   		n++;
   	}           // add the item and resize if required

   	private void swap(int i, int j) {
   		Item temp = a[j];
   		a[j] = a[i];
   		a[i] = temp;
   	}

   	public Item dequeue()	{
   		if(n == 0)
   			throw new java.util.NoSuchElementException("ERROR! Deque is empty!");
   		int key = StdRandom.uniform(n);
   		swap(first, ((first + key) % a.length));
   		Item item = a[first];
   		a[first] = null;
   		first++;
   		n--;
   		if(n == a.length/4)	resize(a.length/2);
   		if(first == n)	first = 0;	//wrap round
   		return item;
   	}                    // remove and return a random item

   	public Item sample()	{
   		int key = StdRandom.uniform(n);
   		return a[(first + key) % a.length];
   	}                     // return (but do not remove) a random item

   	public Iterator<Item> iterator()	{
   		return new RandomIterator();
   	}         // return an independent iterator over items in random order

   	private class RandomIterator implements Iterator<Item> {
   		private Item[] temp;
   		private int start = 0;
   		public RandomIterator() {
   			temp = (Item[]) new Object[n];
   			for(int i = 0; i < n; i++) {
   				temp[i] = a[(first+i)%a.length];	//make a shuffled copy of iterator
   			}
   			StdRandom.shuffle(temp);
   		}

   		public boolean hasNext() {
   			return start != n;
   		}

   		public void remove() {	throw new java.lang.UnsupportedOperationException("ERROR! Remove operation is not supported!");			}

   		public Item next() {
   			if(!hasNext())
				throw new java.util.NoSuchElementException("ERROR! No elements left!");
   			Item item = temp[start++];
   			return item;
   		}

   	} //iterator class uses a temporary array to store a shuffled array

   	public static void main(String[] args)	{
   		RandomizedQueue<String> r = new RandomizedQueue<String>();
   		while(!StdIn.isEmpty()) {
   			String s = StdIn.readString();
   			if(!s.equals("it"))
   				r.enqueue(s);
   			else if(!r.isEmpty())
   				StdOut.println(r.dequeue() + " ");
   		}
   	}   // unit testing (optional)
}
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>	{
	private Node first, last;
	private int N;

	private class Node {
		Item item;
		Node next, prev;
	}

   public Deque()	{
   }                           // construct an empty deque
   public boolean isEmpty()   {
		return N == 0;
	}              // is the deque empty?

	public int size()	{
		return N;
	}                     // return the number of items on the deque

	public void addFirst(Item item)	{
		if(item == null)
			throw new IllegalArgumentException("Item is null!");
		N++;
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.prev = null;
		first.next = oldfirst;
		if(N != 1)
			oldfirst.prev = first;
		else
			last = first;
	}        // add the item to the front

	public void addLast(Item item)   {
		if(item == null)
			throw new java.lang.IllegalArgumentException("ERROR! Item is null!");
		N++;
		Node newlast = new Node();
		newlast.item = item;
		newlast.next = null;

		if(N != 1) {
			last.next = newlast;
			newlast.prev = last;
		}

		last = newlast;
		if(N == 1)
			first = last;
	}        // add the item to the end

	public Item removeFirst()	{
		if(N == 0)
			throw new java.util.NoSuchElementException("ERROR! Deque is empty!");
		N--;
		Item store = first.item;
		if(N != 0) {
			first = first.next;
   		first.prev = null;
		}

		else
			first = null;
		return store;
	}           // remove and return the item from the front

	public Item removeLast()	{
		N--;
		Item store = last.item;
		if(N != 0) {
			last = last.prev;
			last.next = null;
		}
		else
			last = null;
		return store;
	}                 // remove and return the item from the end

	public Iterator<Item> iterator()	{
		return new ListIterator();
	}         // return an iterator over items in order from front to end

	private class ListIterator implements Iterator<Item> {
		//implements FIFO iteration
		Node current = first;

	public boolean hasNext()	{	return current != null;	}

	public void remove()	{	throw new java.lang.UnsupportedOperationException("ERROR! Remove operation is not supported!");	}

	public Item next() {
		if(!hasNext())
			throw new java.util.NoSuchElementException("ERROR! No elements left!");
		Item item = current.item;
		current = current.next;
		return item;
	}
}

public static void main(String[] args)	{
 
 	Deque<String> s = new Deque<String>();
	s.addFirst("Hello");
	s.addLast("My");
	s.addFirst("Hey");
	s.addFirst("Hi");
	for(String out : s) 
		StdOut.println(out);
   }   // unit testing (optional)
}
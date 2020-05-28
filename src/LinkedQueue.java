/** %java LinkedQueue.java < tobe.txt

 to be or not to beVot ostatok: that
 Vot ostatok: is

 */


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last;

    private class Node {
        Item item;
        Node next;
    }

    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())  first = last;
        else    oldlast.next = last;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }

    public boolean isEmpty()
    {return first == null; }

    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext(){ return current != null; }
        public void remove() { throw new UnsupportedOperationException();}
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
	LinkedQueue<String> queue = new LinkedQueue<>();
	while (!StdIn.isEmpty()){
	    String q = StdIn.readString();
	    if (q.equals("-")) StdOut.print(" " +queue.dequeue());
	    else queue.enqueue(q);
    }
	for (String q : queue)
	    StdOut.println("Vot ostatok: " +q);
    }
}

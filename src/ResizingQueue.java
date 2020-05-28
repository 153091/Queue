/** % java ResizingQueue.java < tobe.txt
 *
 Note: ResizingQueue.java uses unchecked or unsafe operations.
 Note: Recompile with -Xlint:unchecked for details.
 to be or not to be Vot ostatki: that
 Vot ostatki: is

 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingQueue<Item> implements Iterable<Item>{

    private Item[] q;
    private int N = 0;
    private int last=0;
    private int first=0;

    public ResizingQueue() { q = (Item[]) new Object[1]; }
    public boolean isEmpty() {return N == 0 ;}
    public void enqueue(Item item) {
        if(N == q.length) resize(q.length*2);
        q[last++] = item;
        N++;
    }
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = q[first];
        q[first++] = null;
        --N;
        if(N>0 && N== (q.length/4) ) resize(q.length/2);
        return item;
    }

    private void resize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i <N; i++)
            copy[i] = q[first+i];
        q = copy;
        first = 0;
        last = N;
    }

    public Iterator<Item> iterator() { return new ArrayIterator();}

    private class ArrayIterator implements Iterator<Item> {
        private int i = first;

        public boolean hasNext() { return i<last;}
        public void remove () { throw new UnsupportedOperationException();}
        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            return q[i++];
        }
    }

    public static void main(String[] args){
        ResizingQueue<String> queue = new ResizingQueue<>();
        while (!StdIn.isEmpty()) {
            String q = StdIn.readString();
            if (q.equals("-")) StdOut.print(" " + queue.dequeue());
            else queue.enqueue(q);
        }
        for (String q : queue)
            StdOut.println(" Vot ostatki: " + q);
    }
}

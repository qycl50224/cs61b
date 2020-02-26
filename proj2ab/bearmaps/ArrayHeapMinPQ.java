package bearmaps;

import org.junit.Test;
import bearmaps.PrintHeapDemo;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private node<T>[] pq;
    private int length;
    private int size;
    public ArrayHeapMinPQ(int s) {
        if (s < 0) {
            throw new IllegalArgumentException();
        }
        length = s;
        pq =  new node[s+1];
        size = 0;
    }

    public class node<T> {
        private T item;
        private double priority;
        public node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
        public node(node<T> n) {
            this.item = n.item;
            this.priority = n.priority;
        }
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        node<T> n = new node<T>(item, priority);
        pq[++size] = n;
        swim(size);
        checkLength();
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        for(int i = 1; i <= size; i++) {
            if (pq[i].item == item) {
                return true;
            }
        }
        return false;
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        return pq[1].item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        T i = pq[1].item;
        exch(1, size--);
        pq[size +1] = null;
        sink(1);
        checkLength();
        return i;
    }
    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {

    }

    private boolean less(int i, int j) {
        if (pq[i].priority < pq[j].priority) {
            return true;
        }
        return false;
    }
    private void exch(int i, int j) {
        node<T> temp = new node<>(pq[i]);
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * we are creating a minPQ, so the root is the smallest
     * @param i
     */
    private void swim(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        while (i > 1 && less(i, i / 2)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }
    private void sink(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException();
        }
        while (i * 2 <= size) {
            int j = i * 2;
            if (j < size && !less(j,j + 1)) { // find the smaller one
                j++; // presure that there are (j+1)th
            }
            if (!less(i, j)) {
                exch(i, j);
                i = j;
            }
        }
    }

    private void checkLength() {
        if (length > 100 && (size/length) < 0.25) {
            pq = smaller();
        } else if (length > 100 && size/length > 0.75) {
            pq = larger();
        }
        System.out.println("the current pq maxN is " + length);
    }

    private node<T>[] smaller() {
        node<T>[] newAry = new node[1 + length / 2];
        length /= 2;
        System.arraycopy(pq,1,newAry,1,size);
        return newAry;
    }

    private node<T>[] larger() {
        node<T>[] newAry = new node[1 + length * 2];
        length *= 2;
        System.arraycopy(pq,1,newAry,1,size);
        return newAry;
    }

    public void printSimpleHeapDrawing() {
        int depth = ((int) (Math.log(pq.length) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < pq.length; i++) {
            System.out.printf("%c ",(Character) pq[i].item);
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    @Test
    public static void main(String args[]) {
        ArrayHeapMinPQ<Character> pq = new ArrayHeapMinPQ<Character>(10);
        pq.add('a', 2.2);
        pq.add('b', 5.2);
        pq.add('c', 2.3);
        pq.add('d', 1.5);
        pq.add('e',0.9);
        pq.add('f', 9.7);
        System.out.println(pq.removeSmallest()== 'e');
        long start = System.currentTimeMillis();
        System.out.println(pq.getSmallest() == 'd');
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");
        pq.printSimpleHeapDrawing();


    }
}

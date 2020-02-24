package bearmaps;

import java.util.ArrayDeque;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private node[] pq;
    private int size;
    public ArrayHeapMinPQ(int size) {
        this.size = size;
        pq = (node[]) new Object[size+1];
    }

    public class node {
        private T item;
        private double priority;
        public node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    private void less() {}
    private void exch() {}

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        for (node n: pq) {
            if (n.item == item) {
                throw new IllegalArgumentException();
            }
        }
        node n = new node(item, priority);
    }
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {}
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        return pq[1].item;
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {}
    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {}
}

package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest<T> implements ExtrinsicMinPQ<T>{

    @Override
    public void add(T item, double priority) {}
    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {}
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {}
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {}
    /* Returns the number of items in the PQ. */
    @Override
    public int size() {}
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {}
}

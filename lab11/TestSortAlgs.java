import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Double> q = new Queue();
        q.enqueue(5.);
        q.enqueue(3.);
        q.enqueue(0.7);
        q.enqueue(13.);
        q.enqueue(29.);
        q.enqueue(0.00001);
        q.enqueue(9.);
        q = QuickSort.quickSort(q);

        assertTrue(isSorted(q));
        Queue<String> pas = new Queue<>();
        pas.enqueue("Joe");
        pas.enqueue("Nancy");
        pas.enqueue("Caroline");
        pas.enqueue("Jeffery");
        pas.enqueue("Tom");
        pas.enqueue("Ava");
        pas.enqueue("Peter");
        pas.enqueue("Josh");
        pas.enqueue("Hug");
        assertTrue(isSorted(MergeSort.mergeSort(pas)));

    }

    @Test
    public void testMergeSort() {
        Queue<Double> q = new Queue();
        q.enqueue(5.);
        q.enqueue(3.);
        q.enqueue(0.7);
        q.enqueue(13.);
        q.enqueue(29.);
        q.enqueue(0.00001);
        q.enqueue(9.);
        q = MergeSort.mergeSort(q);

        assertTrue(isSorted(q));
        Queue<String> pas = new Queue<>();
        pas.enqueue("Joe");
        pas.enqueue("Nancy");
        pas.enqueue("Caroline");
        pas.enqueue("Jeffery");
        pas.enqueue("Tom");
        pas.enqueue("Ava");
        pas.enqueue("Peter");
        pas.enqueue("Josh");
        pas.enqueue("Hug");
        assertTrue(isSorted(MergeSort.mergeSort(pas)));

    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}

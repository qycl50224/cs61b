package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* array size*/
    private int size;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        size = capacity;
        rb = (T []) new Object[capacity];
        for(int i = 0; i < size; i++) {
            rb[i] = null;
        }
        first = 0;
        last = 0;
        fillCount = 0;

    }

    /**
     * override the iterator extended by the boundedQueue interface
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    /**
     * a private class to override iterator
     */
    private class ArrayRingBufferIterator implements Iterator<T> {
        public boolean hasNext() {
            return fillCount < rb.length;
        }
        public T next() {
            T returnItem = rb[first];
            first += 1;
            return returnItem;
        }
    }

    /**
     * return the capacity of this ring buffer
     */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * return the current sum of items in the ring buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * A helper function to construct a circuler, when index up to limit
     * , change it to the start
     */
    private int CheckAndIndexToStart(int index) {
        if(index == size) {
            index = 0;
            return index;
        } else {
            return index;
        }

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        } else {
            rb[last] = x;
            fillCount += 1;
            last += 1;
            last = CheckAndIndexToStart(last);
            return;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if(isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        } else {
            T returnItem = rb[first];
            rb[first] = null;
            fillCount -= 1;
            first += 1;
            first = CheckAndIndexToStart(first);
            return returnItem;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        } else {
            return rb[first];
        }
    }


    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
}
    // TODO: Remove all comments that say TODO when you're done.

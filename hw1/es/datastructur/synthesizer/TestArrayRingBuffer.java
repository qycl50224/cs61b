package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(9);
        arb.enqueue(8);
        arb.enqueue(7);
        arb.enqueue(6);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(3);
        arb.enqueue(2);
        arb.enqueue(1);
        arb.enqueue(0);

        assertEquals(arb.capacity(), 10);
        assertEquals(arb.dequeue(),9);
        assertEquals(arb.dequeue(),8);
        assertEquals(arb.dequeue(),7);
        assertEquals(arb.dequeue(),6);
        assertEquals(arb.dequeue(),5);
        assertEquals(arb.dequeue(),4);
        assertEquals(arb.dequeue(),3);
        assertEquals(arb.dequeue(),2);
        assertEquals(arb.dequeue(),1);
        assertEquals(arb.peek(),0);
        arb.dequeue();

    }
}

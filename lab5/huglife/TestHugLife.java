package huglife;

import org.junit.Test;
import creatures.*;
import static org.junit.Assert.*;

/**
 * Performs basic tests of huglife package.
 *
 * @author Josh Hug
 */

public class TestHugLife {
    /**
     * Performs basic tests of huglife package.
     */

    @Test
    public void populateAndDraw() {
        Grid g = new Grid(20);
        g.placeOccupant(0, 10, new SampleCreature());
        g.placeOccupant(5, 5, new SampleCreature());
        g.drawWorld();
        StdDraw.show(20);
        g.doMove(0, 10, 0, 11);
        g.drawWorld();
        StdDraw.show(20);

    }

    @Test
    public void testReplicate() {
        Creature plip1 = new Plip(2);
        assertEquals(plip1.energy(), 2, 0);
        assertEquals(plip1.replicate().energy(), plip1.energy(),0);
        assertEquals(plip1.energy(), 1, 0);
        assertNotEquals(plip1.replicate(), plip1);
    }

    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestHugLife.class);
    }
} 

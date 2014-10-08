package io.blang.smoothwp;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransitionTest {
    private static final double EPSILON = 1e-6;
    private static final double SQRT2INV = 1 / Math.sqrt(2);
    public static final double METER_TO_GEO = 107817.51838439942;

    @Test
    public void testNextPosInc() {
        Transition t1 = new Transition(0, 0, 1, 1);
        assertArrayEquals(new double[]{1 * SQRT2INV, 1 * SQRT2INV}, t1.next(1), EPSILON);
    }

    @Test
    public void testNextPosDec() {
        Transition t = new Transition(2, 0, 0, 2);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{2 - SQRT2INV, 1 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{2 - 2 * SQRT2INV, 2 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{0, 2}, t.next(1), EPSILON);
        assertFalse(t.hasNext());
    }

    @Test
    public void testNextNegInc() {
        Transition t = new Transition(0, 0, 2, -2);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{1 * SQRT2INV, -1 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{2 * SQRT2INV, -2 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{2, -2}, t.next(1), EPSILON);
        assertFalse(t.hasNext());
    }

    @Test
    public void testNextNegDec() {
        Transition t = new Transition(2, 0, 0, -2);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{2 - 1 * SQRT2INV, -1 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{2 - 2 * SQRT2INV, -2 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{0, -2}, t.next(1), EPSILON);
        assertFalse(t.hasNext());
    }

    @Test
    public void testNext() {
        Transition t = new Transition(0, 0, 1, 1);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{1 * SQRT2INV, 1 * SQRT2INV}, t.next(1), EPSILON);
        assertTrue(t.hasNext());
        assertArrayEquals(new double[]{1, 1}, t.next(1), EPSILON);
        assertFalse(t.hasNext());
    }

    @Test
    public void testNextOne() {
        Transition t1 = new Transition(0, 0, 0.3, 0.3);
        assertTrue(t1.hasNext());
        assertArrayEquals(new double[]{0.3, 0.3}, t1.next(1), EPSILON);
        assertFalse(t1.hasNext());
    }

    @Test
    public void testNextZeroDistance() {
        Transition t1 = new Transition(0, 0, 0, 0);
        assertFalse(t1.hasNext());
    }

    /**
     * Generate waypoints between to points in gpx track format, e.g. testable using josm.
     */
//    @Test
//    public void testWPGenerator() {
//        Transition t = new Transition(48.5598421, 13.4392791, 48.5604988, 13.4408795);
//        while(t.hasNext()) {
//            double[] wp = t.next(1 / METER_TO_GEO);
//            System.out.println("<trkpt lat=\""+wp[0]+"\" lon=\""+wp[1]+"\"/>");
//        }
//    }
}

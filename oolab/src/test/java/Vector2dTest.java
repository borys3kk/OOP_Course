package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d vector1 = new Vector2d(20, -21);
    Vector2d vector2 = new Vector2d(19, 1);
    Vector2d vector3 = new Vector2d(21, 37);
    Vector2d vector4 = new Vector2d(20,-21);
    Vector2d vector5 = new Vector2d(19, 1);
    Vector2d vector6 = new Vector2d(21, 37);

    @Test
    public void testEquals()
    {
        assertEquals(vector1, vector4);
        assertEquals(vector2, vector5);
        assertEquals(vector3, vector6);

        assertNotEquals(vector1, vector2);
        assertNotEquals(vector3, vector4);
        assertNotEquals(vector5, vector6);
    }

    @Test
    public void testToString()
    {
        assertEquals("(20, -21)", vector1.toString());
        assertEquals("(19, 1)", vector2.toString());
        assertEquals("(21, 37)", vector3.toString());

        assertNotEquals("(0, 0)", vector1.toString());
        assertNotEquals("(0, 3)", vector2.toString());
        assertNotEquals("(21, 37", vector3.toString());
    }

    @Test
    public void testPrecedes()
    {
        assertTrue(vector2.precedes(vector3));
        assertTrue(vector4.precedes(vector1));
        assertTrue(vector2.precedes(vector5));
        assertFalse(vector1.precedes(vector2));
        assertFalse(vector2.precedes(vector4));
        assertFalse(vector1.precedes(vector5));
    }

    @Test
    public void testFollows()
    {
        assertTrue(vector3.follows(vector2));
        assertTrue(vector1.follows(vector1));
        assertTrue(vector6.follows(vector5));
        assertFalse(vector5.follows(vector6));
        assertFalse(vector4.follows(vector3));
        assertFalse(vector2.follows(vector3));
    }

    @Test
    public void testUpperRight()
    {
        assertEquals(new Vector2d(21, 37), vector1.upperRight(vector3));
        assertEquals(new Vector2d(20, 1), vector4.upperRight(vector2));
        assertEquals(new Vector2d(21, 37), vector3.upperRight(vector4));
        assertNotEquals(new Vector2d(100, 100), vector3.upperRight(vector4));
        assertNotEquals(new Vector2d(-420, 69), vector1.upperRight(vector2));
        assertNotEquals(new Vector2d(-10, 22), vector3.upperRight(vector4));
    }

    @Test
    public void testLowerLeft()
    {
        assertEquals(new Vector2d(19, -21), vector1.lowerLeft(vector5));
        assertEquals(new Vector2d(19, 1), vector2.lowerLeft(vector6));
        assertEquals(new Vector2d(20, -21), vector1.lowerLeft(vector3));
        assertNotEquals(new Vector2d(-19, -21), vector1.lowerLeft(vector5));
        assertNotEquals(new Vector2d(19, 122), vector2.lowerLeft(vector6));
        assertNotEquals(new Vector2d(220, -21), vector1.lowerLeft(vector3));

    }

    @Test
    public void testAdd()
    {
        assertEquals(new Vector2d(39, -20), vector1.add(vector2));
        assertEquals(new Vector2d(41, 16), vector4.add(vector6));
        assertEquals(new Vector2d(40, 38), vector3.add(vector2));
        assertNotEquals(new Vector2d(192, -21), vector1.lowerLeft(vector5));
        assertNotEquals(new Vector2d(19, 12), vector2.lowerLeft(vector6));
        assertNotEquals(new Vector2d(202, -21), vector1.lowerLeft(vector3));
    }

    @Test
    public void testSubtract()
    {
        assertEquals(new Vector2d(1, -22),vector1.subtract(vector2));
        assertEquals(new Vector2d(-1, -58), vector4.subtract(vector3));
        assertEquals(new Vector2d(2, 36), vector6.subtract(vector2));
        assertNotEquals(new Vector2d(1, -222),vector1.subtract(vector2));
        assertNotEquals(new Vector2d(-12, -58), vector4.subtract(vector3));
        assertNotEquals(new Vector2d(22, 36), vector6.subtract(vector2));
    }

    @Test
    public void testOpposite()
    {
        assertEquals(new Vector2d(-21, -37), vector6.opposite());
        assertEquals(new Vector2d(-20, 21), vector1.opposite());
        assertEquals(new Vector2d(-19,-1), vector5.opposite());
        assertNotEquals(new Vector2d(21, -37), vector6.opposite());
        assertNotEquals(new Vector2d(-20, -21), vector1.opposite());
        assertNotEquals(new Vector2d(19,-1), vector5.opposite());
    }
}

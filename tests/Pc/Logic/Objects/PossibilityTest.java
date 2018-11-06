package Pc.Logic.Objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class PossibilityTest {

    @Test
    public void getElement() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getElement(0));
        assertEquals(0, possibility.getElement(1));
        assertEquals(0, possibility.getElement(2));
        assertEquals(0, possibility.getElement(3));
    }

    @Test
    public void setElement() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getElement(0));
        possibility.setElement(0,5);
        assertEquals(5, possibility.getElement(0));
    }

    @Test
    public void getX() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getX(), 0.0);
    }

    @Test
    public void setX() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getX(), 0.0);
        possibility.setX(5);
        assertEquals(5, possibility.getX(), 0.0);
    }

    @Test
    public void getY() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getY(), 0.0);
    }

    @Test
    public void setY() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getY(), 0.0);
        possibility.setY(5);
        assertEquals(5, possibility.getY(), 0.0);
    }

    @Test
    public void getZ() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getZ(), 0.0);
    }

    @Test
    public void setZ() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getZ(), 0.0);
        possibility.setZ(5);
        assertEquals(5, possibility.getZ(), 0.0);
    }

    @Test
    public void isSingularite() {
        Possibility possibility = new Possibility();
        assertFalse(possibility.isSingularite());
    }

    @Test
    public void setSingularite() {
        Possibility possibility = new Possibility();
        assertFalse(possibility.isSingularite());
        possibility.setSingularite(true);
        assertTrue(possibility.isSingularite());
    }

    @Test
    public void getAngles() {
        Possibility possibility = new Possibility();
        assertEquals(0, possibility.getAngles()[0]);
        assertEquals(0, possibility.getAngles()[1]);
        assertEquals(0, possibility.getAngles()[2]);
        assertEquals(0, possibility.getAngles()[3]);
    }
}
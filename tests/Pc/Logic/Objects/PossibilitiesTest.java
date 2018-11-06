package Pc.Logic.Objects;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PossibilitiesTest {

    @Test
    public void getAllPossibilities() {
        ArrayList<Possibility> possibilities = new ArrayList<>();
        Possibility possibility = new Possibility();
        Possibilities possibilities1 = new Possibilities();
        assertEquals(possibilities, possibilities1.getAllPossibilities());
        possibilities.add(possibility);
        possibilities1.addPossibility(possibility);
        assertEquals(possibilities, possibilities1.getAllPossibilities());
    }

    @Test
    public void addPossibility() {
        Possibilities possibilities = new Possibilities();
        Possibility possibility = new Possibility();
        possibilities.addPossibility(possibility);
        assertTrue(possibilities.getAllPossibilities().size() == 1);
        assertTrue(possibilities.getAllPossibilities().get(0).equals(possibility));
    }
}
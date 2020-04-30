package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GrilleTest {
    @Test
    public void testContains() {
        Grille g = new Grille();
        // liste avec laquelle on va extraire les coords de nos points
        int[] coords = new int[]{1,2, 3, 4,80,2000,200,14};
        // On verifie les points de liste.
        assertEquals(true, g.contains(1, 2, coords));
        assertEquals(false, g.contains(20000, 3000, coords));
        assertEquals(true, g.contains(3, 4, coords));
        assertEquals(false, g.contains(200,13,coords));

    }

}

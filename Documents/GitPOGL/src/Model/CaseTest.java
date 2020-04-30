package Model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaseTest {
    @Test
    public void testLevel(){
        Grille g = new Grille();
        Case c = new Case(false,null,0,0,g);
        //On monte le niveau d'eau
        c.upLevel();
        assertEquals(EtatZone.INONDEE,c.getLevel());

        //On descend le niveau d'eau (on revient à l'état initiale)
        c.downLevel();
        assertEquals(EtatZone.NORMALE, c.getLevel());

        // On augmente le niveau d'eau de 2 niveau
        c.upLevel();
        c.upLevel();
        assertEquals(EtatZone.SUBMERGEE, c.getLevel());
    }

}

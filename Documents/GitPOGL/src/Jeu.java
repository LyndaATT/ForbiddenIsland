import Model.*;
import Vue.*;

public class Jeu {
    public static void main(String[] args) {
        Grille grille = new Grille();
        Vue vue = new Vue(grille);
        // grille.toString();
    }
}

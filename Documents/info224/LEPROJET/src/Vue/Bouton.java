package Vue;

import Model.Grille;

import javax.swing.*;

public class Bouton extends JPanel{
    public Bouton(Grille grille){
        JButton bouton = new JButton("Fin de tour"); // Ce qui s'affichera sur le bouton
        this.add(bouton);
        //*******************
        // Penser à enlever le "grille.toString()" plus tard lorsque l'affichage sera fait !!!
        //*******************
        bouton.addActionListener(e -> { grille.tourCaseI(); grille.toString();});
        // bouton.addActionListener(e -> { modele.nextGen(); });
    }
}
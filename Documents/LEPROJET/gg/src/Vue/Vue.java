package Vue;

import Model.Case;
import Model.EtatZone;
import Model.Grille;
import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private DrawGrille grille;
    public Vue(Grille maGrille) {
        frame = new JFrame(); // Sans vraiment lui fournir de params, mais on fera pleins de trucs dessus par la suite
        frame.setTitle("Ile Interdite");
        frame.setLayout(new FlowLayout());
        grille = new DrawGrille(maGrille);
        frame.add(grille);
        //commandes = new VueCommandes(modele);
        // Pour placer les elts : "Layout"
        frame.add(new Bouton(maGrille));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Class qui nous permet d'afficher la grille
     **/
    class DrawGrille extends JPanel {
        private final static int TAILLE = 12;
        private Grille grille;

        public DrawGrille(Grille grille) {
            this.grille = grille;
            Dimension dim = new Dimension(TAILLE * Grille.LARGEUR,
                    TAILLE * Grille.HAUTEUR);
            this.setPreferredSize(dim);
        }

        public void update() {
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.repaint();
            /** Pour chaque cellule... */
            for (int i = 1; i <= Grille.LARGEUR; i++) {
                for (int j = 1; j <= Grille.HAUTEUR; j++) {
                    paint(g, grille.getCase(i, j), (i - 1) * TAILLE, (j - 1) * TAILLE);
                }
            }
        }

        private void paint(Graphics g, Case c, int x, int y) {
               if(c.getLevel()== EtatZone.NORMALE){ g.setColor(Color.darkGray);}
               if(c.getLevel()== EtatZone.INONDEE){ g.setColor(Color.BLUE);}
               if(c.getLevel()== EtatZone.SUBMERGEE){ g.setColor(Color.RED);}
               g.fillRect(x,y,TAILLE,TAILLE);
        }
       }
}
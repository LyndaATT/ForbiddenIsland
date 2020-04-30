package Vue;

import Controller.ZoneController;
import Model.Case;
import Model.EtatZone;
import Model.Grille;
import Model.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class ZoneView extends JPanel implements Observer {

    private Grille zones;
    private boolean isSelected;
    private MouseListener mouseListener;
    private GridLayout grille=new GridLayout(6,6,10,10);
    public ZoneView(){
        super();
        this.setLayout(grille);
        Dimension taille=new Dimension(660,660);
        setMinimumSize(taille);
        setPreferredSize(taille);
        setMaximumSize(taille);
        this.setVisible(true);
    }


    public void loadZones(Grille grille, Graphics g){
        setVisible(false);
        Case zone;
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                paint(g, grille.getCase(i, j), (i - 1) * 12, (j - 1) * 12);
                zone=grille.getCase(i,j);
                if(zone!=null){
                    zone.addObserver(this);
                    new ZoneController(zone);
                }else{
                    this.add(new JPanel());
                }
            }
        }
        setVisible(true);
    }

    private void paint(Graphics g, Case c, int x, int y) {
        if(c.getLevel()== EtatZone.NORMALE){ g.setColor(Color.darkGray);}
        if(c.getLevel()== EtatZone.INONDEE){ g.setColor(Color.BLUE);}
        if(c.getLevel()== EtatZone.SUBMERGEE){ g.setColor(Color.RED);}
        g.fillRect(x,y,12,12);}
  /*  public void mettreaJjourZones(Grille grille){
        setVisible(false);
        Case zone;
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                zone=grille.getCase(i,j);
                }
            }
        }
        setVisible(true);
    }*/


    public void selectionZone(int x,int y){

//        this.listeZones[x][y].setTypeSelection(1);
        // Selection pour deplacement
        this.repaint();
    }




    public void positionnerJoueur(Joueur j, int x, int y){

    }





    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Mise a jour des images ");
        // loadZones(this.partie.getGrille());
        //repaint();
    }
}

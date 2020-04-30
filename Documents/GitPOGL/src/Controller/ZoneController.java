package Controller;

import Model.Case;
import Vue.ZoneView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ZoneController  implements ActionListener, MouseListener {

    Case zone;
    public ZoneController(Case zone){
        this.zone=zone;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        //    partie.getJoueurActuel().setPosition((Zone) e.getSource());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource() instanceof ZoneView){
        //    switch (zone.getTypeSelection()){
               for (Case adjacente:
                     this.zone.getGrille().getZoneAdjacentes(zone)) {
                   adjacente.setTypeSelection(0);

               }
               /* case 1:
                    this.zone.getGrille().deselectionnerZone();
                    System.out.println("Pour deplacement");
                    this.zone.getGrille().deselectionnerZone();
                    break;
                case 2:
                    System.out.println("Pour Assechement");
                    break;*/

            }

//            switch (z.getTypeSelection()){
//                case 1:
//                    System.out.println("Clique pour deplacement");
//                    break;
//                default:
//                        System.out.println("Clique pour Autre chose");
//                     break;
//
//            }
        }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}



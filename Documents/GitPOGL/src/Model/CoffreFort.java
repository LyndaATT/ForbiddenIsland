package Model;

import java.util.ArrayList;
import java.util.Observable;

public class CoffreFort extends Observable {
    private java.util.ArrayList<Cle> cles;


    public CoffreFort() {
        cles = new ArrayList<>();
    }

    public void ajouterCle(Cle cle) {
        this.cles.add(cle);
        notifyObservers();
    }

    public void retirerCle(ArtefactElement elementArtefact) {
        for(Cle c : cles){
            if(c.getElementCle().equals(elementArtefact)){
                cles.remove(c);
            }
        }
        notifyObservers();
    }

    public ArrayList<Cle> getCles() {
        return cles;
    }
}
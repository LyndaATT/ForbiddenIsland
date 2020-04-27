package Model;
import java.util.ArrayList;
import java.util.Observable;

import static Model.EtatZone.*;

public class Case extends Observable {
    private boolean heliport; // vrai si la case est un heliport faux sinon
    private Artefact artefact;
    private EtatZone level = NORMALE;
    private int posx, posy;
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private int typeSelection= 0;

    /*
     * Constructeur.
     * @param isHeliport : défini si la case est un héliport.
     * @param artefact : défini l'artefact qui est dans la case.
     * @param posx et posy : la position de la case dans la grille.
     */
    public Case(boolean isHeliport, Artefact artefact, int posx, int posy){
        this.heliport = isHeliport;
        this.artefact = artefact;
        this.posx = posx;
        this.posy = posy;
        return;
    }

    /**
     * Méthode qui permet d'assecher une case inondee
     */
    public void downLevel(){
        if(this.level == SUBMERGEE){
            System.out.println("La case ne peut pas être assechée");
        }
        else if(this.level == INONDEE) {
            this.level = NORMALE;
        }
        notifyObservers();
    }

    /**
     * Méthode qui permet de monter le niveau d'eau d'une case,
     * Ne fait rien si cette dernière est déjà submergée
     */
    public void upLevel(){
        switch (this.level) {
            case NORMALE : this.level = INONDEE; break;
            default: this.level = SUBMERGEE;
                      this.joueurs = new ArrayList<>();
                      break;
        }
        notifyObservers();
    }

    /*
     * On retire l'artefact de la case.
     */
    public void removeArtefact(){
        this.artefact = null;
    }

    // Accesseurs.
    public int getPosx(){
        return posx;
    }

    public int getPosy(){
        return posy;
    }

    public Artefact getArtefact(){
        return artefact;
    }

    public EtatZone getLevel(){
        return level;
    }

    public boolean isHeliport() {
        return heliport;
    }
    
    
    public String toString() {
    	String s = new String(); 
    	if(this.heliport) return "h ";
    	else {
    		switch(this.level){
                case SUBMERGEE: s+="s";break;
                case INONDEE: s+="i";break;
                case NORMALE: s+="n";break;
    		}	
    		switch(this.artefact.getElement()){
			case AIR: s+="Air"; break;
			case FEU: s+="Feu"; break;
			case TERRE: s+="Terre"; break;
			case EAU: s+="EAU"; break;
			default: System.out.println(this.artefact); return "-1";
    		}
    		return s;
    		
    	}
    }

    public void setSituationZone(EtatZone etat){
        this.level = etat;
        notifyObservers();
    }

    public void removeJoueur(Joueur j){
        this.joueurs.remove(j);
        notifyObservers();
    }
    public void ajouterJoueur(Joueur j){
        this.joueurs.add(j);
        notifyObservers();

    }
    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }


}


package Model;
import java.util.ArrayList;
import static Model.EtatZone.*;

public class Case {
    private boolean heliport; // vrai si la case est un heliport faux sinon
    private int artefact; //0 rien, 1, 2, 3, 4 unique artefact.
    // private int level = 2; // 2 normal, 1 inondé, 0 sub.
    private EtatZone level = NORMALE;
    private int posx, posy;
    /*
     * Constructeur.
     * @param isHeliport : défini si la case est un héliport.
     * @param artefact : défini l'artefact qui est dans la case.
     * @param posx et posy : la position de la case dans la grille.
     */
    public Case(boolean isHeliport, int artefact, int posx, int posy){
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
        if(this.level == INONDEE) {
            this.level = NORMALE;
        }
    }

    /**
     * Méthode qui permet de monter le niveau d'eau d'une case,
     * Ne fait rien si cette dernière est déjà submergée
     */
    public void upLevel(){
        switch (this.level) {
            case NORMALE : this.level = INONDEE; break;
            default: this.level = SUBMERGEE; break;
        }
    }

    /*
     * On retire l'artefact de la case.
     */
    public void removeArtefact(){
        artefact = 0;
        return;
    }

    // Accesseurs.
    public int getPosx(){
        return posx;
    }

    public int getPosy(){
        return posy;
    }

    public int getArtefact(){
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
    		switch(this.artefact){
			case 0: s+="0"; break;
			case 1: s+="1"; break;
			case 2: s+="2"; break;
			case 3: s+="3"; break;
			case 4: s+="4"; break;
			default: System.out.println(this.artefact); return "-1";
    		}
    		return s;
    		
    	}
    }

}


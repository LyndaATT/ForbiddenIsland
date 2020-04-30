package Model;

import java.lang.management.PlatformLoggingMXBean;
import java.util.Observable;
import java.util.Observer;

public class Joueur extends Observable {
    private String pseudo;
    private Case caseJoueur;
    private Case oldCase = null;
    private Role role;
    private boolean tour ;
    private CoffreFort coffreFort;

    /*
     * Constructeur.
     * @param nom : le nom du joueur.
     * @param caseJoueur : La case ou doit se trouver le joueur
     */
    public Joueur(String pseudo, Role role, Case caseJoueur, boolean tour){
        this.pseudo = pseudo;
        this.caseJoueur = caseJoueur;
        this.role = role;
        this.tour = tour;
        this.coffreFort = new CoffreFort();
    }
    public Joueur(String pseudo, Role role){
        this.pseudo = pseudo;
        this.role = role;
    }


    public int getPosx(){
        return this.caseJoueur.getPosx();
    }

    public int getPosy(){
        return this.caseJoueur.getPosy();
    }

    public Case getCase(){
        return this.caseJoueur;
    }

    public String getPseudo(){
        return this.pseudo;
    }

    public void setRole(Role role){
        if (role!=Role.NAVIGATEUR || role!= Role.EXPLORER || role!=Role.INGENIEUR || role!=Role.MESSAGER || role!=Role.PILOTE || role!=Role.PLONGEUR){ return;}
 		this.role = role;
	}
    public Role getRole() { return this.role; }

    public void InnondeCase(Case c){   c.upLevel(); }
    public void AssecherCase(Case c){ c.downLevel();}

    public boolean isTour() {
        return tour;
    }

    public void setTour(boolean tour){
        this.tour=tour;
        notifyObservers(); // à ne pas oublier!!
    }

    public void setCase(Case newCase){ this.caseJoueur = newCase; }

   /* public void setCase(Case position){
        if(this.caseJoueur!=null){
            System.out.println("Retirer le joueur de la case precedente");
            this.caseJoueur.removeJoueur(this);
        }
        this.caseJoueur=position;
        System.out.println("Affichage dans la case ");
        this.caseJoueur.ajouterJoueur(this);
    }*/
   public Case getAnciennePosition() {
       return this.oldCase;
   }

  public CoffreFort getCoffreFort(){
       return this.coffreFort;
  }

  public void ajouterCle(Cle nouvelleCle) {
      this.coffreFort.ajouterCle(nouvelleCle);
  }
  public void retirerCle(ArtefactElement elementArtefact) {
        this.coffreFort.retirerCle(elementArtefact);
        notifyObservers();
    }

    // renvoit le nombre de clé qu'un joueur a qui correspond à un element donné
    public int avoirToutesCles(ArtefactElement element) {
        int cpt=0;
        for(Cle cle:coffreFort.getCles()   ){
            if(cle.getElementCle().equals(element))
            { cpt++;}
        }
        return cpt;
    }





}

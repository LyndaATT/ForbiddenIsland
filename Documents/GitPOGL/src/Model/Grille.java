package Model;
import java.awt.*;
import java.util.*;
import java.util.List;

import static Model.ArtefactElement.*;
import static Model.ArtefactElement.TERRE;
import static Model.EtatZone.*;

public class Grille extends Observable {
    public static final int HAUTEUR = 6, LARGEUR = 6;
    private Case[][] tabCase;
    private Random random = new Random();

    /*
     * Constructeur.
     * @param x et y le nombre de ligne et de colonnes.
     */
    public Grille() {
        tabCase = new Case[LARGEUR + 2][HAUTEUR + 2];
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++)
                tabCase[i][j] = new Case(false, new Artefact(ArtefactNum(random.nextInt(4))), i, j, this);
        }
        return;
    }

    /**
     * fait correspondre à chaque nombre un artefact
     */
    public ArtefactElement ArtefactNum(int rd) {
        switch (rd) {
            case 0:
                return AIR;
            case 1:
                return EAU;
            case 2:
                return FEU;
            case 3:
                return TERRE;
            default:
                return null;
        }
    }

    /**
     * fait correspondre à chaque artefact un nombre
     */
    public Integer NumAtefact(Artefact a) {
        switch (a.getElement()) {
            case AIR:
                return 0;
            case EAU:
                return 1;
            case FEU:
                return 2;
            case TERRE:
                return 3;
            default:
                return -1;
        }
    }


    /*
     * Méthode recherchant le point x, y dans pos.
     * @param x, y : le point recherché.
     * @param pos : le tableau dans lequel il faut rechercher le point. ({x, y, x, y, ...}).
     * @return true si le point est trouvé, false sinon.
     */
    protected boolean contains(int x, int y, int[] pos) {
        for (int i = 1; i < pos.length; i += 2) {
            if (pos[i - 1] == x && pos[i] == y) return true;
        }
        return false;
    }

    // Accesseurs.
    public Case getCase(int x, int y) {
        if (x < 0 || x >= LARGEUR || y < 0 || y >= HAUTEUR) return new Case(false, null, -1, -1, this);
        return tabCase[x][y];
    }


    public void tourCaseI(List<Case> zoneNonSub) {
        Case c;
        int x, y;
        for (int i = 0; i < 3; i++) {
            c = zoneNonSub.get(random.nextInt(zoneNonSub.size()));
            c.upLevel();
            x = c.getPosx();
            y = c.getPosy();
            this.tabCase[x][y] = c;
        }
    }


    @Override
    public String toString() {
        String s = new String();
        Case c;
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                c = this.tabCase[i][j];
                s += c.toString() + "\t ";
            }
            s += "\n";
        }
        return s;
    }

    private List<Case> zoneNonSubmergees() {
        List<Case> result = new ArrayList<>();
        Case c;
        for (int i = 0; i < LARGEUR ; i++) {
            for (int j = 0; j < HAUTEUR; j++) {
                c = this.tabCase[i][j];
                if (c.getLevel() != SUBMERGEE) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    public void tourCaseI() {
        List<Case> zoneNonSub = zoneNonSubmergees();
        Case c;
        int x, y;
        for (int i = 0; i < 3; i++) {
            c = zoneNonSub.get(random.nextInt(zoneNonSub.size()));
            c.upLevel();
            x = c.getPosx();
            y = c.getPosy();
            this.tabCase[x][y] = c;
            // On enlève la zone de la liste si elle est submergée
            if (c.getLevel() == SUBMERGEE) {
                zoneNonSub.remove(c);
            }
        }
    }

    /**
     * Initialisation aléatoire des cellules, exceptées celle des bords qui
     * ont été ajoutés.
     */
    public void init() {
        for (int i = 1; i <= LARGEUR + 2; i++) {
            for (int j = 1; j <= HAUTEUR + 2; j++) {
                tabCase[i][j] = new Case(false, null, i, j, this);
            }
        }
    }

    public Dimension obtainCase(Case c) {
        for (int i = 0; i < LARGEUR + 2; i++) {
            for (int j = 0; j < HAUTEUR + 2; j++) {
                if (this.tabCase[i][j] == c) {
                    return new Dimension(i, j);
                }
            }
        }
         System.out.println("Zone introuvable...");
        return null;
    }
    public ArrayList<Case> getZoneAdjacentes(Case c) {
        ArrayList<Case> zonesAdjacentes = new ArrayList<>();
        if( c.getPosx()>0 && this.tabCase[c.getPosx()-1][c.getPosx()]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()-1][c.getPosx()]);
        if( c.getPosx()<LARGEUR+2 &&this.tabCase[c.getPosx()+1][c.getPosx()]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()+1][c.getPosx()]);
        if(c.getPosx()>0 &&this.tabCase[c.getPosx()][c.getPosx()-1]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()][c.getPosx()-1]);
        if( c.getPosx()<HAUTEUR+2 && this.tabCase[c.getPosx()][c.getPosx()+1]!=null) zonesAdjacentes.add(this.tabCase[c.getPosx()][c.getPosx()+1]);

        for (Case casee: zonesAdjacentes){
            if (casee.getLevel().equals(SUBMERGEE)){
                zonesAdjacentes.remove(casee);
            }
        }
        return zonesAdjacentes;
    }

    public  void deselectionnerZone(Case mc){
        for(Case c: this.getZoneAdjacentes(mc)){
            c.setTypeSelection(0);
        }

    }

    public static void main(String args[]) {
        Grille grille = new Grille();
        System.out.print(grille.toString());
        //System.out.println(grille.tabCase.length);
        Case macase = grille.getCase(3,2);
        Case centre = grille.getCase(0,0);
        ArrayList<Case> adj = grille.getZoneAdjacentes(macase);
        ArrayList<Case> adj2 = grille.getZoneAdjacentes(centre);

        int i =0;
      for( Case c : adj ){ i++;
          int posx = c.getPosx();
          int posy = c.getPosy();
          System.out.println("Case adj à (3,2) "+i+" a comme coord "+posx+", "+posy);
          /** devra renvoyer : (4,2) (3,1) (3,3) (2,2)**/
      }
        for( Case c : adj2 ){ i++;
            int posx = c.getPosx();
            int posy = c.getPosy();
            System.out.println("Case adj (0,0) "+i+" a comme coord "+posx+", "+posy);
            /** devra renvoyer (1,0) (0,1)**/
        }
    }
}

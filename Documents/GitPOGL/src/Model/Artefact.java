package Model;

import java.util.HashMap;

import static Model.ArtefactElement.*;

public class Artefact {
    private static int idArtefact=1; // va augmenter à chaque fois qu'on recolte unartefact associé au meme element
    private ArtefactElement element;
    private HashMap<ArtefactElement,Artefact> artefacts = new HashMap<>();

    /** Costructeur **/
    public Artefact(ArtefactElement elementA){
        this.element = elementA;
    }

    /** methode qui renvoit l'artefact associé à un element passé en param*/
    public Artefact getArtefactElement(ArtefactElement elementA) {
        Artefact artefact= artefacts.get(elementA);
        if(artefact==null){
            artefact=new Artefact(elementA);
            artefacts.put(elementA,artefact);
        }
        return artefact;
    }
    public ArtefactElement getElement(){
        return  this.element;
    }


}

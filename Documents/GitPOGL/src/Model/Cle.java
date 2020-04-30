package Model;

public class Cle {
    private ArtefactElement elementCle;
    public Cle(ArtefactElement elementArtefact){
        this.elementCle=elementArtefact;
    }

    public ArtefactElement getElementCle() {
        return elementCle;
    }

    static Cle CLEAIR=new Cle(ArtefactElement.AIR);
    static Cle CLEFEU=new Cle(ArtefactElement.FEU);
    static  Cle CLEEAU =new Cle(ArtefactElement.EAU);
    static Cle CLETERRE=new Cle(ArtefactElement.TERRE);
}

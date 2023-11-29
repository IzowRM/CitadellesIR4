package modele;

public class Eveque extends Personnage{
    public Eveque() {
        super("Eveque", 5, Caracteristiques.EVEQUE);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        int n = 0;
        for (int i =0 ;i<this.getJoueur().nbQuartiersDansCite();i++){
            if(this.getJoueur().getCite()[i].getType().equals("RELIGIEUX")){
               n++;
            }
        }

        this.getJoueur().ajouterPieces(n);
    }

    @Override
    public void utiliserPouvoir() {

    }

}

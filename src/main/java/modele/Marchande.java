package modele;

public class Marchande extends Personnage{
    public Marchande() {
        super("Marchande", 6, Caracteristiques.MARCHANDE);
    }
    @Override
    public void utiliserPouvoir() {
    this.getJoueur().ajouterPieces(1);
    }
    @Override
    public void percevoirRessourcesSpecifiques() {

        int n = 0;
        for (int i =0 ;i<this.getJoueur().nbQuartiersDansCite();i++){
            if(this.getJoueur().getCite()[i].getType().equals("COMMERCANT")){
                n++;
            }
        }

        this.getJoueur().ajouterPieces(n);
    }
}

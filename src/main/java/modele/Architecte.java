package modele;

public class Architecte extends Personnage{
    public Architecte() {
        super("Architecte", 6, Caracteristiques.ARCHITECTE);
    }
    @Override
    public void utiliserPouvoir() {
        this.getJoueur().getMain().add( this.getPlateau().getPioche().piocher());
        this.getJoueur().getMain().add( this.getPlateau().getPioche().piocher());
    }
}

package modele;

public class PlateauDeJeu {
    private Personnage[] listePersonnages;
    private Joueur[] listeJoueurs;
    private Pioche pioche;
    private Integer nombrePersonnages;
    private Integer nombreJoueurs;

    public PlateauDeJeu() {
        this.listePersonnages = new Personnage[9];
        this.listeJoueurs = new Joueur[9];
        this.pioche = new Pioche();
        this.nombrePersonnages = 0;
        this.nombreJoueurs = 0;
    }

    public Integer getNombrePersonnages() {
        return nombrePersonnages;
    }

    public Integer getNombreJoueurs() {
        return nombreJoueurs;
    }

    public Pioche getPioche() {
        return pioche;
    }

    public Personnage getPersonnage(Integer i) {
        if (i < 0 || i >= listePersonnages.length) {
            return null;
        }
        return listePersonnages[i];
    }

    public Joueur getJoueur(Integer i) {
        if (i < 0 || i >= listeJoueurs.length) {
            return null;
        }
        return listeJoueurs[i];
    }
    public void ajouterPersonnage(Personnage personnage) {
        if(personnage!=null && nombrePersonnages<9){
            listePersonnages[nombrePersonnages] = personnage;
            personnage.setPlateau(this);
            nombrePersonnages++;
        }

    }
    public void ajouterJoueur(Joueur joueur) {
        if(joueur!=null && nombreJoueurs<9){
            listeJoueurs[nombreJoueurs] = joueur;
            nombreJoueurs++;
        }
    }
}
